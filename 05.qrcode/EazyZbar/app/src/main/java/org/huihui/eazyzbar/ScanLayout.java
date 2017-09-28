package org.huihui.eazyzbar;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/9/28.
 */

public class ScanLayout extends FrameLayout implements IScanView {
    private Paint mPaint;
    private ValueAnimator mValueAnimator;
    private int mAnimatedValue;

    public ScanLayout(Context context) {
        this(context, null);
    }

    public ScanLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScanLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ScanView scanView = new ScanView(getContext());
        addView(scanView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setWillNotDraw(false);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xffff0000);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        mValueAnimator = ObjectAnimator.ofInt(0, 1, 0);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatedValue = ((int) animation.getAnimatedValue());
                invalidate();
            }
        });
        mValueAnimator.setDuration(500);
        mValueAnimator.setRepeatCount(-1);
        mValueAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mAnimatedValue != 0) {
            canvas.drawLine(getMeasuredWidth() / 4, getMeasuredHeight() / 2, getMeasuredWidth() * 3 / 4, getMeasuredHeight() / 2, mPaint);
        }
    }

    public void startScan() {
        if (!mValueAnimator.isRunning()) {
            post(new Runnable() {
                @Override
                public void run() {
                    mValueAnimator.start();
                }
            });
        }
    }

    public void stopScan() {
        post(new Runnable() {
            @Override
            public void run() {
                mValueAnimator.cancel();
                mAnimatedValue = 0;
                invalidate();
            }
        });

    }
}
