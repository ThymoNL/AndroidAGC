//
// Created by thymo on 01/03/18.
//

#ifndef VIRTUALAGC_AGC_INIT_H
#define VIRTUALAGC_AGC_INIT_H

#ifdef __cplusplus
extern "C" {
#endif

#include <jni.h>
#include "agc_engine.h"

#define true 1
#define false 0

static agc_t State;

JNIEXPORT JNICALL jint Java_nl_thymo_virtualagc_AGCController_init(JNIEnv *env, jobject obj);


#ifdef __cplusplus
}
#endif
#endif //VIRTUALAGC_AGC_INIT_H
