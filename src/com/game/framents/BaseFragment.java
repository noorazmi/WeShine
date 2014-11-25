package com.game.framents;

import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.game.app.WeShineApp;
import com.game.utils.ConstantValues;
import com.game.weshine.R;

/**
 * Base Fragment for all fragment in the app.
 * 
 * @author noor.alam
 *
 */
public abstract class BaseFragment extends Fragment implements OnCompletionListener {

	private View mFragmentView;
	private int mAudioFileId = -1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mFragmentView = inflater.inflate(getFragmentLayoutId(), container, false);
		return mFragmentView;
	}

	/**
	 * Returns the view attached to the fragment
	 * 
	 * @return View
	 */
	protected View getFragmentView() {
		return mFragmentView;
	}

	protected void startAudioSound(int audioFileId) {

		String uriPath = ConstantValues.BASE_RESOURCE_PATH + audioFileId;
		mAudioFileId = audioFileId;
		Uri uri = Uri.parse(uriPath);
		MediaPlayer mediaPlayer = MediaPlayer.create(WeShineApp.getInstance(), uri);
		mediaPlayer.setOnCompletionListener(this);
		mediaPlayer.start();
		
//		 SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
//		 int soundID = soundPool.load(getActivity(), R.raw.maze1, 1);
//		 AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
//         float actualVolume = (float) audioManager
//                 .getStreamVolume(AudioManager.STREAM_MUSIC);
//         float maxVolume = (float) audioManager
//                 .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//         float volume = actualVolume / maxVolume;
//		 soundPool.play(soundID, volume, volume, 1, 0, 1f);
		

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		if (mp != null) {
			mp.release();
		}
		onAudioComplete(mAudioFileId);
	}

	
	
	// Returns the layout id which will be attached to the fragment as view
	protected abstract int getFragmentLayoutId();
	protected abstract void onAudioComplete(int audioFileId);

}
