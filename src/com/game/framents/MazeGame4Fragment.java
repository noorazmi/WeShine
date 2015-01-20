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
import com.game.weshine.MainActivity;
import com.game.weshine.R;

public class MazeGame4Fragment extends BaseFragment implements OnGameEndListener, AnimationListener {

	// Drawing surface to draw the path on
	private DrawingSurface mDrawingSurface;

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
		setAnimatedSunView();
		startAudioSound(R.raw.maze4_ondraw);
	}

	private void setAnimatedSunView() {
		
		int screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		ImageView imageView = (ImageView) getFragmentView().findViewById(R.id.mazeGame4_sunImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if(screenSize >= 10){
			params.width = (int) UtilityMethods.convertDpToPixel(138, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(142, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(30, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(155, WeShineApp.getInstance());
		}else if(screenSize >= 7){
			params.width = (int) UtilityMethods.convertDpToPixel(110, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(113, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(14, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(125, WeShineApp.getInstance());
		}else {
			params.width = (int) UtilityMethods.convertDpToPixel(60, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(62, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(8, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(62, WeShineApp.getInstance());
		}
		
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame4_sunImageView), R.drawable.maze4_sun_animation);
	}

	@Override
	public void onGameEnd(boolean isSuccessful) {
		if (isSuccessful) {
			AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame4_superImageView), AnimType.ZOOM_IN, this);
		} else {
			// Reset the view and let the user try to draw right path again.
			resetDrawingSurface();
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		startAudioSound(R.raw.super_sound);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		((MainActivity) getActivity()).popTopFragment();
		Bundle bundle = getArguments();
		if (bundle == null) {
			bundle = new Bundle();
		}
		bundle.putInt(ConstantValues.VIDEO_FILE_NAME, R.raw.maze4_end_video);
		((MainActivity) getActivity()).attachGameEndVideoFragment(bundle);
		AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame4_superImageView), AnimType.ZOOM_OUT, null);
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
		case R.raw.maze4_ondraw:
			startAudioSound(R.raw.maze4);
			break;

		default:
			break;
		}		
	}

}
