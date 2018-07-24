package nl.thymo.androidagc.control;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import nl.thymo.androidagc.R;

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

public class IndicatorPanel extends LinearLayout {
	private boolean displayOn;

	private IndicatorLight uplinkLight, attLight, stbyLight, keyrelLight, oprerrLight,
			priodispLight, nodapLight, tempLight, gimballockLight, progLight, restartLight,
			trackerLight, altLight, velLight;

	public IndicatorPanel(Context context) {
		super(context);
		init();
	}

	public IndicatorPanel(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public IndicatorPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public IndicatorPanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.indicators, this);

		uplinkLight = findViewById(R.id.uplinkLight);
		attLight = findViewById(R.id.attLight);
		stbyLight = findViewById(R.id.stbyLight);
		keyrelLight = findViewById(R.id.keyrelLight);
		oprerrLight = findViewById(R.id.oprerrLight);
		priodispLight = findViewById(R.id.priodispLight);
		nodapLight = findViewById(R.id.nodapLight);
		tempLight = findViewById(R.id.tempLight);
		gimballockLight = findViewById(R.id.gimballockLight);
		progLight = findViewById(R.id.progLight);
		restartLight = findViewById(R.id.restartLight);
		trackerLight = findViewById(R.id.trackerLight);
		altLight = findViewById(R.id.altLight);
		velLight = findViewById(R.id.velLight);

		// Set resources to use for indicators
		uplinkLight.setResources(R.mipmap.uplinkactyon, R.mipmap.uplinkactyoff);
		attLight.setResources(R.mipmap.noatton, R.mipmap.noattoff);
		stbyLight.setResources(R.mipmap.stbyon, R.mipmap.stbyoff);
		keyrelLight.setResources(R.mipmap.keyrelon, R.mipmap.keyreloff);
		oprerrLight.setResources(R.mipmap.oprerron, R.mipmap.oprerroff);
		priodispLight.setResources(R.mipmap.priodispon, R.mipmap.priodispoff);
		nodapLight.setResources(R.mipmap.nodapon, R.mipmap.nodapoff);
		tempLight.setResources(R.mipmap.tempon, R.mipmap.tempoff);
		gimballockLight.setResources(R.mipmap.gimballockon, R.mipmap.gimballockoff);
		progLight.setResources(R.mipmap.progon, R.mipmap.progoff);
		restartLight.setResources(R.mipmap.restarton, R.mipmap.restartoff);
		trackerLight.setResources(R.mipmap.trackeron, R.mipmap.trackeroff);
		altLight.setResources(R.mipmap.alton, R.mipmap.altoff);
		velLight.setResources(R.mipmap.velon, R.mipmap.veloff);

		displayOn = true;
	}

	public void turnOn(Indicator light) {
		setState(light, true);
	}

	public void turnOff(Indicator light) {
		setState(light, false);
	}

	public void setState(Indicator light, boolean state) {
		switch (light) {
			case UPLINK:
				uplinkLight.setState(state & displayOn);
				break;
			case NOATT:
				attLight.setState(state & displayOn);
				break;
			case STBY:
				stbyLight.setState(state); // Will need to be on if in standby
				break;
			case KEYREL:
				keyrelLight.setState(state & displayOn);
				break;
			case OPRERR:
				oprerrLight.setState(state & displayOn);
				break;
			case PRIODISP:
				priodispLight.setState(state & displayOn);
				break;
			case NODAP:
				nodapLight.setState(state & displayOn);
				break;
			case TEMP:
				tempLight.setState(state); // Is OR'd with signal from IMU
				break;
			case GIMBALLOCK:
				gimballockLight.setState(state & displayOn);
				break;
			case PROG:
				progLight.setState(state & displayOn);
				break;
			case RESTART:
				restartLight.setState(state & displayOn);
				break;
			case TRACKER:
				trackerLight.setState(state & displayOn);
				break;
			case ALT:
				altLight.setState(state & displayOn);
				break;
			case VEL:
				velLight.setState(state & displayOn);
				break;
		}
	}

	public void standby(boolean off) {
		displayOn = off;

		// Refresh indicator states to reflect standby
		uplinkLight.setState(uplinkLight.getState());
		attLight.setState(attLight.getState());
		keyrelLight.setState(keyrelLight.getState());
		oprerrLight.setState(oprerrLight.getState());
		priodispLight.setState(priodispLight.getState());
		nodapLight.setState(nodapLight.getState());
		gimballockLight.setState(gimballockLight.getState());
		progLight.setState(progLight.getState());
		restartLight.setState(restartLight.getState());
		trackerLight.setState(trackerLight.getState());
		altLight.setState(altLight.getState());
		velLight.setState(velLight.getState());
	}

	public enum Indicator {
		UPLINK, NOATT, STBY, KEYREL, OPRERR,
		PRIODISP, NODAP, TEMP, GIMBALLOCK,
		PROG, RESTART, TRACKER, ALT, VEL
	}
}
