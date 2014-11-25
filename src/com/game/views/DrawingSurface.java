package com.game.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.game.app.WeShineApp;
import com.game.listeners.OnGameEndListener;
import com.game.pojo.FloatPoint;
import com.game.utils.ColorTool;
import com.game.utils.ConstantValues;
import com.game.utils.Logger;
import com.game.utils.UtilityMethods;

public abstract class DrawingSurface extends ImageView implements OnTouchListener {

	public String TAG = getClass().getName();
	private Canvas mCanvas;
	private Path mPath;
	private Paint mPaint;
	// private LinkedList<Path> paths;
	// GameEndListener instance to invoke the onGameEnd method
	protected OnGameEndListener mGameEndListener;

	// For a right path, isTouchedBlue = true, isTouchedRed = true and
	// isTouchedGreen = true when the user removes the finger from the screen.
	private boolean isTouchedBlue = false;
	private boolean isTouchedGreen = false;
	private boolean isTouchedBlack = false;
	private boolean isTouchedWhite = false;

	private int tolerance = 25;

	private ImageView bottomImageView;
	private Bitmap hotSpotBitmap;

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
		mPaint.setStrokeWidth(UtilityMethods.convertDpToPixel(ConstantValues.STROKE_WIDTH, WeShineApp.getInstance()));
		mCanvas = new Canvas();
		// paths = new LinkedList<Path>();
		mPath = new Path();
		// paths.add(mPath);

		// Set path tracking boolean
		isTouchedBlue = false;
		isTouchedGreen = false;
		isTouchedBlack = false;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// for (Path p : paths) {
		// canvas.drawPath(p, mPaint);
		// }

