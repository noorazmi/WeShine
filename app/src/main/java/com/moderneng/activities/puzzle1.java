package com.moderneng.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.solarenegy.playaudio;
import com.game.utils.ConstantValues;
import com.moderneng.R;
public class puzzle1 extends Activity {
	ImageView imgv1, imgv2, imgv3, imgv4, imgv5, imgv6, imgv7, dragimg;
	int count = 1, viewcount = 1;
	Gamemusic mp, mp4;
	playaudio mp3;
	RelativeLayout mainlay;
	int x = -1;
	int starcount = 0;
	int y = -1;
	int x1 = -1, x2 = -1;
	int y1 = -1, y2 = -1;
      int millisecond=700;
	Boolean play = true;
	Drawable myDrawable;
	Handler h;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
				WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
		  Intent mIntent = getIntent();
	      int intValue = mIntent.getIntExtra("intVariableName", 0);
	      if(mainmenu.height<=480){
		  setContentView(R.layout.puzzle1small);
	     }else{
		setContentView(R.layout.puzzle2);
	    }
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		mainlay = (RelativeLayout) findViewById(R.id.mainlay);
		h = new Handler();
		mp3 = new playaudio(getApplicationContext(), R.raw.puzzle1);
		mp3.start();
	
		imgv1 = (ImageView) findViewById(R.id.ltop);
		imgv2 = (ImageView) findViewById(R.id.lmiddle);
		imgv3 = (ImageView) findViewById(R.id.lbottom);
		imgv4 = (ImageView) findViewById(R.id.mtop);
		imgv5 = (ImageView) findViewById(R.id.mmiddle);
		imgv6 = (ImageView) findViewById(R.id.mbottom);
		imgv7 = (ImageView) findViewById(R.id.rmost);

		imgv1.setOnDragListener(new MyDragListener());
		imgv2.setOnDragListener(new MyDragListener());
		imgv3.setOnDragListener(new MyDragListener());
		imgv4.setOnDragListener(new MyDragListener());
		imgv5.setOnDragListener(new MyDragListener());
		imgv6.setOnDragListener(new MyDragListener());
		imgv7.setOnDragListener(new MyDragListener());
		imgv4.bringToFront();
		dragimg = (ImageView) findViewById(R.id.pdragv);

