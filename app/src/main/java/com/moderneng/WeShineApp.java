package com.moderneng;

import android.app.Application;

public class WeShineApp extends Application {
	private static WeShineApp singleton;
	private static String sLanguage = null;

	public static WeShineApp getInstance() {
		return singleton;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;
	}

	public static WeShineApp getSingleton() {
		return singleton;
	}

	public static void setSingleton(WeShineApp singleton) {
		WeShineApp.singleton = singleton;
	}

	public static String getLanguage() {
		return sLanguage;
	}

	public static void setLanguage(String sLanguage) {
		WeShineApp.sLanguage = sLanguage;
	}
}