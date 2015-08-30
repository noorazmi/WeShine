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
import com.game.utils.ConstantValues;
import com.game.utils.UtilityMethods;
import com.game.views.DrawingSurface;
import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.activities.MazeActivity;

public class MazeGame3Fragment extends BaseFragment implements OnGameEndListener, AnimationListener {

	// Drawing surface to draw the path on
	private DrawingSurface mDrawingSurface;
	private AnimationDrawable mSunAnimationDrawable;
	private  AnimationDrawable mCarAnimationDrawable;

	private Bitmap mTopBitmap;
	private Bitmap mMiddleBitmap;
	private Bitmap mBottomBitmap;



	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game3;
	}

	@Override
	public void onResume() {
		super.onResume();

		System.gc();
		mDrawingSurface = (DrawingSurface) getFragmentView().findViewById(R.id.mazeGame3_middleImageView);
		mDrawingSurface.setOnGameEndListener(this);
		mDrawingSurface.setHotSpotImageView((ImageView) getFragmentView().findViewById(R.id.mazeGame3_bottomImageView));

		setBackgroundImages();
		setAnimatedSunView();
		setAnimatinCarView();
		startAudioSound(R.raw.maze3_ondraw);
	}


	private void setBackgroundImages() {

		mTopBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze3_top_img);
		((ImageView)getFragmentView().findViewById(R.id.mazeGame3_topImageView)).setImageBitmap(mTopBitmap);

		mMiddleBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze3_middle_img);
		mDrawingSurface.setImageBitmap(mMiddleBitmap);

		mBottomBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze3_bottom_img);
		((ImageView)getFragmentView().findViewById(R.id.mazeGame3_bottomImageView)).setImageBitmap(mBottomBitmap);


	}

	private void setAnimatedSunView() {
		
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		ImageView imageView = (ImageView) getFragmentView().findViewById(R.id.mazeGame3_sunImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if(screenSize >= 10){
			params.width = (int) UtilityMethods.convertDpToPixel(198, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(198, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(70, WeShineApp.getInstance());
		}else if(screenSize >= 6.9){
			params.width = (int) UtilityMethods.convertDpToPixel(150, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(155, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(31, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(70, WeShineApp.getInstance());
		}else {
			params.width = (int) UtilityMethods.convertDpToPixel(82, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(82, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(20, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(40, WeShineApp.getInstance());
		}


		//int[] animationImages = {R.drawable.maze3sun0, R.drawable.maze3sun1,R.drawable.maze3sun2,R.drawable.maze3sun3,R.drawable.maze3sun4,R.drawable.maze3sun5,R.drawable.maze3sun6,R.drawable.maze3sun7,R.drawable.maze3sun8,R.drawable.maze3sun9,R.drawable.maze3sun10,R.drawable.maze3sun11,R.drawable.maze3sun12,R.drawable.maze3sun13,R.drawable.maze3sun14,R.drawable.maze3sun15,R.drawable.maze3sun16,R.drawable.maze3sun17,R.drawable.maze3sun18,R.drawable.maze3sun19,R.drawable.maze3sun20};
		//mSunAnimationDrawable = getAnimationDrawable(animationImages, getResources().getInteger(R.integer.maze1sun_animation_frame_duration));
		//mSunAnimationDrawable.setOneShot(false);
		//((ImageView) getFragmentView().findViewById(R.id.mazeGame3_sunImageView)).setImageDrawable(mSunAnimationDrawable);
		//mSunAnimationDrawable.start();

		//AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame3_sunImageView), R.drawable.maze3_sun_animation);
	}

	private void setAnimatinCarView(){
		
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		ImageView imageView = (ImageView) getFragmentView().findViewById(R.id.mazeGame3_carImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if(screenSize >= 10){
			params.width = (int) UtilityMethods.convertDpToPixel(405, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(314, WeShineApp.getInstance());
		}else if(screenSize >= 6.9){
			params.width = (int) UtilityMethods.convertDpToPixel(325, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(252, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(-10, WeShineApp.getInstance());
		}else {
			params.width = (int) UtilityMethods.convertDpToPixel(155, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(120, WeShineApp.getInstance());
		}

		//int[] animationImages = {R.drawable.maze3gfcar1, R.drawable.maze3gfcar1,R.drawable.maze3gfcar2,R.drawable.maze3gfcar3,R.drawable.maze3gfcar4,R.drawable.maze3gfcar5,R.drawable.maze3gfcar6,R.drawable.maze3gfcar7,R.drawable.maze3gfcar8,R.drawable.maze3gfcar9,R.drawable.maze3gfcar10,R.drawable.maze3gfcar11,R.drawable.maze3gfcar12,R.drawable.maze3gfcar13,R.drawable.maze3gfcar14,R.drawable.maze3gfcar15,R.drawable.maze3gfcar16,R.drawable.maze3gfcar17,R.drawable.maze3gfcar18,R.drawable.maze3gfcar19,R.drawable.maze3gfcar20,R.drawable.maze3gfcar21,R.drawable.maze3gfcar22,R.drawable.maze3gfcar23,R.drawable.maze3gfcar24,R.drawable.maze3gfcar25,R.drawable.maze3gfcar26};
		//int[] animationImages = {R.drawable.maze3gfcar1,R.drawable.maze3gfcar3,R.drawable.maze3gfcar5,R.drawable.maze3gfcar7,R.drawable.maze3gfcar9,R.drawable.maze3gfcar11,R.drawable.maze3gfcar13,R.drawable.maze3gfcar15,R.drawable.maze3gfcar17,R.drawable.maze3gfcar19,R.drawable.maze3gfcar21,R.drawable.maze3gfcar23,R.drawable.maze3gfcar25};
		//mCarAnimationDrawable = getAnimationDrawable(animationImages, getResources().getInteger(R.integer.maze1sun_animation_frame_duration));
		//mCarAnimationDrawable.setOneShot(false);
		//((ImageView) getFragmentView().findViewById(R.id.mazeGame3_carImageView)).setImageDrawable(mCarAnimationDrawable);
		//mCarAnimationDrawable.start();
		//AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame3_carImageView), R.drawable.maze3_car_animation);
	}
	@Override
	public void onGameEnd(boolean isSuccessful) {
		if (isSuccessful) {
			AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame3_wellDoneImageView), AnimType.ZOOM_IN, this);
		} else {
			// Reset the view and let the user try to draw right path again.
			resetDrawingSurface();
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		startAudioSound(R.raw.well_done);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		//((MazeActivity) getActivity()).popTopFragment();
		Bundle bundle = getArguments();
		if (bundle == null) {
			bundle = new Bundle();
		}
		bundle.putInt(ConstantValues.VIDEO_FILE_NAME, R.raw.maze3_end_video);
		bundle.putInt(ConstantValues.BUNDLE_EXTRA_VIDEO_DURATION, ConstantValues.MAZE_THREE_VIDEO_DURATION);
		((MazeActivity) getActivity()).attachGameEndVideoFragment(bundle);
		AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame3_wellDoneImageView), AnimType.ZOOM_OUT, null);
		resetDrawingSurface();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

	/**
	 * Resets the drawing surface. Every the paths drawn on the surface will be
	 * erased.
	 */
	private void resetDrawingSurface() {
		mDrawingSurface.reset();
	}

	@Override
	protected void onAudioComplete(int audioFileId) {
		switch (audioFileId) {
		case R.raw.maze3_ondraw:
			startAudioSound(R.raw.maze3);
			break;

		default:
			break;
		}
	}


	private AnimationDrawable getAnimationDrawable(int[] drawables, int duration){

		AnimationDrawable newAnim = new AnimationDrawable();
		for (int i = 0; i < drawables.length ; i++) {
			Bitmap bitmap =  BitmapFactory.decodeResource(getResources(), drawables[i]);
			BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
			newAnim.addFrame(bitmapDrawable, duration);
		}
		return newAnim;
	}

	private void releaseResources(){
		//recycle(mCarAnimationDrawable);
		mCarAnimationDrawable = null;

		//recycle(mSunAnimationDrawable);
		mSunAnimationDrawable = null;




		mTopBitmap.recycle();
		mTopBitmap = null;

		mMiddleBitmap.recycle();
		mMiddleBitmap = null;

		mBottomBitmap.recycle();
		mBottomBitmap = null;
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
		System.gc();
		releaseResources();
	}

}
