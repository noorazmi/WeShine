package com.game.utils;

import android.content.Context;

public class UtilityMethods {
	
	 public static int getPixelsFromDp(Context context,
	            float dp) {
	        final float scale = context.getResources().getDisplayMetrics().density;
	        return (int) (dp * scale + 0.5f);
	    }

}
