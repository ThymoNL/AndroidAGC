package nl.thymo.androidagc.control;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import nl.thymo.androidagc.R;

/**
 * Created by thymo on 28/04/18.
 */

public class ELDPair extends LinearLayout {
    private ELD eld1, eld2;

    public ELDPair(Context context) {
        super(context);
        init();
    }

    public ELDPair(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ELDPair(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ELDPair(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.eld_pair, this);

        eld1 = findViewById(R.id.eld1);
        eld2 = findViewById(R.id.eld2);
    }

    public void set(String pair) {
        eld1.set(pair.charAt(0));
        eld2.set(pair.charAt(1));
    }
}
