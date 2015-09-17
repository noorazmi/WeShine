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

import com.moderneng.R;
import com.moderneng.utils.AppConstant;
import com.moderneng.utils.ImageAndMediaResources;

public class Videoplay extends Activity {
	private VideoView vv;
	private Intent imatch;
	private  int intValue;
	private Bitmap mBitmapThankyou;
	private String mVideoType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_main);
		System.gc();
		Intent mIntent = getIntent();
		intValue = mIntent.getIntExtra("vid", 0);
		mVideoType = mIntent.getStringExtra(AppConstant.VIDEO_TYPE_STORY);
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
//				case R.raw.puzzle1a:
//					finish();
//					imatch=new Intent(Videoplay.this,Puzzle2Activity.class);
//					break;
//				case R.raw.puzzle2a:
//					finish();
//					imatch=new Intent(Videoplay.this,Puzzle3Activity.class);
//					break;
//				case R.raw.puzzle3a:
//					finish();
//					imatch=new Intent(Videoplay.this,Puzzle4Activity.class);
//					break;
//				case R.raw.puzzle4a:
//					finish();
//					imatch=new Intent(Videoplay.this,Puzzle5Activity.class);
//					break;
				case R.raw.matching1_video:
					finish();
					imatch = new Intent(Videoplay.this, Match2Activity.class);
					break;
				case R.raw.matching2_video:
					finish();
					imatch = new Intent(Videoplay.this, Match3Activity.class);
					break;
				case R.raw.matching3_video:
					finish();
					imatch = new Intent(Videoplay.this, Match4Activity.class);
					break;
				case R.raw.matching4_video:
					finish();
					imatch = new Intent(Videoplay.this, Match5Activity.class);
					break;
				case R.raw.matching5_video:
					finish();
					//imatch = new Intent(Videoplay.this, Match5Activity.class);
					break;
//				case R.raw.story_arb:
//					showStoryEndImage();
//					break;
				case R.raw.story:
					showStoryEndImage();
					break;
			}

			if(mVideoType.equals(AppConstant.VIDEO_TYPE_STORY)){
				showStoryEndImage();
			}

			if(imatch!=null){
				startActivity(imatch);
			}
		}

	}

	private void showStoryEndImage(){

		mBitmapThankyou = BitmapFactory.decodeResource(getResources(), ImageAndMediaResources.sImageIdStoryEndThankYouImage);
		((ImageView)findViewById(R.id.imageview_thankyou)).setImageBitmap(mBitmapThankyou);
		findViewById(R.id.imageview_thankyou).setVisibility(View.VISIBLE);
		findViewById(R.id.videoview_game_end).setVisibility(View.GONE);

	}


	@Override
	protected void onStop() {
		super.onStop();
		if(mBitmapThankyou != null){
			mBitmapThankyou.recycle();
		}
	}
}
