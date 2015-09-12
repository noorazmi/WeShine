package com.game.framents;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

import com.game.listeners.OnGameEndListener;
import com.game.util.animation.AnimType;
import com.game.util.animation.AnimationUtil;
import com.game.utils.AppConstant;
import com.game.utils.Logger;
import com.game.utils.UtilityMethods;
import com.game.views.DrawingSurface;
import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.activities.MazeMenuActivity;

public class MazeGame1Fragment extends BaseFragment implements OnGameEndListener, AnimationListener {

	// Drawing surface to draw the path on
	private DrawingSurface mDrawingSurface;
    private AnimationDrawable mBirdsAnimationDrawable;
    private AnimationDrawable mSunAnimationDrawable;

    private Bitmap mTopBitmap;
    private Bitmap mMiddleBitmap;
    private Bitmap mBottomBitmap;
	private Bitmap mBitmapTerrific;



	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game1;
	}

	@Override
	public void onResume() {
		super.onResume();

//		mTopBitmap =   BitmapFactory.decodeResource(getResources(), R.drawable.maze1_top_img);
//		mMiddleBitmap =   BitmapFactory.decodeResource(getResources(), R.drawable.maze1_middle_img);
//		mBottomBitmap =   BitmapFactory.decodeResource(getResources(), R.drawable.maze1_bottom_img);
//
//		((ImageView) getFragmentView().findViewById(R.id.mazeGame1_topImageView)).setImageBitmap(mTopBitmap);
//		((ImageView) getFragmentView().findViewById(R.id.mazeGame1_middleImageView)).setImageBitmap(mMiddleBitmap);
//		((ImageView) getFragmentView().findViewById(R.id.mazeGame1_bottomImageView)).setImageBitmap(mTopBitmap);

		mDrawingSurface = (DrawingSurface) getFragmentView().findViewById(R.id.mazeGame1_middleImageView);
		mDrawingSurface.setOnGameEndListener(this);
		mDrawingSurface.setHotSpotImageView((ImageView) getFragmentView().findViewById(R.id.mazeGame1_bottomImageView));
		mDrawingSurface.setIsTouchedWhite(true);

        setBackgroundImages();
		setAnimatedBirdsView();
		setAnimatedSunView();
		startAudioSound(R.raw.maze1_ondraw);

	}

    private void setBackgroundImages() {

        mTopBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze1_top_img);
        ((ImageView)getFragmentView().findViewById(R.id.mazeGame1_topImageView)).setImageBitmap(mTopBitmap);

        mMiddleBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze1_middle_img);
        mDrawingSurface.setImageBitmap(mMiddleBitmap);

        mBottomBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze1_bottom_img);
        ((ImageView)getFragmentView().findViewById(R.id.mazeGame1_bottomImageView)).setImageBitmap(mBottomBitmap);

		mBitmapTerrific = BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdTerrific);
		((ImageView)getFragmentView().findViewById(R.id.mazeGame1_terrificImageView)).setImageBitmap(mBitmapTerrific);


    }

    private void setAnimatedBirdsView() {
        //mBirdsAnimationDrawable = new AnimationDrawable();
        int[] animationImages = {R.drawable.birds1, R.drawable.birds2, R.drawable.birds3, R.drawable.birds4, R.drawable.birds5, R.drawable.birds6, R.drawable.birds7, R.drawable.birds8, R.drawable.birds9, R.drawable.birds10, R.drawable.birds11, R.drawable.birds12, R.drawable.birds13, R.drawable.birds14, R.drawable.birds15, R.drawable.birds16, R.drawable.birds17, R.drawable.birds18, R.drawable.birds19, R.drawable.birds20, R.drawable.birds21, R.drawable.birds22, R.drawable.birds23, R.drawable.birds24, R.drawable.birds25, R.drawable.birds26, R.drawable.birds27, R.drawable.birds28, R.drawable.birds29, R.drawable.birds30, R.drawable.birds31, R.drawable.birds32};
        //mBirdsAnimationDrawable = getAnimationDrawable(animationImages, getResources().getInteger(R.integer.birds_animation_frame_duration));
        mBirdsAnimationDrawable = getAnimationDrawable(animationImages, getResources().getInteger(R.integer.birds_animation_frame_duration));
        mBirdsAnimationDrawable.setOneShot(false);
        ((ImageView) getFragmentView().findViewById(R.id.mazeGame1_birdsImageView)).setImageDrawable(mBirdsAnimationDrawable);
        mBirdsAnimationDrawable.start();
	}

	private void setAnimatedSunView() {



		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		Logger.error(getTag(), "size%%%%%%%%%%@@@@@@@@************::"+screenSize);
		ImageView imageView = (ImageView) getFragmentView().findViewById(R.id.mazeGame1_sunImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if(screenSize >= 9.4){
			params.width = (int) UtilityMethods.convertDpToPixel(190, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(192, WeShineApp.getInstance());
		}else if(screenSize >= 6.9){
			params.width = (int) UtilityMethods.convertDpToPixel(140, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(148, WeShineApp.getInstance());
		}else {
			params.width = (int) UtilityMethods.convertDpToPixel(80, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(80, WeShineApp.getInstance());
		}
		
		// existing height is ok as is, no need to edit it
		imageView.setLayoutParams(params);

        int[] animationImages = {R.drawable.maze1sun_0, R.drawable.maze1sun_1,R.drawable.maze1sun_2,R.drawable.maze1sun_3,R.drawable.maze1sun_4,R.drawable.maze1sun_5,R.drawable.maze1sun_6,R.drawable.maze1sun_7,R.drawable.maze1sun_8,R.drawable.maze1sun_9,R.drawable.maze1sun_10,R.drawable.maze1sun_11,R.drawable.maze1sun_12,R.drawable.maze1sun_13,R.drawable.maze1sun_14,R.drawable.maze1sun_15,R.drawable.maze1sun_16,R.drawable.maze1sun_17,R.drawable.maze1sun_18,R.drawable.maze1sun_19,R.drawable.maze1sun_20};
        mSunAnimationDrawable = getAnimationDrawable(animationImages, getResources().getInteger(R.integer.maze1sun_animation_frame_duration));
        mSunAnimationDrawable.setOneShot(false);
        ((ImageView) getFragmentView().findViewById(R.id.mazeGame1_sunImageView)).setImageDrawable(mSunAnimationDrawable);
        mSunAnimationDrawable.start();
        //AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame1_sunImageView), R.drawable.sun_animation);
	}

	@Override
	public void onGameEnd(boolean isSuccessful) {
		if (isSuccessful) {
			AnimationUtil.performAnimation(getFragmentView().findViewById(R.id.mazeGame1_terrificImageView), AnimType.ZOOM_IN, this);
		} else {
			// Reset the view and let the user try to draw right path again.
			//resetDrawingSurface();
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		startAudioSound(WeShineApp.sSoundIdTerrific);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		//((MazeActivity) getActivity()).popTopFragment();
		Bundle bundle = getArguments();
		if (bundle == null) {
			bundle = new Bundle();
		}
		bundle.putInt(AppConstant.VIDEO_FILE_NAME, R.raw.maze1_end_video);
		bundle.putInt(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, AppConstant.MAZE_ONE_VIDEO_DURATION);

		((MazeMenuActivity) getActivity()).attachGameEndVideoFragment(bundle);
		AnimationUtil.performAnimation(getFragmentView().findViewById(R.id.mazeGame1_terrificImageView), AnimType.ZOOM_OUT, null);
		//resetDrawingSurface();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

	/**
	 * Resets the drawing surface. Every the paths drawn on the surface will be
	 * erased.
	 */
//	private void resetDrawingSurface() {
//		mDrawingSurface.reset();
//	}

    private AnimationDrawable getAnimationDrawable(int[] drawables, int duration){

        AnimationDrawable newAnim = new AnimationDrawable();
		for (int drawable : drawables) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawable);
			BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
			newAnim.addFrame(bitmapDrawable, duration);
		}
        return newAnim;
    }

    private void releaseResources(){
        recycle(mBirdsAnimationDrawable);
        mBirdsAnimationDrawable = null;

        recycle(mSunAnimationDrawable);
        mSunAnimationDrawable = null;

        mTopBitmap.recycle();
        mTopBitmap = null;

        mMiddleBitmap.recycle();
        mMiddleBitmap = null;

        mBottomBitmap.recycle();
        mBottomBitmap = null;

		mBitmapTerrific.recycle();
		mBitmapTerrific = null;
    }


    private void recycle(AnimationDrawable animationDrawable) {
        int noOfFrames = animationDrawable.getNumberOfFrames();
        for (int i = 0; i < noOfFrames; ++i){
            Drawable frame = animationDrawable.getFrame(i);
            if (frame instanceof BitmapDrawable) {
                ((BitmapDrawable)frame).getBitmap().recycle();
            }
            frame.setCallback(null);
        }
        animationDrawable.setCallback(null);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        releaseResources();
    }

    @Override
	protected void onAudioComplete(int audioFileId) {
		switch (audioFileId) {
		case R.raw.maze1_ondraw:
			startAudioSound(WeShineApp.sSoundIdMaze1);
			break;

		default:
			break;
		}
	}

}
