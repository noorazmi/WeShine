package com.game.framents;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.game.listeners.OnGameEndListener;
import com.game.util.animation.AnimType;
import com.game.util.animation.AnimationUtil;
import com.game.utils.ConstantValues;
import com.game.views.DrawingSurface;
import com.game.weshine.MainActivity;
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
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame1_sunImageView), R.drawable.sun_animation);
	}

	@Override
	public void onGameEnd(boolean isSuccessful) {
		if (isSuccessful) {
			AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame1_terrificImageView), AnimType.ZOOM_IN, this);
		} else {
			// Reset the view and let the user try to draw right path again.
			resetDrawingSurface();
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		startAudioSound(R.raw.terrific);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		((MainActivity) getActivity()).popTopFragment();
		Bundle bundle = getArguments();
		if (bundle == null) {
			bundle = new Bundle();
		}
		bundle.putInt(ConstantValues.VIDEO_FILE_NAME, R.raw.maze1_end_video);
		((MainActivity) getActivity()).attachGameEndVideoFragment(bundle);
		AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame1_terrificImageView), AnimType.ZOOM_OUT, null);
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
		case R.raw.maze1_ondraw:
			startAudioSound(R.raw.maze1);
			break;

		default:
			break;
		}
	}

}
