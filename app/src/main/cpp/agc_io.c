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

#include "agc_io.h"
#include "agc_engine.h"

void ChannelOutput(agc_t *State, int Channel, int Value) {

	switch (Channel) {
		case 010:
			handleDisplay(Value);
			break;
		case 011:
			handleIndicator(Value);
			break;
		case 0163:
			handleChannel163(Value);
			break;
		default:
			// Ignore other channels
			break;
	}
}

int ChannelInput(agc_t *State) {
	return 0;
}

void ChannelRoutine(agc_t *State) {
	return;
}

void ShiftToDeda(agc_t *State, int data) {
	return;
}

void handleIndicator(int value) {
	jclass controller = (*IOenv)->FindClass(IOenv, "nl/thymo/androidagc/AGCController");
	jmethodID methodID = (*IOenv)->GetMethodID(IOenv, controller, "handleIndicator", "(I)V");
	(*IOenv)->CallVoidMethod(IOenv, instance, methodID, value);
}

void handleDisplay(int value) {
	jclass controller = (*IOenv)->FindClass(IOenv, "nl/thymo/androidagc/AGCController");
	jmethodID methodID = (*IOenv)->GetMethodID(IOenv, controller, "handleDisplay", "(I)V");
	(*IOenv)->CallVoidMethod(IOenv, instance, methodID, value);
}

void handleChannel163(int value) {
	jclass controller = (*IOenv)->FindClass(IOenv, "nl/thymo/androidagc/AGCController");
	jmethodID methodID = (*IOenv)->GetMethodID(IOenv, controller, "handleChannel163", "(I)V");
	(*IOenv)->CallVoidMethod(IOenv, instance, methodID, value);
}
