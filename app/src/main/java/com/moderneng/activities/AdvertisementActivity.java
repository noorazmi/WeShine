package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.utils.AppConstant;
import com.moderneng.utils.ImageAndMediaResources;
import com.moderneng.utils.UtilityMethods;

public class AdvertisementActivity extends Activity {

    private Bitmap mBitmapFront;
    private ImageView mImageViewAdvertisement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        mImageViewAdvertisement = (ImageView)findViewById(R.id.imageview_advertisement);
    }


    public void onCloseButtonClick(View v){
        Intent i = new Intent(getApplicationContext(), LanguageMenuActivity.class);
        startActivity(i);
        finish();
    }

    public void onAdvertisementImageClick(View v){
        UtilityMethods.openAdvertisementInWebView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mBitmapFront = BitmapFactory.decodeResource(getResources(), WeShineApp.getResourceId(ImageAndMediaResources.sImageIdAdvertisementFullScreen, AppConstant.RESOURCE_TYPE_DRAWABLE));
        mImageViewAdvertisement.setImageBitmap(mBitmapFront);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mBitmapFront != null) {
            mBitmapFront.recycle();
            mBitmapFront = null;
        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
