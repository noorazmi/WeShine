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
import com.game.utils.Logger;
import com.game.utils.UtilityMethods;
import com.game.views.DrawingSurface;
import com.game.weshine.MazeActivity;
import com.game.weshine.R;

public class MazeGame2Fragment extends BaseFragment implements OnGameEndListener, AnimationListener {

	// Drawing surface to draw the path on
	private DrawingSurface mDrawingSurface;

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game2;
	}

	@Override
	public void onResume() {
		super.onResume();
		mDrawingSurface = (DrawingSurface) getFragmentView().findViewById(R.id.mazeGame2_middleImageView);
		mDrawingSurface.setOnGameEndListener(this);
		mDrawingSurface.setHotSpotImageView((ImageView) getFragmentView().findViewById(R.id.mazeGame2_bottomImageView));
		setAnimatedBirdsView();
		setAnimatedSunView();
		setAnimationGreenShipView();
		setAnimationRedShipView();
		startAudioSound(R.raw.maze2_ondraw);
	}

	private void setAnimatedBirdsView() {
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame2_birdsImageView), R.drawable.maze2_birds_animation);
	}

	private void setAnimatedSunView() {
		
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		Logger.error(getTag(), "size%%%%%%%%%%@@@@@@@@************::"+screenSize);
		ImageView imageView = (ImageView) getFragmentView().findViewById(R.id.mazeGame2_sunImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if(screenSize >= 10){
			params.width = (int) UtilityMethods.convertDpToPixel(198, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(198, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(60, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(20, WeShineApp.getInstance());
		}else if(screenSize >= 6.9){
			params.width = (int) UtilityMethods.convertDpToPixel(153, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(159, WeShineApp.getInstance());
			params.topMargin = (int) UtilityMethods.convertDpToPixel(40, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(18, WeShineApp.getInstance());
		}else {
			params.width = (int) UtilityMethods.convertDpToPixel(82, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(82, WeShineApp.getInstance());
		}
		
		// existing height is ok as is, no need to edit it
		
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame2_sunImageView), R.drawable.maze2_sun_animation);
	}

	private void setAnimationGreenShipView() {
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		ImageView imageView = (ImageView) getFragmentView().findViewById(R.id.mazeGame2_greenShipImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if(screenSize >= 10){
			params.width = (int) UtilityMethods.convertDpToPixel(227, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(390, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(5, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
		}else if(screenSize >= 6.9){
			params.width = (int) UtilityMethods.convertDpToPixel(154, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(265, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(8, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(60, WeShineApp.getInstance());
		}else {
			params.width = (int) UtilityMethods.convertDpToPixel(82, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(140, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(8, WeShineApp.getInstance());
			params.rightMargin = (int) UtilityMethods.convertDpToPixel(35, WeShineApp.getInstance());
		}
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame2_greenShipImageView), R.drawable.maze2_green_ship_animation);
	}

	private void setAnimationRedShipView() {
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		ImageView imageView = (ImageView) getFragmentView().findViewById(R.id.mazeGame2_redShipImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if(screenSize >= 10){
			params.width = (int) UtilityMethods.convertDpToPixel(129, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(300, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(110, WeShineApp.getInstance());
		}else if(screenSize >= 6.9){
			params.width = (int) UtilityMethods.convertDpToPixel(88, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(205, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(85, WeShineApp.getInstance());
		}else {
			params.width = (int) UtilityMethods.convertDpToPixel(50, WeShineApp.getInstance());
			params.height = (int) UtilityMethods.convertDpToPixel(116, WeShineApp.getInstance());
			params.bottomMargin = (int) UtilityMethods.convertDpToPixel(0, WeShineApp.getInstance());
			params.leftMargin = (int) UtilityMethods.convertDpToPixel(47, WeShineApp.getInstance());
		}
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame2_redShipImageView), R.drawable.maze2_red_ship_animation);
	}

	@Override
	public void onGameEnd(boolean isSuccessful) {
		if (isSuccessful) {
			AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame2_fabulusImageView), AnimType.ZOOM_IN, this);
		} else {
			// Reset the view and let the user try to draw right path again.
			resetDrawingSurface();
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		startAudioSound(R.raw.fabulus);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		((MazeActivity) getActivity()).popTopFragment();
		Bundle bundle = getArguments();
		if (bundle == null) {
			bundle = new Bundle();
		}
		bundle.putInt(ConstantValues.VIDEO_FILE_NAME, R.raw.maze2_end_video);
		((MazeActivity) getActivity()).attachGameEndVideoFragment(bundle);
		AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame2_fabulusImageView), AnimType.ZOOM_OUT, null);
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
		case R.raw.maze2_ondraw:
			startAudioSound(R.raw.maze2);
			break;

		default:
			break;
		}
		
	}

}
