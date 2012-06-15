package com.onedatapoint.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import com.onedatapoint.R;
import com.onedatapoint.model.Question;
import com.onedatapoint.model.XYQuestion;

public class GraphView extends ImageView {

    private float lastX = -1.f;
    private float lastY = -1.f;
    private final static float touchImprintSize = 10.f;
    private static final String LOGTAG = "GraphView";
    private String mXLabel;
    private String mYLabel;
    private String mDescription;
    private Activity mActivity;

    public GraphView(Context context) {
        super(context);
        mActivity = (Activity)context;
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mActivity = (Activity)context;
    }

    public GraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mActivity = (Activity)context;
    }

    public void setQuestion(XYQuestion question) {
        mXLabel = question.getXLabel();
        mYLabel = question.getYLabel();
        mDescription = question.getDescription();
        TextView xView = (TextView)mActivity.findViewById(R.id.graphXLabel);
        TextView yView = (TextView)mActivity.findViewById(R.id.graphYLabel);
        TextView description = (TextView)mActivity.findViewById(R.id.graphDescription);

        xView.setText(mXLabel);
        yView.setText(mYLabel);
        description.setText(mDescription);
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

