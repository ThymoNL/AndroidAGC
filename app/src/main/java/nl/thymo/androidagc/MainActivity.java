package nl.thymo.androidagc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import nl.thymo.androidagc.control.ELDPanel;
import nl.thymo.androidagc.control.IndicatorPanel;

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

public class MainActivity extends AppCompatActivity {
	public static final String TAG = "MainActivity";
	public static final int REQUEST_EXTERNAL_STORAGE = 1969;

	//FIXME: Implement controller
	private static AGCController controller;
	private static DSKYTest dskyTest;

	private int agcStatus;

	private IndicatorPanel indicatorPanel;
	private ELDPanel eldPanel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		indicatorPanel = findViewById(R.id.indicatorPanel);
		eldPanel = findViewById(R.id.eldPanel);

		dskyTest = new DSKYTest(indicatorPanel, eldPanel);

		controller = new AGCController(getResources().openRawResource(R.raw.aurora12));

		if (ContextCompat.checkSelfPermission(this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
					REQUEST_EXTERNAL_STORAGE);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		dskyTest.stop();
		controller.stop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		dskyTest.start();
		if (agcStatus == 0)
			controller.start();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
										   @NonNull int[] grantResults) {
		switch (requestCode) {
			case REQUEST_EXTERNAL_STORAGE:
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Log.i(TAG, "Storage permission granted.");
					if (agcStatus != 0)
						agcStatus = controller.initEngine();
						if (agcStatus == 0)
							controller.start();
				} else {
					Log.w(TAG, "Storage permission denied. AGC can't run.");
					agcStatus = -1;
				}
				break;

			default:
				Log.wtf(TAG, "Permission granted we didn't request? request code: "
						+ requestCode);
		}
	}
}
