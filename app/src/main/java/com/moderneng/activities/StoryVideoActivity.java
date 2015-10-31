package com.moderneng.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;

import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.providers.CustomAPEZProvider;
import com.moderneng.utils.AppConstant;
import com.moderneng.utils.FlavourConstants;
import com.moderneng.utils.ImageAndMediaResources;

public class StoryVideoActivity extends Activity {
    private VideoView mVideoViewStory;
    private String mVideoFileName;
    private int mVideoFileId;
    private Bitmap mBitmapThankyou;
    private String mVideoType;
    private Intent mIntent;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_story_video);
        System.gc();
        mIntent = getIntent();
        mVideoFileName = mIntent.getStringExtra(AppConstant.EXTRA_VIDEO_NAME);
        mVideoFileId = mIntent.getIntExtra(AppConstant.EXTRA_VIDEO_ID, 0);
        mVideoType = mIntent.getStringExtra(AppConstant.EXTRA_VIDEO_TYPE);
        mVideoViewStory = (VideoView) this.findViewById(R.id.videoview_game_end);
    }

    private void startNextLevel() {

        if (mVideoType != null && mVideoType.equals(AppConstant.VIDEO_TYPE_STORY)) {
            showStoryEndImage();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = null;
        if (mIntent.getStringExtra(AppConstant.EXTRA_VIDEO_LOCATION).equals(AppConstant.EXTRA_VIDEO_LOCATION_APK)) {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + mVideoFileId);
        } else {//Fetch file from .obb
            uri = CustomAPEZProvider.buildUri(WeShineApp.MEDIA_FILE_BASE_PATH + mVideoFileName + ".mp4");
        }
        mVideoViewStory.setVideoURI(uri);
        mVideoViewStory.start();

        int videoDuration = mIntent.getIntExtra(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, 0);

        mCountDownTimer = new CountDownTimer(videoDuration, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished <= 1000) {
                    startNextLevel();
                }
            }

            @Override
            public void onFinish() {

            }
        };

        mCountDownTimer.start();


        mVideoViewStory.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
            }
        });

    }

    private void showStoryEndImage() {

        mBitmapThankyou = WeShineApp.getBitmapFromObb(ImageAndMediaResources.sImageIdStoryEndThankYouImage);
        ((ImageView) findViewById(R.id.imageview_thankyou)).setImageBitmap(mBitmapThankyou);
        findViewById(R.id.imageview_thankyou).setVisibility(View.VISIBLE);
        findViewById(R.id.videoview_game_end).setVisibility(View.GONE);

        if (FlavourConstants.SHOW_ADVERTISEMENT) {
            Intent i = new Intent(getApplicationContext(), AdvertisementActivity.class);
            startActivity(i);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }

        if (mBitmapThankyou != null) {
            mBitmapThankyou.recycle();
        }
    }
}
