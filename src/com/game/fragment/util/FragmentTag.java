package com.game.fragment.util;

import com.game.framents.GameEndVideoFragment;
import com.game.framents.MazeGame1Fragment;

public enum FragmentTag {
	
	FragmentGameEndVideo(GameEndVideoFragment.class.getSimpleName()),
	FragmentMazeGame1(MazeGame1Fragment.class.getSimpleName());
	
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
