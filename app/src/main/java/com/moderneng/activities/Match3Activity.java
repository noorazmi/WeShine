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

import com.android.model.Gamemusic;
import com.android.model.ImageDragShadowBuilder;
import com.example.solarenegy.AudioPlayer;
import com.game.util.animation.AnimType;
import com.game.util.animation.AnimationUtil;
import com.game.utils.AppConstant;
import com.moderneng.R;
import com.moderneng.WeShineApp;

public class Match3Activity extends Activity {
    ImageView house, sun, golf, store, plane, drag;
    int count = 1;
    Gamemusic mp3;
    AudioPlayer mp;
    private Bitmap mBitmapText;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.match3);
        mp = new AudioPlayer(getApplicationContext(), WeShineApp.sSoundIdMatching3);
        mp.start();
        house = (ImageView) findViewById(R.id.houseblank);
        sun = (ImageView) findViewById(R.id.match3sun);
        golf = (ImageView) findViewById(R.id.golf3blank);
        store = (ImageView) findViewById(R.id.storeblank);
        plane = (ImageView) findViewById(R.id.planeblank);
        drag = (ImageView) findViewById(R.id.dragview3);

        house.setOnDragListener(new mydraglisterner());
        sun.setOnDragListener(new mydraglisterner());
        golf.setOnDragListener(new mydraglisterner());
        store.setOnDragListener(new mydraglisterner());
        plane.setOnDragListener(new mydraglisterner());
        drag.setOnTouchListener(new mytouchlistner());

        mBitmapText = BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdGoogJob);
        ((ImageView) findViewById(R.id.imageview_greeting)).setImageBitmap(mBitmapText);
    }

    private class mytouchlistner implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            mp.stop();
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                DragShadowBuilder view3 = null;
                mp3 = new Gamemusic(getApplicationContext(), R.raw.drag);
                mp3.start();
                ImageView img = (ImageView) v;
                ClipData data = ClipData.newPlainText("", "");
                if (count == 1) {
                    view3 = ImageDragShadowBuilder.fromResource(getApplicationContext(), R.drawable.sun3);
                    v.startDrag(data, view3, img, 0);
                } else if (count == 2) {
                    view3 = ImageDragShadowBuilder.fromResource(getApplicationContext(), R.drawable.flight);
                    v.startDrag(data, view3, img, 0);
                } else if (count == 3) {
                    view3 = ImageDragShadowBuilder.fromResource(getApplicationContext(), R.drawable.store);
                    v.startDrag(data, view3, img, 0);
                } else if (count == 4) {
                    view3 = ImageDragShadowBuilder.fromResource(getApplicationContext(), R.drawable.school);
                    v.startDrag(data, view3, img, 0);
                } else if (count == 5) {
                    view3 = ImageDragShadowBuilder.fromResource(getApplicationContext(), R.drawable.golf3);
                    v.startDrag(data, view3, img, 0);
                }
                return true;
            }

            return false;
        }

    }

    private class mydraglisterner implements OnDragListener {

        @Override
        public boolean onDrag(View v3, DragEvent dragevent) {
            // TODO Auto-generated method stub
            switch (dragevent.getAction()) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_STARTED:
                    drag.setVisibility(View.INVISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    // count = count;
                    break;
                case DragEvent.ACTION_DROP:
                    if (count == 1 && v3.getId() == R.id.match3sun) {
                        sun.setImageResource(R.drawable.sun3);
                        count++;
                        mp3 = new Gamemusic(getApplicationContext(), R.raw.suns);
                        mp3.start();

                        return true;
                    } else if (count == 2 && v3.getId() == R.id.planeblank) {
                        plane.setImageResource(R.drawable.flight);
                        count++;
                        mp3 = new Gamemusic(getApplicationContext(), R.raw.flight);
                        mp3.start();

                        return true;
                    } else if (count == 3 && v3.getId() == R.id.storeblank) {
                        store.setImageResource(R.drawable.store);
                        count++;
                        mp3 = new Gamemusic(getApplicationContext(), R.raw.store);
                        mp3.start();

                        return true;
                    } else if (count == 4 && v3.getId() == R.id.houseblank) {

                        house.setImageResource(R.drawable.school);
                        count++;
                        mp3 = new Gamemusic(getApplicationContext(), R.raw.school);
                        mp3.start();

                        return true;
                    } else if (count == 5 && v3.getId() == R.id.golf3blank) {
                        golf.setImageResource(R.drawable.golf3);
                        count++;

                        return true;
                    } else {
                        mp3 = new Gamemusic(getApplicationContext(), R.raw.wrongs);
                        mp3.start();
                        return false;
                    }

                case DragEvent.ACTION_DRAG_ENDED:
                    if (count == 1) {
                        drag.setImageResource(R.drawable.sun3);
                        drag.setVisibility(View.VISIBLE);
                    } else if (count == 2) {
                        drag.setImageResource(R.drawable.flight);
                        drag.setVisibility(View.VISIBLE);
                    } else if (count == 3) {
                        drag.setImageResource(R.drawable.store);
                        drag.setVisibility(View.VISIBLE);
                    } else if (count == 4) {
                        drag.setImageResource(R.drawable.school);
                        drag.setVisibility(View.VISIBLE);
                    } else if (count == 5) {
                        drag.setImageResource(R.drawable.golf3);
                        drag.setVisibility(View.VISIBLE);
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
        String uriPath = AppConstant.BASE_RESOURCE_PATH + WeShineApp.sSoundIdGoodjob;
        Uri uri = Uri.parse(uriPath);
        mMediaPlayer = MediaPlayer.create(WeShineApp.getInstance(), uri);
        mMediaPlayer.start();
    }

    private void performGreetingTextAnimation(){
        ((ImageView) findViewById(R.id.imageview_greeting)).setVisibility(View.VISIBLE);
        AnimationUtil.performAnimation((ImageView) findViewById(R.id.imageview_greeting), AnimType.ZOOM_IN, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent = new Intent(Match3Activity.this, Videoplay.class);
                intent.putExtra(AppConstant.EXTRA_VIDEO_ID, R.raw.matching3_video);
                intent.putExtra(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, AppConstant.MACHING_THREE_VIDEO_DURATION);
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
