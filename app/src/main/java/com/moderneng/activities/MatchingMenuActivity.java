package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.model.Gamemusic;
import com.game.util.animation.AnimType;
import com.game.util.animation.AnimationUtil;
import com.moderneng.R;
import com.moderneng.WeShineApp;

public class MatchingMenuActivity extends Activity implements OnClickListener {
	private ImageButton game1, game2, game3, game4, game5;
	private Gamemusic mp4, mp;
    private Bitmap mBitmapBg;
	private Bitmap mBitmapTitle;

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedState) {
		super.onRestoreInstanceState(savedState);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.matchmenun);
		game1 = (ImageButton) findViewById(R.id.match1);
		game2 = (ImageButton) findViewById(R.id.match2);
		game3 = (ImageButton) findViewById(R.id.match3);
		game4 = (ImageButton) findViewById(R.id.match4);
		game5 = (ImageButton) findViewById(R.id.match5);
		game1.setOnClickListener(this);
		game2.setOnClickListener(this);
		game3.setOnClickListener(this);
		game4.setOnClickListener(this);
		game5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.match1:
			// mp.pause();
            if(mp4 !=null){
                mp4.pause();
            }

			Intent match1 = new Intent(getApplicationContext(), Match1Activity.class);
			match1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(match1);
			//game1.setOnClickListener(null);
			break;
		case R.id.match2:
			// mp.pause();
            if(mp4 !=null){
                mp4.pause();
            }
			Intent match2 = new Intent(getApplicationContext(), Match2Activity.class);
			match2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(match2);
			//game2.setOnClickListener(null);
			break;
		case R.id.match3:
			// mp.pause();
            if(mp4 !=null){
                mp4.pause();
            }
			Intent match3 = new Intent(getApplicationContext(), Match3Activity.class);
			match3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(match3);
			//game3.setOnClickListener(null);
			break;
		case R.id.match4:
			// mp.pause();
            if(mp4 !=null){
                mp4.pause();
            }
			Intent match4 = new Intent(getApplicationContext(), Match4Activity.class);
			match4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(match4);
			//game4.setOnClickListener(null);
			break;
		case R.id.match5:
			// mp.pause();
            if(mp4 !=null){
                mp4.pause();
            }
			Intent match5 = new Intent(getApplicationContext(), Match5Activity.class);
			match5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(match5);
			//game5.setOnClickListener(null);
			break;
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
        if(mp4 !=null){
            mp4.release();
        }

		if(mp !=null){
			mp.release();
		}

        mBitmapBg.recycle();
        mBitmapBg = null;

		mBitmapTitle.recycle();
		mBitmapTitle = null;
	}

	@Override
	protected void onResume() {
		super.onResume();
        mBitmapBg = BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdMatchingMenuBg);
        ((LinearLayout)findViewById(R.id.linear_layout_container)).setBackgroundDrawable(new BitmapDrawable(mBitmapBg));

		mBitmapTitle =  BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdMatching);
        ((ImageView)findViewById(R.id.imageview_title)).setImageBitmap(mBitmapTitle);
		AnimationUtil.performAnimation((ImageView) findViewById(R.id.imageview_title), AnimType.ZOOM_IN, null);


        mp = new Gamemusic(getApplicationContext(), WeShineApp.sSoundIdMatching);
		mp.start();
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				mp4 = new Gamemusic(getApplicationContext(), R.raw.homesound);
				mp4.start();
			}
		}, 500);
	}
}
