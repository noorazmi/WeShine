package com.game.framents;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.game.weshine.R;

public class GameEndVideoFragment extends BaseFragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		getActivity().getWindow().setFormat(PixelFormat.UNKNOWN);

		// Displays a video file.
		View v = getFragmentView();
		VideoView mVideoView = (VideoView) v.findViewById(R.id.videoview);
		String uriPath = "android.resource://com.game.weshine/" + R.raw.maze1_video;
		Uri uri = Uri.parse(uriPath);
		mVideoView.setVideoURI(uri);
		mVideoView.requestFocus();
		mVideoView.start();
	}
	
	@Override
	protected int getFragmentLayoutId() {
		return R.layout.game_end_video_fragment;
	}

}
