package org.huihui.eazyzbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

/**
 * Created by Administrator on 2017/9/27.
 */

public class DecoderHander {
    private final Handler mHandler;
    private final ImageScanner scanner;
    private final GetResultListener mGetResultListener;
    private HandlerThread mDecode;

    public DecoderHander(GetResultListener getResultListener) {
        mGetResultListener = getResultListener;
        mDecode = new HandlerThread("decode");
        mDecode.start();
        mHandler = new Handler(mDecode.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                DecoderHander.this.handleMessage(msg);
            }
        };
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);
    }

    public void handleMessage(Message msg) {
        Bundle bundle = msg.getData();
        if (bundle != null) {
            byte[] data = bundle.getByteArray("data");
            int width = bundle.getInt("width");
            int height = bundle.getInt("height");
            Image barcode = new Image(width, height, "Y800");
            barcode.setData(data);
            barcode.setCrop(width / 2 - height / 4, height / 4, height / 2, height / 2);
            long l = System.currentTimeMillis();
            int result = scanner.scanImage(barcode);
            Log.e("Time", System.currentTimeMillis() - l + "");
            if (result != 0) {
                CameraInterface.getInstance().stopPreview();
                SymbolSet syms = scanner.getResults();
                for (Symbol sym : syms) {
                    if (mGetResultListener != null) {
                        mGetResultListener.onGetetResult(sym.getData());
                    }
                }
            } else {
                if (mGetResultListener != null) {
                    mGetResultListener.onGetetResult(null);
                }
            }
        }

    }

    public Handler getHandler() {
        return mHandler;
    }

    public void sendMsg(byte[] data, int width, int height) {
        Message message = mHandler.obtainMessage();
        Bundle data1 = new Bundle();
        message.setData(data1);
        data1.putByteArray("data", data);
        data1.putInt("width", width);
        data1.putInt("height", height);
        mHandler.sendMessage(message);
    }


    public void release() {
        mHandler.removeCallbacksAndMessages(null);
    }

    public interface GetResultListener {
        void onGetetResult(String result);
    }
}
