package com.game.framents;

import android.support.v4.view.ViewPager;

import com.game.listeners.OnMazeMenuItemClickListener;
import com.game.utils.Logger;
import com.game.view.adapter.ImageViewPagerAdapter;
import com.game.weshine.MainActivity;
import com.game.weshine.R;

public class MazeGameMenuFragment extends BaseFragment implements OnMazeMenuItemClickListener {

	@Override
	public void onResume() {
		super.onResume();
		ViewPager viewPager = (ViewPager) getFragmentView().findViewById(R.id.game_menu_view_pager);
		ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(getActivity());
		adapter.setOnMazeMenuItemClickListener(this);
		viewPager.setAdapter(adapter);

	}

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game_menu;
	}

	@Override
	public void onGameMenuItemClick(int level) {
		((MainActivity) getActivity()).AttachGameFragment(level);
	}

	@Override
	protected void onAudioComplete(int audioFileId) {
		// TODO Auto-generated method stub
		
	}

}
