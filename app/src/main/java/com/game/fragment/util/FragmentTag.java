package com.game.fragment.util;

import com.game.framents.GameEndVideoFragment;
import com.game.framents.MazeGame1Fragment;
import com.game.framents.MazeGame2Fragment;
import com.game.framents.MazeGame3Fragment;
import com.game.framents.MazeGame4Fragment;
import com.game.framents.MazeGame5Fragment;
import com.game.framents.MazeGameMenuFragment;

public enum FragmentTag {

	FragmentMazeGameMenu(MazeGameMenuFragment.class.getSimpleName()),
	FragmentGameEndVideo(GameEndVideoFragment.class.getSimpleName()),
	FragmentMazeGame1(MazeGame1Fragment.class.getSimpleName()),
	FragmentMazeGame2(MazeGame2Fragment.class.getSimpleName()),
	FragmentMazeGame3(MazeGame3Fragment.class.getSimpleName()),
	FragmentMazeGame4(MazeGame4Fragment.class.getSimpleName()),
	FragmentMazeGame5(MazeGame5Fragment.class.getSimpleName());
	
    private String fragmentTag;
	private FragmentTag(String fragmentTag) {
		this.fragmentTag = fragmentTag;
	}
	
	/**
	 * Returns the tag defined for this fragment.
	 * @return String tag of this fragment.
	 */
	public String getTag(){
		return fragmentTag;
	}
	
	
}
