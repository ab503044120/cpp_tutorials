package org.huihui.eazyzbar;

import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */

public class CameraInterface {
    private Camera mCamera;
    private Camera.Parameters mParams;
    private boolean isPreviewing = false;
    private float mPreviwRate = -1f;
    private static CameraInterface mCameraInterface;

    public interface CamOpenOverCallback {
        public void cameraHasOpened();
    }

    private CameraInterface() {

    }

    public static synchronized CameraInterface getInstance() {
        if (mCameraInterface == null) {
            mCameraInterface = new CameraInterface();
        }
        return mCameraInterface;
    }

    /**
     * 打开Camera
     *
     * @param callback
     */
    public void doOpenCamera(CamOpenOverCallback callback) {
        mCamera = Camera.open();
        if (callback != null) {
            callback.cameraHasOpened();
        }

    }

    public void doStartPreview(SurfaceHolder holder, float previewRate, Camera.PreviewCallback previewCallback) {
        if (isPreviewing) {
            mCamera.stopPreview();
            return;
        }
        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            initCamera(previewRate);
            mCamera.setPreviewCallback(previewCallback);
        }


    }

    /**
     * 使用TextureView预览Camera
     *
     * @param surface
     * @param previewRate
     */
    public void doStartPreview(SurfaceTexture surface, float previewRate, Camera.PreviewCallback previewCallback) {
        if (isPreviewing) {
            mCamera.stopPreview();
            return;
        }
        if (mCamera != null) {
            try {
                mCamera.setPreviewTexture(surface);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            initCamera(previewRate);
            mCamera.setPreviewCallback(previewCallback);
        }


    }

    private void initCamera(float previewRate) {
        if (mCamera != null) {

            mParams = mCamera.getParameters();
            mParams.setPictureFormat(PixelFormat.JPEG);//设置拍照后存储的图片格式
            //设置PreviewSize和PictureSize
            Camera.Size pictureSize = CamParaUtil.getInstance().getPropPictureSize(
                    mParams.getSupportedPictureSizes(), previewRate, 800);
            mParams.setPictureSize(pictureSize.width, pictureSize.height);
            Camera.Size previewSize = CamParaUtil.getInstance().getPropPreviewSize(
                    mParams.getSupportedPreviewSizes(), previewRate, 800);
            mParams.setPreviewSize(previewSize.width, previewSize.height);

            mCamera.setDisplayOrientation(90);

            List<String> focusModes = mParams.getSupportedFocusModes();
            if (focusModes.contains("continuous-video")) {
                mParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }
            mCamera.setParameters(mParams);
            mCamera.startPreview();//开启预览


            isPreviewing = true;
            mPreviwRate = previewRate;

            mParams = mCamera.getParameters(); //重新get一次
        }
    }

    public void stopPreview() {
        if (null != mCamera) {
            try {
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();
                isPreviewing = false;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void resumePreview(Camera.PreviewCallback previewCallback) {
        if (null != mCamera) {
            //因为这里和doStopCamera可能不在一个线程执行,如果同时执行会报Camera is release的错误,所以这里trycatch
            //同时注意最好resumePreview和 dostropCamera在同一个线程中执行
            try {
                mCamera.setPreviewCallback(previewCallback);
                mCamera.startPreview();
                isPreviewing = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 停止预览，释放Camera
     */
    public void doStopCamera() {
        if (null != mCamera) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            isPreviewing = false;
            mPreviwRate = -1f;
            mCamera.release();
            mCamera = null;
            mCameraInterface = null;
        }
    }

}
