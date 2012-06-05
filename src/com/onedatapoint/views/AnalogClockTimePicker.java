package com.onedatapoint.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.onedatapoint.R;

public class AnalogClockTimePicker extends View {
    private Time mCalendar;

    private Drawable mHourHand;
    private Drawable mMinuteHand;
    private Drawable mClockHinge;
    private Drawable mDial;

    private int mDialWidth;
    private int mDialHeight;
    private float mMinutes;
    private float mHour;

    private float mLastX = -1.f;
    private float mLastY = -1.f;

    private boolean mChanged;

    public AnalogClockTimePicker(Context context) {
        this(context, null);
    }

    public AnalogClockTimePicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnalogClockTimePicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Resources resources = context.getResources();

        mHourHand = resources.getDrawable(R.drawable.clock_short_hand);
        mMinuteHand = resources.getDrawable(R.drawable.clock_long_hand);
        mClockHinge = resources.getDrawable(R.drawable.clock_hinge);
        mDial = resources.getDrawable(R.drawable.clockface);

        mCalendar = new Time();
        mDialWidth = mDial.getIntrinsicWidth();
        mDialHeight = mDial.getIntrinsicHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (x != mLastX || y != mLastY) {
            mLastX = x;
            mLastY = y;
            invalidate();
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int dialIntrinsicWidth = mDial.getIntrinsicWidth();
        final int dialIntrinsicHeight = mDial.getIntrinsicHeight();

        final int viewWidth = getWidth();
        final int viewHeight = getWidth();

        mDial.setBounds(viewWidth - dialIntrinsicHeight, viewHeight - dialIntrinsicHeight,
                        dialIntrinsicWidth, dialIntrinsicHeight);
        mDial.draw(canvas);

        if (mLastX < 0 || mLastY < 0)
            return;

        mHourHand.setBounds(viewWidth / 2, viewHeight / 2,
                mHourHand.getIntrinsicWidth(), mHourHand.getIntrinsicHeight());
        mHourHand.draw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFF99CA3C);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.drawCircle((viewWidth - dialIntrinsicWidth) / 2 + Math.min(mLastX, dialIntrinsicWidth),
                          (viewHeight - dialIntrinsicHeight) / 2 + Math.min(mLastY, dialIntrinsicHeight),
                          10.f, paint);

//        mClockHinge.setBounds(viewWidth / 2, viewHeight / 2,
//                              mClockHinge.getIntrinsicWidth(), mClockHinge.getIntrinsicHeight());
//        mClockHinge.draw(canvas);

//        boolean changed = mChanged;
//        if (changed) {
//            mChanged = false;
//        }
//        int availableWidth = getRight() - getLeft();
//        int availableHeight = getBottom() - getTop();
//
//        int x = availableWidth / 2;
//        int y = availableHeight / 2;
//
//        final Drawable dial = mDial;
//        int w = dial.getIntrinsicWidth();
//        int h = dial.getIntrinsicHeight();
//
//        boolean scaled = false;
//
//        if (availableWidth < w || availableHeight < h) {
//            scaled = true;
//            float scale = Math.min((float) availableWidth / (float) w,
//                    (float) availableHeight / (float) h);
//            canvas.save();
//            canvas.scale(scale, scale, x, y);
//        }
//
//        if (changed) {
//            dial.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
//        }
//
//        dial.draw(canvas);
//
//        canvas.save();
//        canvas.rotate(mHour / 12.0f * 360.0f, x, y);
//
//        final Drawable hourHand = mHourHand;
//        if (changed) {
//            w = hourHand.getIntrinsicWidth();
//            h = hourHand.getIntrinsicHeight();
//            hourHand.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
//        }
//        hourHand.draw(canvas);
//        canvas.restore();
//
//        canvas.save();
//        canvas.rotate(mMinutes / 60.0f * 360.0f, x, y);
//
//        final Drawable minuteHand = mMinuteHand;
//        if (changed) {
//            w = minuteHand.getIntrinsicWidth();
//            h = minuteHand.getIntrinsicHeight();
//            minuteHand.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
//        }
//        minuteHand.draw(canvas);
//        canvas.restore();
//
//        canvas.save();
//        final Drawable clockHinge = mClockHinge;
//        if (changed) {
//            w = clockHinge.getIntrinsicWidth();
//            h = clockHinge.getIntrinsicHeight();
//            clockHinge.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
//        }
//        clockHinge.draw(canvas);
//        canvas.restore();
//
//        if (scaled) {
//            canvas.restore();
//        }
    }
}
