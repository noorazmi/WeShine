package com.game.framents;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.VideoView;

import com.game.utils.ConstantValues;
import com.moderneng.R;
import com.moderneng.activities.MazeActivity;

public class GameEndVideoFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getWindow().setFormat(PixelFormat.UNKNOWN);
        int videoDuration = getArguments().getInt(ConstantValues.BUNDLE_EXTRA_VIDEO_DURATION);
        // Displays a video file.
        View v = getFragmentView();
        VideoView mVideoView = (VideoView) v.findViewById(R.id.videoview);
        String uriPath = ConstantValues.BASE_RESOURCE_PATH + getArguments().getInt(ConstantValues.VIDEO_FILE_NAME);
        Uri uri = Uri.parse(uriPath);
        mVideoView.setVideoURI(uri);
        mVideoView.requestFocus();
        mVideoView.setOnCompletionListener(this);
        mVideoView.start();

        new CountDownTimer(videoDuration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished <= 2500){
                    startNextMaze();
                }
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.game_end_video_fragment;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp = null;
    }

    private void startNextMaze(){
        if(getArguments().getInt(ConstantValues.VIDEO_FILE_NAME) == R.raw.maze1_end_video){
            ((MazeActivity)getActivity()).AttachGameFragment(ConstantValues.GAME_LEVEL_1);
        }else if(getArguments().getInt(ConstantValues.VIDEO_FILE_NAME) == R.raw.maze2_end_video){
            ((MazeActivity)getActivity()).AttachGameFragment(ConstantValues.GAME_LEVEL_2);
        }else if(getArguments().getInt(ConstantValues.VIDEO_FILE_NAME) == R.raw.maze3_end_video){
            ((MazeActivity)getActivity()).AttachGameFragment(ConstantValues.GAME_LEVEL_3);
        }else if(getArguments().getInt(ConstantValues.VIDEO_FILE_NAME) == R.raw.maze4_end_video){
            ((MazeActivity)getActivity()).AttachGameFragment(ConstantValues.GAME_LEVEL_4);
        }else if(getArguments().getInt(ConstantValues.VIDEO_FILE_NAME) == R.raw.maze5_end_video){
            ((MazeActivity)getActivity()).gotToMazeGameMenu();

        }
    }
    @Override
    protected void onAudioComplete(int audioFileId) {
    }

}
