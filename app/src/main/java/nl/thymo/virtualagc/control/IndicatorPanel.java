package nl.thymo.virtualagc.control;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import nl.thymo.virtualagc.R;

/**
 * Created by thymo on 27/04/18.
 */

public class IndicatorPanel extends LinearLayout {
    private IndicatorLight uplinkLight, attLight, stbyLight, keyrelLight, oprerrLight,
            priodispLight, nodapLight, tempLight, gimballockLight, progLight, restartLight,
            trackerLight, altLight, velLight;

    public enum Indicator {
        UPLINK, NOATT, STBY, KEYREL, OPRERR,
        PRIODISP, NODAP, TEMP, GIMBALLOCK,
        PROG, RESTART, TRACKER, ALT, VEL
    }

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
    }

    public void turnOn(Indicator light) {
        setState(light, true);
    }

    public void turnOff(Indicator light) {
        setState(light, false);
    }

    private void setState(Indicator light, boolean state) {
        switch (light) {
            case UPLINK:
                uplinkLight.setState(state);
                break;
            case NOATT:
                attLight.setState(state);
                break;
            case STBY:
                stbyLight.setState(state);
                break;
            case KEYREL:
                keyrelLight.setState(state);
                break;
            case OPRERR:
                oprerrLight.setState(state);
                break;
            case PRIODISP:
                priodispLight.setState(state);
                break;
            case NODAP:
                nodapLight.setState(state);
                break;
            case TEMP:
                tempLight.setState(state);
                break;
            case GIMBALLOCK:
                gimballockLight.setState(state);
                break;
            case PROG:
                progLight.setState(state);
                break;
            case RESTART:
                restartLight.setState(state);
                break;
            case TRACKER:
                trackerLight.setState(state);
                break;
            case ALT:
                altLight.setState(state);
                break;
            case VEL:
                velLight.setState(state);
                break;
        }
    }
}
