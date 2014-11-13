package com.game.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class UtilityMethods {

	/**
	 * This method converts dp unit to equivalent pixels, depending on device
	 * density.
	 * 
	 * @param dp
	 *            A value in dp (density independent pixels) unit. Which we need
	 *            to convert into pixels
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent px equivalent to dp depending on
	 *         device density
	 */

	public static float convertDpToPixel(float dp, Context context) {
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
		return px;
	}

	/**
	 * This method converts device specific pixels to density independent
	 * pixels.
	 * 
	 * @param px
	 *            A value in px (pixels) unit. Which we need to convert into dp
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}
	
	/**
	 * Returns a Point which x and y values are in dp  
	 * @param x x value in px
	 * @param y y value in px
	 * @return Point which x and y Values are in dp
	 */
	public static final Point getPointPXToDP(int x, int y, Context context){
		return new Point((int)UtilityMethods.convertPixelsToDp(x, context), (int)UtilityMethods.convertPixelsToDp(y, context));
	}
	
	
	/**
	 * Validates if a Point falls under the boundary of passed ValidationRegion
	 * @param validationRegion
	 * @param machingPoint
	 * @return true if the point falls under the provided ValidationRegion
	 */
	
	public static final  boolean isMachingRegion( Rect validationRegion, Point machingPoint){
		return validationRegion.contains(machingPoint.x, machingPoint.y);
	}
	
}
