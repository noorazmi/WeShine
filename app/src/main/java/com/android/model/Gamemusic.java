package com.android.model;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.example.solarenegy.HorizontalPage.OnScreenSwitchListener;

public class Gamemusic {
	MediaPlayer mp;

	Context c;

	private OnCompleteListener mOnCompleteListener;

	public Gamemusic(Context ctx, int id) {
		this.c = ctx;
		mp = new MediaPlayer();
	
		mp = MediaPlayer.create(c, id);
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				if(mOnCompleteListener != null){
					mOnCompleteListener.onComplete();
				}
				mp.reset();
				mp.release();
			}
		});
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.setLooping(false);
	}

	public Gamemusic(OnScreenSwitchListener onScreenSwitchListener, int id) {
		// TODO Auto-generated constructor stub

		mp = new MediaPlayer();
		mp = MediaPlayer.create(c, id);
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.setLooping(false);
	}
	public void start() {
		if (mp != null) {
			mp.start();
		}
	}
	public void stop() {
		if (mp != null && mp.isPlaying() == true) {
			mp.stop();
		}
	}
//	public void pause() {
////		if (mp != null && mp.isPlaying() == true) {
//		if (mp != null) {
//			mp.pause();
//		}
	//}
	public void release() {
		if (mp != null ) {
			mp.release();
		}
	}

	public void setOnCompleteListener(OnCompleteListener onCompleteListener){
		this.mOnCompleteListener = onCompleteListener;
	}

	public static interface OnCompleteListener{
		void onComplete();
	}

}
