package com.android.app.tools;

import java.util.Timer;
import java.util.TimerTask;

import com.example.ydym.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PiechartView extends View{
	
	private Paint paint;//����
	private RectF oval;//ͼ�δ�С
	
	public float phoSpace = 0;//�ֻ���ռ�ռ�ı���
	public float SDspace = 0;//����SD����ռ�ռ�ı���

	private  float phoAngle = 0 ;//�ֻ��ռ�ĽǶ�
	private  float SDangle = 0 ;//����SD����ռ�ռ�ĽǶ�
	
	
	
	private int phoColor = 0;//�ֻ��ڴ���ռ����ɫ
	private int SDcolor = 0;//����SD���ڴ���ռ����ɫ
	private int bgColor = 0;//������ɫ

	
	public PiechartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		
		phoColor = context.getResources().getColor(R.color.olivedrab);
		SDcolor = context.getResources().getColor(R.color.darkorange);
		bgColor = context.getResources().getColor(R.color.indianred);
	}
	/**
	 * ���ñ���ͼ���ֻ���sd�ڴ��ڿռ�����ռ�ñ���
	 * ��̬
	 * */
	
	
	
	
	
	public void setProportion(float f1,float f2){
		this.phoSpace = f1;
		this.SDspace = f2;
		Log.d("DDD", f1+"f1");
		float a = phoSpace+SDspace;
		float pho1 = phoSpace/a;
		float SD1 = SDspace/a;
		phoAngle = pho1*360;
		SDangle = SD1*360;
		// android��ʵ��view�ĸ��������鷽����һ����invalidate����һ����postInvalidate��
		// ����ǰ������UI�߳�������ʹ�ã��������ڷ�UI�߳���ʹ�á�
		postInvalidate();//ʵ��View�ĸ��£���UI�߳��У�
	}
	
	public void setLoadingProportion(float f1,float f2){
		phoSpace = f1;
		SDspace = f2;
		
		float a = phoSpace+SDspace;
		float pho1 = phoSpace/a;
		float SD1 = SDspace/a;
		
		final float phoneTargetAngle = pho1*360;
		final float sdTargetAngle = SD1*360;
		
		final Timer timer = new Timer();
		final TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				phoAngle += 4;
				SDangle += 4;
				postInvalidate();
				if (phoAngle >= phoneTargetAngle) {
					phoAngle = phoneTargetAngle;
				}
				if (SDangle >= sdTargetAngle) {
					SDangle = sdTargetAngle;
				}
				if (phoAngle == phoneTargetAngle
						&& SDangle == sdTargetAngle) {
					timer.cancel();
				}
				
			}
		};
		// 1ִ�еĶ��� 2 �ӳ�ִ�е�ʱ�䣨����һ��ִ�е��ӳ� 3 ÿ�����ִ��һ��
		timer.schedule(timerTask, 26, 26);
		
	}
	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int widths = MeasureSpec.getSize(widthMeasureSpec);
		int heigths = MeasureSpec.getSize(heightMeasureSpec);
		
		oval = new RectF(0, 0, widths, heigths);
		
		// �洢�ؼ������ĵĿ��ֵ
		setMeasuredDimension(widths, heigths);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setAntiAlias(true);//ȡ�����
		
		paint.setColor(bgColor);
		canvas.drawArc(oval, -90, 360, true, paint);
		
		paint.setColor(phoColor);
		canvas.drawArc(oval, -90, phoAngle, true, paint);
		paint.setColor(SDcolor);
		canvas.drawArc(oval, -90+phoAngle, SDangle, true, paint);
		
	}

}
