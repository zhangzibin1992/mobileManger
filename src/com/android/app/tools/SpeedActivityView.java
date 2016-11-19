package com.android.app.tools;

import java.util.Timer;
import java.util.TimerTask;

import com.example.ydym.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class SpeedActivityView extends View{
	
	private Paint paint = new Paint();
	private RectF oval;
	
	private final int START_ANGLE = -90;//��ʼ�Ƕ�
	private int endAngle = 0;//�����Ƕ�
	
	private int state = 0;//״ֵ̬��0������ʱ����ת��1����˳ʱ����ת
	
	private boolean isRunning = false;//�Ƿ�����
	
	private int color = 0;
	
	
	//�����ٶ�
	private int[] back = new int[]{-6,-6,-10,-10,-12};
	private int backIndex;
	
	private int[] goon = new int[]{12,12,12,10,10,8,8,6,6};
	private int goonIndex;

	public SpeedActivityView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setAngle(360);
		color = context.getResources().getColor(R.color.orange);
	}
	
	private void setAngle(final int angle){
		endAngle = angle;
		postInvalidate();
		isRunning = false;
	}
	
	public void setAngleWithAnim(final int angle){
		if(isRunning){
			return;
		}
		isRunning = true;
		state = 0;
		
		final Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				switch (state) {
				case 0:
					endAngle +=back[backIndex++];
					if(backIndex>=back.length){
						backIndex=back.length-1;
					}
					postInvalidate();
					if(endAngle<=0){
						endAngle = 0;
						state = 1;
						backIndex = 0;
					}
					
					break;
				case 1:
					endAngle+=goon[goonIndex++];
					if(goonIndex>=goon.length){
						goonIndex = goon.length-1;
					}
					postInvalidate();
					if(endAngle>=angle){
						endAngle = angle;
						timer.cancel();
						goonIndex = 0;
						isRunning = false;
					}
					break;
					
					

				default:
					break;
				}
			}
		};
		
		timer.schedule(timerTask, 24, 24);
		
		
		
		
	}
	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
		
		int widths = MeasureSpec.getSize(widthMeasureSpec);
		int heigths = MeasureSpec.getSize(heightMeasureSpec);
		
		oval=new RectF(0, 0, widths, heigths);
		setMeasuredDimension(widths, heigths);
		
		
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		paint.setAntiAlias(true);
		paint.setColor(color);
		canvas.drawArc(oval, START_ANGLE, endAngle, true, paint);
		
		
		
	}
}
