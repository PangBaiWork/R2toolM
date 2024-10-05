#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <pthread.h>
#include <errno.h>

extern int errno;

static char *tar = "/data/data/com.termux/files/usr/bin/tar";
static char *tar_gz_file = "/storage/emulated/0/Download/eclipse-inst-jre-linux-x86_64.tar.gz";
static char *extract_dir = "/data/data/com.termux/files/home/Project/test_proc/tmp";

static void printError(char *name)
{
    printf("%s: %s\n", name, strerror(errno));
    exit(0);
}

static void checkError(int ret, char *name)
{
    if (ret == -1)
        printError(name);
}

static long getFileSize(FILE *fp)
{
    long fileSize = 0;
    fseek(fp, 0, SEEK_END);
    fileSize = ftell(fp);
    fseek(fp, 0, SEEK_SET);
    return fileSize;
}

static void *extractArchiveThread(void *data)
{
    int inputStreamFd = *(int*)(data);
    
    int fd = open("/dev/null", O_WRONLY);
    if (fd == -1) 
    {
        perror("open");
        exit(0);
    }
    
    pid_t pid = fork();
    
    if (pid == 0)
    {
        dup2(inputStreamFd, 0);
        dup2(fd, 1);
        dup2(fd, 2);
        
        char *argv[] = { "tar", "-xzv", "-C", extract_dir, NULL};
        
        execv(tar, argv);
        perror("execve");
        exit(1);
    }
    int status = 0;
    waitpid(pid, &status, 0);
    return NULL;
}

static void *readFileThread(void *data)
{
    int outoutStreamFD = *(int*)(data);
    
    FILE *fp = fopen(tar_gz_file, "rb");
    
    if (fp == NULL)
    {
        printError("fopen");
    }
    
    long size = getFileSize(fp);
    
    if (size == 0)
    {
        printf("FileSize = 0\n");
        return NULL;
    }
    
    char buffer[4096];
    
    int done_size = 0;
    int done_proc = 0;
    
    while (!feof(fp))
    {
        memset(buffer, 0, sizeof(buffer));
        int num = 0;
        num = fread(buffer, sizeof(char), sizeof(buffer), fp);
        done_size += num;
        if (num == 0)
        {
            continue;
        }
        else
        {
            int proc = (int)((float) done_size / size * 100);
            int ret = write(outoutStreamFD, buffer, num);
            if (proc != done_proc)
            {
                printf("write = %d\n", done_proc);
                done_proc = proc;
            }
        }
    }
    
    return NULL;
}

int main(int argc, char **argv)
{
    int fds[2];
    checkError(pipe(fds), "pipe");
    
    pthread_t readPthread;
    pthread_create(&readPthread, NULL, readFileThread, &fds[1]);
    
    pthread_t extractPthread;
    pthread_create(&extractPthread, NULL, extractArchiveThread, &fds[0]);
    
    pthread_join(readPthread, NULL);
    return 0;
}