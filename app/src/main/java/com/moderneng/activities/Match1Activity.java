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

import com.android.model.Gamemusic;
import com.android.model.ImageDragShadowBuilder;
import com.game.util.animation.AnimType;
import com.game.util.animation.AnimationUtil;
import com.game.utils.AppConstant;
import com.moderneng.R;
import com.moderneng.WeShineApp;

public class Match1Activity extends Activity {
    ImageView drag, sun, tree, golf, car, house, cloud;
    int count = 1;
    Boolean play = false, mp4play = true;
    Gamemusic mp, mp4, mp5;
    RelativeLayout li;
    private Bitmap mBitmapText;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.match1);
        mp = new Gamemusic(getApplicationContext(), WeShineApp.sSoundIdMatching1);
        mp.start();
        drag = (ImageView) findViewById(R.id.draglayout);
        golf = (ImageView) findViewById(R.id.golfblank);
        sun = (ImageView) findViewById(R.id.Sunblank);
        tree = (ImageView) findViewById(R.id.treeblank);
        house = (ImageView) findViewById(R.id.solarblank);
        cloud = (ImageView) findViewById(R.id.cloudblank);
        li = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        golf.setOnDragListener(new MyDragListener());
        sun.setOnDragListener(new MyDragListener());
        tree.setOnDragListener(new MyDragListener());
        house.setOnDragListener(new MyDragListener());
        cloud.setOnDragListener(new MyDragListener());
        drag.setOnTouchListener(new MyTouchListener());
        mBitmapText = BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdExcellent);
        ((ImageView) findViewById(R.id.imageview_greeting)).setImageBitmap(mBitmapText);
    }

    private final class MyTouchListener implements OnTouchListener {

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                DragShadowBuilder view1 = null;
                mp = new Gamemusic(getApplicationContext(), R.raw.drag);
                mp.start();
                ImageView img = (ImageView) view;
                ClipData data = ClipData.newPlainText("", "");

                if (count == 1) {
                    view1 = ImageDragShadowBuilder.fromResource(getApplicationContext(), R.drawable.cloud);
                    view.startDrag(data, view1, img, 0);
                } else if (count == 2) {
                    view1 = ImageDragShadowBuilder.fromResource(getApplicationContext(), R.drawable.sun);
                    view.startDrag(data, view1, img, 0);
                } else if (count == 3) {
                    view1 = ImageDragShadowBuilder.fromResource(getApplicationContext(), R.drawable.solar);
                    view.startDrag(data, view1, img, 0);
                } else if (count == 4) {
                    view1 = ImageDragShadowBuilder.fromResource(getApplicationContext(), R.drawable.golf);
                    view.startDrag(data, view1, img, 0);
                } else if (count == 5) {
                    view1 = ImageDragShadowBuilder.fromResource(getApplicationContext(), R.drawable.tree);
                    view.startDrag(data, view1, img, 0);
                }

                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    drag.setVisibility(View.INVISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    if (count == 1 && v.getId() == R.id.cloudblank) {
                        mp = new Gamemusic(getApplicationContext(), R.raw.clouds);
                        mp.start();
                        cloud.setImageResource(R.drawable.cloud);
                        count++;
                        return true;
                    } else if (count == 2 && v.getId() == R.id.Sunblank) {
                        sun.setImageResource(R.drawable.sun);
                        mp = new Gamemusic(getApplicationContext(), R.raw.suns);
                        mp.start();
                        count++;
                        return true;
                    } else if (count == 3 && v.getId() == R.id.solarblank) {
                        house.setImageResource(R.drawable.solar);
                        mp = new Gamemusic(getApplicationContext(),
                                R.raw.solarpanels);
                        mp.start();
                        count++;
                        return true;
                    } else if (count == 4 && v.getId() == R.id.golfblank) {
                        golf.setImageResource(R.drawable.golf);
                        mp = new Gamemusic(getApplicationContext(), R.raw.golfcart);
                        mp.start();
                        count++;
                        return true;
                    } else if (count == 5 && v.getId() == R.id.treeblank) {
                        tree.setImageResource(R.drawable.tree);
                        count++;
                        play = true;
                        return true;
                    } else {
                        mp = new Gamemusic(getApplicationContext(), R.raw.wrongs);
                        mp.start();
                        //count = count;
                        return false;
                    }
                case DragEvent.ACTION_DRAG_ENDED:
                    if (count == 1) {
                        drag.setImageResource(R.drawable.cloud);
                        drag.setVisibility(View.VISIBLE);
                    } else if (count == 2) {
                        drag.setImageResource(R.drawable.sun);
                        drag.setVisibility(View.VISIBLE);
                    } else if (count == 3) {
                        drag.setImageResource(R.drawable.solar);
                        drag.setVisibility(View.VISIBLE);
                    } else if (count == 4) {
                        drag.setImageResource(R.drawable.golf);
                        drag.setVisibility(View.VISIBLE);
                    } else if (count == 5) {
                        drag.setImageResource(R.drawable.tree);
                        drag.setVisibility(View.VISIBLE);
                    } else if (count == 6) {
                        count++;
                        startAudioSound();
                        performGreetingTextAnimation();
                    }

                default:
                    break;
            }
            return true;
        }
    }


    protected void startAudioSound() {
        String uriPath = AppConstant.BASE_RESOURCE_PATH + WeShineApp.sSoundIdExcellent;
        Uri uri = Uri.parse(uriPath);
        mMediaPlayer = MediaPlayer.create(WeShineApp.getInstance(), uri);
        mMediaPlayer.start();
    }

    private void performGreetingTextAnimation() {

        ((ImageView) findViewById(R.id.imageview_greeting)).setVisibility(View.VISIBLE);
        AnimationUtil.performAnimation((ImageView) findViewById(R.id.imageview_greeting), AnimType.ZOOM_IN, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent = new Intent(Match1Activity.this, Videoplay.class);
                intent.putExtra(AppConstant.EXTRA_VIDEO_ID, R.raw.matching1_video);
                intent.putExtra(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, AppConstant.MACHING_ONE_VIDEO_DURATION);
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
        if (mBitmapText != null) {
            mBitmapText.recycle();
            mBitmapText = null;
        }
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
