package nl.thymo.virtualagc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import nl.thymo.virtualagc.control.IndicatorPanel;

public class MainActivity extends AppCompatActivity {
    //FIXME: Implement controller
    //static AGCController controller = new AGCController();

    static DSKYTest dskyTest;

    IndicatorPanel indicatorPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorPanel = findViewById(R.id.indicatorPanel);

        dskyTest = new DSKYTest(indicatorPanel);
        dskyTest.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dskyTest.stop();
    }
}
