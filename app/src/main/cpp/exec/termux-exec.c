#include <dlfcn.h>
#include <elf.h>
#include <errno.h>
#include <fcntl.h>
#include <libgen.h>
#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#ifndef TERMUX_BASE_DIR
#define TERMUX_BASE_DIR "/data/data/work.pangbai.r2toolm/files"
#endif

#ifndef TERMUX_PREFIX
#define TERMUX_PREFIX "/data/data/work.pangbai.r2toolm/files/usr"
#endif

#ifdef __aarch64__
#define EM_NATIVE EM_AARCH64
#elif defined(__arm__) || defined(__thumb__)
#define EM_NATIVE EM_ARM
#elif defined(__x86_64__)
#define EM_NATIVE EM_X86_64
#elif defined(__i386__)
#define EM_NATIVE EM_386
#else
#error "unknown arch"
#endif

#define starts_with(value, str) !strncmp(value, str, sizeof(str) - 1)

#include <stdlib.h>
#include <stdio.h>

int readenv_atoi(char *env) {
    char *p;
    //getenv函数是gcc自带的函数，在stdlib.h头文件中声明
    if (getenv(env) != NULL)
        puts(getenv(env));
    if ((p = getenv(env)))
        return (atoi(p));
    else
        return (0);
}


void printf_yellow(const char *s) { printf("\033[0m\033[1;33m%s\033[0m", s); }

void printf_green(const char *s) { printf("\033[0m\033[1;32m%s\033[0m", s); }

void printf_red(const char *s) { printf("\033[0m\033[1;31m%s\033[0m", s); }

static const char *termux_rewrite_executable(const char *filename, char *buffer,
                                             int buffer_len) {
    if (starts_with(filename, TERMUX_BASE_DIR) ||
        starts_with(filename, "/system/"))
        return filename;

    strcpy(buffer, TERMUX_PREFIX "/bin/");
    char *bin_match = strstr(filename, "/bin/");
    if (bin_match == filename || bin_match == (filename + 4)) {
        // We have either found "/bin/" at the start of the string or at
        // "/xxx/bin/". Take the path after that.
        strncpy(buffer + sizeof(TERMUX_PREFIX "/bin/") - 1, bin_match + 5,
                buffer_len - sizeof(TERMUX_PREFIX "/bin/"));
        filename = buffer;
    }
    return filename;
}

static char *const *remove_ld_preload(char *const *envp) {
    for (int i = 0; envp[i] != NULL; i++) {
        if (strstr(envp[i], "LD_PRELOAD=") == envp[i]) {
            int env_length = 0;
            while (envp[env_length] != NULL) env_length++;

            char **new_envp = malloc(sizeof(char *) * env_length);
            int new_envp_idx = 0;
            int old_envp_idx = 0;
            while (old_envp_idx < env_length) {
                if (old_envp_idx != i) {
                    new_envp[new_envp_idx++] = envp[old_envp_idx];
                }
                old_envp_idx++;
            }
            new_envp[env_length] = NULL;
            return new_envp;
        }
    }
    return envp;
}

