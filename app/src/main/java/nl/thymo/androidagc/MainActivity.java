package nl.thymo.androidagc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.thymo.androidagc.control.ELDPanel;
import nl.thymo.androidagc.control.IndicatorPanel;

public class MainActivity extends AppCompatActivity {
    //FIXME: Implement controller
    private static AGCController controller;

    private static DSKYTest dskyTest;

    IndicatorPanel indicatorPanel;
    ELDPanel eldPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorPanel = findViewById(R.id.indicatorPanel);
        eldPanel = findViewById(R.id.eldPanel);

        dskyTest = new DSKYTest(indicatorPanel, eldPanel);
        dskyTest.start();

        controller = new AGCController(getResources().openRawResource(R.raw.aurora12));
        controller.initEngine();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dskyTest.stop();
    }
}
