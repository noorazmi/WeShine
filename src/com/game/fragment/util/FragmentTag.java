package com.game.fragment.util;

import com.game.framents.GameEndVideoFragment;

public enum FragmentTag {
	
	FragmentGameEndVideo(GameEndVideoFragment.class.getSimpleName());
	
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
