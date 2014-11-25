package com.game.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Custom logger to implement debug flag
 */
public class Logger {

	
	public static void debug(String tag, String message) {
		if (ConstantValues.APP_DEBUG) {
			Log.e(tag, ConstantValues.APP_TAG+message);
		}
	}

	public static void error(String tag, String message) {
		if (ConstantValues.APP_DEBUG) {
			Log.e(tag, message);
		}
	}

	/**
	 * Show a string on the screen via Toast.
	 * 
	 * @param msg
	 *            String
	 * @param context Context           
	 * @return void
	 */

	public static void toast(String msg, Context context) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