int execve(const char *filename, char *const *argv, char *const *envp) {
    /*

    puts("hello");
    */
    bool android_10_debug = getenv("TERMUX_ANDROID10_DEBUG") != NULL;
    if (android_10_debug) {
        printf("execve(%s):\n", filename);
        int tmp_argv_count = 0;
        while (argv[tmp_argv_count] != NULL) {
            printf("  %s\n", argv[tmp_argv_count]);
            tmp_argv_count++;
        }
    }

    int fd = -1;
    const char **new_argv = NULL;
    const char **new_envp = NULL;

    char *resolved_path = NULL;
    size_t envp_count = 0;
    char filename_buffer[512];
    filename = termux_rewrite_executable(filename, filename_buffer,
                                         sizeof(filename_buffer));

    // Error out if the file is not executable:
    if (access(filename, X_OK) != 0) goto final;

    fd = open(filename, O_RDONLY);
    if (fd == -1) goto final;


    while (envp[envp_count] != NULL) envp_count++;
    // LD_LIBRARY_PATH messes up system programs with CANNOT_LINK_EXECUTABLE
    // errors. If we remove.it, this problem is solved. /system/bin/sh is fine, it
    // only uses libc++, libc, and libdl.
    if (starts_with(filename, "/system/") &&
        strcmp(filename, "/system/bin/sh") != 0) {


        new_envp = malloc((envp_count + 1) * sizeof(char *));

        size_t pos = 0;
        for (size_t i = 0; i < envp_count; i++) {
            // Skip it if it is LD_LIBRARY_PATH or LD_PRELOAD
            if (!starts_with(envp[i], "LD_LIBRARY_PATH=") &&
                !starts_with(envp[i], "LD_PRELOAD="))
                new_envp[pos++] = (const char *) envp[i];
        }
        new_envp[pos] = NULL;

        envp = (char **) new_envp;
        // Not.sure if needed.
        environ = (char **) new_envp;
    }

    // execve(2): "A maximum line length of 127 characters is allowed
    // for the first line in a #! executable shell script."
    char header[128];
    ssize_t read_bytes = read(fd, header, sizeof(header) - 1);

    // If we are executing a non-native ELF file, unset LD_PRELOAD.
    // This avoids CANNOT LINK EXECUTABLE errors when running 32-bit code
    // on 64-bit.
    if (read_bytes >= 20 && !memcmp(header, ELFMAG, SELFMAG)) {
        Elf32_Ehdr *ehdr = (Elf32_Ehdr *) header;
        if (ehdr->e_machine != EM_NATIVE) {
            envp = remove_ld_preload(envp);
        }

        for (size_t i = 0; i < envp_count; i++) {
            if (starts_with(envp[i], "ANDROID10=") && envp[i][10] == '0') goto final;
        }


        resolved_path = malloc(sizeof(char) * 512);
        int arg_num = 0;
        while (argv[arg_num] != NULL) arg_num++;

        char *linker = "/system/bin/linker64";
        char path[256];
        int flag = 0;
        /*for debug
        puts(filename);
        puts("filename");
        int j=0;
        while (argv[j] != NULL) fprintf(stderr,"\nargv %s ",argv[j++]);
        puts("test");
        */
        if (access(argv[0], F_OK) != -1) {
            if (realpath(argv[0], resolved_path) != NULL &&
                strstr(resolved_path, "busybox") == NULL) {
                flag = 1;
                //  fprintf(stderr, "\ncase3\n");
            } else {
                // fprintf(stderr, "\ncase4\n");
            }
        } else {
            strcpy(path, "/data/data/work.pangbai.r2toolm/files/usr/bin/");
            strcat(path, argv[0]);
            if (access(path, F_OK) != -1 && realpath(path, resolved_path) != NULL) {
                if (strstr(resolved_path, "busybox") != NULL) {
                    //    fprintf(stderr, "\ncase1\n");
                    flag = 0;

                } else {
                    //     fprintf(stderr, "\ncase2\n");
                    flag = 1;
                }
            }
        }

        if (strcmp(argv[0], "busybox") != 0 && flag) {
            new_argv = malloc(sizeof(char *) * (3 + arg_num));
            new_argv[0] = argv[0];
            new_argv[arg_num + 2] = NULL;
            while (arg_num >= 0) {
                new_argv[arg_num + 1] = argv[arg_num];
                arg_num--;
            }

            new_argv[1] = resolved_path;
        } else {
            // for shell of busybox

            new_argv = malloc(sizeof(char *) * (4 + arg_num));
            new_argv[0] = argv[0];
            new_argv[1] = TERMUX_PREFIX "/bin/busybox";
            new_argv[arg_num + 3] = NULL;

            while (arg_num >= 0) {
                new_argv[arg_num + 2] = argv[arg_num];
                arg_num--;
            }
        }
        /* test
         int i = 0;
         while (new_argv[i] != NULL)fprintf(stderr,"\nnew argv %s" ,new_argv[i++]);
     */
        argv = (char **) new_argv;

        filename = linker;

        goto final;
    }
    if (read_bytes < 5 || !(header[0] == '#' && header[1] == '!')) goto final;

    header[read_bytes] = 0;
    char *newline_location = strchr(header, '\n');
    if (newline_location == NULL) goto final;

    // Strip whitespace at end of shebang:
    while (*(newline_location - 1) == ' ') newline_location--;

    // Null-terminate the shebang line:
    *newline_location = 0;

    // Skip whitespace to find interpreter start:
    char *interpreter = header + 2;
    while (*interpreter == ' ') interpreter++;
    if (interpreter == newline_location) goto final;

    char *arg = NULL;
    char *whitespace_pos = strchr(interpreter, ' ');
    if (whitespace_pos != NULL) {
        // Null-terminate the interpreter string.
        *whitespace_pos = 0;

        // Find start of argument:
        arg = whitespace_pos + 1;
        while (*arg != 0 && *arg == ' ') arg++;
        if (arg == newline_location) {
            // Only whitespace after interpreter.
            arg = NULL;
        }
    }

    char interp_buf[512];
    const char *new_interpreter =
            termux_rewrite_executable(interpreter, interp_buf, sizeof(interp_buf));
    // remove next line for android10
    // if (new_interpreter == interpreter) goto final;

    int orig_argv_count = 0;
    while (argv[orig_argv_count] != NULL) orig_argv_count++;

    new_argv = malloc(sizeof(char *) * (4 + orig_argv_count));

    int current_argc = 0;
    new_argv[current_argc++] = basename(interpreter);
    if (arg) new_argv[current_argc++] = arg;
    new_argv[current_argc++] = filename;
    int i = 1;
    while (orig_argv_count-- > 1) new_argv[current_argc++] = argv[i++];
    new_argv[current_argc] = NULL;

    filename = new_interpreter;
    argv = (char **) new_argv;

    final:
    if (fd != -1) close(fd);
    int (*real_execve)(const char *, char *const[], char *const[]) =
    dlsym(RTLD_NEXT, "execve");

    bool android_10_wrapping = getenv("TERMUX_ANDROID10") != NULL;
    if (android_10_wrapping) {
        char realpath_buffer[PATH_MAX];
        bool realpath_ok = realpath(filename, realpath_buffer) != NULL;
        if (realpath_ok) {
            bool wrap_in_proot = (strstr(realpath_buffer, TERMUX_BASE_DIR) != NULL);
            if (android_10_debug) {
                printf("termux-exec: realpath(\"%s\") = \"%s\", wrapping=%s\n",
                       filename, realpath_buffer, wrap_in_proot ? "yes" : "no");
            }
            if (wrap_in_proot) {
                orig_argv_count = 0;
                while (argv[orig_argv_count] != NULL) orig_argv_count++;

                new_argv = malloc(sizeof(char *) * (2 + orig_argv_count));
                filename = TERMUX_PREFIX "/bin/proot";
                new_argv[0] = "proot";
                for (int i = 0; i < orig_argv_count; i++) {
                    new_argv[i + 1] = argv[i];
                }
                new_argv[orig_argv_count + 1] = NULL;
                argv = (char **) new_argv;
                // Remove LD_PRELOAD environment variable when wrapping in proot
                envp = remove_ld_preload(envp);
            }
        } else {
            errno = 0;
        }

        if (android_10_debug) {
            printf("real_execve(%s):\n", filename);
            int tmp_argv_count = 0;
            while (argv[tmp_argv_count] != NULL) {
                printf("  %s\n", argv[tmp_argv_count]);
                tmp_argv_count++;
            }
        }
    }

    int ret = real_execve(filename, argv, envp);

    free(new_argv);
    free(new_envp);
    free(resolved_path);
    return ret;
}



