package com.game.weshine;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.game.fragment.util.FragmentFactory;
import com.game.framents.MazeGame1Fragment;
import com.game.framents.MazeGameMenuFragment;
import com.game.listeners.OnMazeMenuItemClickListener;
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

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }

	
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
