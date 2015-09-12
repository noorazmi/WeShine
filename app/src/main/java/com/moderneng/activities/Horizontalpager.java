package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.example.solarenegy.HorizontalPage;
import com.example.solarenegy.AudioPlayer;
import com.game.utils.AppConstant;
import com.moderneng.R;
import com.moderneng.WeShineApp;

public class Horizontalpager extends Activity {
	int count;
	AudioPlayer mp;
	//int[] backgoundmusic = { R.raw.slide1, R.raw.slide2, R.raw.slide3, R.raw.slide4, R.raw.slide5 };
	int[] backgoundmusic ;
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// Create the view switcher
		HorizontalPage realViewSwitcher = new HorizontalPage(getApplicationContext());

		// Add some views to it

		//final int[] backgroundDrawables = { R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5 };

		int[] backgroundDrawables = null;
		if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
			backgroundDrawables = new int[]{R.drawable.aedu1, R.drawable.aedu2, R.drawable.aedu3, R.drawable.aedu4, R.drawable.aedu5};
			backgoundmusic = new int[]{R.raw.slide1, R.raw.slide2, R.raw.slide3, R.raw.slide4, R.raw.slide5};
		}else if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ARABIC)){
			backgroundDrawables = new int[]{R.drawable.edu1_arb, R.drawable.edu2_arb, R.drawable.edu3_arb, R.drawable.edu4_arb, R.drawable.edu5_arb};
			backgoundmusic = new int[]{R.raw.aedu1_arb, R.raw.aedu2_arb, R.raw.aedu3_arb, R.raw.aedu4_arb, R.raw.aedu5_arb};

		}


		Intent mIntent = getIntent();
		int intValue = mIntent.getIntExtra("intVariableName", 0);
		count = intValue;
		for (int i = intValue; i < 5; i++) {
			ImageView textView = new ImageView(getApplicationContext());
			textView.setScaleType(ScaleType.FIT_XY);
			textView.setImageResource(backgroundDrawables[i]);
			realViewSwitcher.addView(textView);
		}
		mp = new AudioPlayer(this, backgoundmusic[count]);

		// set as content view
		setContentView(realViewSwitcher);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		mp.start();
		// count++;
		realViewSwitcher.setOnScreenSwitchListener(onScreenSwitchListener);

	}

	private final HorizontalPage.OnScreenSwitchListener onScreenSwitchListener = new HorizontalPage.OnScreenSwitchListener() {
		@Override
		public void onScreenSwitched(final int screen) {
			mp.pause();
			int  id = 0;
			     System.gc();
			if(screen==0){
				id=count;
			}else if(screen==1){
				id=count+1;
			}else if(screen==2){
				id=count+2;
			}else if(screen==3){
				id=count+3;
			}else if(screen==4){
				id=count+4;
			}
		//count++;
			mp = new AudioPlayer(getApplicationContext(), backgoundmusic[id]);
			mp.start();
			Log.d("HorizontalPager", "switched to screen: " + screen);
		}
	};

	public void onBackPressed() {
		mp.pause();
		super.onBackPressed();
	}
}
