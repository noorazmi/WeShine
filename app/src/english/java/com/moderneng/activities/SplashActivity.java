package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.utils.AppConstant;

public class SplashActivity extends Activity {
    private int TIME_OUT_MILLISEC = 1500;
    private Bitmap mBitmapSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.gc();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        mBitmapSplash = BitmapFactory.decodeResource(getResources(), R.drawable.splash, opts);
        ((ImageView) findViewById(R.id.imageview_splash)).setImageBitmap(mBitmapSplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                WeShineApp.setLanguage(AppConstant.LANGUAGE_ENGLISH);
                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(i);
                mBitmapSplash.recycle();
                mBitmapSplash = null;
                finish();
            }
        }, TIME_OUT_MILLISEC);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mBitmapSplash != null){
            mBitmapSplash.recycle();
            mBitmapSplash = null;
        }
    }
}