		canvas.drawPath(mPath, mPaint);
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
		// setPathInfo((int) x, (int) y);
	}

	private void touch_up() {
		mPath.lineTo(mX, mY);
		// commit the path to our offscreen
		mCanvas.drawPath(mPath, mPaint);
		//onTouchUpCalc();
		// kill this so we don't double draw
		// onTouchEndEvent(isTouchedBlue && isTouchedGreen && isTouchedBlack &&
		// isTouchedWhite);
		onTouchEndEvent(checkRightPath()); 
		// mPath = new Path();
		// paths.add(mPath);
		if (!isTouchedBlue || !isTouchedGreen || !isTouchedBlack || !isTouchedWhite) {
			reset();
		}
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			// Logger.debug(ConstantValues.APP_TAG, "ACTION_DOWN: x:" + x +
			// " y:" + y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			// Logger.debug(ConstantValues.APP_TAG, "ACTION_MOVE: x:" + x +
			// " y:" + y);
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			// Logger.debug(ConstantValues.APP_TAG, "ACTION_UP: x:" + x + " y:"
			// + y);
			touch_up();
			invalidate();
			break;
		}
		return true;

	}

	private void initHotSpotBitmap() {

		if(hotSpotBitmap != null){
			return;
		}
		
		if (bottomImageView == null) {
			Logger.debug(TAG, "Please set hot spot imageView first");
			return;
		}
		bottomImageView.setDrawingCacheEnabled(true);
		hotSpotBitmap = Bitmap.createBitmap(bottomImageView.getDrawingCache());
		if (hotSpotBitmap == null) {
			Logger.debug(TAG, "Hot spot bitmap was not created.");
			return;
		}else {
			bottomImageView.setDrawingCacheEnabled(false);
		}
		
	}

	private boolean checkRightPath() {
		initHotSpotBitmap(); 
//		if (bottomImageView == null) {
//			Logger.debug(TAG, "Please set hotspot imageVeiw first");
//			return;
//		}
//		bottomImageView.setDrawingCacheEnabled(true);
//		Bitmap hotspots = Bitmap.createBitmap(bottomImageView.getDrawingCache());
//		if (hotspots == null) {
//			Logger.debug(ConstantValues.APP_TAG, "Hot spot bitmap was not created.");
//			return;
//		} else {
//			bottomImageView.setDrawingCacheEnabled(false);

			if(hotSpotBitmap == null){
				Logger.debug(TAG, "******************Hot Spot image is null");
				return false;
			}
			FloatPoint[] floatPoints = UtilityMethods.getPoints(mPath, ConstantValues.POINTS_COUNT);
			
			for (FloatPoint floatPoint : floatPoints) {
				int hotSpotColorPixel = -1;
				try {
					hotSpotColorPixel = hotSpotBitmap.getPixel((int) floatPoint.getX(), (int) floatPoint.getY());
					if (isTouchedGreen && isTouchedBlue && isTouchedBlack && isTouchedWhite) {
						Logger.debug(TAG, "********************ALL COLORS TOUCHED");
						return true;

					}
					if (!isTouchedGreen && ColorTool.closeMatch(Color.GREEN, hotSpotColorPixel, tolerance)) {
						isTouchedGreen = true;
						Logger.debug(TAG, "********************touchColor:GREEN");
					}
					if (!isTouchedBlue && ColorTool.closeMatch(Color.BLUE, hotSpotColorPixel, tolerance)) {
						isTouchedBlue = true;
						Logger.debug(TAG, "********************touchColor:BLUE");
					}
					if (!isTouchedBlack && ColorTool.closeMatch(Color.BLACK, hotSpotColorPixel, tolerance)) {
						isTouchedBlack = true;
						Logger.debug(TAG, "********************touchColor:BLACK");
					}
					if (!isTouchedWhite && ColorTool.closeMatch(Color.WHITE, hotSpotColorPixel, tolerance)) {
						isTouchedWhite = true;
						Logger.debug(TAG, "********************touchColor:WHITE");
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
			
			return false;

		//}

	}

	/**
	 * Get the color from the hotspot image at point x-y.
	 * 
	 */
//	public int getHotspotColor(int x, int y) {
//
//		if (bottomImageView == null) {
//			Logger.debug(TAG, "Please set hotspot imageVeiw first");
//			return 0;
//		}
//		bottomImageView.setDrawingCacheEnabled(true);
//		Bitmap hotspots = Bitmap.createBitmap(bottomImageView.getDrawingCache());
//		if (hotspots == null) {
//			Logger.debug(ConstantValues.APP_TAG, "Hot spot bitmap was not created.");
//			return 0;
//		} else {
//			bottomImageView.setDrawingCacheEnabled(false);
//			int hotSpotPixel = -1;
//			try {
//				hotSpotPixel = hotspots.getPixel(x, y);
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			}
//			return hotSpotPixel;
//		}
//	}

//	private void setPathInfo(int x, int y) {
//		if (isTouchedGreen && isTouchedBlue && isTouchedBlack && isTouchedWhite) {
//			Logger.debug(TAG, "********************ALL COLORS TOUCHED");
//			return;
//		}
//		int touchColor = getHotspotColor((int) x, (int) y);
//
//		if (!isTouchedGreen && ColorTool.closeMatch(Color.GREEN, touchColor, tolerance)) {
//			isTouchedGreen = true;
//			Logger.debug(TAG, "********************touchColor:GREEN");
//			return;
//		}
//		if (!isTouchedBlue && ColorTool.closeMatch(Color.BLUE, touchColor, tolerance)) {
//			isTouchedBlue = true;
//			Logger.debug(TAG, "********************touchColor:BLUE");
//			return;
//		}
//		if (!isTouchedBlack && ColorTool.closeMatch(Color.BLACK, touchColor, tolerance)) {
//			isTouchedBlack = true;
//			Logger.debug(TAG, "********************touchColor:BLACK");
//			return;
//		}
//		if (!isTouchedWhite && ColorTool.closeMatch(Color.WHITE, touchColor, tolerance)) {
//			isTouchedWhite = true;
//			Logger.debug(TAG, "********************touchColor:WHITE");
//			return;
//		}
//
//	}

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

	/**
	 * Three colors Green, Blue and Black will be on every maze level. White
	 * color will be present on Maze level 2, 3, 4 and 5. So in case of
	 * MazeGame1Fragment, we have to set it true because the right path will
	 * check all colors eg Red, green, black and white.
	 */
	public void setIsTouchedWhite(boolean status) {
		isTouchedWhite = status;
	}

}
