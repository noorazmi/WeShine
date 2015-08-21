package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.android.model.Gamemusic;
import com.example.solarenegy.ColorTool;
import com.example.solarenegy.playaudio;
import com.moderneng.R;

public class Mmain extends Activity implements View.OnTouchListener {
	ImageView frontimg, backimg, bird, bird2, beev, bird3;
	Animation bird1;
	Gamemusic mp;
	playaudio mp4;
	Boolean isopen = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.mmainmenu);
		bird = (ImageView) findViewById(R.id.sunim);
		bird2 = (ImageView) findViewById(R.id.bird2);
		beev = (ImageView) findViewById(R.id.bee);
		beev.setBackgroundResource(R.drawable.bee);
		bird.setBackgroundResource(R.drawable.word1);
		bird2.setBackgroundResource(R.drawable.word2);
		bird3 = (ImageView) findViewById(R.id.bird3);
		bird3.setBackgroundResource(R.drawable.bird3);
		AnimationDrawable bird3anim = (AnimationDrawable) bird3.getBackground();
		bird3anim.start();
		AnimationDrawable bird2anim = (AnimationDrawable) bird2.getBackground();
		bird2anim.start();
		AnimationDrawable beeanim = (AnimationDrawable) beev.getBackground();
		beeanim.start();
		AnimationDrawable gyroAnimation = (AnimationDrawable) bird
				.getBackground();
		gyroAnimation.start();
		mp = new Gamemusic(getApplicationContext(), R.raw.mgames);
		mp.start();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mp.pause();
				mp4 = new playaudio(getApplicationContext(), R.raw.homesound);
				mp4.start();
			}
		}, 1100);
		frontimg = (ImageView) findViewById(R.id.frontimg);
		backimg = (ImageView) findViewById(R.id.backimg);
		if (frontimg != null) {
			frontimg.setOnTouchListener(this);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Boolean handlehere = false;
		final int action = event.getAction();
		final int evx = (int) event.getX();
		final int evy = (int) event.getY();
		int nextimage = -1;
		ImageView front = (ImageView) v.findViewById(R.id.frontimg);
		if (front == null)
			return false;
		Integer tagNum = (Integer) front.getTag();
		int currentResource = (tagNum == null) ? R.drawable.mcardfront : tagNum
				.intValue();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (currentResource == R.drawable.mcardfront) {
				handlehere = true;
			} else
				handlehere = true;
			break;
		case MotionEvent.ACTION_UP:
			int touchcolor = getHotspotColor(R.id.backimg, evx, evy);
			ColorTool ct = new ColorTool();
			int tolerence = 25;
			if (ct.closeMatch(Color.BLUE, touchcolor, tolerence)) {
				nextimage = 0;
			} else if (ct.closeMatch(Color.RED, touchcolor, tolerence)) {
				nextimage = 1;
			} else if (ct.closeMatch(Color.GREEN, touchcolor, tolerence)) {
				nextimage = 2;
			} else if (ct.closeMatch(Color.WHITE, touchcolor, tolerence)) {
				nextimage = 3;
			}
			handlehere = true;
			break;
		default:
			handlehere = false;
		}
		if (handlehere) {
			if (nextimage == 0) {
				if(mp4 != null){
					mp4.pause();
				}

				Intent i = new Intent(Mmain.this, Level1.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				frontimg.setOnTouchListener(null);
			} else if (nextimage == 1) {
				if(mp4 != null){
					mp4.pause();
				}
				Intent i2 = new Intent(Mmain.this, Mlevel2.class);
				i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i2);
				frontimg.setOnTouchListener(null);
			} else if (nextimage == 2) {
				if(mp4 != null){
					mp4.pause();
				}
				Intent i3 = new Intent(Mmain.this, Mlevel3.class);
				i3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i3);
				frontimg.setOnTouchListener(null);
			}
		}
		return handlehere;
	}

	public int getHotspotColor(int hotspotId, int x, int y) {
		ImageView img = (ImageView) findViewById(hotspotId);
		if (img == null) {
			Log.d("ImageAreasActivity", "hot spot image not found");
			return 0;
		} else {
			img.setDrawingCacheEnabled(true);
			Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
			if (hotspots == null) {
				Log.d("ImageAreasActivity", "Hot spot bitmap was not created");
				return 0;
			} else {
				img.setDrawingCacheEnabled(false);
				return hotspots.getPixel(x, y);
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mp4 != null) {
			mp4.stop();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(mp4 != null){

		mp4.pause();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(mp4 != null){

			mp4.pause();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		frontimg.setOnTouchListener(this);
	}
}
