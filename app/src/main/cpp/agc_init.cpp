//
// Created by thymo on 01/03/18.
//

#include "agc_init.h"

#ifdef __cplusplus
extern "C" {
#endif

jint Java_nl_thymo_virtualagc_AGCController_init(JNIEnv *env, jobject obj) {
	return agc_engine_init(&State, "Aurora12.bin", NULL, 1);
}

#ifdef __cplusplus
}
#endif