package com.onedatapoint.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.onedatapoint.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AnalogClockTimePicker extends View {
    private final static String LOGTAG = "AnalogClockTimePicker";
    private final static boolean mDebug = false;

    private Calendar mCalendar;

    private Drawable mHourHand;
    private Drawable mMinuteHand;
    private Drawable mClockHinge;
    private Drawable mDial;

    private int mMinutes;
    private int mHour;

    private float mLastX = -1.f;
    private float mLastY = -1.f;

    private SimpleDateFormat mTimeFormatter;

    private final static int mTimeTextDelta = 10;
    private float mTimeTextSize = 20;
    private int mDayTimeTextDelta = 25;
    private double mHourAngle;

    private class ClockState {
        public ClockState() {}

        public ClockState(ClockState clockState) {
            mToggledDayTime = clockState.mToggledDayTime;
            mChanged = clockState.mChanged;
            mFirstTouch = clockState.mFirstTouch;
            mIsDayTime = clockState.mIsDayTime;
        }

        private boolean mToggledDayTime;
        private boolean mChanged;
        private boolean mFirstTouch;
        private boolean mIsDayTime;
    }

    private ClockState mCurrentState;
    private ClockState mToggleDayTimeState;

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
        mHour = -1;
        mMinutes = -1;

        mToggleDayTimeState = new ClockState();
        mToggleDayTimeState.mChanged = true;
        mToggleDayTimeState.mToggledDayTime = true;

        mCurrentState = new ClockState();
        mCurrentState.mChanged = true;

        setTime(Calendar.getInstance());
    }

    public void setTime(Calendar now) {
        mCalendar = now;
        mHour = now.get(Calendar.HOUR);
        mMinutes = now.get(Calendar.MINUTE);
        mCurrentState.mIsDayTime = now.get(Calendar.AM_PM) == Calendar.AM;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        // The user touched near the AM/PM toggle
        if ((mHour != -1 && mCurrentState.mFirstTouch) && x - (mDial.getIntrinsicWidth() - mDayTimeTextDelta) <= 40
                && (y - mDayTimeTextDelta) <= 40) {

            boolean isDayTime = !mCurrentState.mIsDayTime;
            mCalendar.set(Calendar.AM_PM, isDayTime ? Calendar.AM : Calendar.PM);
            mCurrentState = new ClockState(mToggleDayTimeState);
            mCurrentState.mIsDayTime = isDayTime;

            invalidate();
            return super.onTouchEvent(event);
        }

        if (x != mLastX || y != mLastY) {
            mLastX = Math.min(x, mDial.getIntrinsicWidth());
            mLastY = Math.min(y, mDial.getIntrinsicHeight());

            double cX = getWidth() / 2;
            double cY = getHeight() / 2;
            double R = mDial.getIntrinsicWidth() / 2;

            final double pX = mLastX;
            final double pY = mLastY;

            // Find closest point on the circle to the touch point
            double vX = pX - cX;
            double vY = pY - cY;
            double magV = Math.sqrt(vX * vX + vY * vY);
            double aX = cX + vX / magV * R;
            double aY = cY + vY / magV * R;

            // Find angle between 12 o'clock and closest point to touch point
            double bX = cX;
            double bY = cY - R;

            double angle = 2 * Math.toDegrees(Math.atan2(aY - bY, aX - bX));
            if (mDebug)
                Log.v(LOGTAG, "a: " + aX + "," + aY + "; b: " + bX + "," + bY + " angle: " + mHourAngle);

            mCurrentState.mChanged = true;

            // First touch is to set the hour hand
            if (mCurrentState.mFirstTouch) {
                mHourAngle = angle;
                mHour = (int)Math.floor((12 * angle) / 360);
            } else {
                mMinutes = (int)Math.floor((60 * angle) / 360);
            }

            mCalendar.set(Calendar.HOUR, mHour);
            mCalendar.set(Calendar.MINUTE, mMinutes);

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

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        if (mCurrentState.mChanged) {
            canvas.save();
            // We could use mHourAngle here but when the minute hand is at 12 we want the hour hand to be pointing
            // to that hour hour exactly.
            canvas.rotate(mHour / 12.0f * 360.0f, (viewWidth / 2), (viewHeight / 2));
            canvas.translate((viewWidth / 2) - mHourHand.getIntrinsicWidth() / 2,
                    (viewHeight / 2) - mHourHand.getIntrinsicHeight() + (mClockHinge.getIntrinsicWidth() / 3));

            mHourHand.setBounds(0, 0,
                    mHourHand.getIntrinsicWidth(), mHourHand.getIntrinsicHeight());
            mHourHand.draw(canvas);
            canvas.restore();

            paint.setColor(Color.WHITE);
            paint.setTextSize(mTimeTextSize);

            if (!mCurrentState.mFirstTouch) {
                canvas.save();
                canvas.rotate(mMinutes / 60.0f * 360.0f, (viewWidth / 2), (viewHeight / 2));
                canvas.translate((viewWidth / 2) - mMinuteHand.getIntrinsicWidth() / 2,
                        (viewHeight / 2) - mMinuteHand.getIntrinsicHeight() + (mClockHinge.getIntrinsicWidth() / 3));

                mMinuteHand.setBounds(0, 0,
                        mMinuteHand.getIntrinsicWidth(), mMinuteHand.getIntrinsicHeight());

                mMinuteHand.draw(canvas);
                canvas.restore();
                canvas.drawText(toString(), mTimeTextDelta, viewHeight - mTimeTextDelta, paint);
                canvas.drawText(mCurrentState.mIsDayTime ? "AM" : "PM", dialIntrinsicWidth - mDayTimeTextDelta, mDayTimeTextDelta, paint);
            }

            mCurrentState.mFirstTouch = !mCurrentState.mFirstTouch;
        }

        canvas.save();
        canvas.translate((viewWidth / 2) - (mClockHinge.getIntrinsicWidth() / 2),
                         (viewHeight / 2) - (mClockHinge.getIntrinsicHeight() / 2));
        mClockHinge.setBounds(0, 0,
                mClockHinge.getIntrinsicWidth(), mClockHinge.getIntrinsicHeight());
        mClockHinge.draw(canvas);
        canvas.restore();

        if (mDebug && mCurrentState.mChanged) {
            paint.setColor(0xFF99CA3C);
            canvas.drawCircle((viewWidth - dialIntrinsicWidth) / 2 + Math.min(mLastX, dialIntrinsicWidth),
                    (viewHeight - dialIntrinsicHeight) / 2 + Math.min(mLastY, dialIntrinsicHeight),
                    10.f, paint);
        }

        mCurrentState.mChanged = false;
        mCurrentState.mToggledDayTime = false;
    }

    @Override
    public String toString() {
        if (mTimeFormatter == null)
            mTimeFormatter = new SimpleDateFormat("hh:mm");
        return mTimeFormatter.format(mCalendar.getTime());
    }
}
