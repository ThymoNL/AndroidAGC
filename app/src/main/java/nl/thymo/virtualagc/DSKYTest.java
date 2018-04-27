package nl.thymo.virtualagc;

import android.util.Log;

import nl.thymo.virtualagc.control.IndicatorPanel;
import nl.thymo.virtualagc.control.IndicatorPanel.Indicator;

/**
 * Created by thymo on 27/04/18.
 */

class DSKYTest implements Runnable {
    private Thread t;
    private boolean stop;

    private IndicatorPanel indicatorPanel;

    private static final String TAG = "DSKYTest";

    DSKYTest(IndicatorPanel indicatorPanel) {
        this.indicatorPanel = indicatorPanel;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        Indicator[] lights = Indicator.values();

        try {
            while (!stop) {
                for (int i = 0; i < lights.length; i++) {
                    indicatorPanel.turnOff(lights[i]);
                    Thread.sleep(250);
                }
                Thread.sleep(2 * 1000);
                for (int i = 0; i < lights.length; i++) {
                    indicatorPanel.turnOn(lights[i]);
                    Thread.sleep(250);
                }
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
