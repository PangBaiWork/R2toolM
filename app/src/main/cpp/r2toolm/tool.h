//
// Created by pangbai on 2024/8/14.
//


#ifndef R2TOOLM_TOOL_H
#define R2TOOLM_TOOL_H

#include <android/log.h>
//定义TAG之后，我们可以在LogCat通过TAG过滤出NDK打印的日志
#define TAG "FromJNI"
// 定义info信息
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
// 定义debug信息
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
// 定义error信息
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

#include <string>
using namespace std;
namespace tool {

    class tool {
    public:
        int log(string log);
    };

} // tool

#endif //R2TOOLM_TOOL_H
