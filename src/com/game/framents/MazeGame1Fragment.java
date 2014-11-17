
    package com.game.framents;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.game.listeners.OnGameEndListener;
import com.game.util.animation.AnimType;
import com.game.util.animation.AnimationUtil;
import com.game.views.DrawingSurface;
import com.game.weshine.MainActivity;
import com.game.weshine.R;

public class MazeGame1Fragment extends BaseFragment implements OnGameEndListener, AnimationListener {

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game1;
	}

	@Override
	public void onResume() {
		super.onResume();
		DrawingSurface drawingSurface = (DrawingSurface) getFragmentView().findViewById(R.id.mazeGame1_gameSurface);
		drawingSurface.setOnGameEndListener(this);
		setAnimatedBirdsView();
		setAnimatedSunView();
	}

	private void setAnimatedBirdsView(){
		AnimationUtil.performFrameAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame1_birdsImageView), R.drawable.birds_animation);
	}
	
	private void setAnimatedSunView(){
		
	}
	
	@Override
	public void onGameEnd(boolean isSuccessful) {
		if (isSuccessful) {
			AnimationUtil.performAnimation((ImageView) getFragmentView().findViewById(R.id.mazeGame1_terrificImageView), AnimType.ZOOM_IN, this);
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		((ImageView) getFragmentView().findViewById(R.id.mazeGame1_terrificImageView)).setVisibility(View.VISIBLE);
	}

	@Override
	public void onAnimationEnd(Animation animation) {

		((MainActivity) getActivity()).attachGameEndVideoFragment(getArguments());
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

}
