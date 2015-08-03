package com.moderneng.activities;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.android.model.Modelclass;
import com.moderneng.R;

public class Splash extends Activity {
	int Time_Out_millis = 1500;
	static int width;
	static int height;
	Display mDisplay;
	Modelclass mod;
	public static   Bitmap star1, star2, star3,
	star4,star5,sstar1,sstar2,sstar3,sstar4,sstar5,sstar6,sstar7,
	sstar8,sstar9,sstar10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.gc();
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		     setContentView(R.layout.splash);
		     mDisplay = this.getWindowManager().getDefaultDisplay();
			  height=mDisplay.getHeight();
	           width=mDisplay.getWidth();
		     BitmapFactory.Options opts = new BitmapFactory.Options();
				opts.inPreferredConfig = Bitmap.Config.RGB_565;
			sstar1 = BitmapFactory.decodeResource(getResources(),R.drawable.p1_star1,opts);
			sstar2 = BitmapFactory.decodeResource(getResources(),
					R.drawable.p1_star2,opts);
			sstar3 = BitmapFactory.decodeResource(getResources(),
					R.drawable.p1_star3,opts);
			sstar4 = BitmapFactory.decodeResource(getResources(),
					R.drawable.p1_star4,opts);
			sstar5 = BitmapFactory.decodeResource(getResources(),
					R.drawable.p1_star5,opts);
			sstar6 = BitmapFactory.decodeResource(getResources(),
					R.drawable.p1_star6,opts);
			sstar7 = BitmapFactory.decodeResource(getResources(),
					R.drawable.p1_star7,opts);
			sstar8 = BitmapFactory.decodeResource(getResources(),
					R.drawable.p1_star8,opts);
			sstar9 = BitmapFactory.decodeResource(getResources(),
					R.drawable.p1_star9,opts);
		        new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), mainmenu.class);
				startActivity(i);
				finish();
			}
		}, Time_Out_millis);
	}
}
