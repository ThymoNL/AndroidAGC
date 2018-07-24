package nl.thymo.androidagc;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import nl.thymo.androidagc.control.ELDPanel;
import nl.thymo.androidagc.control.ELDPanel.ELDIndicator;
import nl.thymo.androidagc.control.IndicatorPanel;
import nl.thymo.androidagc.control.IndicatorPanel.Indicator;

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

	private IndicatorPanel indicatorPanel;
	private ELDPanel eldPanel;

	static {
		System.loadLibrary("agc_control");
		System.loadLibrary("agc_engine");
		System.loadLibrary("agc_engine_init");
		System.loadLibrary("rfopen");
	}

	AGCController(InputStream pack, IndicatorPanel indicatorPanel, ELDPanel eldPanel) {
		this.pack = pack;
		this.indicatorPanel = indicatorPanel;
		this.eldPanel = eldPanel;
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

	private static final int ANUN_PAIR = 12 << 11;
	private static final int PROG_PAIR = 11 << 11;
	private static final int VERB_PAIR = 10 << 11;
	private static final int NOUN_PAIR = 9 << 11;
	private static final int R1D1_PAIR = 8 << 11;
	private static final int R1D23_PAIR = 7 << 11;
	private static final int R1D45_PAIR = 6 << 11;
	private static final int R2D12_PAIR = 5 << 11;
	private static final int R2D34_PAIR = 4 << 11;
	private static final int R23D51_PAIR = 3 << 11;
	private static final int R3D23_PAIR = 2 << 11;
	private static final int R3D45_PAIR = 1 << 11;

	private static final int DSKY_BLANK = 0;
	private static final int DSKY_ZERO = 21;
	private static final int DSKY_ONE = 3;
	private static final int DSKY_TWO = 25;
	private static final int DSKY_THREE = 27;
	private static final int DSKY_FOUR = 15;
	private static final int DSKY_FIVE = 30;
	private static final int DSKY_SIX = 28;
	private static final int DSKY_SEVEN = 19;
	private static final int DSKY_EIGHT = 29;
	private static final int DSKY_NINE = 31;

	private void handleIndicator(int value) {
		Log.d(TAG, "Got indicator update: data = " + Integer.toBinaryString(value));

		eldPanel.setIndicator(ELDIndicator.ACTY, 2 == (2 & value));
		indicatorPanel.setState(Indicator.UPLINK, 4 == (4 & value));
		// Other lights are handled by channel 163
	}

	private void handleDisplay(int value) {
		Log.d(TAG, "Got display update: data = " + Integer.toBinaryString(value));

		// TODO: Handle relay codes

		if (ANUN_PAIR == (ANUN_PAIR & value)) {
			value &= 511; // Mask latch selector
			indicatorPanel.setState(Indicator.PRIODISP, 1 == (1 & value));
			indicatorPanel.setState(Indicator.NODAP, 2 == (2 & value));
			indicatorPanel.setState(Indicator.VEL, 4 == (4 & value));
			indicatorPanel.setState(Indicator.NOATT, 8 == (8 & value));
			indicatorPanel.setState(Indicator.ALT, 16 == (16 & value));
			indicatorPanel.setState(Indicator.GIMBALLOCK, 32 == (32 & value));
			indicatorPanel.setState(Indicator.TRACKER, 128 == (128 & value));
			indicatorPanel.setState(Indicator.PROG, 256 == (256 & value));
		} else if (PROG_PAIR == (PROG_PAIR & value)) {
			value &= 511;
		}
	}

	private void handleChannel163(int value) {
		Log.d(TAG, "Got Ch163 update: data = " + Integer.toBinaryString(value));

		// Logical OR between software<->hardware signal
		indicatorPanel.setState(Indicator.TEMP, 8 == (8 & value));

		// Flashing modulated by AGC engine
		indicatorPanel.setState(Indicator.KEYREL, 16 == (16 & value));
		indicatorPanel.setState(Indicator.OPRERR, 64 == (64 & value));

		// V/N FLASH
		eldPanel.setIndicator(ELDIndicator.VERB, 32 == (32 & value));
		eldPanel.setIndicator(ELDIndicator.NOUN, 32 == (32 & value));

		indicatorPanel.setState(Indicator.RESTART, 128 == (128 & value));
		indicatorPanel.setState(Indicator.STBY, 256 == (256 & value));

		// DSKY ELDs on/off
		eldPanel.standby(512 == (512 & value));
		indicatorPanel.standby(512 == (512 & value));
	}
}
