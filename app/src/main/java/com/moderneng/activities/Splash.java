package com.moderneng.activities;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.model.Modelclass;
import com.moderneng.R;

public class Splash extends Activity {
    int Time_Out_millis = 1500;
    private Bitmap mBitmapSplash;

    //static int width;
    //static int height;
    //Display mDisplay;
    //Modelclass mod;
//    public static Bitmap star1, star2, star3,
//            star4, star5, sstar1, sstar2, sstar3, sstar4, sstar5, sstar6, sstar7,
//            sstar8, sstar9, sstar10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.gc();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        mBitmapSplash =  BitmapFactory.decodeResource(getResources(), R.drawable.splash, opts);
        ((ImageView) findViewById(R.id.imageview_splash)).setImageBitmap(mBitmapSplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent i = new Intent(getApplicationContext(), mainmenu.class);
                Intent i = new Intent(getApplicationContext(), LanguageMenuActivity.class);
                startActivity(i);
                mBitmapSplash.recycle();
                mBitmapSplash = null;
                finish();
            }
        }, Time_Out_millis);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
