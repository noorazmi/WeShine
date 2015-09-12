package com.game.framents;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

import com.game.listeners.OnGameEndListener;
import com.game.util.animation.AnimType;
import com.game.util.animation.AnimationUtil;
import com.game.utils.AppConstant;
import com.game.utils.UtilityMethods;
import com.game.views.DrawingSurface;
import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.activities.MazeMenuActivity;

public class MazeGame5Fragment extends BaseFragment implements OnGameEndListener, AnimationListener {

	// Drawing surface to draw the path on
	private DrawingSurface mDrawingSurface;

	private Bitmap mTopBitmap;
	private Bitmap mMiddleBitmap;
	private Bitmap mBottomBitmap;
	private Bitmap mBitmapExcellent;



	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game5;
	}

	@Override
	public void onResume() {
		super.onResume();
		mDrawingSurface = (DrawingSurface) getFragmentView().findViewById(R.id.mazeGame5_middleImageView);
		mDrawingSurface.setOnGameEndListener(this);
		mDrawingSurface.setHotSpotImageView((ImageView) getFragmentView().findViewById(R.id.mazeGame5_bottomImageView));

		setBackgroundImages();
		setAnimatedSunView();
		startAudioSound(R.raw.maze5_ondraw);
	}



	private void setBackgroundImages() {

		mTopBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze5_top_img);
		((ImageView)getFragmentView().findViewById(R.id.mazeGame5_topImageView)).setImageBitmap(mTopBitmap);

		mMiddleBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze5_middle_img);
		mDrawingSurface.setImageBitmap(mMiddleBitmap);

		mBottomBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze5_bottom_img);
		((ImageView)getFragmentView().findViewById(R.id.mazeGame5_bottomImageView)).setImageBitmap(mBottomBitmap);

		mBitmapExcellent = BitmapFactory.decodeResource(getResources(), WeShineApp.sImageIdExcellent);
		((ImageView)getFragmentView().findViewById(R.id.mazeGame5_excellentImageView)).setImageBitmap(mBitmapExcellent);



	}

	private void setAnimatedSunView() {
		
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		ImageView imageView = (ImageView) getFragmentView().findViewById(R.id.mazeGame5_sunImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if(screenSize >= 10){
			params.width = (int) UtilityMethods.convertDpToPixel(145, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(151, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(5, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(122, WeShineApp.getInstance());
		}else if(screenSize >= 6.9){
			params.width = (int) UtilityMethods.convertDpToPixel(110, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(115, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(4, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(90, WeShineApp.getInstance());
		}else {
			params.width = (int) UtilityMethods.convertDpToPixel(60, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(63, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(5, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(47, WeShineApp.getInstance());
		}
		//AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame5_sunImageView), R.drawable.maze5_sun_animation);
	}

	@Override
	public void onGameEnd(boolean isSuccessful) {
		if (isSuccessful) {
			AnimationUtil.performAnimation(getFragmentView().findViewById(R.id.mazeGame5_excellentImageView), AnimType.ZOOM_IN, this);
		} else {
			// Reset the view and let the user try to draw right path again.
			resetDrawingSurface();
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		startAudioSound(WeShineApp.sSoundIdExcellent);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		//((MazeActivity) getActivity()).popTopFragment();
		Bundle bundle = getArguments();
		if (bundle == null) {
			bundle = new Bundle();
		}
		bundle.putInt(AppConstant.VIDEO_FILE_NAME, R.raw.maze5_end_video);
		bundle.putInt(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, AppConstant.MAZE_FIVE_VIDEO_DURATION);
		((MazeMenuActivity) getActivity()).attachGameEndVideoFragment(bundle);
		AnimationUtil.performAnimation(getFragmentView().findViewById(R.id.mazeGame5_excellentImageView), AnimType.ZOOM_OUT, null);
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
		case R.raw.maze5_ondraw:
			startAudioSound(WeShineApp.sSoundIdMaze5);
			break;

		default:
			break;
		}
	}


	private void releaseResources(){

		mTopBitmap.recycle();
		mTopBitmap = null;

		mMiddleBitmap.recycle();
		mMiddleBitmap = null;

		mBottomBitmap.recycle();
		mBottomBitmap = null;

		mBitmapExcellent.recycle();
		mBitmapExcellent = null;
	}




	@Override
	public void onDetach() {
		super.onDetach();
		System.gc();
		releaseResources();
	}

}
