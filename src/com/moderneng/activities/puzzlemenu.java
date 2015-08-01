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

public class puzzlemenu extends Activity implements OnClickListener {
	ImageButton pgame1, pgame2, pgame3, pgame4, pgame5;
	Gamemusic mp,mp4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// setContentView(R.layout.puzzlemenu);
		setContentView(R.layout.puzzlemenun);
		mp4 = new Gamemusic(getApplicationContext(), R.raw.puzzlevoice);
		mp4.start();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mp4.pause();
				mp=new Gamemusic(getApplicationContext(), R.raw.homesound);
				mp.start();
			}
		}, 500);
		pgame1 = (ImageButton) findViewById(R.id.puzzle1);
		pgame1.setOnClickListener(this);
		pgame2 = (ImageButton) findViewById(R.id.puzzle2);
		pgame2.setOnClickListener(this);
		pgame3 = (ImageButton) findViewById(R.id.puzzle3);
		pgame3.setOnClickListener(this);
		pgame4 = (ImageButton) findViewById(R.id.puzzle4);
		pgame5 = (ImageButton) findViewById(R.id.puzzle5);
		pgame5.setOnClickListener(this);
		pgame4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.puzzle1:
			mp.pause();
			Intent ip1 = new Intent(getApplicationContext(), puzzle1.class);
			ip1.putExtra("intVariableName", 0);
			ip1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ip1);
			pgame1.setOnClickListener(null);
			break;
		case R.id.puzzle2:
			mp.pause();
			Intent ip2 = new Intent(getApplicationContext(), puzzle2.class);
			ip2.putExtra("intVariableName", 1);
			ip2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ip2);
			pgame2.setOnClickListener(null);
			break;
		case R.id.puzzle3:
			mp.pause();
			Intent ip3 = new Intent(getApplicationContext(), puzzle3.class);
			ip3.putExtra("intVariableName", 2);
			ip3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ip3);
			pgame3.setOnClickListener(null);
			break;
		case R.id.puzzle4:
			mp.pause();
			Intent ip4 = new Intent(getApplicationContext(), puzzle4.class);
			ip4.putExtra("intVariableName", 3);
			ip4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ip4);
			pgame4.setOnClickListener(null);
			break;
		case R.id.puzzle5:
			mp.pause();
			Intent ip5 = new Intent(getApplicationContext(), puzzle5.class);
			ip5.putExtra("intVariableName", 4);
			ip5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ip5);
			pgame5.setOnClickListener(null);
			break;

		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mp.pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		//mp.start();
		pgame1.setOnClickListener(this);
		pgame2.setOnClickListener(this);
		pgame3.setOnClickListener(this);
		pgame4.setOnClickListener(this);
		pgame5.setOnClickListener(this);
		
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mp.stop();
	super.onDestroy();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		mp.pause();
		super.onBackPressed();
	}
}
