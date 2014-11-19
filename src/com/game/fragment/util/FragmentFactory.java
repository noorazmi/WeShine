package com.game.fragment.util;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;

import com.game.framents.GameEndVideoFragment;
import com.game.framents.MazeGame1Fragment;
import com.game.framents.MazeGameMenuFragment;
import com.game.weshine.MainActivity;

public class FragmentFactory {

   public static void attachGameLevelOneFragment(Activity activity, Bundle bundle){
	   FragmentHelper.addFragmentOnBackStack(new WeakReference<MainActivity>((MainActivity) activity), new MazeGame1Fragment(), null, FragmentTag.FragmentGameEndVideo.getTag());
   }
	
	public static void attachGameEndVideoFragment(Activity activity, Bundle bundle) {
		//FragmentHelper.replaceFragment(new WeakReference<MainActivity>((MainActivity) activity), new GameEndVideoFragment(), null, FragmentTag.FragmentGameEndVideo.getTag());
		FragmentHelper.addFragmentOnBackStack(new WeakReference<MainActivity>((MainActivity) activity), new GameEndVideoFragment(), null, FragmentTag.FragmentGameEndVideo.getTag());
	}
	

}
