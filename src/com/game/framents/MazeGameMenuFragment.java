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

		Logger.toast("clicked position:"+level, getActivity());
		((MainActivity)getActivity()).AttachGameFragment(level);
//		switch (level) {
//		case 0:
//			
//			break;
//		case 1:
//
//			break;
//		case 2:
//
//			break;
//		case 3:
//
//			break;
//		case 4:
//
//			break;
//
//		default:
//			break;
//		}
	}

}
