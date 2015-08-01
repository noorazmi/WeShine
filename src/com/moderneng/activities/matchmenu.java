package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.android.model.Gamemusic;
import com.moderneng.R;

public class matchmenu extends Activity implements OnClickListener {
	ImageButton game1, game2, game3, game4, game5;
	Gamemusic mp4, mp;
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.matchmenun);
		mp = new Gamemusic(getApplicationContext(), R.raw.matchingstart);
		mp.start();
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mp.pause();
				mp4 = new Gamemusic(getApplicationContext(), R.raw.homesound);
				mp4.start();
			}
		}, 500);
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

/*	private void setlayout() {
		if (mainmenu.height <= 480) {
			setContentView(R.layout.matchmenusmall);
		} else {
			setContentView(R.layout.matchmenu);
		}
	}*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.match1:
			// mp.pause();
			mp4.pause();
			Intent match1 = new Intent(getApplicationContext(), match1.class);
			match1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(match1);
			game1.setOnClickListener(null);
			break;
		case R.id.match2:
			// mp.pause();
			mp4.pause();
			Intent match2 = new Intent(getApplicationContext(), match2.class);
			match2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(match2);
			game2.setOnClickListener(null);
			break;
		case R.id.match3:
			// mp.pause();
			mp4.pause();
			Intent match3 = new Intent(getApplicationContext(), match3.class);
			match3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(match3);
			game3.setOnClickListener(null);
			break;
		case R.id.match4:
			// mp.pause();
			mp4.pause();
			Intent match4 = new Intent(getApplicationContext(), match4.class);
			match4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(match4);
			game4.setOnClickListener(null);
			break;
		case R.id.match5:
			// mp.pause();
			mp4.pause();
			Intent match5 = new Intent(getApplicationContext(), match5.class);
			match5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(match5);
			game5.setOnClickListener(null);
			break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// mp.pause();
		mp4.pause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		// mp.start();
		// mp4.pause();
		super.onResume();
		game1.setOnClickListener(this);
		game2.setOnClickListener(this);
		game3.setOnClickListener(this);
		game4.setOnClickListener(this);
		game5.setOnClickListener(this);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		// mp.stop();
		mp4.stop();
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		mp4.pause();
		// mp.pause();
	}
}
