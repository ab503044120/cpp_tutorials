package org.huihui.pthread;

import android.app.Application;
import android.util.Log;

/**
 * Created by huihui on 2017/11/20.
 */

public class PthreadApplication extends Application {
    public static PthreadApplication mPthreadApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mPthreadApplication = this;
        Log.e("pthread", "application启动了");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e("pthread", "application销毁了");
    }
}
