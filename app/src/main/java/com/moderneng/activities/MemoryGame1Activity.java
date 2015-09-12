package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.model.Gamemusic;
import com.game.util.animation.AnimType;
import com.game.util.animation.AnimationUtil;
import com.game.utils.AppConstant;
import com.moderneng.R;
import com.moderneng.WeShineApp;

public class MemoryGame1Activity extends Activity implements OnClickListener, AnimationListener {
	private ImageView plate1, cart1, blue1, blue2, cart2, plate2, clockani, textimg;
	private Animation animation1;
	private Animation animation2;
	int id;
	private View v1, v2;
	private int count = 0, clickcount = 0;
	private boolean isBackOfCardShowing = true;
	private Boolean isface = false;
	private int gamecount = 0;
	private MediaPlayer mMediaPlayerClock;
	private Gamemusic gamemusic;
	private TextView tv;
	private RelativeLayout textlay;
	private AnimationDrawable clockanimation;
	private CountDownTimer t;
	private ScaleAnimation scal,scal1;
    private boolean isGameWon = false;
	private Bitmap mBitmapBg;
	private Bitmap mBitmapTitle;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_memory_games_level1);
		animation1 = AnimationUtils.loadAnimation(this, R.anim.tomid);
		animation1.setAnimationListener(this);
		animation2 = AnimationUtils.loadAnimation(this, R.anim.toend);
		animation2.setAnimationListener(this);
		clockani = (ImageView) findViewById(R.id.clock);
		clockani.setBackgroundResource(R.drawable.clock);
		clockanimation = (AnimationDrawable) clockani.getBackground();
		clockanimation.start();
		tv = (TextView) findViewById(R.id.time);
		scal = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
		scal.setDuration(1000);
		scal.setFillAfter(true);
		scal1 = new ScaleAnimation(0, 1f, 0, 1f,Animation.RELATIVE_TO_SELF, (float) 0.5,Animation.RELATIVE_TO_SELF, (float) 0.5);
		scal1.setDuration(1000);
		scal1.setFillAfter(true);
		scal.setAnimationListener(this);
		textlay = (RelativeLayout) findViewById(R.id.textlay);
		textimg = (ImageView) findViewById(R.id.congrat);
		plate1 = (ImageView) findViewById(R.id.plate1);
		cart1 = (ImageView) findViewById(R.id.cart1);
		blue1 = (ImageView) findViewById(R.id.blue1);
		plate2 = (ImageView) findViewById(R.id.plate2);
		cart2 = (ImageView) findViewById(R.id.cart2);
		blue2 = (ImageView) findViewById(R.id.blue2);

		gamemusic = new Gamemusic(getApplicationContext(), WeShineApp.sSoundIdFindTheSimilarCards);
		gamemusic.start();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				//mMediaPlayerClock = new MediaPlayer(getApplicationContext(), R.raw.clocksound);
				//mMediaPlayerClock = new MediaPlayer();
				//mMediaPlayerClock.start();
				String uriPath = AppConstant.BASE_RESOURCE_PATH + R.raw.clocksound;
				Uri uri = Uri.parse(uriPath);
				mMediaPlayerClock = MediaPlayer.create(WeShineApp.getInstance(), uri);
				if(mMediaPlayerClock != null){
					mMediaPlayerClock.setVolume(0.25f,0.25f);
					mMediaPlayerClock.start();
				}

			}
		}, 800);
		ImageView v = new ImageView(this);
		plate1.setAnimation(animation1);
		plate2.setAnimation(animation1);
		cart1.setAnimation(animation1);
		cart2.setAnimation(animation1);
		blue1.setAnimation(animation1);
		blue2.setAnimation(animation1);
		plate1.startAnimation(animation1);
		plate2.startAnimation(animation1);
		cart1.startAnimation(animation1);
		cart2.startAnimation(animation1);
		blue1.startAnimation(animation1);
		blue2.startAnimation(animation1);
		t = new CountDownTimer(30000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				int millisecond = (int) (millisUntilFinished / 1000);
				tv.setText("" + millisecond);
			}

			@Override
			public void onFinish() {
				tv.setText("0");
				mMediaPlayerClock.stop();
				clockanimation.stop();
				gamemusic = new Gamemusic(getApplicationContext(), WeShineApp.sSoundIdGameOverTingTing);
				gamemusic.setOnCompleteListener(new Gamemusic.OnCompleteListener() {
					@Override
					public void onComplete() {
						finish();
					}
				});
                if(!isGameWon){
				    gamemusic.start();
                    textimg.setImageResource(R.drawable.p1_gameover);
                    textimg.setAnimation(scal1);
                    scal1.start();
                    textlay.setVisibility(View.VISIBLE);
                    plate1.setOnClickListener(null);
                    cart1.setOnClickListener(null);
                    blue1.setOnClickListener(null);
                    plate2.setOnClickListener(null);
                    cart2.setOnClickListener(null);
                    blue2.setOnClickListener(null);
                }

			}
		}.start();

		/*
		 * new Handler().postDelayed(new Runnable() {
		 *
		 * @Override public void run() { // TODO Auto-generated method stub if
		 * (isface == false) { mMediaPlayerClock.stop(); clockanimation.stop(); gamemusic =
		 * new Gamemusic(getApplicationContext(), R.raw.gameover);
		 * gamemusic.start(); textimg.setImageResource(R.drawable.gameover);
		 * textlay.setVisibility(View.VISIBLE);
		 *
		 * plate1.setOnClickListener(null); cart1.setOnClickListener(null);
		 * blue1.setOnClickListener(null); plate2.setOnClickListener(null);
		 * cart2.setOnClickListener(null); blue2.setOnClickListener(null); } }
		 * }, 30000);
		 */
	}


	
	@Override
	public void onClick(View v) {
		id = v.getId();
		clickcount++;
		if (clickcount == 1) {
			v1 = v;
			v1.setAnimation(animation1);
			v1.startAnimation(animation1);
		} else if (clickcount == 2) {
			v2 = v;
			v2.setAnimation(animation1);
			v2.startAnimation(animation1);
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if(animation==scal){
			t.cancel();
//		new Handler().postDelayed(new Runnable() {
//
//				@Override
//				public void run() {
//					Intent i = new Intent(Level1.this, Mlevel2.class);
//					startActivity(i);
//					finish();
//				}
//			}, 2500);

		}
		if (count == 0) {
			if (animation == animation1) {
				plate1.setImageResource(R.drawable.plate);
				plate2.setImageResource(R.drawable.plate);
				cart1.setImageResource(R.drawable.cgolf);
				cart2.setImageResource(R.drawable.cgolf);
				blue1.setImageResource(R.drawable.cblue);
				blue2.setImageResource(R.drawable.cblue);
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						plate1.startAnimation(animation2);
						plate2.startAnimation(animation2);
						cart1.startAnimation(animation2);
						cart2.startAnimation(animation2);
						blue1.startAnimation(animation2);
						blue2.startAnimation(animation2);
						// tv.setText("20");
					}
				}, 1800);
			}
			if (animation == animation2) {
				plate1.setImageResource(R.drawable.front);
				plate2.setImageResource(R.drawable.front);
				cart1.setImageResource(R.drawable.front);
				cart2.setImageResource(R.drawable.front);
				blue1.setImageResource(R.drawable.front);
				blue2.setImageResource(R.drawable.front);
				count++;
				plate1.setOnClickListener(this);
				cart1.setOnClickListener(this);
				blue1.setOnClickListener(this);
				plate2.setOnClickListener(this);
				cart2.setOnClickListener(this);
				blue2.setOnClickListener(this);
			}

		}
		if (clickcount == 1) {
			if (animation == animation1) {
				if (v1.getId() == R.id.plate1) {
					plate1.setImageResource(R.drawable.plate);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdSolarLight);
					gamemusic.start();
				} else if (v1.getId() == R.id.plate2) {
					plate2.setImageResource(R.drawable.plate);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdSolarLight);
					gamemusic.start();
				} else if (v1.getId() == R.id.cart1) {
					cart1.setImageResource(R.drawable.cgolf);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdGolfCart);
					gamemusic.start();
				} else if (v1.getId() == R.id.cart2) {
					cart2.setImageResource(R.drawable.cgolf);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdGolfCart);
					gamemusic.start();
				} else if (v1.getId() == R.id.blue1) {
					blue1.setImageResource(R.drawable.cblue);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdGreenBilli);
					gamemusic.start();
				} else if (v1.getId() == R.id.blue2) {
					blue2.setImageResource(R.drawable.cblue);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdGreenBilli);
					gamemusic.start();
				}
				// isface = true;
			}
		} else if (clickcount == 2) {
			if (animation == animation1) {
				if (v2.getId() == R.id.plate1) {
					plate1.setImageResource(R.drawable.plate);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdSolarLight);
					gamemusic.start();
				} else if (v2.getId() == R.id.plate2) {
					plate2.setImageResource(R.drawable.plate);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdSolarLight);
					gamemusic.start();
				} else if (v2.getId() == R.id.cart1) {
					cart1.setImageResource(R.drawable.cgolf);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdGolfCart);
					gamemusic.start();
				} else if (v2.getId() == R.id.cart2) {
					cart2.setImageResource(R.drawable.cgolf);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdGolfCart);
					gamemusic.start();
				} else if (v2.getId() == R.id.blue1) {
					blue1.setImageResource(R.drawable.cblue);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdGreenBilli);
					gamemusic.start();
				} else if (v2.getId() == R.id.blue2) {
					blue2.setImageResource(R.drawable.cblue);
					gamemusic = new Gamemusic(getApplicationContext(),
							WeShineApp.sSoundIdGreenBilli);
					gamemusic.start();
				}

			} else if (clickcount == 2) {
				if (animation == animation2) {
					ImageView img1 = (ImageView) v1;
					ImageView img2 = (ImageView) v2;
					img1.setImageResource(R.drawable.front);
					img2.setImageResource(R.drawable.front);
					clickcount = 0;
				}
			
			}
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					if ((v1.getId() == R.id.plate1 && v2.getId() == R.id.plate2)
							|| v2.getId() == R.id.plate1
							&& v1.getId() == R.id.plate2) {
						gamemusic = new Gamemusic(getApplicationContext(),
								R.raw.p2drop);
						gamemusic.start();
						gamecount++;
						isface = true;
						v1.setVisibility(View.INVISIBLE);
						v2.setVisibility(View.INVISIBLE);
						clickcount = 0;

					} else if ((v1.getId() == R.id.cart1 && v2.getId() == R.id.cart2)
							|| v2.getId() == R.id.cart1
							&& v1.getId() == R.id.cart2) {
						gamemusic = new Gamemusic(getApplicationContext(),
								R.raw.p2drop);
						gamemusic.start();
						gamecount++;
						clickcount = 0;
						v1.setVisibility(View.INVISIBLE);
						v2.setVisibility(View.INVISIBLE);
						isface = true;

					} else if ((v1.getId() == R.id.blue1 && v2.getId() == R.id.blue2)
							|| v2.getId() == R.id.blue1
							&& v1.getId() == R.id.blue2) {
						gamemusic = new Gamemusic(getApplicationContext(),
								R.raw.p2drop);
						gamemusic.start();
						isface = true;
						gamecount++;
						v1.setVisibility(View.INVISIBLE);
						v2.setVisibility(View.INVISIBLE);
						clickcount = 0;

					} else {
						ImageView img1 = (ImageView) v1;
						ImageView img2 = (ImageView) v2;
						gamemusic = new Gamemusic(getApplicationContext(),
								R.raw.wrongs);
						gamemusic.start();
						img1.setImageResource(R.drawable.front);
						img2.setImageResource(R.drawable.front);
						clickcount = 0;
						isface = false;
						gamecount = gamecount;
					}

				}

			}, 900);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					if (gamecount == 3) {
						if (isface == true) {
							isGameWon = true;
							mMediaPlayerClock.stop();
							//textlay.bringToFront();
							//textlay.setVisibility(View.VISIBLE);
							//textimg.setImageResource(R.drawable.welldone1);
							//textimg.setAnimation(scal);
							//gamemusic = new Gamemusic(getApplicationContext(), R.raw.welldonesound);
							//gamemusic.start();
							t.cancel();

							clockanimation.stop();
							Intent intent = new Intent(MemoryGame1Activity.this, BalloonAnimationActivity.class);
							intent.putExtra(AppConstant.EXTRA_GREETING_IMAGE_RESOURCE_ID, WeShineApp.sImageIdWellDone);
							//intent.putExtra(AppConstant.EXTRA_GREETING_SOUND_ID, R.raw.well_done);
							intent.putExtra(AppConstant.EXTRA_GREETING_SOUND_ID, WeShineApp.sSoundIdWellDone);
							intent.putExtra(AppConstant.EXTRA_BALLOON_ANIMATION_SOUND_ID, R.raw.ballon_playing);
							intent.putExtra(AppConstant.EXTRA_BALLOON_ANIMATION_SOUND_DELAY, AppConstant.BALLOON_ANIMATION_SOUND_DELAY);

							startActivityForResult(intent, 100);
						}
					}
				}
			}, 2000);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Intent i = new Intent(MemoryGame1Activity.this, MemoryGame3Activity.class);
					startActivity(i);
					finish();

	}



	@Override
	public void onAnimationRepeat(Animation arg0) {

	}

	@Override
	public void onAnimationStart(Animation animation) {
		if (count == 0) {
			if (animation == animation1) {
				plate1.setImageResource(R.drawable.front);
				plate2.setImageResource(R.drawable.front);
				cart1.setImageResource(R.drawable.front);
				cart2.setImageResource(R.drawable.front);
				blue1.setImageResource(R.drawable.front);
				blue2.setImageResource(R.drawable.front);
			}
			if (animation == animation2) {
				plate1.setImageResource(R.drawable.front);
				plate2.setImageResource(R.drawable.front);
				cart1.setImageResource(R.drawable.front);
				cart2.setImageResource(R.drawable.front);
				blue1.setImageResource(R.drawable.front);
				blue2.setImageResource(R.drawable.front);
			}
			if (clickcount == 1) {
				if (animation == animation1) {
					ImageView v = (ImageView) v1;
					v.setImageResource(R.drawable.front);
				}
			} else if (clickcount == 2) {
				if (animation == animation1) {
					ImageView v = (ImageView) v2;
					v.setImageResource(R.drawable.front);
				} else if (animation == animation2) {
					ImageView imgv1 = (ImageView) v1;
					ImageView imgv2 = (ImageView) v2;
					imgv1.setImageResource(R.drawable.front);
					imgv2.setImageResource(R.drawable.front);
				}
			} else if (clickcount > 2) {
				if (animation == animation2) {
					ImageView v = (ImageView) v1;
					v.setImageResource(R.drawable.front);
				}
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.gc();
		super.onResume();
//		if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
//			mBitmapBg = BitmapFactory.decodeResource(getResources(), R.drawable.memlevel1);
//		}else if (WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ARABIC)){
//			mBitmapBg = BitmapFactory.decodeResource(getResources(), R.drawable.memory_games_level_one_bg);
//		}

		mBitmapBg = BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdMemoryGamesLevel1);
		findViewById(R.id.relative_layout_parent).setBackgroundDrawable(new BitmapDrawable(getResources(), mBitmapBg));

		mBitmapTitle = BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdMemoryGame);
		((ImageView) findViewById(R.id.imageview_title)).setImageBitmap(mBitmapTitle);
		AnimationUtil.performAnimation(findViewById(R.id.imageview_title), AnimType.ZOOM_IN, null);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		mMediaPlayerClock.pause();
		t.cancel();
		System.gc();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if(mBitmapBg != null){
			mBitmapBg.recycle();
			mBitmapBg = null;
		}

		if (mBitmapTitle != null) {
			mBitmapTitle.recycle();
			mBitmapTitle = null;
		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		mMediaPlayerClock.stop();
		t.cancel();
		System.gc();
	}
}
