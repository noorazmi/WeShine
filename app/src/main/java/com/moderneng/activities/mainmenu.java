package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.solarenegy.ColorTool;
import com.example.solarenegy.playaudio;
import com.moderneng.R;

public class mainmenu extends Activity implements View.OnTouchListener {
	playaudio mp;
	ImageView iv, backimage;
	static int width;
	static int height;
	Display mDisplay;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.mainmenu);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		mDisplay = this.getWindowManager().getDefaultDisplay();
		height=mDisplay.getHeight();
        width=mDisplay.getWidth();
		mp = new playaudio(this, R.raw.homesound);
		mp.start();
		iv = (ImageView) findViewById(R.id.image);
		if (iv != null) {
			iv.setOnTouchListener(this);
		}
	}
	public boolean onTouch(View v, MotionEvent ev) {
		boolean handledHere = false;
		final int action = ev.getAction();
		final int evX = (int) ev.getX();
		final int evY = (int) ev.getY();
		int nextImage = -1;

		ImageView imageView = (ImageView) v.findViewById(R.id.image);
		if (imageView == null)
			return false;
		Integer tagNum = (Integer) imageView.getTag();
		int currentResource = (tagNum == null) ? R.drawable.p2_ship_default
				: tagNum.intValue();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (currentResource == R.drawable.p2_ship_default) {
				nextImage = R.drawable.p2_ship_pressed;
				handledHere = true;
			} else
				handledHere = true;
			break;

		case MotionEvent.ACTION_UP:

			int touchColor = getHotspotColor(R.id.image_areas, evX, evY);
			ColorTool ct = new ColorTool();
			int tolerance = 25;
			nextImage = R.drawable.p2_ship_default;
			   if (ct.closeMatch(Color.RED, touchColor, tolerance))
				nextImage = 0;
			else if (ct.closeMatch(Color.GREEN, touchColor, tolerance))
				nextImage = 1;
			else if (ct.closeMatch(Color.BLUE, touchColor, tolerance))
				nextImage = 2;
			else if (ct.closeMatch(Color.WHITE, touchColor, tolerance))
				nextImage = 3;
			if (currentResource == nextImage) {
				nextImage = -1;
			}
		            	handledHere = true;
			break;

		default:
			handledHere = false;
		} // end switch

		if (handledHere) {

			if (nextImage == 0) {
				mp.pause();
				Intent introintent = new Intent(getApplicationContext(),
						Videoplay.class);
				int id=R.raw.story2;
				introintent.putExtra("vid", id);
				startActivity(introintent);
			}
			if (nextImage == 1) {
				mp.pause();
				Intent i1 = new Intent(getApplicationContext(),
						GameActivity.class);
				i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i1);
				iv.setOnTouchListener(null);
			}
			if (nextImage == 2) {
				mp.pause();
				Intent i = new Intent(getApplicationContext(), Intro.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				iv.setOnTouchListener(null);
			}
			if (nextImage == 3) {

			}
		}
		return handledHere;
	}
	public int getHotspotColor(int hotspotId, int x, int y) {
		   ImageView img = (ImageView) findViewById(hotspotId);
		if (img == null) {
			  Log.d("ImageAreasActivity", " Hot spot image not found");
			return 0;
		} else {
			img.setDrawingCacheEnabled(true);
			Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
			if (hotspots == null) {
				Log.d("ImageAreasActivity","Hot spot bitmap was not created");
				return 0;
			} else {
				img.setDrawingCacheEnabled(false);
				return hotspots.getPixel(x, y);
			}
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		iv.setOnTouchListener(this);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		  mp.stop();
		super.onDestroy();
		
	}
} // end class
