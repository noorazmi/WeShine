package com.example.solarenegy;

import com.example.solarenegy.HorizontalPage.OnScreenSwitchListener;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class playaudio {
	MediaPlayer mp;
	Context c;

	public playaudio(Context ctx, int id) {
		this.c = ctx;
		mp = new MediaPlayer();
		mp = MediaPlayer.create(c, id);
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.setLooping(false);
	}

	public playaudio(OnScreenSwitchListener onScreenSwitchListener, int id) {
		// TODO Auto-generated constructor stub

		mp = new MediaPlayer();
		mp.reset();
		mp.release();
		mp = MediaPlayer.create(c, id);
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.setLooping(false);
	}

	public void start() {
		// TODO Auto-generated method stub
		mp.start();
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
		//mp.reset();
		mp.release();
	}
}
