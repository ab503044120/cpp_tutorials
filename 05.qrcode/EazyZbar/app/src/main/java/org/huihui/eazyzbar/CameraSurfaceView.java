package org.huihui.eazyzbar;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/9/28.
 */

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback, DecoderHander.GetResultListener {
    private DecoderHander mDecoderHander;
    private IScanView mUpWithScanView;

    public CameraSurfaceView(Context context) {
        this(context, null);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        CameraInterface.getInstance().doOpenCamera(new CameraInterface.CamOpenOverCallback() {
            @Override
            public void cameraHasOpened() {
                CameraInterface.getInstance().doStartPreview(holder, 16.0f / 9.0f, CameraSurfaceView.this);
                mDecoderHander = new DecoderHander(CameraSurfaceView.this);
            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mDecoderHander.release();
        CameraInterface.getInstance().doStopCamera();
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        camera.setPreviewCallback(null);
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size size = parameters.getPreviewSize();
        mDecoderHander.sendMsg(data, size.width, size.height);
    }

    @Override
    public void onGetetResult(final DecoderHander.Result result) {
        if (result != null) {
            if (mUpWithScanView != null) {
                mUpWithScanView.stopScan();
                mUpWithScanView.showResult(result.mBitmap);
            }

            post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), result.result, Toast.LENGTH_SHORT).show();
                }
            });
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    resumeScan();
                }
            }, 3000);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    resumeScan();
                }
            });
        }
    }

    private void resumeScan() {
        if (mUpWithScanView != null) {
            mUpWithScanView.startScan();
        }
        CameraInterface.getInstance().resumePreview(CameraSurfaceView.this);
    }

    public void setUpWithScanView(IScanView upWithScanView) {
        mUpWithScanView = upWithScanView;
    }
}
