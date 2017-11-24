package org.huihui.pthread;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by huihui on 2017/11/20.
 */

public class BootBroadCastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("pthread", "BootBroadCastReciever收到了开机广播" +context.getApplicationContext().toString()+ (context.getApplicationContext()==PthreadApplication.mPthreadApplication));
    }
}
