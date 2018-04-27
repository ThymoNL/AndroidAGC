package nl.thymo.virtualagc.control;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by thymo on 27/04/18.
 */

public class KeyPad extends View {
    public KeyPad(Context context) {
        super(context);
        init();
    }

    public KeyPad(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyPad(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public KeyPad(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        
    }
}
