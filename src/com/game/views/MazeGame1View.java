package com.game.views;

import java.util.LinkedList;

import com.game.utils.UtilityMethods;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MazeGame1View extends ImageView implements OnTouchListener{

	private Canvas  mCanvas;
	private Path    mPath;
	private Paint       mPaint;   
	private LinkedList<Path> paths = new LinkedList<Path>();
	  public MazeGame1View(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    setFocusable(true);
	    setFocusableInTouchMode(true);
	    setOnTouchListener(this);
	    mPaint = new Paint();
	    mPaint.setAntiAlias(true);
	    mPaint.setDither(true);
	    mPaint.setColor(Color.RED);
	    mPaint.setStyle(Paint.Style.STROKE);
	    mPaint.setStrokeJoin(Paint.Join.ROUND);
	    mPaint.setStrokeCap(Paint.Cap.ROUND);
	    mPaint.setStrokeWidth(6);
	    mCanvas = new Canvas();
	    mPath = new Path();
	    paths.add(mPath);
	    
	    
	    
	  }
	  @Override
	    protected void onDraw(Canvas canvas) {            
		  super.onDraw(canvas);
	        for (Path p : paths){
	            canvas.drawPath(p, mPaint);
	        }
	        
	        
	    }

	  @Override
	  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

	      int desiredWidth = 550;
	      int desiredHeight = 250;

	      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	      int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	      int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	      int heightSize = MeasureSpec.getSize(heightMeasureSpec);
	      
	      int ww = UtilityMethods.getPixelsFromDp(getContext(), 550);
	      int hh = UtilityMethods.getPixelsFromDp(getContext(), 250);

	      int width;
	      int height;

	      //Measure Width
	      if (widthMode == MeasureSpec.EXACTLY) {
	          //Must be this size
	          width = widthSize;
	      } else if (widthMode == MeasureSpec.AT_MOST) {
	          //Can't be bigger than...
	          width = Math.min(desiredWidth, widthSize);
	      } else {
	          //Be whatever you want
	          width = desiredWidth;
	      }

	      //Measure Height
	      if (heightMode == MeasureSpec.EXACTLY) {
	          //Must be this size
	          height = heightSize;
	      } else if (heightMode == MeasureSpec.AT_MOST) {
	          //Can't be bigger than...
	          height = Math.min(desiredHeight, heightSize);
	      } else {
	          //Be whatever you want
	          height = desiredHeight;
	      }

	      //MUST CALL THIS
	      //setMeasuredDimension(width, height);
	      setMeasuredDimension(ww, hh);
	  }
	  
	  
	    private float mX, mY;
	    private static final float TOUCH_TOLERANCE = 4;

	    private void touch_start(float x, float y) {
	        mPath.reset();
	        mPath.moveTo(x, y);
	        mX = x;
	        mY = y;
	    }
	    private void touch_move(float x, float y) {
	        float dx = Math.abs(x - mX);
	        float dy = Math.abs(y - mY);
	        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
	            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
	            mX = x;
	            mY = y;
	        }
	    }
	    private void touch_up() {
	        mPath.lineTo(mX, mY);
	        // commit the path to our offscreen
	        mCanvas.drawPath(mPath, mPaint);
	        // kill this so we don't double draw            
	        mPath = new Path();
	        paths.add(mPath);
	    }



	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
	      float x = event.getX();
	      float y = event.getY();

	      switch (event.getAction()) {
	          case MotionEvent.ACTION_DOWN:
	              touch_start(x, y);
	              invalidate();
	              break;
	          case MotionEvent.ACTION_MOVE:
	              touch_move(x, y);
	              invalidate();
	              break;
	          case MotionEvent.ACTION_UP:
	              touch_up();
	              invalidate();
	              break;
	      }
	      return true;
	}
	  
}
