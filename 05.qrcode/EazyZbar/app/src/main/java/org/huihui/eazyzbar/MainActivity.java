package org.huihui.eazyzbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/9/27.
 */

public class MainActivity extends AppCompatActivity {
    private CameraSurfaceView cv;
    private ScanLayout sl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cv = (CameraSurfaceView) findViewById(R.id.cv);
        sl = (ScanLayout) findViewById(R.id.sl);
        cv.setUpWithScanView(sl);
    }
}
