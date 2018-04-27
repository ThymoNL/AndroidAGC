package nl.thymo.virtualagc;

/**
 * Created by thymo on 02/03/18.
 */

class AGCController {
    static {
        System.loadLibrary("agc_init");
        System.loadLibrary("agc_engine");
    }

    native boolean init();
}
