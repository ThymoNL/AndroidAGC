//
// Created by thymo on 01/05/18.
//

#include <stdio.h>

FILE * rfopen(const char *Filename, const char *mode)
{
	const char *dir = "/sdcard/AndroidAGC/";
	char path[256];
	snprintf(path, sizeof path, "%s%s", dir, Filename);
	FILE * rom = fopen(path, mode);

	return rom;
}