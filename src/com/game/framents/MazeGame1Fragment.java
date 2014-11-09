package com.game.framents;

import com.game.weshine.R;
import com.game.weshine.R.layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MazeGame1Fragment  extends Fragment{
	
	 public MazeGame1Fragment() {
		 
		
		 
     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_main, container, false);
         return rootView;
     }

     
}
