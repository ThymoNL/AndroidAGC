package nl.thymo.androidagc.control;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import nl.thymo.androidagc.R;

/**
 * Created by thymo on 05/03/18.
 */

public class ELD extends AppCompatImageView {


    public ELD(Context context) {
        super(context);
        init();
    }

    public ELD(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ELD(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Nothing for now
    }

    public void set(char c) {
        switch (c) {
            case '-':
                setImageResource(R.mipmap.minuson);
                break;
            case '+':
                setImageResource(R.mipmap.pluson);
                break;
            case '0':
                setImageResource(R.mipmap.a7seg_21);
                break;
            case '1':
                setImageResource(R.mipmap.a7seg_3);
                break;
            case '2':
                setImageResource(R.mipmap.a7seg_25);
                break;
            case '3':
                setImageResource(R.mipmap.a7seg_27);
                break;
            case '4':
                setImageResource(R.mipmap.a7seg_15);
                break;
            case '5':
                setImageResource(R.mipmap.a7seg_30);
                break;
            case '6':
                setImageResource(R.mipmap.a7seg_28);
                break;
            case '7':
                setImageResource(R.mipmap.a7seg_19);
                break;
            case '8':
                setImageResource(R.mipmap.a7seg_29);
                break;
            case '9':
                setImageResource(R.mipmap.a7seg_31);
                break;
            case '|':
                setImageResource(R.mipmap.plusminusoff);
                break;
            case 'x':
                setImageResource(R.mipmap.a7seg_0);
                break;
        }
    }
}
