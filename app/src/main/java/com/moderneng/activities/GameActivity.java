package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.solarenegy.ColorTool;
import com.example.solarenegy.playaudio;
import com.moderneng.R;

public class GameActivity extends Activity implements OnTouchListener {
	playaudio mp;
	ImageView back, front;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.gc();
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gmainmenu);
//		mp=new playaudio(this, R.raw.homesound);
//		mp.start();
		front = (ImageView) findViewById(R.id.frontimg);
		if (front != null) {
			front.setOnTouchListener(this);
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent ev) {
		// TODO Auto-generated method stub
		Boolean handleHere = false;
		final int action = ev.getAction();
		final int evX = (int) ev.getX();
		final int evY = (int) ev.getY();
		int nextimage = -1;
		ImageView img = (ImageView) v.findViewById(R.id.frontimg);
		if (img == null)
			return false;
		Integer tagNum = (Integer) front.getTag();
		int currentResource = (tagNum == null) ? R.drawable.p2_ship_default
				: tagNum.intValue();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (currentResource == R.drawable.p2_ship_default) {
				nextimage = R.drawable.p2_ship_pressed;
				handleHere = true;
				/*
				 * } else if (currentResource != R.drawable.p2_ship_default) {
				 * nextImage = R.drawable.p2_ship_default; handledHere = true;
				 */
			} else
				handleHere = true;
			break;
		case MotionEvent.ACTION_UP:
			int touchcolor = gethotspotcolor(R.id.backimg, evX, evY);
			ColorTool ct = new ColorTool();
			int tolerence = 25;
			if (ct.closeMatch(Color.RED, touchcolor, tolerence)) {
				nextimage = 0;
			} else if (ct.closeMatch(Color.BLUE, touchcolor, tolerence)) {
				nextimage = 1;
			} else if (ct.closeMatch(Color.GREEN, touchcolor, tolerence)) {
				nextimage = 2;
			}else if (ct.closeMatch(Color.MAGENTA, touchcolor, tolerence)) {
				nextimage = 3;
			}else if(ct.closeMatch(Color.RED, touchcolor, tolerence)){
				nextimage = 4;
			}
			if (currentResource == nextimage) {
				nextimage = -1;
			}
			handleHere = true;
			break;

		default:
			handleHere = false;
		}
		if (handleHere) {
			//mp.pause();
			if(mp != null){
				mp.release();
				 mp = null;
			}
			 
			if (nextimage == 0) {
				// mp.pause();
					Intent mazeActivity = new Intent(getApplicationContext(),MazeActivity.class);
					mazeActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mazeActivity);
					front.setOnTouchListener(null);
				front.setOnTouchListener(null);
			}
			if (nextimage == 1) {
				//mp.pause();
				Intent match = new Intent(getApplicationContext(),
						matchmenu.class);
				match.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(match);
				front.setOnTouchListener(null);
			}
			if (nextimage == 2) {
				//mp.pause();
				Intent puzzle = new Intent(getApplicationContext(),
						puzzlemenu.class);
				puzzle.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(puzzle);
				front.setOnTouchListener(null);
			}
			if (nextimage == 3) {
				  // mp.pause();
				Intent memory = new Intent(getApplicationContext(),
						Mmain.class);
				memory.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(memory);
				front.setOnTouchListener(null);
			}
			
		}
		 
		return handleHere;
	}
	private int gethotspotcolor(int hotspotid, int x, int y) {
		// TODO Auto-generated method stub
		ImageView spotimg = (ImageView) findViewById(hotspotid);
		if (spotimg == null) {
			Log.d("image ", "hotspot image is not found");
			return 0;
		} else {
			spotimg.setDrawingCacheEnabled(true);
			Bitmap spotbitmap = Bitmap.createBitmap(spotimg.getDrawingCache());
			if (spotbitmap == null) {
				Log.d("image ", "hotspot bitmap is not created");
				return 0;
			} else {
				spotimg.setDrawingCacheEnabled(false);
				return spotbitmap.getPixel(x, y);
			}
		}
	}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	mp=new playaudio(this, R.raw.homesound);
	mp.start();
	    //mp.start();
	   front.setOnTouchListener(this);
}
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	  // mp.stop();
	super.onDestroy();
}
@Override
protected void onPause() {
	// TODO Auto-generated method stub
	   //mp.pause();
	super.onPause();
}

@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
	if(mp != null){
		mp.release();
		mp =  null;
	}
}
}
