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

#ifndef ANDROIDAGC_AGC_IO_H
#define ANDROIDAGC_AGC_IO_H

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEnv *IOenv;
jobject instance;

int ch015 = 0, SbyPressed = 0;

void handleDisplay(int value);
void handleIndicator(int value);
void handleChannel163(int value);

#ifdef __cplusplus
}
#endif

#endif //ANDROIDAGC_AGC_IO_H
