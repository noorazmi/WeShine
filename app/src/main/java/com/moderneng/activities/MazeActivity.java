package com.moderneng.activities;

import android.app.Activity;
import android.os.Bundle;

import com.game.fragment.util.FragmentFactory;
import com.game.framents.MazeGameMenuFragment;
import com.game.utils.ConstantValues;
import com.moderneng.R;

public class MazeActivity extends Activity {

	private MazeGameMenuFragment mMazeGameMenuFragment;
	private int currentGameLevel = ConstantValues.GAME_LEVEL_0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maze);
		if (savedInstanceState == null) {
			mMazeGameMenuFragment = new MazeGameMenuFragment();
			getFragmentManager().beginTransaction().add(R.id.container, mMazeGameMenuFragment).commit();
		}
	}
	public void AttachGameFragment(int gameLevel){
		currentGameLevel = gameLevel;
		switch (gameLevel) {
		case ConstantValues.GAME_LEVEL_0:
			FragmentFactory.attachGameLevelOneFragment(this, null);
			break;
		case ConstantValues.GAME_LEVEL_1:
			FragmentFactory.attachGameLevelTwoFragment(this, null);
			break;
		case ConstantValues.GAME_LEVEL_2:
			FragmentFactory.attachGameLevelThreeFragment(this, null);
			break;
		case ConstantValues.GAME_LEVEL_3:
			FragmentFactory.attachGameLevelFourFragment(this, null);
			break;
		case ConstantValues.GAME_LEVEL_4:
			FragmentFactory.attachGameLevelFiveFragment(this, null);
			break;

		default:
			break;
		}
	}
	
	public void openNextLevel(){
		if(currentGameLevel <= ConstantValues.GAME_LEVEL_4){
			AttachGameFragment(currentGameLevel);
		}
		
	}
	
	public void attachGameEndVideoFragment(Bundle bundle) {
		FragmentFactory.attachGameEndVideoFragment(this, bundle);
		if(currentGameLevel < ConstantValues.GAME_LEVEL_4){
			if(mMazeGameMenuFragment != null){
				mMazeGameMenuFragment.setCurrentMenuItem(++currentGameLevel);
			}
		}
	}
	

	/**
	 * Pops the top fragment from the fragemnt stack
	 */
	public void popTopFragment(){
		FragmentFactory.popTopFragment(this);
	}
	

}
