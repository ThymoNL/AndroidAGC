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

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

public class ELDRow extends LinearLayout {
    private ELD eldSign, eld1, eld2, eld3, eld4, eld5;

    public ELDRow(Context context) {
        super(context);
        init();
    }

    public ELDRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ELDRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ELDRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.eld_row, this);

        eldSign = findViewById(R.id.eldSign);
        eld1 = findViewById(R.id.eld1);
        eld2 = findViewById(R.id.eld2);
        eld3 = findViewById(R.id.eld3);
        eld4 = findViewById(R.id.eld4);
        eld5 = findViewById(R.id.eld5);
    }

    public void set(String row) {
        eldSign.set(row.charAt(0));
        eld1.set(row.charAt(1));
        eld2.set(row.charAt(2));
        eld3.set(row.charAt(3));
        eld4.set(row.charAt(4));
        eld5.set(row.charAt(5));
    }
}
