package com.game.weshine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.app.WeShineApp;
import com.game.util.animation.AnimationUtil;
import com.game.utils.ConstantValues;
import com.game.utils.Logger;
import com.game.utils.UtilityMethods;

public class SunCatcherActivity extends Activity implements OnTouchListener {

	private static final String TAG = SunCatcherActivity.class.getName();
	// private ImageView golfCarImageView;
	private boolean dragging = false;
	private Rect hitRect = new Rect();
	private AbsoluteLayout mainLayout;
	private int yPositionGolfCar;

	private ImageView letterView;
	private ImageView birdImageView;
	private ImageView sunImageView;
	private int mAudioFileId = -1;
	private MediaPlayer mediaPlayer;
	private double screenSize;
	private int birdMarginTop;
	private TextView timerText;
	private ImageView betteryView;
	private ImageView raysImageView;
	private int xFromPosSunRay;
	private int yFromPosSunRay;
	private int yToPosSunRay;
	private int xToPosSunRay;
	private int xPositionGolfCar;
	private int rayHitCount = 0;
	private final int BATTERY_25_HIT_COUNT = 10;
	private final int BATTERY_50_HIT_COUNT = 20;
	private final int BATTERY_75_HIT_COUNT = 30;
	private final int BATTERY_100_HIT_COUNT = 40;
	private int rayHitMargin;
	private int SCREEN_WIDTH;
	private int SCREEN_HEIGHT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sun_catcher);
		screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		SCREEN_WIDTH = UtilityMethods.getScreenWidth(this);
		SCREEN_HEIGHT = UtilityMethods.getScreenHeight(this);
		letterView = (ImageView) findViewById(R.id.sunCatcher_golfCarImageView);
		letterView.setOnTouchListener(this);
		mainLayout = (AbsoluteLayout) findViewById(R.id.mainLayout);
		mainLayout.setOnTouchListener(this);
		birdImageView = (ImageView) findViewById(R.id.sunCatcher_birdImageView);
		setBatteryImageView();
		setCircleTextView();
		setGolfCarImageView();
		setLakeImageView();
		setBirdImageView();
		startAudioSound(R.raw.sun_cather_music);
		timerText = (TextView) findViewById(R.id.sunCatcher_circleTextView);
		betteryView = (ImageView) findViewById(R.id.sunCatcher_batteryImageView);
		raysImageView = (ImageView) findViewById(R.id.sunCatcher_raysImageView);
		sunImageView = (ImageView) findViewById(R.id.sunCatcher_sunImageView);
		new CountDownTimer(60000, 1000) {

			public void onTick(long millisUntilFinished) {
				int remainingMilliSecs = (int) millisUntilFinished / 1000;
				if (remainingMilliSecs == 58) {
					startAudioSound(R.raw.catchupthesun);
				}
				timerText.setText("" + remainingMilliSecs);
			}

			public void onFinish() {
				timerText.setText("" + 0);
				if (rayHitCount >= BATTERY_100_HIT_COUNT) {
					betteryView.setImageResource(R.drawable.battery100);
					(findViewById(R.id.sunCatcher_batteryFullImageView)).setVisibility(View.VISIBLE);
					startAudioSound(R.raw.battery_full_thankyou);
				}

			}

		}.start();

		new CountDownTimer(60000, 1000) {

			public void onTick(long millisUntilFinished) {
				// int remainingMilliSecs = (int) millisUntilFinished / 1000;
				raysAnimation(UtilityMethods.getRandomInt(1, 5));
			}

			public void onFinish() {
			}
		}.start();
	}

	private void setAnimatedSunView() {

		Logger.error(TAG, "size%%%%%%%%%%@@@@@@@@************::" + screenSize);
		AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) sunImageView.getLayoutParams();
		if (screenSize >= 9.4) {
			int sunWidth = (int) UtilityMethods.convertDpToPixel(153, WeShineApp.getInstance());
			params.width = sunWidth;
			int sunHeight = (int) UtilityMethods.convertDpToPixel(159, WeShineApp.getInstance());
			params.height = sunHeight;
			xFromPosSunRay = SCREEN_WIDTH/2 - sunWidth/2;
			yFromPosSunRay = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
			params.x = xFromPosSunRay;
			params.y = yFromPosSunRay;
			yFromPosSunRay = yFromPosSunRay + sunHeight/2;
		} else if (screenSize >= 6.9) {
			int sunWidth = (int) UtilityMethods.convertDpToPixel(153, WeShineApp.getInstance());
			params.width = sunWidth;
			int sunHeight = (int) UtilityMethods.convertDpToPixel(159, WeShineApp.getInstance());
			params.height = sunHeight;
			xFromPosSunRay = SCREEN_WIDTH/2 - sunWidth/2;
			//xToPosSunRay = xFromPosSunRay + sunImageView.getWidth() / 2;
			yFromPosSunRay = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
			params.x = xFromPosSunRay;
			params.y = yFromPosSunRay;
			yFromPosSunRay = yFromPosSunRay + sunHeight/2;
		} else {
			params.width = (int) UtilityMethods.convertDpToPixel(82, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(82, WeShineApp.getInstance());
		}
		Log.d(TAG, "WIDTH****:"+SCREEN_WIDTH);
		// existing height is ok as is, no need to edit it
		sunImageView.setLayoutParams(params);
		AnimationUtil.performFrameAnimation(sunImageView, R.drawable.maze2_sun_animation);
	}

	private void setBatteryImageView() {
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
		TextView imageView = (TextView) findViewById(R.id.sunCatcher_circleTextView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if (screenSize >= 9.4) {
			params.width = (int) UtilityMethods.convertDpToPixel(100, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(99, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(30, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(250, WeShineApp.getInstance());
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
		ImageView imageView = (ImageView) findViewById(R.id.sunCatcher_golfCarImageView);
		AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) imageView.getLayoutParams();
		int carWidth;
		if (screenSize >= 9.4) {
			carWidth = (int) UtilityMethods.convertDpToPixel(200, WeShineApp.getInstance());
			params.width = carWidth;
			params.height = (int) UtilityMethods.convertDpToPixel(200, WeShineApp.getInstance());
			xPositionGolfCar = SCREEN_WIDTH / 3;
			params.x = xPositionGolfCar;
			yPositionGolfCar = (int) (SCREEN_HEIGHT - UtilityMethods.convertDpToPixel(250, WeShineApp.getInstance()));
			params.y = yPositionGolfCar;
			yToPosSunRay = yPositionGolfCar;
		} else if (screenSize >= 6.9) {
			carWidth = (int) UtilityMethods.convertDpToPixel(200, WeShineApp.getInstance());
			params.width = carWidth;
			params.height = (int) UtilityMethods.convertDpToPixel(200, WeShineApp.getInstance());
			xPositionGolfCar = SCREEN_WIDTH / 3;
			params.x = xPositionGolfCar;
			yPositionGolfCar = (int) (SCREEN_HEIGHT - UtilityMethods.convertDpToPixel(390, WeShineApp.getInstance()));
			params.y = yPositionGolfCar;
			yToPosSunRay = yPositionGolfCar;
		} else {
			carWidth = (int) UtilityMethods.convertDpToPixel(200, WeShineApp.getInstance());
			params.width = carWidth;
			params.height = (int) UtilityMethods.convertDpToPixel(200, WeShineApp.getInstance());
			xPositionGolfCar = (int) UtilityMethods.convertDpToPixel(UtilityMethods.getScreenWidth(this) / 3, WeShineApp.getInstance());
			params.x = xPositionGolfCar;
			yPositionGolfCar = (int) UtilityMethods.convertDpToPixel(UtilityMethods.getScreenHeight(this) - 390, WeShineApp.getInstance());
			params.y = yPositionGolfCar;
			yToPosSunRay = yPositionGolfCar;
		}

		rayHitMargin = carWidth / 2;
		Log.d(TAG, "rayHitMargin***" + rayHitMargin);

	}

	private void setLakeImageView() {
		ImageView imageView = (ImageView) findViewById(R.id.sunCatcher_lakeImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if (screenSize >= 9.4) {
			params.width = (int) UtilityMethods.convertDpToPixel(510, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(379, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(120, WeShineApp.getInstance());
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

	private void setBirdImageView() {
		ImageView imageView = (ImageView) findViewById(R.id.sunCatcher_birdImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if (screenSize >= 9.4) {
			params.width = (int) UtilityMethods.convertDpToPixel(80, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(91, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(365, WeShineApp.getInstance());
		} else if (screenSize >= 6.9) {
			params.width = (int) UtilityMethods.convertDpToPixel(60, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(68, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(230, WeShineApp.getInstance());
		} else {
			params.width = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(116, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(47, WeShineApp.getInstance());
		}

		AnimationUtil.performFrameAnimation((ImageView) findViewById(R.id.sunCatcher_birdImageView), R.drawable.sun_catcher_bird_animation);
		int toX = UtilityMethods.getScreenWidth(this);
		if (screenSize >= 9.4) {
			toX = toX - (int) UtilityMethods.convertDpToPixel(98, WeShineApp.getInstance());
			birdMarginTop = (int) UtilityMethods.convertDpToPixel(179, WeShineApp.getInstance());
		} else if (screenSize >= 6.9) {
			toX = toX - (int) UtilityMethods.convertDpToPixel(80, WeShineApp.getInstance());
			birdMarginTop = (int) UtilityMethods.convertDpToPixel(120, WeShineApp.getInstance());
		} else {
			toX = toX - (int) UtilityMethods.convertDpToPixel(10, WeShineApp.getInstance());
			birdMarginTop = (int) UtilityMethods.convertDpToPixel(120, WeShineApp.getInstance());
		}
		birdFlyAnimation(toX, birdMarginTop);

	}

	private void birdFlyAnimation(int xToPosSunRay, int yToPosSunRay) {

		ObjectAnimator birdAnimX = ObjectAnimator.ofFloat(birdImageView, "translationX", 0, xToPosSunRay);
		birdAnimX.setDuration(10000);
		ObjectAnimator birdAnimY = ObjectAnimator.ofFloat(birdImageView, "translationY", 0, yToPosSunRay);
		birdAnimY.setDuration(10000);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				birdImageView.setBackgroundResource(R.drawable.birds_first17);
				super.onAnimationEnd(animation);
			}
		});
		animatorSet.play(birdAnimX).with(birdAnimY);
		animatorSet.start();

	}

	private void raysAnimation(int rayNumber) {
		// xToPosSunRay = 0;
		int oneSixScreen = SCREEN_WIDTH / 6; 
		switch (rayNumber) {
		case 1:
			raysImageView.setImageResource(R.drawable.rays1);
			xToPosSunRay = 1*oneSixScreen;
			break;
		case 2:
			raysImageView.setImageResource(R.drawable.rays2);
			xToPosSunRay = 2*oneSixScreen;

			break;
		case 3:
			raysImageView.setImageResource(R.drawable.rays3);
			xToPosSunRay = 3*oneSixScreen;

			break;
		case 4:
			raysImageView.setImageResource(R.drawable.rays4);
			xToPosSunRay = 4*oneSixScreen;
			break;
		case 5:
			raysImageView.setImageResource(R.drawable.rays5);
			xToPosSunRay = 5*oneSixScreen;
			break;

		default:
			break;
		}

		AnimationSet replaceAnimation = new AnimationSet(false);
		// Log.d(TAG, "xFromPosSunRay::" + xFromPosSunRay + " yFromPosSunRay:" +
		// yFromPosSunRay);
		TranslateAnimation trans = new TranslateAnimation(0, xFromPosSunRay, TranslateAnimation.ABSOLUTE, xToPosSunRay, 0, yFromPosSunRay, TranslateAnimation.ABSOLUTE, yToPosSunRay);
		trans.setDuration(1000);
		replaceAnimation.addAnimation(trans);
		replaceAnimation.setAnimationListener(new Animation.AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				detectSunrayAndCarCollision();
			}
		});

		// start our animation
		raysImageView.startAnimation(replaceAnimation);

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
		int x = (int) event.getX();
		int y = (int) event.getY();
		xPositionGolfCar = x;
		Log.d(TAG, "xPositionGolfCar:" + xPositionGolfCar + " xToPosSunRay: " + xToPosSunRay);

		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			if (v == letterView) {
				dragging = true;
				eventConsumed = false;
			}
		} else if (action == MotionEvent.ACTION_UP) {

			dragging = false;
			eventConsumed = false;

		} else if (action == MotionEvent.ACTION_MOVE) {
			if (v != letterView) {
				if (dragging) {
					setAbsoluteLocationCentered(letterView, x, yPositionGolfCar);
				}
			}
		}

		return eventConsumed;

	}

	private void setAbsoluteLocationCentered(View v, int x, int y) {
		setAbsoluteLocation(v, x - v.getWidth() / 2, y);

	}

	private void setAbsoluteLocation(View v, int x, int y) {
		AbsoluteLayout.LayoutParams alp = (AbsoluteLayout.LayoutParams) v.getLayoutParams();
		alp.x = x;
		alp.y = y;
		v.setLayoutParams(alp);
	}

	protected void startAudioSound(int audioFileId) {

		String uriPath = ConstantValues.BASE_RESOURCE_PATH + audioFileId;
		mAudioFileId = audioFileId;
		Uri uri = Uri.parse(uriPath);
		mediaPlayer = MediaPlayer.create(WeShineApp.getInstance(), uri);
		mediaPlayer.setOnCompletionListener(new MediaListener());
		mediaPlayer.start();
	}

	class MediaListener implements OnCompletionListener {

		@Override
		public void onCompletion(MediaPlayer mp) {
			if (mp != null) {
				mp.release();
			}
		}

	}

	private void detectSunrayAndCarCollision() {
		if (xPositionGolfCar >= xToPosSunRay - rayHitMargin && xPositionGolfCar <= xToPosSunRay + rayHitMargin) {

			Log.d(TAG, "detected collision*******:" + rayHitCount);
			rayHitCount++;
			if (rayHitCount == BATTERY_25_HIT_COUNT) {
				betteryView.setImageResource(R.drawable.battery25);
				startAudioSound(R.raw.well_done);
			} else if (rayHitCount == BATTERY_50_HIT_COUNT) {
				betteryView.setImageResource(R.drawable.battery50);
				startAudioSound(R.raw.perfect);
			} else if (rayHitCount == BATTERY_75_HIT_COUNT) {
				betteryView.setImageResource(R.drawable.battery75);
				startAudioSound(R.raw.super_sound);
			}

		}
	}

	@Override
	protected void onResume() {
		setAnimatedSunView();
		super.onResume();
	}

	@Override
	protected void onRestart() {
		mediaPlayer.start();
		super.onRestart();
	}

	@Override
	protected void onPause() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}

		super.onPause();
	}

	@Override
	protected void onDestroy() {
		mediaPlayer.stop();
		mediaPlayer.release();
		super.onDestroy();
	}

}
