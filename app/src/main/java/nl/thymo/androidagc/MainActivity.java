package nl.thymo.androidagc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
		agcStatus = controller.initEngine();

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
}