int copyFiles(const char *sourcePath, const char *destinationPath) {
    // 检查参数是否为NULL
    if (sourcePath == NULL || destinationPath == NULL) {
        fprintf(stderr, "Source or destination path is NULL.\n");
        return -1;
    }

    // 构建Shell命令
    char command[512];
    //   snprintf(command, sizeof(command), "cp -r %s %s", sourcePath, destinationPath);
    snprintf(command, sizeof(command), "ls %s ", sourcePath);
    printf_yellow(command);
    // 执行Shell命令
    int result = system(command);

    // 检查命令执行结果
    if (result == -1) {
        return -1;
    } else if (result != 0) {
        fprintf(stderr, "Command failed with exit code %d\n", result);
        return result;
    }
    return 1;
}

int symlink(const char *sourcePath, const char *destinationPath) {
    int (*real_symlink)(const char *, const char *const) =
    dlsym(RTLD_NEXT, "symlink");

    int ret = real_symlink(sourcePath, destinationPath);
    if (ret == -1) {
        // 如果创建符号链接失败，则尝试复制文件
        printf_yellow(
                "\nWarning: Can't link, attempting to copy the file instead\n");
        printf_yellow(sourcePath);
        printf_yellow("->");
        puts(destinationPath);
        ret = copyFiles(sourcePath, destinationPath);
        if (ret == -1) {
            printf_red("\ncopyfile failed\n");
        } else {
            printf_green("\nFile copied successfully\n");
        }
    } else {
        printf_green("\nSymbolic link created successfully\n");
    }
    return ret;
}
