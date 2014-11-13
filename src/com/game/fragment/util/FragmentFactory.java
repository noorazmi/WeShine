package com.game.fragment.util;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;

import com.game.framents.GameEndVideoFragment;
import com.game.weshine.MainActivity;

public class FragmentFactory {

	public static void attachGameEndVideoFragment(Activity activity, Bundle bundle) {
		FragmentHelper.replaceFragmentWithDefaultAnimation(new WeakReference<MainActivity>((MainActivity) activity), new GameEndVideoFragment(), null, FragmentTag.FragmentGameEndVideo.getTag());
	}
	

}
