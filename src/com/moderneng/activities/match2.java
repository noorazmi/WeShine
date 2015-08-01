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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.model.Gamemusic;
import com.android.model.ImageDragShadowBuilder;
import com.example.solarenegy.playaudio;
import com.moderneng.R;

public class match2 extends Activity {
	ImageView stower, golf, sun, boat, drag, wtower;
	int  count = 1;
	Gamemusic mp;
	playaudio mp3;
   //	TranslateAnimation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.match2);
		mp3 = new playaudio(getApplicationContext(), R.raw.matching2);
		mp3.start();
		stower = (ImageView) findViewById(R.id.stowerblank);
		golf = (ImageView) findViewById(R.id.golf1blank);
		sun = (ImageView) findViewById(R.id.sun1blank);
		boat = (ImageView) findViewById(R.id.boatblank);
		drag = (ImageView) findViewById(R.id.draglayout);
		wtower = (ImageView) findViewById(R.id.towerblank);
		stower.setOnDragListener(new Mydraglisterner());
		golf.setOnDragListener(new Mydraglisterner());
		sun.setOnDragListener(new Mydraglisterner());
		boat.setOnDragListener(new Mydraglisterner());
		wtower.setOnDragListener(new Mydraglisterner());
		drag.setOnTouchListener(new Mytouchlistener());
	}

	private final class Mytouchlistener implements OnTouchListener {

		@Override
		public boolean onTouch(View view, MotionEvent event) {
			// TODO Auto-generated method stub
			mp3.stop();

			if (event.getAction() == event.ACTION_DOWN) {
				DragShadowBuilder view2 = null;
				mp = new Gamemusic(getApplicationContext(), R.raw.drag);
				mp.start();
				ImageView img = (ImageView) view;
				ClipData data = ClipData.newPlainText("", "");
				if (count == 1) {
					view2 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.sun2);
					view.startDrag(data, view2, img, 0);

				} else if (count == 2) {
					view2 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.bouy);
					view.startDrag(null, view2, null, 0);
				} else if (count == 3) {
					view2 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.vessal);
					view.startDrag(null, view2, null, 0);

				} else if (count == 4) {
					view2 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.golf2);
					view.startDrag(null, view2, null, 0);

				} else if (count == 5) {
					view2 = ImageDragShadowBuilder.fromResource(
							getApplicationContext(), R.drawable.pole);
					view.startDrag(null, view2, null, 0);

				}
				return true;
			} else {
				return false;
			}
		}

	}

	class Mydraglisterner implements OnDragListener {

		@Override
		public boolean onDrag(View v, DragEvent dragevent) {
			// TODO Auto-generated method stub
			switch (dragevent.getAction()) {
			case DragEvent.ACTION_DRAG_ENTERED:
				break;
			case DragEvent.ACTION_DRAG_STARTED:
				drag.setVisibility(View.INVISIBLE);
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				if (count == 1) {
					drag.setImageResource(R.drawable.sun2);
					drag.setVisibility(View.VISIBLE);
				} else if (count == 2) {
					drag.setImageResource(R.drawable.bouy);

					drag.setVisibility(View.VISIBLE);
				} else if (count == 3) {
					drag.setImageResource(R.drawable.vessal);
					drag.setVisibility(View.VISIBLE);
				} else if (count == 4) {
					drag.setImageResource(R.drawable.golf2);
					drag.setVisibility(View.VISIBLE);
				} else if (count == 5) {
					drag.setImageResource(R.drawable.pole);
					drag.setVisibility(View.VISIBLE);
				} else if (count == 6) {
					count++;
					    Intent imatch2 = new Intent(match2.this,
								Videoplay.class);
						int id=R.layout.match2;
						imatch2.putExtra("vid", id);
						startActivity(imatch2);
						finish();
				}
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				break;
			case DragEvent.ACTION_DROP:
				View v1 = (View) dragevent.getLocalState();
				if (count == 1 && v.getId() == R.id.sun1blank) {
					ViewGroup group = (ViewGroup) v.getParent();
					mp = new Gamemusic(getApplicationContext(), R.raw.suns);
					mp.start();
					sun.setImageResource(R.drawable.sun2);
					int[] imagecordinates=new int[2];
					sun.getLocationOnScreen(imagecordinates);
					// sun.setOnClickListener(null);
					count++;
					return true;
				} else if (count == 2 && v.getId() == R.id.towerblank) {
					ViewGroup group = (ViewGroup) v.getParent();
					wtower.setImageResource(R.drawable.bouy);
					mp = new Gamemusic(getApplicationContext(), R.raw.bouy);
					mp.start();
					count++;
					// wtower.setOnClickListener(null);
					return true;

				} else if (count == 3 && v.getId() == R.id.boatblank) {
					ViewGroup group = (ViewGroup) v.getParent();
					boat.setImageResource(R.drawable.vessal);
					count++;
					mp = new Gamemusic(getApplicationContext(), R.raw.vessel);
					// boat.setOnClickListener(null);
					mp.start();
					return true;
				} else if (count == 4 && v.getId() == R.id.golf1blank) {
					ViewGroup group = (ViewGroup) v.getParent();
					golf.setImageResource(R.drawable.golf2);
					count++;
					int[] imagecordinates=new int[2];
					golf.getLocationOnScreen(imagecordinates);
					mp = new Gamemusic(getApplicationContext(), R.raw.golfcart);
					mp.start();
					// golf.setOnClickListener(null);
					return true;
				} else if (count == 5 && v.getId() == R.id.stowerblank) {
					ViewGroup group = (ViewGroup) v.getParent();
					stower.setImageResource(R.drawable.pole);
					count++;
			
					return true;
				} else {
					mp = new Gamemusic(getApplicationContext(), R.raw.wrongs);
					mp.start();
					count = count;
					return false;
				}
			}

			return true;
		}
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
	}

	

}
