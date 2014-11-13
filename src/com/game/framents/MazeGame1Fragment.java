package com.game.framents;

import com.game.listeners.OnGameEndListener;
import com.game.views.DrawingSurface;
import com.game.weshine.MainActivity;
import com.game.weshine.R;

public class MazeGame1Fragment  extends BaseFragment implements OnGameEndListener{
	
	
	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game1;
	}

	@Override
	public void onResume() {
		super.onResume();
		DrawingSurface drawingSurface = (DrawingSurface) getFragmentView().findViewById(R.id.maze1GameSurface);
		drawingSurface.setOnGameEndListener(this);
	}
	
	@Override
	public void onGameEnd(boolean isSuccessful) {
		if(isSuccessful){
		  ((MainActivity)getActivity()).attachGameEndVideoFragment(getArguments());
		}
	}

}
