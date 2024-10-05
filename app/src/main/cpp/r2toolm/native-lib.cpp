#include <jni.h>
#include <string>
#include <r_socket.h>
#include "tool.h"
#include <r_core.h>
#include <r_main.h>
#include "redirect.h"
#include <r_asm.h>
#include <r_cmd.h>
extern "C" JNIEXPORT jstring JNICALL Java_work_pangbai_r2toolm_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}



extern "C"
JNIEXPORT jint JNICALL
Java_work_pangbai_r2toolm_MainActivityJava_Init(JNIEnv *env, jobject thiz, jstring path) {
    // TODO: implement Init()
    const char *str = env->GetStringUTFChars(path, reinterpret_cast<jboolean *>(JNI_TRUE));

    return 0;
}

#define PREFIX "/data/data/work.pangbai.r2toolm/files/usr"
extern "C"
JNIEXPORT jlong JNICALL
Java_work_pangbai_r2toolm_r2_R2_r2pipeNew(JNIEnv *env, jclass clazz) {
    // TODO: implement r2pipeNew()
   runLoggingThread();  // redirect stdio to Logcat
    //symbol analysis needed
    setenv("PATH",
           PREFIX"/bin:/product/bin:/apex/com.android.runtime/bin:/apex/com.android.art/bin:/system_ext/bin:/system/bin:/system/xbin:/vendor/bin",
           true);
    jlong handle = (jlong) (size_t) r_core_new();

    return handle;
}
extern "C"
JNIEXPORT jstring JNICALL
Java_work_pangbai_r2toolm_r2_R2_r2pipeCmd(JNIEnv *env, jclass clazz, jlong core, jstring cmd) {
    // TODO: implement r2pipeCmd()
    if (core) {

        auto *cCore = (r_core_t *) core;
        const char *cCmd = env->GetStringUTFChars(cmd, NULL);

        r_config_set(cCore->config,"scr.color","2");
        r_config_set(cCore->config,"scr.html","true");
        return env->NewStringUTF(r_core_cmd_str(cCore, cCmd));
    }
    return env->NewStringUTF("");

}
extern "C"
JNIEXPORT void JNICALL
Java_work_pangbai_r2toolm_r2_R2_r2pipeFree(JNIEnv *env, jclass clazz, jlong core) {
    // TODO: implement r2pipeFree()
    if (core) {
        auto *cCore = (r_core_t *) core;
        r_core_free(cCore);
    }
}

int main() {
    r_core_t *core = r_core_new();
   char *res= r_core_cmd_str(core, "o /bin/ls");
   res= r_core_cmd_str(core, "aaa");
   res = r_core_cmd_str(core, "afl");
    puts(res);
    return 1;

}