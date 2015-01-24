package com.game.framents;

import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

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

public class MazeGame1Fragment extends BaseFragment implements OnGameEndListener, AnimationListener {

	// Drawing surface to draw the path on
	private DrawingSurface mDrawingSurface;

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game1;
	}

	@Override
	public void onResume() {
		super.onResume();
		mDrawingSurface = (DrawingSurface) getFragmentView().findViewById(R.id.mazeGame1_middleImageView);
		mDrawingSurface.setOnGameEndListener(this);
		mDrawingSurface.setHotSpotImageView((ImageView) getFragmentView().findViewById(R.id.mazeGame1_bottomImageView));
		mDrawingSurface.setIsTouchedWhite(true);
		setAnimatedBirdsView();
		setAnimatedSunView();
		startAudioSound(R.raw.maze1_ondraw);

	}

	private void setAnimatedBirdsView() {
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame1_birdsImageView), R.drawable.birds_animation);
	}

	private void setAnimatedSunView() {
		double screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
		Logger.error(getTag(), "size%%%%%%%%%%@@@@@@@@************::"+screenSize);
		ImageView imageView = (ImageView) getFragmentView().findViewById(R.id.mazeGame1_sunImageView);
		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		if(screenSize >= 10){
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
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame1_sunImageView), R.drawable.sun_animation);
	}

	@Override
	public void onGameEnd(boolean isSuccessful) {
		if (isSuccessful) {
			AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame1_terrificImageView), AnimType.ZOOM_IN, this);
		} else {
			// Reset the view and let the user try to draw right path again.
			//resetDrawingSurface();
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		startAudioSound(R.raw.terrific);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		((MazeActivity) getActivity()).popTopFragment();
		Bundle bundle = getArguments();
		if (bundle == null) {
			bundle = new Bundle();
		}
		bundle.putInt(ConstantValues.VIDEO_FILE_NAME, R.raw.maze1_end_video);
		((MazeActivity) getActivity()).attachGameEndVideoFragment(bundle);
		AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame1_terrificImageView), AnimType.ZOOM_OUT, null);
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

	@Override
	protected void onAudioComplete(int audioFileId) {
		switch (audioFileId) {
		case R.raw.maze1_ondraw:
			startAudioSound(R.raw.maze1);
			break;

		default:
			break;
		}
	}

}
