package com.game.framents;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Base Fragment for all fragment in the app.
 * @author noor.alam
 *
 */
public abstract class BaseFragment extends Fragment{
	
	private View mFragmentView; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mFragmentView = inflater.inflate(getFragmentLayoutId(), container, false);
		return mFragmentView;
	}
	
	
	/**
	 * Returns the view attached to the fragment
	 * @return View
	 */
	public View getFragmentView(){
		return mFragmentView;
	}
	
	//Returns the layout id which will be attached to the fragment as view
	protected abstract int getFragmentLayoutId();
	
	
	
}
