package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.android.model.Gamemusic;
import com.example.solarenegy.ColorTool;
import com.example.solarenegy.AudioPlayer;
import com.game.util.animation.AnimType;
import com.game.util.animation.AnimationUtil;
import com.moderneng.R;
import com.moderneng.WeShineApp;

public class MemoryGamesMenuActivity extends Activity implements View.OnTouchListener {
    private ImageView frontimg, backimg, bird, bird2, beev, bird3;
    private Animation bird1;
    private Gamemusic mp;
    private AudioPlayer mp4;
    private Boolean isopen = true;
    private Bitmap mBitmapFront;
    private Bitmap mBitmapBack;
    private Bitmap mBitmapTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_memory_games_menu);

        bird = (ImageView) findViewById(R.id.sunim);
        bird2 = (ImageView) findViewById(R.id.bird2);
        beev = (ImageView) findViewById(R.id.bee);
        beev.setBackgroundResource(R.drawable.bee);
        bird.setBackgroundResource(R.drawable.word1);
        bird2.setBackgroundResource(R.drawable.word2);
        bird3 = (ImageView) findViewById(R.id.bird3);
        bird3.setBackgroundResource(R.drawable.bird3);
        AnimationDrawable bird3anim = (AnimationDrawable) bird3.getBackground();
        bird3anim.start();
        AnimationDrawable bird2anim = (AnimationDrawable) bird2.getBackground();
        bird2anim.start();
        AnimationDrawable beeanim = (AnimationDrawable) beev.getBackground();
        beeanim.start();
        AnimationDrawable gyroAnimation = (AnimationDrawable) bird.getBackground();
        gyroAnimation.start();
        //mp = new Gamemusic(getApplicationContext(), R.raw.mgames);
//        if (WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)) {
//            mp = new Gamemusic(getApplicationContext(), R.raw.mgames);
//        } else if (WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ARABIC)) {
//            mp = new Gamemusic(getApplicationContext(), R.raw.memmory_games_arb);
//        }

//        mp = new Gamemusic(getApplicationContext(), WeShineApp.sSoundIdMemoryGames);
//        mp.start();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mp.pause();
//                mp4 = new AudioPlayer(getApplicationContext(), R.raw.homesound);
//                mp4.start();
//            }
//        }, 1100);
        frontimg = (ImageView) findViewById(R.id.frontimg);
        backimg = (ImageView) findViewById(R.id.backimg);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Boolean handlehere = false;
        final int action = event.getAction();
        final int evx = (int) event.getX();
        final int evy = (int) event.getY();
        int nextimage = -1;
        ImageView front = (ImageView) v.findViewById(R.id.frontimg);
        if (front == null)
            return false;
        Integer tagNum = (Integer) front.getTag();
        int currentResource = (tagNum == null) ? R.drawable.mcardfront : tagNum.intValue();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (currentResource == R.drawable.mcardfront) {
                    handlehere = true;
                } else
                    handlehere = true;
                break;
            case MotionEvent.ACTION_UP:
                int touchcolor = getHotspotColor(R.id.backimg, evx, evy);
                ColorTool ct = new ColorTool();
                int tolerence = 25;
                if (ct.closeMatch(Color.BLUE, touchcolor, tolerence)) {
                    nextimage = 0;
                } else if (ct.closeMatch(Color.RED, touchcolor, tolerence)) {
                    nextimage = 1;
                } else if (ct.closeMatch(Color.GREEN, touchcolor, tolerence)) {
                    nextimage = 2;
                } else if (ct.closeMatch(Color.WHITE, touchcolor, tolerence)) {
                    nextimage = 3;
                }
                handlehere = true;
                break;
            default:
                handlehere = false;
        }
        if (handlehere) {
            if (nextimage == 0) {
                if (mp4 != null) {
                    mp4.pause();
                }

                Intent i = new Intent(MemoryGamesMenuActivity.this, MemoryGame1Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                frontimg.setOnTouchListener(null);
            } else if (nextimage == 1) {
                if (mp4 != null) {
                    mp4.pause();
                }
                Intent i2 = new Intent(MemoryGamesMenuActivity.this, MemoryGame3Activity.class);
                i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i2);
                frontimg.setOnTouchListener(null);
            } else if (nextimage == 2) {
                if (mp4 != null) {
                    mp4.pause();
                }
                Intent i3 = new Intent(MemoryGamesMenuActivity.this, MemoryGame2Activity.class);
                i3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i3);
                frontimg.setOnTouchListener(null);
            }
        }
        return handlehere;
    }

    public int getHotspotColor(int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById(hotspotId);
        if (img == null) {
            Log.d("ImageAreasActivity", "hot spot image not found");
            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspots == null) {
                Log.d("ImageAreasActivity", "Hot spot bitmap was not created");
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                return hotspots.getPixel(x, y);
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mBitmapFront != null) {
            mBitmapFront.recycle();
            mBitmapFront = null;
        }

        if (mBitmapBack != null) {
            mBitmapBack.recycle();
            mBitmapBack = null;
        }
        if (mBitmapTitle != null) {
            mBitmapTitle.recycle();
            mBitmapTitle = null;
        }
    }

    @Override
    protected void onPause() {
        if (mp4 != null) {
            mp4.release();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mp = new Gamemusic(getApplicationContext(), WeShineApp.sSoundIdMemoryGames);
        mp.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mp4 = new AudioPlayer(getApplicationContext(), R.raw.homesound);
                mp4.start();
            }
        }, 1100);

        mBitmapFront = BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdMemoryBg);
        mBitmapBack = BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdMemoryBgHotspot);

        frontimg.setImageBitmap(mBitmapFront);
        backimg.setImageBitmap(mBitmapBack);
        frontimg.setOnTouchListener(this);

        mBitmapTitle = BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdMemoryGames);
        ((ImageView) findViewById(R.id.imageview_title)).setImageBitmap(mBitmapTitle);
        AnimationUtil.performAnimation((ImageView) findViewById(R.id.imageview_title), AnimType.ZOOM_IN, null);
    }
}
