package com.onedatapoint.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class GraphView extends ImageView {

    private float lastX;
    private float lastY;
    private final static float touchImprintSize = 4.f;
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

        BitmapDrawable bmd = (BitmapDrawable) getDrawable();
        Bitmap bm = bmd.getBitmap();

        Bitmap bitmapResult = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmapResult);
        c.drawBitmap(bm, getLeft(), getTop(), new Paint());

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setARGB(200, 0, 0, 200);
        paint.setStyle(Paint.Style.STROKE);

        c.drawCircle(lastX, lastY, touchImprintSize, paint);

        setImageBitmap(bitmapResult);
        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.v(LOGTAG, "onDraw");
        Paint paint = new Paint();
        canvas.drawCircle(lastX, lastY, touchImprintSize, paint);
        super.onDraw(canvas);
    }
}
