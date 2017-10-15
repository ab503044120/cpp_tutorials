package org.huihui.eazyzbar;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/9/28.
 */

public interface IScanView {
    public void stopScan();

    public void startScan();

    public void showResult(Bitmap bitmap);
}
