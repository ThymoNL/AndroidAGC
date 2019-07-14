/*
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

package nl.thymo.androidagc.control;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public final class NewIndicatorLight extends View {
	public boolean AMBER = true;
	public boolean WHITE = false;

	private boolean state = false;
	private boolean color;
	private String title = null;

	private Paint textPaint;

	public NewIndicatorLight(Context context) {
		super(context);
		init();
	}

	public NewIndicatorLight(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public NewIndicatorLight(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public NewIndicatorLight(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		textPaint = new Paint();
		textPaint.setColor(Color.BLACK);
	}

	public boolean getState() {
		return state;
	}

	public void setState() {
		if (state) turnOn();
		else turnOff();
	}

	public void turnOn() {
		state = true;
	}

	public void turnOff() {
		state = false;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (color == WHITE) {
			canvas.drawARGB(255, 255, 255, 255);
		} else if (color == AMBER) {
			canvas.drawARGB(255, 255, 200, 0);
		}

		if (title != null)
			canvas.drawText(title, getWidth() / 2, getHeight() / 2, textPaint);
	}
}
