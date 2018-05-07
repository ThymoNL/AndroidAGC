package nl.thymo.androidagc;

import android.util.Log;

import nl.thymo.androidagc.control.ELDPanel;
import nl.thymo.androidagc.control.ELDPanel.*;
import nl.thymo.androidagc.control.IndicatorPanel;
import nl.thymo.androidagc.control.IndicatorPanel.*;

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

class DSKYTest implements Runnable {
    private Thread t;
    private boolean stop;

    private IndicatorPanel indicatorPanel;
    private ELDPanel eldPanel;

    private static final String TAG = "DSKYTest";

    DSKYTest(IndicatorPanel indicatorPanel, ELDPanel eldPanel) {
        this.indicatorPanel = indicatorPanel;
        this.eldPanel = eldPanel;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        Indicator[] lights = Indicator.values();
        ELDIndicator[] eldLights = ELDIndicator.values();
        ELDDigitRow[] eldDigitRows = ELDDigitRow.values();

        try {
            while (!stop) {
                // Indicator lights
                for (int i = 0; i < lights.length; i++) {
                    indicatorPanel.turnOff(lights[i]);
                    Thread.sleep(250);
                }
                // Display lights
                for (int i = 0; i < eldLights.length; i++) {
                    eldPanel.turnOff(eldLights[i]);
                    Thread.sleep(250);
                }
                // Display digits
                eldPanel.setRow("xx", eldDigitRows[0]);
                eldPanel.setRow("xx", eldDigitRows[1]);
                eldPanel.setRow("xx", eldDigitRows[2]);
                eldPanel.setRow("|xxxxx", eldDigitRows[3]);
                eldPanel.setRow("|xxxxx", eldDigitRows[4]);
                eldPanel.setRow("|xxxxx", eldDigitRows[5]);

                Thread.sleep(2 * 1000);
                // Indicator lights
                for (int i = 0; i < lights.length; i++) {
                    indicatorPanel.turnOn(lights[i]);
                    Thread.sleep(250);
                }
                // Display lights
                for (int i = 0; i < eldLights.length; i++) {
                    eldPanel.turnOn(eldLights[i]);
                    Thread.sleep(250);
                }
                // Display digits
                for (int i = 1; i <= 9; i++) {
                    eldPanel.setRow(""+i+i, eldDigitRows[0]);
                    eldPanel.setRow(""+i+i, eldDigitRows[1]);
                    eldPanel.setRow(""+i+i, eldDigitRows[2]);
                    eldPanel.setRow("-"+i+i+i+i+i, eldDigitRows[3]);
                    eldPanel.setRow("-"+i+i+i+i+i, eldDigitRows[4]);
                    eldPanel.setRow("-"+i+i+i+i+i, eldDigitRows[5]);
                    Thread.sleep(1000);
                }
                eldPanel.setRow("00", eldDigitRows[0]);
                eldPanel.setRow("00", eldDigitRows[1]);
                eldPanel.setRow("00", eldDigitRows[2]);
                eldPanel.setRow("+00000", eldDigitRows[3]);
                eldPanel.setRow("+00000", eldDigitRows[4]);
                eldPanel.setRow("+00000", eldDigitRows[5]);
                Thread.sleep(2 * 1000);
            }
        } catch (InterruptedException e) {
            Log.e(TAG, "Exception during test", e);
        }
    }

    void start() {
        Log.i(TAG, "Starting test");
        stop = false;
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    void stop() {
        Log.i(TAG, "Stopping test");
        stop = true;
    }
}
