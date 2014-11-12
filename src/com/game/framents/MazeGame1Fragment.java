package com.game.framents;

import com.game.weshine.R;
import com.game.weshine.R.layout;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MazeGame1Fragment  extends Fragment{
	
	 public MazeGame1Fragment() {
		 
		
		 
     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//         ImageView mBirdsView = (ImageView) rootView.findViewById(R.id.birdsView);
//         mBirdsView.setBackgroundResource(R.drawable.birds_animation);
//         AnimationDrawable birdsAnimation;
//         
//         birdsAnimation = (AnimationDrawable) mBirdsView.getBackground();
//         birdsAnimation.start();
         return rootView;
     }

     
}
