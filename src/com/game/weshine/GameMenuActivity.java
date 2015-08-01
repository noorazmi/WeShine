package com.game.weshine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameMenuActivity extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_menu_activity);
		((Button)findViewById(R.id.sun_catcher_button)).setOnClickListener(this);;
		((Button)findViewById(R.id.maze_button)).setOnClickListener(this);;
		((Button)findViewById(R.id.baloon_button)).setOnClickListener(this);;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.sun_catcher_button:
			intent = new Intent(this, SunCatcherActivity.class);
			startActivity(intent);
			break;
		case R.id.maze_button:
			intent = new Intent(this, MazeActivity.class);
			startActivity(intent);
			break;
		case R.id.baloon_button:
			intent = new Intent(this, BaloonActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	
	

	
	
}
