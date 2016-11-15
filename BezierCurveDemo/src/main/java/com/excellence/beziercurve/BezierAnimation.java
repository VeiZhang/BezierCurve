package com.excellence.beziercurve;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static android.R.attr.start;

/**
 * Created by ZhangWei on 2016/11/15.
 */

public class BezierAnimation {
	
	/**
	 *
	 * @param startView
	 *            动画的起点位置
	 * @param endView
	 *            动画的终点位置
	 * @param context
	 * @param rl
	 *            父窗体 用于添加的动画的View
	 * @param time
	 *            动画持续时间单位s
	 */
	
	public static void AddToCart(ImageView startView, View endView, Context context, final RelativeLayout rl, int time) {
		final ImageView animImage = new ImageView(context);
		animImage.setImageDrawable(startView.getDrawable());
		
		RelativeLayout.LayoutParams animParams = new RelativeLayout.LayoutParams(startView.getWidth(), startView.getHeight());
		rl.addView(animImage, animParams);

		// 计算父控件的位置
		int[] parent = new int[2];
		rl.getLocationInWindow(parent);
		
		// 计算起点控件位置
		int[] startLocation = new int[2];
		startView.getLocationInWindow(startLocation);
		
		// 计算终点控件位置
		int[] endLocation = new int[2];
		endView.getLocationInWindow(endLocation);

		PointF startP = new PointF(startLocation[0] - parent[0], startLocation[1] - parent[1]);
		PointF endP = new PointF(endLocation[0] - parent[0] - startView.getWidth() / 2 + endView.getWidth() / 2, endLocation[1] - parent[1] - startView.getHeight() / 2 + endView.getHeight() / 2);
		PointF controlP = new PointF(endP.x, startP.y);

		ValueAnimator animator = new ValueAnimator();
		animator.setObjectValues(startP, endP);
		animator.setEvaluator(new PointFTypeEvaluator(controlP));
		animator.setDuration(time * 1000);
		animator.start();
		
		// 动画绘制过程中
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				PointF pointF = (PointF) animation.getAnimatedValue();
				animImage.setTranslationX(pointF.x);
				animImage.setTranslationY(pointF.y);
			}
		});
		
		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				rl.removeView(animImage);
			}
		});
	}
	
	private static class PointFTypeEvaluator implements TypeEvaluator<PointF> {
		private PointF mControlP = null;
		
		public PointFTypeEvaluator(PointF controlP) {
			mControlP = controlP;
		}
		
		@Override
		public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
			return getBezierPonit(startValue, endValue, mControlP, fraction);
		}
		
		private PointF getBezierPonit(PointF start, PointF end, PointF control, float t) {
			PointF pointF = new PointF();
			pointF.x = (1 - t) * (1 - t) * start.x + 2 * t * (1 - t) * control.x + t * t * end.x;
			pointF.y = (1 - t) * (1 - t) * start.y + 2 * t * (1 - t) * control.y + t * t * end.y;
			return pointF;
		}
	}
}
