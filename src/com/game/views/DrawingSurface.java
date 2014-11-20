package com.game.views;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.game.listeners.OnGameEndListener;
import com.game.utils.ColorTool;
import com.game.utils.ConstantValues;
import com.game.utils.Logger;

public abstract class DrawingSurface extends ImageView implements OnTouchListener {

	public String TAG = getClass().getName();
	private Canvas mCanvas;
	private Path mPath;
	private Paint mPaint;
	private LinkedList<Path> paths;
	// GameEndListener instance to invoke the onGameEnd method
	protected OnGameEndListener mGameEndListener;

	// For a right path, isTouchedBlue = true, isTouchedRed = true and
	// isTouchedGreen = true when the user removes the finger from the screen.
	private boolean isTouchedBlue = false;
	private boolean isTouchedGreen = false;
	private boolean isTouchedBlack = false;

	private int tolerance = 25;

	private ImageView bottomImageView;

	public DrawingSurface(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);
		setFocusableInTouchMode(true);
		setOnTouchListener(this);
		init();
	}

	/**
	 * Initialize the view drawing objects
	 */
	private void init() {

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(ConstantValues.STROKE_WIDTH);
		mCanvas = new Canvas();
		paths = new LinkedList<Path>();
		mPath = new Path();
		paths.add(mPath);

		// Set path tracking boolean
		isTouchedBlue = false;
		isTouchedGreen = false;
		isTouchedBlack = false;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		for (Path p : paths) {
			canvas.drawPath(p, mPaint);
		}
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
		onTouchEndEvent(isTouchedBlue && isTouchedGreen && isTouchedBlack);

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
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
			touch_up();
			invalidate();
			break;
		}
		return true;

	}

	/**
	 * Get the color from the hotspot image at point x-y.
	 * 
	 */
	public int getHotspotColor(int x, int y) {

		if (bottomImageView == null) {
			Log.d(TAG, "Please set hotspot imageVeiw first");
			return 0;
		}
		bottomImageView.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(bottomImageView.getDrawingCache());
		if (hotspots == null) {
			Logger.debug(ConstantValues.APP_TAG, "Hot spot bitmap was not created.");
			return 0;
		} else {
			bottomImageView.setDrawingCacheEnabled(false);
			return hotspots.getPixel(x, y);
		}
	}

	private void setPathInfo(int x, int y) {
		int touchColor = getHotspotColor((int) x, (int) y);
		Logger.debug(TAG, "touchColor:" + touchColor);

		if (ColorTool.closeMatch(Color.GREEN, touchColor, tolerance)) {
			isTouchedGreen = true;
		} else if (ColorTool.closeMatch(Color.BLUE, touchColor, tolerance)) {
			isTouchedBlue = true;
		} else if (ColorTool.closeMatch(Color.BLACK, touchColor, tolerance)) {
			isTouchedBlack = true;
		}

	}

	public void setOnGameEndListener(OnGameEndListener gameEndListener) {
		this.mGameEndListener = gameEndListener;
	}

	public void setHotSpotImageView(ImageView hotSpotImageView) {
		this.bottomImageView = hotSpotImageView;
	}

	abstract protected void onTouchEndEvent(boolean isSuccess);

	/**
	 * Resets the view in fresh state.
	 */
	public void reset() {
		init();
		invalidate();
	}

}
