package com.game.framents;

import android.support.v4.view.ViewPager;

import com.game.listeners.OnMazeMenuItemClickListener;
import com.game.view.adapter.ImageViewPagerAdapter;
import com.moderneng.R;
import com.moderneng.activities.MazeActivity;

public class MazeGameMenuFragment extends BaseFragment implements OnMazeMenuItemClickListener {

	private ViewPager mViewPager;
	@Override
	public void onResume() {
		super.onResume();
		mViewPager = (ViewPager) getFragmentView().findViewById(R.id.game_menu_view_pager);
		ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(getActivity());
		adapter.setOnMazeMenuItemClickListener(this);
		mViewPager.setAdapter(adapter);

	}

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game_menu;
	}

	@Override
	public void onGameMenuItemClick(int level) {
		((MazeActivity) getActivity()).AttachGameFragment(level);
	}
	
	@Override
	protected void onAudioComplete(int audioFileId) {
	}
	
    public void setCurrentMenuItem(int level){
    	mViewPager.setCurrentItem(level);
    }

}
