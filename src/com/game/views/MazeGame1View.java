package com.game.views;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.Toast;

import com.game.utils.Logger;
import com.game.utils.UtilityMethods;

public class MazeGame1View extends DrawingSurface{

	//private Rect regionStart;
	//private Rect regionMid;
	//private Rect regionEnd;
	
	public MazeGame1View(Context context, AttributeSet attrs) {
		super(context, attrs);
		//int rectWidth = UtilityMethods.getValidationRectWidthInPX(getContext());
		//int rectHeight = UtilityMethods.getValidationRectHeightInPX(getContext());
		///int left = 700;//x1
		//int top = 500;//y1
		
		
		//regionStart = new Rect(left, top, left+rectWidth, top+rectHeight);
		
		//left = 280;
		//top = 155;
		
		//regionEnd = new Rect(left, top, left+rectWidth, top+rectHeight);
	}

	@Override
	protected void onTouchEndEvent(boolean isSuccess) {
//		if(UtilityMethods.isMachingRegion(regionStart, getTouchStartPoint()) && UtilityMethods.isMachingRegion(regionEnd, getTouchEndPoint())){
//			Toast.makeText(getContext(), TAG+" Success", Toast.LENGTH_LONG).show();
//		}
		
		if(isSuccess){
			Logger.toast("Congratulations ! Successfull", getContext());
		}else {
			Logger.toast("You failed! Try again", getContext());
		}
	}
}
