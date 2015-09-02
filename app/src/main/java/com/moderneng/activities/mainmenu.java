package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.solarenegy.ColorTool;
import com.example.solarenegy.AudioPlayer;
import com.game.utils.AppConstant;
import com.moderneng.R;
import com.moderneng.WeShineApp;

public class mainmenu extends Activity implements View.OnTouchListener {
    AudioPlayer mp;
    private ImageView mImageViewTop, mImageViewBottom;
    static int width;
    static int height;
    Display mDisplay;
    private Bitmap mBitmapTop;
    private Bitmap mBitmapBottom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mainmenu);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mDisplay = this.getWindowManager().getDefaultDisplay();
        height = mDisplay.getHeight();
        width = mDisplay.getWidth();
//        mp = new AudioPlayer(this, R.raw.homesound);
//        mp.start();
//
//        mImageViewTop = (ImageView) findViewById(R.id.image);
//        mBitmapBottom = BitmapFactory.decodeResource(getResources(), R.drawable.home_screen_hotspot);
//        if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
//            mBitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.home_screen);
//        }else{
//            mBitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.arb_home_screen);
//        }
//
//        mImageViewTop.setImageBitmap(mBitmapTop);
//        ((ImageView) findViewById(R.id.image_areas)).setImageBitmap(mBitmapBottom);
//        if (mImageViewTop != null) {
//            mImageViewTop.setOnTouchListener(this);
//        }
    }

    public boolean onTouch(View v, MotionEvent ev) {
        boolean handledHere = false;
        final int action = ev.getAction();
        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();
        int nextImage = -1;

        ImageView imageView = (ImageView) v.findViewById(R.id.image);
        if (imageView == null) {
            return false;
        }
        Integer tagNum = (Integer) imageView.getTag();
        int currentResource = (tagNum == null) ? R.drawable.p2_ship_default : tagNum.intValue();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (currentResource == R.drawable.p2_ship_default) {
                    nextImage = R.drawable.p2_ship_pressed;
                    handledHere = true;
                } else
                    handledHere = true;
                break;

            case MotionEvent.ACTION_UP:

                int touchColor = getHotspotColor(R.id.image_areas, evX, evY);
                ColorTool ct = new ColorTool();
                int tolerance = 75;
                nextImage = R.drawable.p2_ship_default;
                if (ct.closeMatch(Color.RED, touchColor, tolerance)) {
                    nextImage = 0;
                } else if (ct.closeMatch(Color.GREEN, touchColor, tolerance)) {
                    nextImage = 1;
                } else if (ct.closeMatch(Color.BLUE, touchColor, tolerance)) {
                    nextImage = 2;
                } else if (ct.closeMatch(Color.WHITE, touchColor, tolerance)) {
                    nextImage = 3;
                }
                if (currentResource == nextImage) {
                    nextImage = -1;
                }
                handledHere = true;
                break;

            default:
                handledHere = false;
        } // end switch

        if (handledHere) {

            if (nextImage == 0) {
                mp.release();
                Intent introintent = new Intent(getApplicationContext(), Videoplay.class);
                if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
                    introintent.putExtra(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, AppConstant.STORY_VIDEO_DURATION);
                    introintent.putExtra("vid", R.raw.story2);
                }else if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ARABIC)){
                    introintent.putExtra(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, AppConstant.ARB_STORY_VIDEO_DURATION);
                    introintent.putExtra("vid", R.raw.story_arb);
                }
                startActivity(introintent);
            }
            if (nextImage == 1) {
                mp.release();
                Intent i1 = new Intent(getApplicationContext(), GameActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i1);
                mImageViewTop.setOnTouchListener(null);
            }
            if (nextImage == 2) {
                mp.release();
                Intent i = new Intent(getApplicationContext(), Intro.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                mImageViewTop.setOnTouchListener(null);
            }
            if (nextImage == 3) {

            }
        }
        return handledHere;
    }

    public int getHotspotColor(int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById(hotspotId);
        if (img == null) {
            Log.d("ImageAreasActivity", " Hot spot image not found");
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
    protected void onResume() {
        super.onResume();
        mp = new AudioPlayer(this, R.raw.homesound);
        mp.start();

        mImageViewTop = (ImageView) findViewById(R.id.image);
        mBitmapBottom = BitmapFactory.decodeResource(getResources(), R.drawable.home_screen_hotspot);
        if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
            mBitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.home_screen);
        }else{
            mBitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.arb_home_screen);
        }

        mImageViewTop.setImageBitmap(mBitmapTop);
        ((ImageView) findViewById(R.id.image_areas)).setImageBitmap(mBitmapBottom);
        if (mImageViewTop != null) {
            mImageViewTop.setOnTouchListener(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.release();
        mp = null;
        releaseResources();
    }

    private void releaseResources(){
        mBitmapTop.recycle();
        mBitmapTop = null;


        mBitmapBottom.recycle();
        mBitmapBottom = null;

    }

}
