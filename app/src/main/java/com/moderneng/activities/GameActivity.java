package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.solarenegy.ColorTool;
import com.example.solarenegy.AudioPlayer;
import com.game.utils.AppConstant;
import com.moderneng.R;
import com.moderneng.WeShineApp;

public class GameActivity extends Activity implements OnTouchListener {
    AudioPlayer mp;
    ImageView back, mImageViewTop;
    private Bitmap mBitmapTop;
    private Bitmap mBitmapBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmainmenu);


//		mImageViewTop = (ImageView) findViewById(R.id.frontimg);
//		mBitmapBottom = BitmapFactory.decodeResource(getResources(), R.drawable.arb_game_home_screen_hotspot);
//		if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
//			mBitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.game_home_screen);
//		}else{
//			mBitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.arb_game_home_screen);
//		}
//
//		mImageViewTop.setImageBitmap(mBitmapTop);
//		((ImageView) findViewById(R.id.backimg)).setImageBitmap(mBitmapBottom);
//
//		mImageViewTop = (ImageView) findViewById(R.id.frontimg);
//		if (mImageViewTop != null) {
//			mImageViewTop.setOnTouchListener(this);
//		}

    }


    @Override
    public boolean onTouch(View v, MotionEvent ev) {
        final int action = ev.getAction();
        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_UP:
                int touchcolor = gethotspotcolor(R.id.backimg, evX, evY);
                ColorTool ct = new ColorTool();
                int tolerence = 100;
                if (ct.closeMatch(Color.RED, touchcolor, tolerence)) {
                    //nextimage = 0;
                    Intent mazeActivity = new Intent(getApplicationContext(), MazeActivity.class);
                    mazeActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mazeActivity);
                    mImageViewTop.setOnTouchListener(null);
                    mImageViewTop.setOnTouchListener(null);
                } else if (ct.closeMatch(Color.BLUE, touchcolor, tolerence)) {
                    //nextimage = 1;
                    Intent match = new Intent(getApplicationContext(), matchmenu.class);
                    match.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(match);
                    mImageViewTop.setOnTouchListener(null);
                } else if (ct.closeMatch(Color.GREEN, touchcolor, tolerence)) {
                   // nextimage = 2;
                    Intent puzzle = new Intent(getApplicationContext(), puzzlemenu.class);
                    puzzle.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(puzzle);
                    mImageViewTop.setOnTouchListener(null);
                } else if (ct.closeMatch(Color.MAGENTA, touchcolor, tolerence)) {
//                    nextimage = 3;
                    Intent memory = new Intent(getApplicationContext(), Mmain.class);
                    memory.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(memory);
                    mImageViewTop.setOnTouchListener(null);
                } else if (ct.closeMatch(Color.BLACK, touchcolor, tolerence)) {
                    //nextimage = 4;
                    Intent memory = new Intent(getApplicationContext(), SunCatcherActivity.class);
                    memory.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(memory);
                    mImageViewTop.setOnTouchListener(null);
                } else if (ct.closeMatch(Color.YELLOW, touchcolor, tolerence)) {
                    //nextimage = 5;
                    Intent memory = new Intent(getApplicationContext(), BalloonActivity.class);
                    memory.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(memory);
                    mImageViewTop.setOnTouchListener(null);
                }

                break;

            default:
                break;
        }

        return true;
    }

    private int gethotspotcolor(int hotspotid, int x, int y) {
        // TODO Auto-generated method stub
        ImageView spotimg = (ImageView) findViewById(hotspotid);
        if (spotimg == null) {
            Log.d("image ", "hotspot image is not found");
            return 0;
        } else {
            spotimg.setDrawingCacheEnabled(true);
            Bitmap spotbitmap = Bitmap.createBitmap(spotimg.getDrawingCache());
            if (spotbitmap == null) {
                Log.d("image ", "hotspot bitmap is not created");
                return 0;
            } else {
                spotimg.setDrawingCacheEnabled(false);
                return spotbitmap.getPixel(x, y);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.gc();
        mp = new AudioPlayer(this, R.raw.homesound);
        mp.start();

        mImageViewTop = (ImageView) findViewById(R.id.frontimg);
        mBitmapBottom = BitmapFactory.decodeResource(getResources(), R.drawable.arb_game_home_screen_hotspot);
        if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
            mBitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.game_home_screen);
        }else{
            mBitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.arb_game_home_screen);
        }

        mImageViewTop.setImageBitmap(mBitmapTop);
        ((ImageView) findViewById(R.id.backimg)).setImageBitmap(mBitmapBottom);

        mImageViewTop = (ImageView) findViewById(R.id.frontimg);
        if (mImageViewTop != null) {
            mImageViewTop.setOnTouchListener(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp != null) {
            mp.release();
            mp = null;
        }
        System.gc();
        releaseResources();
    }


    private void releaseResources(){
        mBitmapTop.recycle();
        mBitmapTop = null;


        mBitmapBottom.recycle();
        mBitmapBottom = null;

    }
}
