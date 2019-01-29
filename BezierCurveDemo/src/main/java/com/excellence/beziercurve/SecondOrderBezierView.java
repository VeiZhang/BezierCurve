package com.excellence.beziercurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ZhangWei on 2016/11/15.
 */

public class SecondOrderBezierView extends View {
	
	private Paint	mPaint	= null;
	private int		centerX;
	private int		centerY;
	
	private PointF	start;
	private PointF	end;
	private PointF	control;
	
	public SecondOrderBezierView(Context context) {
		this(context, null);
	}
	
	public SecondOrderBezierView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init();
	}
	
	private void init() {
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setStrokeWidth(8);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setTextSize(60);
		
		start = new PointF(0, 0);
		end = new PointF(0, 0);
		control = new PointF(0, 0);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		centerX = w / 2;
		centerY = h / 2;
		
		// 根据控件的长宽初始化数据点和控制点
		start.x = centerX - 200;
		start.y = centerY;
		end.x = centerX + 400;
		end.y = centerY - 400;
		control.x = centerX;
		control.y = centerY - 100;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		control.x = event.getX();
		control.y = event.getY();
		invalidate();
		return true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// 绘制起始、终止数据点
		mPaint.setStrokeWidth(20);
		mPaint.setColor(Color.GREEN);
		canvas.drawPoint(start.x, start.y, mPaint);
		canvas.drawPoint(end.x, end.y, mPaint);
		
		// 绘制控制点
		mPaint.setColor(Color.RED);
		canvas.drawPoint(control.x, control.y, mPaint);
		
		// 绘制辅助线
		mPaint.setStrokeWidth(4);
		mPaint.setColor(Color.GRAY);
		canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
		canvas.drawLine(end.x, end.y, control.x, control.y, mPaint);

        //绘制贝塞尔曲线
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.quadTo(control.x, control.y, end.x, end.y);
        canvas.drawPath(path, mPaint);
	}
}
