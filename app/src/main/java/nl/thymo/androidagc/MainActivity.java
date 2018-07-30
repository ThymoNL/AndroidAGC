package nl.thymo.androidagc;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

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

	private static AGCController controller;
	private static DSKYTest dskyTest;

	private int agcStatus;

	@SuppressLint("ClickableViewAccessibility") // FIXME: Provide custom view for buttons
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		IndicatorPanel indicatorPanel = findViewById(R.id.indicatorPanel);
		ELDPanel eldPanel = findViewById(R.id.eldPanel);
		ImageButton proButton = findViewById(R.id.proButton);

		proButton.setOnTouchListener((v, event) -> {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				controller.pressSby(true);
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				controller.pressSby(false);
				return true;
			}
			return false;
		});

		if (ContextCompat.checkSelfPermission(this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
					REQUEST_EXTERNAL_STORAGE);
		} else {
			controller = new AGCController(getResources().openRawResource(R.raw.aurora12),
					indicatorPanel, eldPanel);
			controller.initEngine();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		//dskyTest.stop();
		controller.stop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		//dskyTest.start();
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

	public void buttonClick(View view) {
		int id = view.getId();

		switch (id) {
			case R.id.d0Button:
				controller.sendKey(020);
				break;
			case R.id.d1Button:
				controller.sendKey(1);
				break;
			case R.id.d2Button:
				controller.sendKey(2);
				break;
			case R.id.d3Button:
				controller.sendKey(3);
				break;
			case R.id.d4Button:
				controller.sendKey(4);
				break;
			case R.id.d5Button:
				controller.sendKey(5);
				break;
			case R.id.d6Button:
				controller.sendKey(6);
				break;
			case R.id.d7Button:
				controller.sendKey(7);
				break;
			case R.id.d8Button:
				controller.sendKey(010);
				break;
			case R.id.d9Button:
				controller.sendKey(011);
				break;
			case R.id.verbButton:
				controller.sendKey(021);
				break;
			case R.id.resetButton:
				controller.sendKey(022);
				break;
			case R.id.keyrelButton:
				controller.sendKey(031);
				break;
			case R.id.plusButton:
				controller.sendKey(032);
				break;
			case R.id.minusButton:
				controller.sendKey(033);
				break;
			case R.id.enterButton:
				controller.sendKey(034);
				break;
			case R.id.clearButton:
				controller.sendKey(036);
				break;
			case R.id.nounButton:
				controller.sendKey(037);
				break;
			default:
				Log.wtf(TAG, "Got invalid id for handler");
				break;
		}
	}
}
