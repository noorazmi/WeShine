package com.moderneng.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
import com.example.solarenegy.playaudio;
import com.moderneng.R;



public class puzzle4 extends Activity {
	RelativeLayout p4rlay;
	ImageView p4imgv1, p4imgv2, p4imgv3, p4imgv4, p4imgv5, p4imgv6, p4imgv7,
			p4dragv;
	int count = 1;
	playaudio mp;
	Gamemusic mp3,mp7;
	int x,y;
    int millisecond=700;
Boolean play=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
		if(mainmenu.height<=480){
			setContentView(R.layout.puzzle1small);
		}else{
		setContentView(R.layout.puzzle2);
		}
		p4rlay = (RelativeLayout) findViewById(R.id.mainlay);
		p4rlay.setBackgroundResource(R.drawable.p4bg);
		p4dragv = (ImageView) findViewById(R.id.pdragv);
		p4dragv.setImageResource(R.drawable.p4m5);
		mp = new playaudio(getApplicationContext(), R.raw.puzzle4);
		mp.start();
		p4imgv1 = (ImageView) findViewById(R.id.ltop);
		p4imgv2 = (ImageView) findViewById(R.id.lmiddle);
		p4imgv3 = (ImageView) findViewById(R.id.lbottom);
		p4imgv4 = (ImageView) findViewById(R.id.mtop);
		p4imgv5 = (ImageView) findViewById(R.id.mmiddle);
		p4imgv6 = (ImageView) findViewById(R.id.mbottom);
		p4imgv7 = (ImageView) findViewById(R.id.rmost);
		p4imgv4.bringToFront();
		p4dragv.setOnTouchListener(new Mytouchlistener());
		p4imgv1.setOnDragListener(new Mydraglistener());
		p4imgv2.setOnDragListener(new Mydraglistener());
		p4imgv3.setOnDragListener(new Mydraglistener());
		p4imgv4.setOnDragListener(new Mydraglistener());
		p4imgv5.setOnDragListener(new Mydraglistener());
		p4imgv6.setOnDragListener(new Mydraglistener());
		p4imgv7.setOnDragListener(new Mydraglistener());

	}

	private class Mytouchlistener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				mp.stop();
				mp3=new Gamemusic(getApplicationContext(), R.raw.drag);
				mp3.start();

				DragShadowBuilder p4img = null;
				ImageView img = (ImageView) v;
				ClipData data = ClipData.newPlainText("", "");
				if (count == 1) {
					p4img = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.p4m5);
					v.startDrag(data, p4img, img, 0);

				} else if (count == 2) {
					p4img = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.p4m4);
					v.startDrag(data, p4img, img, 0);

				} else if (count == 3) {
					p4img = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.p4m3);
					v.startDrag(data, p4img, img, 0);

				} else if (count == 4) {
					p4img = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.p4m1);
					v.startDrag(data, p4img, img, 0);

				} else if (count == 5) {
					p4img = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.p4m6);
					v.startDrag(data, p4img, img, 0);

				} else if (count == 6) {
					p4img = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.p4m2);
					v.startDrag(data, p4img, img, 0);

				} else if (count == 7) {
					p4img = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.p4m7);
					v.startDrag(data, p4img, img, 0);

				}
				return true;
			}
			return false;
		}

	}

	private class Mydraglistener implements OnDragListener {

		@Override
		public boolean onDrag(View dragv, DragEvent dragevent) {
			// TODO Auto-generated method stub
			switch (dragevent.getAction()) {
			case DragEvent.ACTION_DRAG_ENTERED:
				
				break;
			case DragEvent.ACTION_DRAG_STARTED:
				p4dragv.setVisibility(View.INVISIBLE);
				mp3 = new Gamemusic(getApplicationContext(), R.raw.drag);
			
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				break;
			case DragEvent.ACTION_DROP:
				if (count == 1 && dragv.getId() == R.id.mmiddle) {
					p4imgv5.setImageResource(R.drawable.p4m5);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.p2drop);
					mp3.start();
					 int[] imageCordinates = new int[2];
					 p4imgv5.getLocationOnScreen(imageCordinates);

						 x = imageCordinates[0]+(p4imgv5.getWidth()/2);
						 y = imageCordinates[1]+(p4imgv5.getHeight()/2);
					smallanim mv = new smallanim(puzzle4.this);
					p4rlay.addView(mv);
					return true;
				} else if (count == 2 && dragv.getId() == R.id.mtop) {
					p4imgv4.setImageResource(R.drawable.p4m4);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.supermp);
					mp3.start();
					 int[] imageCordinates = new int[2];
					 p4imgv4.getLocationOnScreen(imageCordinates);

						 x = imageCordinates[0]+(p4imgv4.getWidth()/2);
						 y = imageCordinates[1]+(p4imgv4.getHeight()/2);
					smallanim mv = new smallanim(puzzle4.this);
					p4rlay.addView(mv);
					return true;
				} else if (count == 3 && dragv.getId() == R.id.lbottom) {
					p4imgv3.setImageResource(R.drawable.p4m3);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.p2drop);
					mp3.start();
					 int[] imageCordinates = new int[2];
					 p4imgv3.getLocationOnScreen(imageCordinates);

						 x = imageCordinates[0]+(p4imgv3.getWidth()/2);
						 y = imageCordinates[1]+(p4imgv3.getHeight()/2);
					smallanim mv = new smallanim(puzzle4.this);
					p4rlay.addView(mv);
					return true;
				} else if (count == 4 && dragv.getId() == R.id.ltop) {
					p4imgv1.setImageResource(R.drawable.p4m1);
					count++;
					mp3 = new Gamemusic(getApplicationContext(), R.raw.p2drop);
					mp3.start();
					 int[] imageCordinates = new int[2];
					 p4imgv1.getLocationOnScreen(imageCordinates);

						 x = imageCordinates[0]+(p4imgv1.getWidth()/2);
						 y = imageCordinates[1]+(p4imgv1.getHeight()/2);
					smallanim mv = new smallanim(puzzle4.this);
					p4rlay.addView(mv);
					return true;
				} else if (count == 5 && dragv.getId() == R.id.mbottom) {
					p4imgv6.setImageResource(R.drawable.p4m6);
					count++;
			     	mp3=new Gamemusic(getApplicationContext(), R.raw.p2drop);
				    mp3.start();
					 int[] imageCordinates = new int[2];
					 p4imgv6.getLocationOnScreen(imageCordinates);

						 x = imageCordinates[0]+(p4imgv6.getWidth()/2);
						 y = imageCordinates[1]+(p4imgv6.getHeight()/2);
					smallanim mv = new smallanim(puzzle4.this);
					p4rlay.addView(mv);
					return true;
				} else if (count == 6 && dragv.getId() == R.id.lmiddle) {
					p4imgv2.setImageResource(R.drawable.p4m2);
					count++;
				    mp3=new Gamemusic(getApplicationContext(), R.raw.p2drop);
			     	mp3.start();
			   	 int[] imageCordinates = new int[2];
			   	p4imgv2.getLocationOnScreen(imageCordinates);

					 x = imageCordinates[0]+(p4imgv2.getWidth()/2);
					 y = imageCordinates[1]+(p4imgv2.getHeight()/2);
				smallanim mv = new smallanim(puzzle4.this);
				p4rlay.addView(mv);
					return true;
				} else if (count == 7 && dragv.getId() == R.id.rmost) {
					p4imgv7.setImageResource(R.drawable.p4m7);
					count++;
				 /*   mp3=new Gamemusic(getApplicationContext(), R.raw.goodjob);
				    mp3.start();*/
				    int[] imageCordinates = new int[2];
				    p4imgv7.getLocationOnScreen(imageCordinates);

				    x = imageCordinates[0]+(p4imgv7.getWidth()/2);
				    y = imageCordinates[1]+(p4imgv7.getHeight()/2);
					smallanim mv = new smallanim(puzzle4.this);
					p4rlay.addView(mv);
					return true;
				     }  else {
				    count = count;
				     mp3=new Gamemusic(getApplicationContext(), R.raw.wrongs);
				     mp3.start();

					return true;

				}

			case DragEvent.ACTION_DRAG_ENDED:
				if (count == 1) {
					p4dragv.setImageResource(R.drawable.p4m5);
					p4dragv.setVisibility(View.VISIBLE);
				} else if (count == 2) {
					p4dragv.setImageResource(R.drawable.p4m4);
					p4dragv.setVisibility(View.VISIBLE);
				} else if (count == 3) {
					p4dragv.setImageResource(R.drawable.p4m3);
					p4dragv.setVisibility(View.VISIBLE);
				} else if (count == 4) {
					p4dragv.setImageResource(R.drawable.p4m1);
					p4dragv.setVisibility(View.VISIBLE);
				} else if (count == 5) {
					p4dragv.setImageResource(R.drawable.p4m6);
					p4dragv.setVisibility(View.VISIBLE);
				} else if (count == 6) {
					p4dragv.setImageResource(R.drawable.p4m2);
					p4dragv.setVisibility(View.VISIBLE);
				} else if (count == 7) {
					p4dragv.setImageResource(R.drawable.p4m7);
					p4dragv.setVisibility(View.VISIBLE);
				}else if(count==8){
					 count++;
//					   Intent ipuzzle4 = new Intent(puzzle4.this,
//								Videoplay.class);
//						int id=R.raw.puzzle4a;
//						ipuzzle4.putExtra("vid", id);
//						ipuzzle4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						puzzle4.this.finish();
//						startActivity(ipuzzle4);

					Intent intent = new Intent(puzzle4.this, BalloonAnimationActivity.class);
					startActivityForResult(intent, 100);
		
				}
				break;

			    default:
				break;
			}
			return true;
		}
	
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Intent intent =new Intent(this,puzzle5.class);
		startActivity(intent);
		finish();

	}
	private class smallanim extends View{
	     Context ctx1;
	      int smcount=0;
	      Bitmap smstar1,smstar2,smstar3,smstar4,smstar5,smstar6,smstar7,smstar8,smstar9;
		  public smallanim(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		ctx1=context;
		smstar1=Splash.sstar1;
		smstar2=Splash.sstar2;
		smstar3=Splash.sstar3;
		smstar4=Splash.sstar4;
		smstar5=Splash.sstar5;
		smstar6=Splash.sstar6;
		smstar7=Splash.sstar7;
		smstar8=Splash.sstar8;
		smstar9=Splash.sstar9;
		}
			@Override
				protected void onDraw(Canvas canvas) {
					// TODO Auto-generated method stub
					super.onDraw(canvas);
				
					if(smcount<=15){
						canvas.drawBitmap(smstar1, x-smstar1.getWidth()/2,y-smstar1.getHeight()/2, null);
						smcount++;
						invalidate();
					}
					if(smcount>=2 && smcount<4){
						canvas.drawBitmap(smstar2,x-smstar2.getWidth()/2,y-smstar2.getHeight()/2, null);
						smcount++;
						invalidate();
					}
					if(smcount>=4 && smcount<6){
						canvas.drawBitmap(smstar3,x-smstar3.getWidth()/2,y-smstar3.getHeight()/2, null);
						smcount++;
						invalidate();
					}
					if(smcount>=6 && smcount<8){
						canvas.drawBitmap(smstar4,x-smstar4.getWidth()/2,y-smstar4.getHeight()/2, null);
						smcount++;
						invalidate();
					}
					if(smcount>=8 && smcount<10){
						canvas.drawBitmap(smstar5,x-smstar5.getWidth()/2,y-smstar5.getHeight()/2, null);
						smcount++;
						invalidate();
					}
					if(smcount>=10 && smcount<12){
						canvas.drawBitmap(smstar6,x-smstar6.getWidth()/2,y-smstar6.getHeight()/2, null);
						smcount++;
						invalidate();
					}
					if(smcount>=12 && smcount<14){
						canvas.drawBitmap(smstar7,x-smstar7.getWidth()/2,y-smstar7.getHeight()/2, null);
						smcount++;
						invalidate();
					}
					if(smcount>=14 && smcount<16){
						canvas.drawBitmap(smstar8,x-smstar8.getWidth()/2,y-smstar8.getHeight()/2, null);
						smcount++;
						invalidate();
					}
					if(smcount>=16 && smcount<18){
						canvas.drawBitmap(smstar9,x-smstar9.getWidth()/2,y-smstar9.getHeight()/2, null);
						smcount++;
						invalidate();
					}
				}
	}
}
