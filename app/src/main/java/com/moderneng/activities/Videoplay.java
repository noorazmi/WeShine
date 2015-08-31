package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;

import com.game.utils.AppConstant;
import com.moderneng.R;
import com.moderneng.WeShineApp;

public class Videoplay extends Activity {
	private VideoView vv;
	private Intent imatch;
	private  int intValue;
	private Bitmap mBitmapThankyou;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		System.gc();
		Intent mIntent = getIntent();
		intValue = mIntent.getIntExtra("vid", 0);
		vv = (VideoView) this.findViewById(R.id.videoview_game_end);
		String uri = "android.resource://" + getPackageName() + "/" + intValue;
		vv.setVideoURI(Uri.parse(uri));
		vv.start();

		int videoDuration = mIntent.getIntExtra(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, 0);

		new CountDownTimer(videoDuration, 500) {
			@Override
			public void onTick(long millisUntilFinished) {
				if(millisUntilFinished <= 1000){
					startNextLevel();
				}
			}
			@Override
			public void onFinish() {

			}
		}.start();



		vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				 mp.reset();
			}
		});
	}

	private void startNextLevel(){

		{

			switch (intValue) {
				case R.raw.puzzle1a:
					finish();
					imatch=new Intent(Videoplay.this,puzzle2.class);
					break;
				case R.raw.puzzle2a:
					finish();
					imatch=new Intent(Videoplay.this,puzzle3.class);
					break;
				case R.raw.puzzle3a:
					finish();
					imatch=new Intent(Videoplay.this,puzzle4.class);
					break;
				case R.raw.puzzle4a:
					finish();
					imatch=new Intent(Videoplay.this,puzzle5.class);
					break;
				case R.raw.match1:
					finish();
					imatch = new Intent(Videoplay.this, match2.class);
					break;
				case R.raw.match2:
					finish();
					imatch = new Intent(Videoplay.this, match3.class);
					break;
				case R.raw.match3:
					finish();
					imatch = new Intent(Videoplay.this, match4.class);
					break;
				case R.raw.match4:
					finish();
					imatch = new Intent(Videoplay.this, match5.class);
					break;
				case R.raw.arb_story_iphone:
					showStoryEndImage();
					break;
				case R.raw.story2:
					showStoryEndImage();
					break;
			}
			if(imatch!=null){
				startActivity(imatch);
			}
		}

	}

	private void showStoryEndImage(){
		if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
			mBitmapThankyou = BitmapFactory.decodeResource(getResources(), R.drawable.story_end_image);
		}else if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ARABIC)){
			mBitmapThankyou = BitmapFactory.decodeResource(getResources(), R.drawable.arb_story_end_image);
		}
		((ImageView)findViewById(R.id.imageview_thankyou)).setImageBitmap(mBitmapThankyou);
		((ImageView)findViewById(R.id.imageview_thankyou)).setVisibility(View.VISIBLE);
		((VideoView)findViewById(R.id.videoview_game_end)).setVisibility(View.GONE);

	}


	@Override
	protected void onStop() {
		super.onStop();
		if(mBitmapThankyou != null){
			mBitmapThankyou.recycle();
		}
	}
}
