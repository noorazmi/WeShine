package com.game.framents;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.FrameLayout.LayoutParams;

import com.game.app.WeShineApp;
import com.game.listeners.OnGameEndListener;
import com.game.util.animation.AnimType;
import com.game.util.animation.AnimationUtil;
import com.game.utils.ConstantValues;
import com.game.utils.UtilityMethods;
import com.game.views.DrawingSurface;
import com.game.weshine.MazeActivity;
import com.game.weshine.R;

public class MazeGame5Fragment extends BaseFragment implements OnGameEndListener, AnimationListener {

	// Drawing surface to draw the path on
	private DrawingSurface mDrawingSurface;

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
		setAnimatedSunView();
		startAudioSound(R.raw.maze5_ondraw);
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
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame5_sunImageView), R.drawable.maze5_sun_animation);
	}

	@Override
	public void onGameEnd(boolean isSuccessful) {
		if (isSuccessful) {
			AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame5_excellentImageView), AnimType.ZOOM_IN, this);
		} else {
			// Reset the view and let the user try to draw right path again.
			resetDrawingSurface();
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		startAudioSound(R.raw.excellent);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		((MazeActivity) getActivity()).popTopFragment();
		Bundle bundle = getArguments();
		if (bundle == null) {
			bundle = new Bundle();
		}
		bundle.putInt(ConstantValues.VIDEO_FILE_NAME, R.raw.maze5_end_video);
		((MazeActivity) getActivity()).attachGameEndVideoFragment(bundle);
		AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame5_excellentImageView), AnimType.ZOOM_OUT, null);
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
			startAudioSound(R.raw.maze5);
			break;

		default:
			break;
		}
	}

}
