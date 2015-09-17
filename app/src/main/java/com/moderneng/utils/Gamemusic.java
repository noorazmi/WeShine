package com.moderneng.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.moderneng.views.HorizontalPage;


public class Gamemusic {
	private MediaPlayer mMediaPlayer;
	private Context mContext;

	private OnCompleteListener mOnCompleteListener;

	public Gamemusic(Context ctx, int id) {
		this.mContext = ctx;
		mMediaPlayer = new MediaPlayer();
	
		mMediaPlayer = MediaPlayer.create(mContext, id);
		mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				if (mOnCompleteListener != null) {
					mOnCompleteListener.onComplete();
				}
				mp.reset();
				mp.release();
			}
		});
		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mMediaPlayer.setLooping(false);
	}

	public Gamemusic(HorizontalPage.OnScreenSwitchListener onScreenSwitchListener, int id) {
		mMediaPlayer = new MediaPlayer();
		mMediaPlayer = MediaPlayer.create(mContext, id);
		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mMediaPlayer.setLooping(false);
	}
	public void start() {
		if (mMediaPlayer != null) {
			mMediaPlayer.start();
		}
	}
	public void stop() {
		if (mMediaPlayer != null && mMediaPlayer.isPlaying() == true) {
			mMediaPlayer.stop();
		}
	}
	public void release() {
		if (mMediaPlayer != null ) {
			mMediaPlayer.release();
		}
	}

	public void setOnCompleteListener(OnCompleteListener onCompleteListener){
		this.mOnCompleteListener = onCompleteListener;
	}

	public interface OnCompleteListener{
		void onComplete();
	}

}
