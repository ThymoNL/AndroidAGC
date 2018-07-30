package nl.thymo.androidagc.control;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

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

public class ELDPanel extends ConstraintLayout {
	private boolean displayOn, r1signPlus, r1signMin, r2signPlus, r2signMin, r3signPlus, r3signMin;

	private IndicatorLight eldActy, eldProg, eldVerb, eldNoun, r1Sep, r2Sep, r3Sep;
	private ELDPair modePair, verbPair, nounPair;
	private ELDRow r1Eld, r2Eld, r3Eld;

	public ELDPanel(Context context) {
		super(context);
		init();
	}

	public ELDPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ELDPanel(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.eld_display, this);

		eldActy = findViewById(R.id.eldActy);
		eldProg = findViewById(R.id.eldProg);
		eldVerb = findViewById(R.id.eldVerb);
		eldNoun = findViewById(R.id.eldNoun);
		modePair = findViewById(R.id.modePair);
		verbPair = findViewById(R.id.verbPair);
		nounPair = findViewById(R.id.nounPair);
		r1Eld = findViewById(R.id.r1Eld);
		r2Eld = findViewById(R.id.r2Eld);
		r3Eld = findViewById(R.id.r3Eld);
		r1Sep = findViewById(R.id.r1Sep);
		r2Sep = findViewById(R.id.r2Sep);
		r3Sep = findViewById(R.id.r3Sep);

		// Set resources for indicators
		eldActy.setResources(R.mipmap.compactyon, R.mipmap.compactyoff);
		eldProg.setResources(R.mipmap.rprogon, R.mipmap.rprogoff);
		eldVerb.setResources(R.mipmap.verbon, R.mipmap.verboff);
		eldNoun.setResources(R.mipmap.nounon, R.mipmap.nounoff);
		r1Sep.setResources(R.mipmap.hseparatoron, R.mipmap.hseparator);
		r2Sep.setResources(R.mipmap.hseparatoron, R.mipmap.hseparator);
		r3Sep.setResources(R.mipmap.hseparatoron, R.mipmap.hseparator);

		displayOn = true;
		r1signPlus = false;
		r2signPlus = false;
		r3signPlus = false;
		r1signMin = false;
		r2signMin = false;
		r3signMin = false;
	}

	public void turnOn(ELDIndicator light) {
		setIndicator(light, true);
	}

	public void turnOff(ELDIndicator light) {
		setIndicator(light, false);
	}

	public void setIndicator(ELDIndicator light, boolean state) {
		switch (light) {
			case ACTY:
				eldActy.setState(state & displayOn);
				break;
			case PROG:
				eldProg.setState(state & displayOn);
				break;
			case VERB:
				eldVerb.setState(state & displayOn);
				break;
			case NOUN:
				eldNoun.setState(state & displayOn);
				break;
			case R1SEP:
				r1Sep.setState(state & displayOn);
				break;
			case R2SEP:
				r2Sep.setState(state & displayOn);
				break;
			case R3SEP:
				r3Sep.setState(state & displayOn);
				break;
		}
	}

	public void setRow(String s, ELDDigitRow select) {
		String current, update;
		char sign;
		switch (select) {
			case R1D1:
				current = r1Eld.get();
				update = current.substring(0, 1) + s + current.substring(2);
				r1Eld.set(update);
				break;
			case R1D23:
				current = r1Eld.get();

				sign = s.charAt(0);
				if (sign == '|') {
					r1signPlus = false;
					if (r1signMin)
						sign = '-';
				} else if (sign == '+') {
					r1signPlus = true;
				} else {
					throw new IllegalArgumentException("Sign field does not contain valid data!");
				}
				update = sign + current.substring(1, 2) + s.substring(1)
						+ current.substring(4);

				r1Eld.set(update);
				break;
			case R1D45:
				current = r1Eld.get();

				sign = s.charAt(0);
				if (sign == '|') {
					r1signMin = false;
					if (r1signPlus)
						sign = '+'; // TODO: Check if this is correct
				} else if (sign == '-') {
					r1signMin = true;
				} else {
					throw new IllegalArgumentException("Sign field does not contain valid data!");
				}
				update = sign + current.substring(1, 4) + s.substring(1);

				r1Eld.set(update);
				break;
			case R2D12:
				current = r2Eld.get();

				sign = s.charAt(0);
				if (sign == '|') {
					r2signPlus = false;
					if (r2signMin)
						sign = '-';
				} else if (sign == '+') {
					r2signPlus = true;
				} else {
					throw new IllegalArgumentException("Sign field does not contain valid data!");
				}
				update = sign + s.substring(1) + current.substring(3);

				r2Eld.set(update);
				break;
			case R2D34:
				current = r2Eld.get();

				sign = s.charAt(0);
				if (sign == '|') {
					r2signMin = false;
					if (r2signPlus)
						sign = '+'; // TODO: Check if this is correct
				} else if (sign == '-') {
					r2signMin = true;
				} else {
					throw new IllegalArgumentException("Sign field does not contain valid data!");
				}
				update = sign + current.substring(1, 3) + s.substring(1) + current.substring(5);

				r2Eld.set(update);
				break;
			case R23D51:
				current = r2Eld.get();
				update = current.substring(0, 5) + s.charAt(0);
				r2Eld.set(update);

				current = r3Eld.get();
				update = current.charAt(0) + "" + s.charAt(1) + current.substring(2);
				r3Eld.set(update);
				break;
			case R3D23:
				current = r3Eld.get();

				sign = s.charAt(0);
				if (sign == '|') {
					r3signPlus = false;
					if (r3signMin)
						sign = '-';
				} else if (sign == '+') {
					r3signPlus = true;
				} else {
					throw new IllegalArgumentException("Sign field does not contain valid data!");
				}
				update = sign + current.substring(1, 2) + s.substring(1) + current.substring(4);
				r3Eld.set(update);
				break;
			case R3D45:
				current = r3Eld.get();

				sign = s.charAt(0);
				if (sign == '|') {
					r3signMin = false;
					if (r3signPlus)
						sign = '+'; // TODO: Check if this is correct
				} else if (sign == '-') {
					r3signMin = true;
				} else {
					throw new IllegalArgumentException("Sign field does not contain valid data!");
				}
				update = sign + current.substring(1, 4) + s.substring(1);
				r3Eld.set(update);
				break;
			case MODE:
				modePair.set(s);
				break;
			case VERB:
				verbPair.set(s);
				break;
			case NOUN:
				nounPair.set(s);
				break;
		}
	}

	public void standby(boolean off) {
		displayOn = !off;
		if (!off) {
			r1Sep.turnOn();
			r2Sep.turnOn();
			r3Sep.turnOn();
			eldProg.turnOn();
			eldVerb.turnOn();
			eldNoun.turnOn();
		} else {
			r1Sep.turnOff();
			r2Sep.turnOff();
			r3Sep.turnOff();
			eldProg.turnOff();
			eldVerb.turnOff();
			eldNoun.turnOff();
			eldActy.turnOff();
		}
	}

	public enum ELDIndicator {
		ACTY, PROG, VERB, NOUN, R1SEP, R2SEP, R3SEP
	}

	public enum ELDDigitRow {
		MODE, VERB, NOUN, R1D1, R1D23, R1D45, R2D12, R2D34, R23D51, R3D23, R3D45
	}
}
