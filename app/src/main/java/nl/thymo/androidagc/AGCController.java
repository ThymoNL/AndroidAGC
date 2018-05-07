package nl.thymo.androidagc;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by thymo on 02/03/18.
 */

class AGCController implements Runnable {
	private static final String TAG = "AGCController";

	private InputStream pack;

	AGCController(InputStream pack) {
		this.pack = pack;
		initStorage();
	}

	static {
        System.loadLibrary("agc_init");
        System.loadLibrary("agc_engine");
        System.loadLibrary("agc_engine_init");
        System.loadLibrary("rfopen");
    }

    native int init();

    void initEngine() {
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
		}
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
			int read = 0;
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

    }
}
