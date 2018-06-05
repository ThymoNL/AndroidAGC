/**
 * AndroidAGC
 * Copyright © 2018 Thymo van Beers
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

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
bool halt = false;

JNIEXPORT JNICALL jint Java_nl_thymo_androidagc_AGCController_init(JNIEnv *env, jobject obj);
JNIEXPORT JNICALL void Java_nl_thymo_androidagc_AGCController_cycle(JNIEnv *env, jobject obj);
JNIEXPORT JNICALL void Java_nl_thymo_androidagc_AGCController_halt(JNIEnv *env, jobject obj);

#ifdef __cplusplus
}
#endif
#endif //VIRTUALAGC_AGC_INIT_H
