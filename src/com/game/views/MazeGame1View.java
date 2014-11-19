package com.game.views;

import android.content.Context;
import android.util.AttributeSet;

import com.game.utils.Logger;

public class MazeGame1View extends DrawingSurface {

	public MazeGame1View(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onTouchEndEvent(boolean isSuccess) {
		if (isSuccess) {
			Logger.toast("Congratulations ! Successfull", getContext());
		} else {
			Logger.toast("You failed! Try again", getContext());
		}

		if (mGameEndListener != null) {
			mGameEndListener.onGameEnd(isSuccess);
		}
	}

}
