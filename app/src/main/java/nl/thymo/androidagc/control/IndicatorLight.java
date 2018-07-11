package nl.thymo.androidagc.control;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

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

public class IndicatorLight extends AppCompatImageView {
	private int onResource = 0, offResource = 0;
	private boolean state = false;

	public IndicatorLight(Context context) {
		super(context);
	}

	public IndicatorLight(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public IndicatorLight(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setResources(int onResource, int offResource) {
		this.onResource = onResource;
		this.offResource = offResource;
	}

	boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		if (state)
			turnOn();
		else
			turnOff();
	}

	public void turnOn() {
		state = true;
		post(() -> setImageResource(onResource));
	}

	public void turnOff() {
		state = false;
		post(() -> setImageResource(offResource));
	}
}
