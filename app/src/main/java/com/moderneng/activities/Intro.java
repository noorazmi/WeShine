package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.solarenegy.playaudio;
import com.moderneng.R;

public class Intro extends Activity implements OnClickListener {
	ImageButton slide1, slide2, slide3, slide4, slide5;
	int intValue;
	playaudio mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		System.gc();
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.eduslide);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	     	mp = new playaudio(getApplicationContext(), R.raw.homesound);
		    mp.start();
		slide1 = (ImageButton) findViewById(R.id.imag1);
		slide2 = (ImageButton) findViewById(R.id.imag2);
		slide3 = (ImageButton) findViewById(R.id.image3);
		slide4 = (ImageButton) findViewById(R.id.image4);
		slide5 = (ImageButton) findViewById(R.id.image5);
		slide1.setOnClickListener(this);
		slide2.setOnClickListener(this);
		slide3.setOnClickListener(this);
		slide4.setOnClickListener(this);
		slide5.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.imag1:
			intValue = 0;
			mp.pause();
			start();
			break;
		case R.id.imag2:
			intValue = 1;
			mp.pause();
			start();
			break;
		case R.id.image3:
			intValue = 2;
			mp.pause();
			start();
			break;
		case R.id.image4:
			intValue = 3;
			mp.pause();
			start();
			break;
		case R.id.image5:
			intValue = 4;
			mp.pause();
			start();
		}
	}
	public void start() {
		Intent myIntent = new Intent(Intro.this, Horizontalpager.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		myIntent.putExtra("intVariableName", intValue);
		startActivity(myIntent);
		slide1.setOnClickListener(null);
		slide2.setOnClickListener(null);
		slide3.setOnClickListener(null);
		slide4.setOnClickListener(null);
		slide5.setOnClickListener(null);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		mp.stop();
		super.onBackPressed();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		 mp.start();
		 super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mp.start();
		slide1.setOnClickListener(this);
		slide2.setOnClickListener(this);
		slide3.setOnClickListener(this);
		slide4.setOnClickListener(this);
		slide5.setOnClickListener(this);
		super.onResume();
	}
}
