package com.moderneng.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.animation.AnimType;
import com.moderneng.animation.AnimationUtil;
import com.moderneng.utils.AppConstant;
import com.moderneng.utils.AudioPlayer;
import com.moderneng.utils.Gamemusic;
import com.moderneng.utils.ImageAndMediaResources;
import com.moderneng.views.ImageDragShadowBuilder;

public class Match4Activity extends Activity {
    private ImageView sun4, redbuoy, ship, dolfin, greenb, drag4;
    private int count = 1;
    private AudioPlayer mp;
    private Gamemusic mp3, mp5;
    private int sunx, suny, redx, redy, redy3, shipx, shipy, dolfinx, dolfiny;
    private RelativeLayout li;
    private int delayMillis = 800;
    private Boolean mp5play = true;
    private Bitmap mBitmapText;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.match4);
        mp = new AudioPlayer(getApplicationContext(), ImageAndMediaResources.sSoundIdMatching4);
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

        mBitmapText = BitmapFactory.decodeResource(getResources(), ImageAndMediaResources.sImageIdGoogJob);
        ((ImageView) findViewById(R.id.imageview_greeting)).setImageBitmap(mBitmapText);

    }

    private class Mytouchlistener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
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
                        startAudioSound();
                        performGreetingTextAnimation();
                    }
            }
            return true;
        }
    }

    protected void startAudioSound() {
        String uriPath = AppConstant.BASE_RESOURCE_PATH + ImageAndMediaResources.sSoundIdGoodjob;
        Uri uri = Uri.parse(uriPath);
        mMediaPlayer = MediaPlayer.create(WeShineApp.getInstance(), uri);
        mMediaPlayer.start();
    }


    private void performGreetingTextAnimation(){
        findViewById(R.id.imageview_greeting).setVisibility(View.VISIBLE);
        AnimationUtil.performAnimation(findViewById(R.id.imageview_greeting), AnimType.ZOOM_IN, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent = new Intent(Match4Activity.this, Videoplay.class);
                intent.putExtra(AppConstant.EXTRA_VIDEO_ID, R.raw.matching4_video);
                intent.putExtra(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, AppConstant.MACHING_FOUR_VIDEO_DURATION);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mBitmapText != null){
            mBitmapText.recycle();
            mBitmapText = null;
        }

        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
