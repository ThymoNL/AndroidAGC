package nl.thymo.virtualagc.control;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by thymo on 05/03/18.
 */

public class IndicatorLight extends AppCompatImageView {
    int onResource = 0, offResource = 0;
    boolean state = false;

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

    void turnOn() {
        state = true;
        setImageResource(onResource);
    }

    void turnOff() {
        state = false;
        setImageResource(offResource);
    }
}
