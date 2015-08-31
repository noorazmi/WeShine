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
import android.widget.RelativeLayout;

import com.android.model.Gamemusic;
import com.android.model.ImageDragShadowBuilder;
import com.example.solarenegy.AudioPlayer;
import com.game.utils.AppConstant;
import com.moderneng.R;

public class match4 extends Activity {
	ImageView sun4, redbuoy, ship, dolfin, greenb, drag4;
	int count = 1;
	AudioPlayer mp;
	Gamemusic mp3, mp5;
	int sunx, suny, redx, redy, redy3, shipx, shipy, dolfinx, dolfiny;
	RelativeLayout li;
	int delayMillis = 800;
	Boolean mp5play = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
		setContentView(R.layout.match4);
		mp = new AudioPlayer(getApplicationContext(), R.raw.matching4);
		mp.start();
		sun4 = (ImageView) findViewById(R.id.sun4v);
		redbuoy = (ImageView) findViewById(R.id.redbuoyv);
		ship = (ImageView) findViewById(R.id.vessel1v);
		dolfin = (ImageView) findViewById(R.id.dolfinv);
		greenb = (ImageView) findViewById(R.id.greenbouyv);
		drag4 = (ImageView) findViewById(R.id.drag4v);
		sun4.setOnDragListener(new Mydraglistener());
		redbuoy.setOnDragListener(new Mydraglistener());
		ship.setOnDragListener(new Mydraglistener());
		dolfin.setOnDragListener(new Mydraglistener());
		greenb.setOnDragListener(new Mydraglistener());
		li = (RelativeLayout) findViewById(R.id.m4mainl);
		drag4.setOnTouchListener(new Mytouchlistener());
	}

	private class Mytouchlistener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			mp.stop();
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				DragShadowBuilder view3 = null;
				ClipData data = ClipData.newPlainText("", "");
				ImageView img = (ImageView) v;
				mp3 = new Gamemusic(getApplicationContext(), R.raw.drag);
				mp3.start();
				if (count == 1) {
					view3 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.sun4);
					v.startDrag(data, view3, img, 0);
				} else if (count == 2) {
					view3 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.redbouy);
					v.startDrag(data, view3, img, 0);
				} else if (count == 3) {
					view3 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.vessal1);
					v.startDrag(data, view3, img, 0);
				} else if (count == 4) {
					view3 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.dolfin);
					v.startDrag(data, view3, img, 0);
				} else if (count == 5) {
					view3 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.greenbouy);
					v.startDrag(data, view3, img, 0);
				}
				return true;
			}
			return false;
		}
	}

	private class Mydraglistener implements OnDragListener {
		@Override
		public boolean onDrag(View dragview, DragEvent dragEvent) {
			// TODO Auto-generated method stub
			switch (dragEvent.getAction()) {
			case DragEvent.ACTION_DRAG_ENTERED:
				break;
			case DragEvent.ACTION_DRAG_STARTED:
				drag4.setVisibility(View.INVISIBLE);
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				break;
			case DragEvent.ACTION_DROP:
				if (count == 1 && dragview.getId() == R.id.sun4v) {
					sun4.setImageResource(R.drawable.sun4);
					mp3 = new Gamemusic(getApplicationContext(), R.raw.suns);
					mp3.start();
					count++;
					
					return true;
				} else if (count == 2 && dragview.getId() == R.id.redbuoyv) {
					redbuoy.setImageResource(R.drawable.redbouy);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.bouy);
					mp3.start();
				
					return true;
				} else if (count == 3 && dragview.getId() == R.id.vessel1v) {
					ship.setImageResource(R.drawable.vessal1);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.vessel);
					mp3.start();
			
					return true;
				} else if (count == 4 && dragview.getId() == R.id.dolfinv) {
					dolfin.setImageResource(R.drawable.dolfin);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.dolfina);
					mp3.start();
			
					return true;
				} else if (count == 5 && dragview.getId() == R.id.greenbouyv) {
					greenb.setImageResource(R.drawable.greenbouy);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.bouy);
					mp3.start();
					return true;
				} else {
					count = count;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.wrongs);
					mp3.start();
					return false;
				}

			case DragEvent.ACTION_DRAG_ENDED:
				if (count == 1) {
					drag4.setImageResource(R.drawable.sun4);
					drag4.setVisibility(View.VISIBLE);
				} else if (count == 2) {
					drag4.setImageResource(R.drawable.redbouy);
					drag4.setVisibility(View.VISIBLE);
				} else if (count == 3) {
					drag4.setImageResource(R.drawable.vessal1);
					drag4.setVisibility(View.VISIBLE);
				} else if (count == 4) {
					drag4.setImageResource(R.drawable.dolfin);
					drag4.setVisibility(View.VISIBLE);
				} else if (count == 5) {
					drag4.setImageResource(R.drawable.greenbouy);
					drag4.setVisibility(View.VISIBLE);
				} else if (count == 6) {
					count++;
					Intent imatch4 = new Intent(match4.this, Videoplay.class);
						int id=R.raw.match4;
						imatch4.putExtra("vid", id);
						imatch4.putExtra(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, AppConstant.MACHING_FOUR_VIDEO_DURATION);
						startActivity(imatch4);
					   finish();
		

				}
			}
			return true;
		}
	}

	}
