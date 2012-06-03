package com.onedatapoint.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class GraphView extends ImageView {

    private float lastX = -1.f;
    private float lastY = -1.f;
    private final static float touchImprintSize = 10.f;
    private static final String LOGTAG = "GraphView";

    public GraphView(Context context) {
        super(context);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        lastX = event.getX();
        lastY = event.getY();

        Log.v(LOGTAG, "onTouchEvent " + lastX + ", " + lastY);

        invalidate();

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (lastX < 0 || lastY < 0)
            return;

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFF99CA3C);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.drawCircle(lastX, lastY, touchImprintSize, paint);
    }
}

