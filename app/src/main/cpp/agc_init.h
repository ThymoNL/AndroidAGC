//
// Created by thymo on 01/03/18.
//

#ifndef VIRTUALAGC_AGC_INIT_H
#define VIRTUALAGC_AGC_INIT_H
#include <jni.h>
#ifdef __cplusplus
extern "C" {
#endif

#define true 1
#define false 0

JNIEXPORT JNICALL jboolean Java_nl_thymo_virtualagc_AGCController_init(JNIEnv *env, jobject obj);

#ifdef __cpluspls
}
#endif
#endif //VIRTUALAGC_AGC_INIT_H
