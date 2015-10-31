package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.utils.AppConstant;
import com.moderneng.utils.FlavourConstants;
import com.moderneng.utils.UtilityMethods;


public class SplashActivity extends Activity {
    int Time_Out_millis = 1500;
    private Bitmap mBitmapSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.gc();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        //BitmapFactory.Options opts = new BitmapFactory.Options();
        //opts.inPreferredConfig = Bitmap.Config.RGB_565;
        //mBitmapSplash = BitmapFactory.decodeResource(getResources(), R.drawable.splash, opts);
        mBitmapSplash = WeShineApp.getBitmapFromObb("splash.png", UtilityMethods.getScreenWidth(), UtilityMethods.getScreenHeight());
        ((ImageView) findViewById(R.id.imageview_splash)).setImageBitmap(mBitmapSplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent i = new Intent(getApplicationContext(), LanguageMenuActivity.class);
                //startActivity(i);
                if(FlavourConstants.SHOW_ADVERTISEMENT){
                    Intent i = new Intent(getApplicationContext(), AdvertisementActivity.class);
                    i.putExtra(AppConstant.EXTRA_FROM, AppConstant.FROM_SPLASH);
                    startActivity(i);
                }else{
                    Intent i = new Intent(getApplicationContext(), LanguageMenuActivity.class);
                    startActivity(i);
                }
                mBitmapSplash.recycle();
                mBitmapSplash = null;
                finish();
            }
        }, Time_Out_millis);
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
