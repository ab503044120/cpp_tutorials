package org.huihui.eazyzbar;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.TextureView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/9/27.
 */

public class CameraView extends TextureView implements TextureView.SurfaceTextureListener, Camera.PreviewCallback, DecoderHander.GetResultListener {
    private SurfaceTexture mSurface;
    private DecoderHander mDecoderHander;

    public CameraView(Context context) {
        this(context, null);
    }

    public CameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setSurfaceTextureListener(this);
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mSurface = surface;
        CameraInterface.getInstance().doOpenCamera(new CameraInterface.CamOpenOverCallback() {
            @Override
            public void cameraHasOpened() {
                CameraInterface.getInstance().doStartPreview(mSurface, 16.0f / 9.0f, CameraView.this);
                mDecoderHander = new DecoderHander(CameraView.this);
            }
        });
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mSurface = null;
        CameraInterface.getInstance().doStopCamera();
        mDecoderHander.release();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        mSurface = surface;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size size = parameters.getPreviewSize();
        camera.setPreviewCallback(null);
        mDecoderHander.sendMsg(data, size.width, size.height);
    }

    @Override
    public void onGetetResult(final String result) {
        if (result != null) {
            post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                }
            });
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    CameraInterface.getInstance().resumePreview(CameraView.this);
                }
            }, 1000);
        } else {
            CameraInterface.getInstance().resumePreview(this);
        }

    }
}
