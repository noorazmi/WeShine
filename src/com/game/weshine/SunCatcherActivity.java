package com.game.weshine;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.app.WeShineApp;
import com.game.util.animation.AnimationUtil;
import com.game.utils.Logger;
import com.game.utils.UtilityMethods;

public class SunCatcherActivity extends Activity implements OnTouchListener {

	private static final String TAG = SunCatcherActivity.class.getName();
	//private ImageView golfCarImageView;
	private boolean dragging = false;
	private Rect hitRect = new Rect();
	private AbsoluteLayout mainLayout;
	
private ImageView letterView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sun_catcher);
		//golfCarImageView = (ImageView) findViewById(R.id.sunCatcher_golfCarImageView);
		//golfCarImageView.setOnTouchListener(this);
		letterView = (ImageView) findViewById(R.id.sunCatcher_golfCarImageView);
		letterView.setOnTouchListener(this);
		mainLayout = (AbsoluteLayout) findViewById(R.id.mainLayout);
		mainLayout.setOnTouchListener(this);
		setAnimatedSunView();
		setBatteryImageView();
		setCircleTextView();
		setGolfCarImageView();
		setLakeImageView();
	}

	private void setAnimatedSunView() {

		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		Logger.error(TAG, "size%%%%%%%%%%@@@@@@@@************::" + screenSize);
		ImageView imageView = (ImageView) findViewById(R.id.sunCatcher_sunImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if (screenSize >= 9.4) {
			params.width = (int) UtilityMethods.convertDpToPixel(198, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(198, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(60, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(20, WeShineApp.getInstance());
		} else if (screenSize >= 6.9) {
			params.width = (int) UtilityMethods.convertDpToPixel(153, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(159, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(40, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(18, WeShineApp.getInstance());
		} else {
			params.width = (int) UtilityMethods.convertDpToPixel(82, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(82, WeShineApp.getInstance());
		}

		// existing height is ok as is, no need to edit it

		AnimationUtil.performFrameAnimation((ImageView) findViewById(R.id.sunCatcher_sunImageView), R.drawable.maze2_sun_animation);
	}

	private void setBatteryImageView() {
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		ImageView imageView = (ImageView) findViewById(R.id.sunCatcher_batteryImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if (screenSize >= 9.4) {
			params.width = (int) UtilityMethods.convertDpToPixel(129, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(300, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(110, WeShineApp.getInstance());
		} else if (screenSize >= 6.9) {
			params.width = (int) UtilityMethods.convertDpToPixel(100, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(60, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(30, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(60, WeShineApp.getInstance());
		} else {
			params.width = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(116, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(47, WeShineApp.getInstance());
		}
	}

	private void setCircleTextView() {
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		TextView imageView = (TextView) findViewById(R.id.sunCatcher_circleTextView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if (screenSize >= 9.4) {
			params.width = (int) UtilityMethods.convertDpToPixel(129, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(300, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(110, WeShineApp.getInstance());
		} else if (screenSize >= 6.9) {
			params.width = (int) UtilityMethods.convertDpToPixel(80, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(80, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(15, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(170, WeShineApp.getInstance());
		} else {
			params.width = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(116, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(47, WeShineApp.getInstance());
		}

	}

	private void setGolfCarImageView() {
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		ImageView imageView = (ImageView) findViewById(R.id.sunCatcher_golfCarImageView);
		AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) imageView.getLayoutParams();
		if (screenSize >= 9.4) {
			params.width = (int) UtilityMethods.convertDpToPixel(129, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(300, WeShineApp.getInstance());
			//params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			//params.leftMargin = (int) UtilityMethods.convertDpToPixel(110, WeShineApp.getInstance());
		} else if (screenSize >= 6.9) {
			params.width = (int) UtilityMethods.convertDpToPixel(200, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(200, WeShineApp.getInstance());
			params.x = (int)UtilityMethods.convertDpToPixel(UtilityMethods.getScreenWidth(this)/3, WeShineApp.getInstance());;
			
			params.y = (int)UtilityMethods.convertDpToPixel(UtilityMethods.getScreenHeight(this)-390, WeShineApp.getInstance());;
			//imageView.setLayoutParams(params);
			//params.bottomMargin = (int) UtilityMethods.convertDpToPixel(-15, WeShineApp.getInstance());
		} else {
			params.width = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(116, WeShineApp.getInstance());
			//params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			//params.leftMargin = (int) UtilityMethods.convertDpToPixel(47, WeShineApp.getInstance());
		}
	}
	
	
	private void setLakeImageView(){
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		ImageView imageView = (ImageView) findViewById(R.id.sunCatcher_lakeImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if (screenSize >= 9.4) {
			params.width = (int) UtilityMethods.convertDpToPixel(129, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(300, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(110, WeShineApp.getInstance());
		} else if (screenSize >= 6.9) {
			params.width = (int) UtilityMethods.convertDpToPixel(300, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(223, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(90, WeShineApp.getInstance());
		} else {
			params.width = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(116, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(47, WeShineApp.getInstance());
		}

		AnimationUtil.performFrameAnimation((ImageView) findViewById(R.id.sunCatcher_lakeImageView), R.drawable.lake_animation);

	}

	@Override
	/**
	 * NOTE:  Had significant problems when I tried to react to ACTION_MOVE on letterView.   Kept getting alternating (X,Y) 
	 * locations of the motion events, which caused the letter to flicker and move back and forth.  The only solution I could 
	 * find was to determine when the user had touched down on the letter, then process moves in the ACTION_MOVE 
	 * associated with the mainLayout.
	 */
	public boolean onTouch(View v, MotionEvent event) {
		boolean eventConsumed = true;
		int x = (int)event.getX();
		int y = (int)event.getY();

		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			if (v == letterView) {
				dragging = true;
				eventConsumed = false;
			}
		} else if (action == MotionEvent.ACTION_UP) {

//			if (dragging) {
//				emptyLetterView.getHitRect(hitRect);
//				if (hitRect.contains(x, y))
//					setSameAbsoluteLocation(letterView, emptyLetterView);
//			}
			dragging = false;
			eventConsumed = false;

		} else if (action == MotionEvent.ACTION_MOVE) {
			if (v != letterView) {
				if (dragging) {
					setAbsoluteLocationCentered(letterView, x, y);
				}
			}
		}

		return eventConsumed;

	}

	private void setSameAbsoluteLocation(View v1, View v2) {
		AbsoluteLayout.LayoutParams alp2 = (AbsoluteLayout.LayoutParams) v2.getLayoutParams();
		setAbsoluteLocation(v1, alp2.x, alp2.y);
	}

	private void setAbsoluteLocationCentered(View v, int x, int y) {
		setAbsoluteLocation(v, x - v.getWidth() / 2, y - v.getHeight() / 2);
	}

	private void setAbsoluteLocation(View v, int x, int y) {
		AbsoluteLayout.LayoutParams alp = (AbsoluteLayout.LayoutParams) v.getLayoutParams();
		alp.x = x;
		alp.y = y;
		v.setLayoutParams(alp);
	}

}
