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
	//TODO: Convert horizontal separators to IndicatorLight (for P06)
	private IndicatorLight eldActy, eldProg, eldVerb, eldNoun, r1Sep, r2Sep, r3Sep;
	private ELDPair progPair, verbPair, nounPair;
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
		progPair = findViewById(R.id.progPair);
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
	}

	public void turnOn(ELDIndicator light) {
		setIndicator(light, true);
	}

	public void turnOff(ELDIndicator light) {
		setIndicator(light, false);
	}

	private void setIndicator(ELDIndicator light, boolean state) {
		switch (light) {
			case ACTY:
				eldActy.setState(state);
				break;
			case PROG:
				eldProg.setState(state);
				break;
			case VERB:
				eldVerb.setState(state);
				break;
			case NOUN:
				eldNoun.setState(state);
				break;
			case R1SEP:
				r1Sep.setState(state);
				break;
			case R2SEP:
				r2Sep.setState(state);
				break;
			case R3SEP:
				r3Sep.setState(state);
				break;
		}
	}

	public void setRow(String s, ELDDigitRow select) {
		switch (select) {
			case R1:
				r1Eld.set(s);
				break;
			case R2:
				r2Eld.set(s);
				break;
			case R3:
				r3Eld.set(s);
				break;
			case PROG:
				progPair.set(s);
				break;
			case VERB:
				verbPair.set(s);
				break;
			case NOUN:
				nounPair.set(s);
				break;
		}
	}

	public enum ELDIndicator {
		ACTY, PROG, VERB, NOUN, R1SEP, R2SEP, R3SEP
	}

	public enum ELDDigitRow {
		PROG, VERB, NOUN, R1, R2, R3
	}
}
