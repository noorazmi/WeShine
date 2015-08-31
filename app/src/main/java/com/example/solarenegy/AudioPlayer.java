package com.example.solarenegy;

import com.example.solarenegy.HorizontalPage.OnScreenSwitchListener;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class AudioPlayer {
	private MediaPlayer mp;
	private Context mContext;

	public AudioPlayer(Context ctx, int id) {
		this.mContext = ctx;
		mp = new MediaPlayer();
		mp = MediaPlayer.create(mContext, id);
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.setLooping(false);
	}

	public AudioPlayer(OnScreenSwitchListener onScreenSwitchListener, int id) {
		mp = new MediaPlayer();
		mp.reset();
		mp.release();
		mp = MediaPlayer.create(mContext, id);
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.setLooping(false);
	}

	public void start() {
		if(mp != null){
			mp.start();
		}
	}

	public void stop() {
		if (mp != null && mp.isPlaying() == true) {
			mp.stop();
		}
	}

	public void pause() {
		if (mp != null && mp.isPlaying() == true) {
			mp.pause();
		}
	}

	public void release() {
		if (mp != null) {
			mp.release();
		}

	}
}
