package com.game.framents;

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

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game3;
	}

	@Override
	public void onResume() {
		super.onResume();
		mDrawingSurface = (DrawingSurface) getFragmentView().findViewById(R.id.mazeGame3_middleImageView);
		mDrawingSurface.setOnGameEndListener(this);
		mDrawingSurface.setHotSpotImageView((ImageView) getFragmentView().findViewById(R.id.mazeGame3_bottomImageView));
		setAnimatedSunView();
		setAnimatinCarView();
		startAudioSound(R.raw.maze3_ondraw);
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
		
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame3_sunImageView), R.drawable.maze3_sun_animation);
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
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame3_carImageView), R.drawable.maze3_car_animation);
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
		((MazeActivity) getActivity()).popTopFragment();
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

}
