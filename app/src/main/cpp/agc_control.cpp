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
#include <time.h>
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

	struct timespec res;
	if (clock_getres(CLOCK_MONOTONIC, &res)) {
		__android_log_print(ANDROID_LOG_FATAL, "AGCInit",
							"System does not support CLOCK_MONOTONIC!");
		return -1;
	} else {
		__android_log_print(ANDROID_LOG_INFO, "AGCInit", "CLOCK_MONOTONIC resolution %li ns",
							res.tv_nsec);
	}

	return agc_engine_init(&State, "Aurora12.bin", NULL, 1);
}

void Java_nl_thymo_androidagc_AGCController_cycle(JNIEnv *env, jobject obj)
{
	// Save JNI info so IO can call Java code
	IOenv = env;
	instance = obj;

	long DesiredCycles = 0;
	struct timespec last, now;
	clock_gettime(CLOCK_MONOTONIC, &now);
	last = now;

	while (1) {
		if (halt) {
			break;
		}

		clock_gettime(CLOCK_MONOTONIC, &now);

		DesiredCycles = static_cast<long>((now.tv_nsec - last.tv_nsec) / 1000000000.0 * AGC_PER_SECOND);

		if (DesiredCycles == 0) { // Save some power
			struct timespec req, rem;
			req.tv_sec = 0;
			req.tv_nsec = 10000000; // 10ms
			nanosleep(&req, &rem);
			continue;
		}

		while (DesiredCycles-- > 0) {
			//__android_log_print(ANDROID_LOG_VERBOSE, "AGCClock", "Cycles: %lu", State.CycleCounter++);
			agc_engine(&State);
		}

		last = now;
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