		dragimg.setOnTouchListener(new MyTouchListener());

	}

	private final class MyTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			mp3.stop();
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				DragShadowBuilder view1 = null;
				mp = new Gamemusic(getApplicationContext(), R.raw.drag);
				mp.start();
				ImageView img = (ImageView) view;
				ClipData data = ClipData.newPlainText("", "");
				Point offset = new Point((int) motionEvent.getX(),
						(int) motionEvent.getY());

				// ImageDragShadowBuilder shadowBuilder;
				if (count == 1) {
					view1 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.img1);
					view.startDrag(data, view1, img, 0);

				} else if (count == 2) {
					view1 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.img2);
					view.startDrag(data, view1, img, 0);

				} else if (count == 3) {
					view1 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.img3);
					view.startDrag(data, view1, img, 0);

				} else if (count == 4) {
					view1 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.img4);
					view.startDrag(data, view1, img, 0);

				} else if (count == 5) {
					view1 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.img5);
					view.startDrag(data, view1, img, 0);

				} else if (count == 6) {
					view1 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.img6);
					view.startDrag(data, view1, img, 0);

				} else if (count == 7) {
					view1 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.img7);
					view.startDrag(data, view1, img, 0);

				}

				return true;
			}
			return false;

		}
	}

	class MyDragListener implements OnDragListener {

		@Override
		public boolean onDrag(View v, DragEvent event) {
			int action = event.getAction();
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				// do nothing
				dragimg.setVisibility(View.INVISIBLE);
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				break;
			case DragEvent.ACTION_DROP:
				// Dropped, reassign View to ViewGroup
				View view = (View) event.getLocalState();

				if (count == 1 && v.getId() == R.id.ltop) {

					mp = new Gamemusic(getApplicationContext(), R.raw.psun);
					mp.start();
					imgv1.setImageResource(R.drawable.img1);
			
					int[] imageCordinates = new int[2];
					imgv1.getLocationOnScreen(imageCordinates);

					x = imageCordinates[0] + (imgv1.getWidth() / 2);
					y = imageCordinates[1] + (imgv1.getHeight() / 2);
					smallanim mv = new smallanim(puzzle1.this);
					mainlay.addView(mv);
					count++;
					return true;
				} else if (count == 2 && v.getId() == R.id.lmiddle) {
					imgv2.setImageResource(R.drawable.img2);
					mp = new Gamemusic(getApplicationContext(), R.raw.psun);
					mp.start();

					int[] imageCordinates = new int[2];
					imgv2.getLocationOnScreen(imageCordinates);
					x = imageCordinates[0] + (imgv2.getWidth() / 2);
					y = imageCordinates[1] + (imgv2.getHeight() / 2);
					smallanim mv = new smallanim(puzzle1.this);
					mainlay.addView(mv);
					count++;
					return true;
				} else if (count == 3 && v.getId() == R.id.lbottom) {
					imgv3.setImageResource(R.drawable.img3);
					mp = new Gamemusic(getApplicationContext(), R.raw.psun);
					mp.start();
					int[] imageCordinates = new int[2];
					imgv3.getLocationOnScreen(imageCordinates);

					x = imageCordinates[0] + (imgv3.getWidth() / 2);
					y = imageCordinates[1] + (imgv3.getHeight() / 2);
					smallanim mv = new smallanim(puzzle1.this);
					mainlay.addView(mv);
					count++;
					return true;
				} else if (count == 4 && v.getId() == R.id.mtop) {
					imgv4.setImageResource(R.drawable.img4);
					count++;
					mp = new Gamemusic(getApplicationContext(), R.raw.psun);
					mp.start();
					int[] imageCordinates = new int[2];
					imgv4.getLocationOnScreen(imageCordinates);
					x = imageCordinates[0] + (imgv4.getWidth() / 2);
					y = imageCordinates[1] + (imgv4.getHeight() / 2);
					smallanim mv = new smallanim(puzzle1.this);
					mainlay.addView(mv);
					return true;
				} else if (count == 5 && v.getId() == R.id.mmiddle) {
					imgv5.setImageResource(R.drawable.img5);
					count++;
					mp = new Gamemusic(getApplicationContext(), R.raw.supermp);
					mp.start();
					int[] imageCordinates = new int[2];
					imgv5.getLocationOnScreen(imageCordinates);
					x = imageCordinates[0] + (imgv5.getWidth() / 2);
					y = imageCordinates[1] + (imgv5.getHeight() / 2);
					smallanim mv = new smallanim(puzzle1.this);
					mainlay.addView(mv);
					return true;
				} else if (count == 6 && v.getId() == R.id.mbottom) {
					imgv6.setImageResource(R.drawable.img6);
					count++;
					mp = new Gamemusic(getApplicationContext(), R.raw.psun);
					mp.start();

					int[] imageCordinates = new int[2];
					imgv6.getLocationOnScreen(imageCordinates);

					x = imageCordinates[0] + (imgv6.getWidth() / 2);
					y = imageCordinates[1] + (imgv6.getHeight() / 2);
					smallanim mv = new smallanim(puzzle1.this);
					mainlay.addView(mv);
					return true;
				} else if (count == 7 && v.getId() == R.id.rmost) {
					imgv7.setImageResource(R.drawable.img7);
					count++;
					int[] imageCordinates = new int[2];
					imgv7.getLocationOnScreen(imageCordinates);

					x = imageCordinates[0] + (imgv7.getWidth() / 2);
					y = imageCordinates[1] + (imgv7.getHeight() / 2);
					smallanim mv = new smallanim(puzzle1.this);
					mainlay.addView(mv);
					return true;
				} else {
					 mp = new Gamemusic(getApplicationContext(), R.raw.wrongs);
					  mp.start();
					  // count = count;
					   return true;
				}

			case DragEvent.ACTION_DRAG_ENDED:
				if (count == 1) {
					dragimg.setImageResource(R.drawable.img1);
					dragimg.setVisibility(View.VISIBLE);
				} else if (count == 2) {
					dragimg.setImageResource(R.drawable.img2);
					dragimg.setVisibility(View.VISIBLE);
				} else if (count == 3) {
					dragimg.setImageResource(R.drawable.img3);
					dragimg.setVisibility(View.VISIBLE);
				} else if (count == 4) {
					dragimg.setImageResource(R.drawable.img4);
					dragimg.setVisibility(View.VISIBLE);
				} else if (count == 5) {
					dragimg.setImageResource(R.drawable.img5);
					dragimg.setVisibility(View.VISIBLE);
				} else if (count == 6) {
					dragimg.setImageResource(R.drawable.img6);
					dragimg.setVisibility(View.VISIBLE);
				} else if (count == 7) {
					dragimg.setImageResource(R.drawable.img7);
					dragimg.setVisibility(View.VISIBLE);
				} else if (count == 8) {
					   count++;
//					    Intent ipuzzle1 = new Intent(puzzle1.this, Videoplay.class);
//						int id=R.raw.puzzle1a;
//						ipuzzle1.putExtra("vid", id);
//						ipuzzle1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						puzzle1.this.finish();
//						startActivity(ipuzzle1);
					Intent intent = new Intent(puzzle1.this, BalloonAnimationActivity.class);
					intent.putExtra(ConstantValues.EXTRA_GREETING_IMAGE_RESOURCE_ID, R.drawable.awsome);
					intent.putExtra(ConstantValues.EXTRA_GREETING_SOUND_ID, R.raw.awesome);
					intent.putExtra(ConstantValues.EXTRA_BALLOON_ANIMATION_SOUND_ID, R.raw.hey);
					intent.putExtra(ConstantValues.EXTRA_BALLOON_ANIMATION_SOUND_DELAY, ConstantValues.BALLOON_ANIMATION_SOUND_DELAY);

					startActivityForResult(intent, 100);
		
				}
			default:
				break;
			}
			return true;
		}
	}

	
	private class smallanim extends View {
		Context ctx1;
		int smcount = 0;
		Bitmap smstar1, smstar2, smstar3, 
		smstar4, smstar5, smstar6, smstar7,
				smstar8, smstar9;

		public smallanim(Context context) {
			super(context);
			ctx1 = context;
			smstar1 = Splash.sstar1;
			smstar2 = Splash.sstar2;
			smstar3 = Splash.sstar3;
			smstar4 = Splash.sstar4;
			smstar5 = Splash.sstar5;
			smstar6 = Splash.sstar6;
			smstar7 = Splash.sstar7;
			smstar8 = Splash.sstar8;
			smstar9 = Splash.sstar9;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			if (smcount <= 15) {
				canvas.drawBitmap(smstar1, x - smstar1.getWidth() / 2, y
						- smstar1.getHeight() / 2, null);
				smcount++;
				invalidate();
			}
			if (smcount >= 2 && smcount < 4) {
				canvas.drawBitmap(smstar2, x - smstar2.getWidth() / 2, y
						- smstar2.getHeight() / 2, null);
				smcount++;
				invalidate();
			}
			if (smcount >= 4 && smcount < 6) {
				canvas.drawBitmap(smstar3, x - smstar3.getWidth() / 2, y
						- smstar3.getHeight() / 2, null);
				smcount++;
				invalidate();
			}
			if (smcount >= 6 && smcount < 8) {
				canvas.drawBitmap(smstar4, x - smstar4.getWidth() / 2, y
						- smstar4.getHeight() / 2, null);
				smcount++;
				invalidate();
			}
			if (smcount >= 8 && smcount < 10) {
				canvas.drawBitmap(smstar5, x - smstar5.getWidth() / 2, y
						- smstar5.getHeight() / 2, null);
				smcount++;
				invalidate();
			}
			if (smcount >= 10 && smcount < 12) {
				canvas.drawBitmap(smstar6, x - smstar6.getWidth() / 2, y
						- smstar6.getHeight() / 2, null);
				smcount++;
				invalidate();
			}
			if (smcount >= 12 && smcount < 14) {
				canvas.drawBitmap(smstar7, x - smstar7.getWidth() / 2, y
						- smstar7.getHeight() / 2, null);
				smcount++;
				invalidate();
			}
			if (smcount >= 14 && smcount < 16) {
				canvas.drawBitmap(smstar8, x - smstar8.getWidth() / 2, y
						- smstar8.getHeight() / 2, null);
				smcount++;
				invalidate();
			}
			if (smcount >= 16 && smcount < 18) {
				canvas.drawBitmap(smstar9, x - smstar9.getWidth() / 2, y
						- smstar9.getHeight() / 2, null);
				smcount++;
				invalidate();
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		mp3.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mp3.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// mp4.stop();
		mp3.stop();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		mp3.start();
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent =new Intent(this,puzzle2.class);
        startActivity(intent);
        finish();

    }
}
