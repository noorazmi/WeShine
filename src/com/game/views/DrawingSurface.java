package com.game.views;

import java.util.LinkedList;

import com.game.utils.ColorTool;
import com.game.utils.ConstantValues;
import com.game.utils.Logger;
import com.game.weshine.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public abstract class DrawingSurface extends ImageView implements OnTouchListener {

	public String TAG = getClass().getName();
	private Canvas mCanvas;
	private Path mPath;
	private Paint mPaint;
	private LinkedList<Path> paths = new LinkedList<Path>();

	// first Touch point of the user
	private Point mStartPoint;

	// Last point when the user separated his finger from the screen.
	private Point mEndPoint;

	// For a right path, isTouchedBlue = true, isTouchedRed = false and
	// isTouchedGreen = true at the end of the user finger drag.
	private boolean isTouchedBlue = false;
	//private boolean isTouchedRed = false;
	private boolean isTouchedGreen = false;

	private int tolerance = 25;

	// Note : after isTouchedBlue = true and before isTouchedGreen = true, any
	// time if isTouchedRed value becomed isTouchedRed = true the isRightPath
	// will always be
	// isRightPath = false
	private boolean isRightPath = true;

	public DrawingSurface(Context context, AttributeSet attrs) {
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

		mStartPoint = new Point(-1, -1);
		mEndPoint = new Point(-1, -1);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (Path p : paths) {
			canvas.drawPath(p, mPaint);
		}
	}

	// @Override
	// protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	//
	// int desiredWidth = 550;
	// int desiredHeight = 250;
	//
	// int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	// int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	// int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	// int heightSize = MeasureSpec.getSize(heightMeasureSpec);
	//
	// int ww = UtilityMethods.getPixelsFromDp(getContext(), 550);
	// int hh = UtilityMethods.getPixelsFromDp(getContext(), 250);
	//
	// int width;
	// int height;
	//
	// // Measure Width
	// if (widthMode == MeasureSpec.EXACTLY) {
	// // Must be this size
	// width = widthSize;
	// } else if (widthMode == MeasureSpec.AT_MOST) {
	// // Can't be bigger than...
	// width = Math.min(desiredWidth, widthSize);
	// } else {
	// // Be whatever you want
	// width = desiredWidth;
	// }
	//
	// // Measure Height
	// if (heightMode == MeasureSpec.EXACTLY) {
	// // Must be this size
	// height = heightSize;
	// } else if (heightMode == MeasureSpec.AT_MOST) {
	// // Can't be bigger than...
	// height = Math.min(desiredHeight, heightSize);
	// } else {
	// // Be whatever you want
	// height = desiredHeight;
	// }
	//
	// // MUST CALL THIS
	// // setMeasuredDimension(width, height);
	// setMeasuredDimension(ww, hh);
	// }

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
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
		setPathInfo((int) x, (int) y);
	}

	private void touch_up() {
		mPath.lineTo(mX, mY);
		// commit the path to our offscreen
		mCanvas.drawPath(mPath, mPaint);
		// kill this so we don't double draw
		mPath = new Path();
		paths.add(mPath);
		onTouchEndEvent(isTouchedBlue && isTouchedGreen && isRightPath);
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			mStartPoint.set((int) x, (int) y);
			Log.d(ConstantValues.APP_TAG, "ACTION_DOWN: x:" + x + " y:" + y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(ConstantValues.APP_TAG, "ACTION_MOVE: x:" + x + " y:" + y);
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			Log.d(ConstantValues.APP_TAG, "ACTION_UP: x:" + x + " y:" + y);
			mEndPoint.set((int) x, (int) y);
			touch_up();
			invalidate();
			break;
		}
		return true;

	}

	/**
	 * Returns the starting point of touch
	 * 
	 * @return Point
	 */
	public Point getTouchStartPoint() {
		return mStartPoint;
	}

	/**
	 * Returns the point where the user pull his figer from the screen
	 * 
	 * @return Point
	 */
	public Point getTouchEndPoint() {
		return mEndPoint;
	}

	/**
	 * Get the color from the hotspot image at point x-y.
	 * 
	 */
	public int getHotspotColor(int x, int y) {

		setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(getDrawingCache());
		if (hotspots == null) {
			Logger.debug(ConstantValues.APP_TAG, "Hot spot bitmap was not created");
			return 0;
		} else {
			setDrawingCacheEnabled(false);
			return hotspots.getPixel(x, y);
		}
	}

	private void setPathInfo(int x, int y) {
		
		
		
//		if (isRightPath == false) {
//			return;
//		}
		int touchColor = getHotspotColor((int) x, (int) y);
		Logger.debug(TAG, "touchColor:"+touchColor);
		
		if (ColorTool.closeMatch(Color.RED, touchColor, tolerance)) {
			if(isTouchedBlue == true){
				isRightPath = false;
			}
			//isTouchedRed = true;
			Logger.debug(ConstantValues.APP_TAG, "Touch color RED"+" x= "+x +" y="+y);
		} else if (ColorTool.closeMatch(Color.GREEN, touchColor, tolerance)) {
			isTouchedGreen = true;
			Logger.debug(ConstantValues.APP_TAG, "Touch color GREEN");
		} else if (ColorTool.closeMatch(Color.BLUE, touchColor, tolerance)) {
			Logger.debug(ConstantValues.APP_TAG, "Touch color BLUE");
			isTouchedBlue = true;
		}

	}

	abstract protected void onTouchEndEvent(boolean isSuccess);
}
