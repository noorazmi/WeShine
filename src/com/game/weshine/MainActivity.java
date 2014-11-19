package com.game.weshine;

import android.app.Activity;
import android.os.Bundle;

import com.game.fragment.util.FragmentFactory;
import com.game.framents.MazeGameMenuFragment;
import com.game.utils.ConstantValues;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			// getFragmentManager().beginTransaction().add(R.id.container, new
			// MazeGame1Fragment()).commit();
			getFragmentManager().beginTransaction().add(R.id.container, new MazeGameMenuFragment()).commit();
			// getFragmentManager().beginTransaction()
			// .add(R.id.container, new GameEndVideoFragment())
			// .commit();
		}
	}
	public void AttachGameFragment(int gameLevel){
		switch (gameLevel) {
		case ConstantValues.GAME_LEVEL_0:
			FragmentFactory.attachGameLevelOneFragment(this, null);
			break;

		default:
			break;
		}
	}
	
	public void attachGameEndVideoFragment(Bundle bundle) {
		FragmentFactory.attachGameEndVideoFragment(this, null);
	}

	

}
