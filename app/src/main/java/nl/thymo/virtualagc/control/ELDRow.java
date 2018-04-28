package nl.thymo.virtualagc.control;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import nl.thymo.virtualagc.R;

/**
 * Created by thymo on 05/04/18.
 */

public class ELDRow extends LinearLayout {
    private ELD signEld, d1Eld, d2Eld, d3Eld, d4Eld, d5Eld;

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
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.eld_row, this);

        signEld = findViewById(R.id.signEld);
        d1Eld = findViewById(R.id.d1Eld);
        d2Eld = findViewById(R.id.d2Eld);
        d3Eld = findViewById(R.id.d3Eld);
        d4Eld = findViewById(R.id.d4Eld);
        d5Eld = findViewById(R.id.d5Eld);
    }

    public void set(String row) {
        signEld.set(row.charAt(0));
        d1Eld.set(row.charAt(1));
        d2Eld.set(row.charAt(2));
        d3Eld.set(row.charAt(3));
        d4Eld.set(row.charAt(4));
        d5Eld.set(row.charAt(5));
    }
}
