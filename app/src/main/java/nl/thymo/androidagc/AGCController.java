package nl.thymo.androidagc;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

class AGCController implements Runnable {
	private static final String TAG = "AGCController";
	private Thread t;

	private InputStream pack;

	static {
		System.loadLibrary("agc_control");
		System.loadLibrary("agc_engine");
		System.loadLibrary("agc_engine_init");
		System.loadLibrary("rfopen");
	}

	AGCController(InputStream pack) {
		this.pack = pack;
		initStorage();
	}

	private native int init();

	private native void cycle();

	private native void halt();

	int initEngine() {
		int status = init();
		switch (status) {
			case 0:
				Log.i(TAG, status + ": Engine initialized successfully");
				break;
			case 1:
				Log.e(TAG, status + ": ROM Image file not found");
				break;
			case 2:
				Log.e(TAG, status + ": ROM Image file larger than core memory");
				break;
			case 3:
				Log.e(TAG, status + ": ROM Image file size is odd");
				break;
			case 4:
				Log.e(TAG, status + ": agc_t structure not allocated");
				break;
			case 5:
				Log.e(TAG, status + ": File read error");
				break;
			case 6:
				Log.e(TAG, status + ": Core-dump file not found");
				break;
		}

		return status;
	}

	/**
	 * This uses a lot of duct tape
	 * Redo this in the future
	 */
	private void initStorage() {
		try {
			String path = Environment.getExternalStorageDirectory() + "/AndroidAGC/";
			new File(path).mkdirs();
			File file = new File(path, "Aurora12.bin");
			file.createNewFile();
			byte[] buf = new byte[1024];
			int read;
			FileOutputStream out = new FileOutputStream(file);

			try {
				while ((read = pack.read(buf)) > 0) {
					out.write(buf, 0, read);
				}
				Log.i(TAG, "AGC rope written to storage");
			} catch (FileNotFoundException e) {
				Log.e(TAG, "Could not open file for writing", e);
			} finally {
				pack.close();
				out.close();
			}
		} catch (IOException e) {
			Log.e(TAG, "IOException during write", e);
		}
	}

	public void run() {
		cycle();
	}

	void start() {
		Log.i(TAG, "Starting AGC");

		if (t == null || !t.isAlive()) {
			t = new Thread(this);
			t.start();
		}
	}

	void stop() {
		Log.i(TAG, "Stopping AGC");

		halt();
	}

	private void handleIndicator(int value) {
		//TODO: Implement
		Log.d(TAG, "Got indicator update: data = " + value);
	}

	private void handleDisplay(int value) {
		//TODO: Implement
		Log.d(TAG, "Got display update: data = " + value);
	}
}
