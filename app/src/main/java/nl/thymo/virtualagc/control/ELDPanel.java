package nl.thymo.virtualagc.control;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import nl.thymo.virtualagc.R;

/**
 * Created by thymo on 28/04/18.
 */

public class ELDPanel extends ConstraintLayout {
    //TODO: Convert horizontal separators to IndicatorLight (for P06)
    private IndicatorLight eldActy, eldProg, eldVerb, eldNoun;
    private ELD progELD1, progELD2, verbELD1, verbELD2, nounELD1, nounELD2;
    private ELDRow r1Eld, r2Eld, r3Eld;

    public enum ELDIndicator {
        ACTY, PROG, VERB, NOUN
    }

    public enum ELDDigitRow {
        PROG, VERB, NOUN, R1, R2, R3
    }

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
        progELD1 = findViewById(R.id.progELD1);
        progELD2 = findViewById(R.id.progELD2);
        verbELD1 = findViewById(R.id.verbELD1);
        verbELD2 = findViewById(R.id.verbELD2);
        nounELD1 = findViewById(R.id.nounELD1);
        nounELD2 = findViewById(R.id.nounELD2);
        r1Eld = findViewById(R.id.r1Eld);
        r2Eld = findViewById(R.id.r2Eld);
        r3Eld = findViewById(R.id.r3Eld);

        // Set resources for indicators
        eldActy.setResources(R.mipmap.compactyon, R.mipmap.compactyoff);
        eldProg.setResources(R.mipmap.rprogon, R.mipmap.rprogoff);
        eldVerb.setResources(R.mipmap.verbon, R.mipmap.verboff);
        eldNoun.setResources(R.mipmap.nounon, R.mipmap.nounoff);
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
                progELD1.set(s.charAt(0));
                progELD2.set(s.charAt(1));
                break;
            case VERB:
                verbELD1.set(s.charAt(0));
                verbELD2.set(s.charAt(1));
                break;
            case NOUN:
                nounELD1.set(s.charAt(0));
                nounELD2.set(s.charAt(1));
                break;
        }
    }
}
