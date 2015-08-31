package com.moderneng.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.model.Gamemusic;
import com.android.model.ImageDragShadowBuilder;
import com.example.solarenegy.AudioPlayer;
import com.game.utils.AppConstant;
import com.moderneng.R;

public class match5 extends Activity {
	ImageView sun5, solar5, golf5, tree5, ehouse, drag5;
	int count = 1;
	AudioPlayer mp;
	Gamemusic mp3,mp5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mp = new AudioPlayer(getApplicationContext(), R.raw.matching5);
		mp.start();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match5);	 
		sun5 = (ImageView) findViewById(R.id.sun5v);
		solar5 = (ImageView) findViewById(R.id.solar5v);
		golf5 = (ImageView) findViewById(R.id.golf5v);
		tree5 = (ImageView) findViewById(R.id.tree5v);
		ehouse = (ImageView) findViewById(R.id.house5v);
		drag5 = (ImageView) findViewById(R.id.dragv5);
		sun5.setOnDragListener(new Mydraglistner());
		solar5.setOnDragListener(new Mydraglistner());
		golf5.setOnDragListener(new Mydraglistner());
		tree5.setOnDragListener(new Mydraglistner());
		ehouse.setOnDragListener(new Mydraglistner());
		drag5.setOnTouchListener(new Mytouchlisterner());
	}

	private class Mytouchlisterner implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			mp.stop();
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				DragShadowBuilder view5 = null;
				mp3 = new Gamemusic(getApplicationContext(), R.raw.drag);
				mp3.start();
				ClipData data = ClipData.newPlainText("", "");
				ImageView img = (ImageView) v;

				if (count == 1) {
					view5 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.sun5);
					v.startDrag(data, view5, img, 0);

				} else if (count == 2) {
					view5 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.tree5);
					v.startDrag(data, view5, img, 0);

				} else if (count == 3) {
					view5 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.golf5);
					v.startDrag(data, view5, img, 0);

				} else if (count == 4) {
					view5 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.solar5);
					v.startDrag(data, view5, img, 0);
				} else if (count == 5) {
					view5 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.inverter);
					v.startDrag(data, view5, img, 0);
				}
				return true;
			}
			return false;
		}
	}
	private class Mydraglistner implements OnDragListener {

		@Override
		public boolean onDrag(View dview5, DragEvent devent) {
			// TODO Auto-generated method stub
			
			switch (devent.getAction()) {
			case DragEvent.ACTION_DRAG_ENTERED:
				break;
			case DragEvent.ACTION_DRAG_STARTED:
				drag5.setVisibility(View.INVISIBLE);
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				break;
			case DragEvent.ACTION_DROP:
				if (count == 1 && dview5.getId() == R.id.sun5v) {
					sun5.setImageResource(R.drawable.sun5);
					mp3 = new Gamemusic(getApplicationContext(), R.raw.suns);
					mp3.start();
				    count++;
					return true;
				} else if (count == 2 && dview5.getId() == R.id.tree5v) {
					tree5.setImageResource(R.drawable.tree5);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.trees);
					mp3.start();
					return true;
				} else if (count == 3 && dview5.getId() == R.id.golf5v) {
					golf5.setImageResource(R.drawable.golf5);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.golfcart);
					mp3.start();
		           return true;
				} else if (count == 4 && dview5.getId() == R.id.solar5v) {
					solar5.setImageResource(R.drawable.solar5);
					count++;
					mp3 = new Gamemusic(getApplicationContext(),
							R.raw.solarpanels);
					mp3.start();
					return true;
				} else if (count == 5 && dview5.getId() == R.id.house5v) {
					ehouse.setImageResource(R.drawable.inverter);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.store);
					 mp3.start();
					return true;
				} else {
					mp3 = new Gamemusic(getApplicationContext(), R.raw.wrongs);
					mp3.start();
					return false;
				}
			case DragEvent.ACTION_DRAG_ENDED:
				if (count == 1) {
					drag5.setImageResource(R.drawable.sun5);
					drag5.setVisibility(View.VISIBLE);
				} else if (count == 2) {
					drag5.setImageResource(R.drawable.tree5);
					drag5.setVisibility(View.VISIBLE);
				} else if (count == 3) {
					drag5.setImageResource(R.drawable.golf5);
					drag5.setVisibility(View.VISIBLE);
				} else if (count == 4) {
					drag5.setImageResource(R.drawable.solar5);
					drag5.setVisibility(View.VISIBLE);
				} else if (count == 5) {
					drag5.setImageResource(R.drawable.inverter);
					drag5.setVisibility(View.VISIBLE);
				} else if (count == 6) {
					count++;
					Intent imatch5 = new Intent(match5.this, Videoplay.class);
					int id=R.raw.match5ss;
					imatch5.putExtra("vid", id);
					imatch5.putExtra(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, AppConstant.MACHING_FIVE_VIDEO_DURATION);
					startActivity(imatch5);
					finish();
				}
			}
			return true;

		}

	}
	
}
