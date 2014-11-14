package com.game.app;

import android.app.Application;

public class WeShineApp extends Application {
	private static WeShineApp singleton;

	public static WeShineApp getInstance() {
		return singleton;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;
	}
}