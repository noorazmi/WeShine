package com.moderneng.framents;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

import com.moderneng.listeners.OnGameEndListener;
import com.moderneng.animation.AnimType;
import com.moderneng.animation.AnimationUtil;
import com.moderneng.utils.AppConstant;
import com.moderneng.utils.GameMusic;
import com.moderneng.utils.ImageAndMediaResources;
import com.moderneng.utils.UtilityMethods;
import com.moderneng.views.DrawingSurface;
import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.activities.MazeMenuActivity;

public class MazeGame4Fragment extends BaseFragment implements OnGameEndListener, AnimationListener {

	// Drawing surface to draw the path on
	private DrawingSurface mDrawingSurface;

	private Bitmap mTopBitmap;
	private Bitmap mMiddleBitmap;
	private Bitmap mBottomBitmap;
	private Bitmap mBitmapSuper;

	private GameMusic mGameMusic1;
	private GameMusic mGameMusic2;
	private GameMusic mGameMusicEnd;


	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game4;
	}

	@Override
	public void onResume() {
		super.onResume();
		mDrawingSurface = (DrawingSurface) getFragmentView().findViewById(R.id.mazeGame4_middleImageView);
		mDrawingSurface.setOnGameEndListener(this);
		mDrawingSurface.setHotSpotImageView((ImageView) getFragmentView().findViewById(R.id.mazeGame4_bottomImageView));

		setBackgroundImages();
		setAnimatedSunView();
		//startAudioSound(R.raw.maze1_ondraw);
		mGameMusic1 = new GameMusic(getActivity(), "maze1_ondraw");
		mGameMusic1.start();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mGameMusic2 = new GameMusic(getActivity(), ImageAndMediaResources.sSoundIdMaze4);
				mGameMusic2.start();
			}
		}, 500);
	}



	private void setBackgroundImages() {

		//mTopBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze4_top_img);
		mTopBitmap =  WeShineApp.getBitmapFromObb("maze4_top_img.png");
		((ImageView)getFragmentView().findViewById(R.id.mazeGame4_topImageView)).setImageBitmap(mTopBitmap);

		//mMiddleBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze4_middle_img);
		mMiddleBitmap =  WeShineApp.getBitmapFromObb("maze4_middle_img.png");
		mDrawingSurface.setImageBitmap(mMiddleBitmap);

		//mBottomBitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maze4_bottom_img);
		mBottomBitmap =  WeShineApp.getBitmapFromObb("maze4_bottom_img.png");
		((ImageView)getFragmentView().findViewById(R.id.mazeGame4_bottomImageView)).setImageBitmap(mBottomBitmap);

		mBitmapSuper = BitmapFactory.decodeResource(getResources(), ImageAndMediaResources.sImageIdSuper);
		((ImageView)getFragmentView().findViewById(R.id.mazeGame4_superImageView)).setImageBitmap(mBitmapSuper);

	}
	private void setAnimatedSunView() {
		
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		ImageView imageView = (ImageView) getFragmentView().findViewById(R.id.mazeGame4_sunImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if(screenSize >= 10){
			params.width = (int) UtilityMethods.convertDpToPixel(138, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(142, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(30, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(155, WeShineApp.getInstance());
		}else if(screenSize >= 6.9){
			params.width = (int) UtilityMethods.convertDpToPixel(110, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(113, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(19, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(115, WeShineApp.getInstance());
		}else {
			params.width = (int) UtilityMethods.convertDpToPixel(60, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(62, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(8, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(62, WeShineApp.getInstance());
		}
		
		//AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame4_sunImageView), R.drawable.maze4_sun_animation);
	}

	@Override
	public void onGameEnd(boolean isSuccessful) {
		if (isSuccessful) {
			AnimationUtil.performAnimation(getFragmentView().findViewById(R.id.mazeGame4_superImageView), AnimType.ZOOM_IN, this);
		} else {
			// Reset the view and let the user try to draw right path again.
			resetDrawingSurface();
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		//startAudioSound(ImageAndMediaResources.sSoundIdSuper);
		mGameMusicEnd = new GameMusic(getActivity(), ImageAndMediaResources.sSoundIdSuper);
		mGameMusicEnd.start();
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		//((MazeActivity) getActivity()).popTopFragment();
		Bundle bundle = getArguments();
		if (bundle == null) {
			bundle = new Bundle();
		}
		//bundle.putInt(AppConstant.VIDEO_FILE_NAME, R.raw.maze4_end_video);
		bundle.putString(AppConstant.VIDEO_FILE_NAME, "maze4_end_video");
		bundle.putInt(AppConstant.BUNDLE_EXTRA_VIDEO_DURATION, AppConstant.MAZE_FOUR_VIDEO_DURATION);
		((MazeMenuActivity) getActivity()).attachGameEndVideoFragment(bundle);
		AnimationUtil.performAnimation(getFragmentView().findViewById(R.id.mazeGame4_superImageView), AnimType.ZOOM_OUT, null);
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
	protected void onAudioComplete(String audioFileId) {
		switch (audioFileId) {
		case "maze1_ondraw":
			startAudioSound(ImageAndMediaResources.sSoundIdMaze4);
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

		mBitmapSuper.recycle();
		mBitmapSuper = null;

		if(mGameMusic1 != null){
			mGameMusic1.release();
		}

		if(mGameMusic2 != null){
			mGameMusic2.release();
		}

		if(mGameMusicEnd != null){
			mGameMusicEnd.release();
			mGameMusicEnd = null;
		}
	}




	@Override
	public void onDetach() {
		super.onDetach();
		System.gc();
		releaseResources();
	}
}
