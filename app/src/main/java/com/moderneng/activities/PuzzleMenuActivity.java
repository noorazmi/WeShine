package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.moderneng.animation.AnimType;
import com.moderneng.animation.AnimationUtil;
import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.utils.AudioPlayer;
import com.moderneng.utils.Gamemusic;
import com.moderneng.utils.ImageAndMediaResources;

public class PuzzleMenuActivity extends Activity implements OnClickListener {
	private ImageButton pgame1, pgame2, pgame3, pgame4, pgame5;
	private Gamemusic mp;
	private Gamemusic mp4;
	private Bitmap mBitmapBg;
	private Bitmap mBitmapTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.puzzlemenun);

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
		int id = v.getId();
		switch (id) {
		case R.id.puzzle1:
			Intent ip1 = new Intent(getApplicationContext(), Puzzle1Activity.class);
			ip1.putExtra("intVariableName", 0);
			ip1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ip1);
			break;
		case R.id.puzzle2:
			Intent ip2 = new Intent(getApplicationContext(), Puzzle2Activity.class);
			ip2.putExtra("intVariableName", 1);
			ip2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ip2);
			break;
		case R.id.puzzle3:
			Intent ip3 = new Intent(getApplicationContext(), Puzzle3Activity.class);
			ip3.putExtra("intVariableName", 2);
			ip3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ip3);
			break;
		case R.id.puzzle4:
			Intent ip4 = new Intent(getApplicationContext(), Puzzle4Activity.class);
			ip4.putExtra("intVariableName", 3);
			ip4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ip4);
			break;
		case R.id.puzzle5:
			Intent ip5 = new Intent(getApplicationContext(), Puzzle5Activity.class);
			ip5.putExtra("intVariableName", 4);
			ip5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ip5);
			break;

		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if(mp4 !=null){
			mp4.release();
		}

		if(mp !=null){
			mp.release();
		}

		mBitmapBg.recycle();
		mBitmapBg = null;

		mBitmapTitle.recycle();
		mBitmapTitle = null;
	}


	@Override
	protected void onResume() {
		super.onResume();

		mBitmapBg = BitmapFactory.decodeResource(getResources(), ImageAndMediaResources.sImageIdPuzzleMenuBg);
		findViewById(R.id.linear_layout_container).setBackgroundDrawable(new BitmapDrawable(mBitmapBg));

		mBitmapTitle =  BitmapFactory.decodeResource(getResources(), ImageAndMediaResources.sImageIdPuzzle);
		((ImageView)findViewById(R.id.imageview_title)).setImageBitmap(mBitmapTitle);
		AnimationUtil.performAnimation(findViewById(R.id.imageview_title), AnimType.ZOOM_IN, null);


		mp4 = new Gamemusic(getApplicationContext(), ImageAndMediaResources.sSoundIdPuzzle);
		mp4.start();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mp = new Gamemusic(getApplicationContext(), R.raw.homesound);
				mp.start();
			}
		}, 500);
	}


}
