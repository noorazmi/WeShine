package com.android.model;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.example.solarenegy.HorizontalPage.OnScreenSwitchListener;

public class Gamemusic {
	MediaPlayer mp;

	Context c;

	public Gamemusic(Context ctx, int id) {
		this.c = ctx;
		mp = new MediaPlayer();
	
		mp = MediaPlayer.create(c, id);
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		if (mp != null) {
			mp.start();
		}
	}
	public void stop() {
		if (mp.isPlaying() == true) {
			mp.stop();
		}
	}
	public void pause() {
		if (mp.isPlaying() == true) {
			mp.pause();
		}
	}
	public void release() {
		mp.release();
	}
}
