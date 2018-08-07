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

#include <android/log.h>
#include <inttypes.h>
#include <unistd.h>
#include "agc_control.h"
#include "agc_engine.h"
#include "agc_io.h"

#ifdef __cplusplus
extern "C" {
#endif

jint Java_nl_thymo_androidagc_AGCController_init(JNIEnv *env, jobject obj)
{
	IOenv = env;
	instance = obj;
	return agc_engine_init(&State, "Aurora12.bin", NULL, 1);
}

void Java_nl_thymo_androidagc_AGCController_cycle(JNIEnv *env, jobject obj)
{
	// Save JNI info so IO can call Java code
	IOenv = env;
	instance = obj;

	while (1) {
		if (halt)
			break;
		agc_engine(&State);
		__android_log_print(ANDROID_LOG_VERBOSE, "AGCClock", "Cyclecount: %" PRIu64 "\n",
							State.CycleCounter);
		usleep((unsigned int) 11.7);
	}
	halt = false;
}

void Java_nl_thymo_androidagc_AGCController_halt(JNIEnv *env, jobject obj)
{
	halt = true;
}

void Java_nl_thymo_androidagc_AGCController_sendKey(JNIEnv *env, jobject obj, jint keycode)
{
	__android_log_print(ANDROID_LOG_DEBUG, "AGCIO", "Sending keycode: %d", keycode);

	ch015 = keycode;
}

void Java_nl_thymo_androidagc_AGCController_pressSby(JNIEnv *env, jobject obj, jboolean pressed)
{
	if (pressed)
		__android_log_print(ANDROID_LOG_DEBUG, "AGCIO", "PRO pressed");
	else
		__android_log_print(ANDROID_LOG_DEBUG, "AGCIO", "PRO unpressed");

	SbyPressed = !pressed;
}

#ifdef __cplusplus
}
#endif
