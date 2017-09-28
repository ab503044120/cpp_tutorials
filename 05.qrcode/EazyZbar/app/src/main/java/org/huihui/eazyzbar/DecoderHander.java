package org.huihui.eazyzbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

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
            int result = scanner.scanImage(barcode);
            if (result != 0) {
                CameraInterface.getInstance().stopPreview();
                SymbolSet syms = scanner.getResults();
                for (Symbol sym : syms) {
                    if (mGetResultListener != null) {
                        Result result1 = new Result();
//                        BitmapFactory.Options options = new BitmapFactory.Options();
//                        options.inPreferredConfig = Bitmap.Config.RGB_565;
//                        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//                        newOpts.inJustDecodeBounds = true;
//                        YuvImage yuvimage = new YuvImage(
//                                data,
//                                ImageFormat.NV21,
//                                width,
//                                height,
//                                null);
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        yuvimage.compressToJpeg(new Rect(0, 0, width, height), 100, baos);// 80--JPG图片的质量[0-100],100最高
//                        byte[] rawImage = baos.toByteArray();
//                        //将rawImage转换成bitmap
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length, options);
//                        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, width / 2 - height / 4, height / 4, height / 2, height / 2);
//                        result1.mBitmap = bitmap1;
                        result1.result = sym.getData();
                        mGetResultListener.onGetetResult(result1);
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
        scanner.destroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    public interface GetResultListener {
        void onGetetResult(Result result);
    }

    public static class Result {
        public String result;
        Bitmap mBitmap;
    }
}
