package com.game.framents;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;
import android.media.MediaPlayer;

import com.game.utils.ConstantValues;
import com.game.weshine.MazeActivity;
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
		String uriPath = ConstantValues.BASE_RESOURCE_PATH + getArguments().getInt(ConstantValues.VIDEO_FILE_NAME);
		Uri uri = Uri.parse(uriPath);
		mVideoView.setVideoURI(uri);
		mVideoView.requestFocus();
		mVideoView.setOnCompletionListener(this);
		mVideoView.start();
	}
	
	@Override
	protected int getFragmentLayoutId() {
		return R.layout.game_end_video_fragment;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		//Video complete now pop the video fragment
		mp = null;
		getFragmentManager().popBackStack();
		
		if(getArguments().getInt(ConstantValues.VIDEO_FILE_NAME) == R.raw.maze5_end_video){
			//If we are in the fifth level of game, don't try to add other level. The game is over.
			return;
		}
		((MazeActivity) getActivity()).openNextLevel();
		
	}

	@Override
	protected void onAudioComplete(int audioFileId) {
	}

}
