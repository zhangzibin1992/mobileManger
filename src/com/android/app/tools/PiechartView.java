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
	
	private Paint paint;//画笔
	private RectF oval;//图形大小
	
	public float phoSpace = 0;//手机所占空间的比例
	public float SDspace = 0;//外置SD卡所占空间的比例

	private  float phoAngle = 0 ;//手机空间的角度
	private  float SDangle = 0 ;//外置SD卡所占空间的角度
	
	
	
	private int phoColor = 0;//手机内存所占的颜色
	private int SDcolor = 0;//外置SD卡内存所占的颜色
	private int bgColor = 0;//背景颜色

	
	public PiechartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		
		phoColor = context.getResources().getColor(R.color.olivedrab);
		SDcolor = context.getResources().getColor(R.color.darkorange);
		bgColor = context.getResources().getColor(R.color.indianred);
	}
	/**
	 * 设置饼形图，手机和sd内存在空间中所占得比例
	 * 静态
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
		// android中实现view的更新有两组方法，一组是invalidate，另一组是postInvalidate，
		// 其中前者是在UI线程自身中使用，而后者在非UI线程中使用。
		postInvalidate();//实现View的更新（非UI线程中）
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
		// 1执行的动作 2 延迟执行的时间（）第一次执行的延迟 3 每隔多久执行一次
		timer.schedule(timerTask, 26, 26);
		
	}
	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int widths = MeasureSpec.getSize(widthMeasureSpec);
		int heigths = MeasureSpec.getSize(heightMeasureSpec);
		
		oval = new RectF(0, 0, widths, heigths);
		
		// 存储控件测量的的宽高值
		setMeasuredDimension(widths, heigths);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setAntiAlias(true);//取消锯齿
		
		paint.setColor(bgColor);
		canvas.drawArc(oval, -90, 360, true, paint);
		
		paint.setColor(phoColor);
		canvas.drawArc(oval, -90, phoAngle, true, paint);
		paint.setColor(SDcolor);
		canvas.drawArc(oval, -90+phoAngle, SDangle, true, paint);
		
	}

}
