package com.game.framents;

import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;

import com.game.listeners.OnMazeMenuItemClickListener;
import com.game.utils.AppConstant;
import com.game.view.adapter.ImageViewPagerAdapter;
import com.moderneng.R;
import com.moderneng.WeShineApp;
import com.moderneng.activities.MazeMenuActivity;

public class MazeGameMenuFragment extends BaseFragment implements OnMazeMenuItemClickListener, View.OnClickListener {

	//private ViewPager mViewPager;

    private ImageButton mImageButtonMaze1;
    private ImageButton mImageButtonMaze2;
    private ImageButton mImageButtonMaze3;
    private ImageButton mImageButtonMaze4;
    private ImageButton mImageButtonMaze5;
	@Override
	public void onResume() {
		super.onResume();
        mImageButtonMaze1 = (ImageButton) getFragmentView().findViewById(R.id.maze_menu_image1);
        mImageButtonMaze2 = (ImageButton) getFragmentView().findViewById(R.id.maze_menu_image2);
        mImageButtonMaze3 = (ImageButton) getFragmentView().findViewById(R.id.maze_menu_image3);
        mImageButtonMaze4 = (ImageButton) getFragmentView().findViewById(R.id.maze_menu_image4);
        mImageButtonMaze5 = (ImageButton) getFragmentView().findViewById(R.id.maze_menu_image5);

        mImageButtonMaze1.setOnClickListener(this);
        mImageButtonMaze2.setOnClickListener(this);
        mImageButtonMaze3.setOnClickListener(this);
        mImageButtonMaze4.setOnClickListener(this);
        mImageButtonMaze5.setOnClickListener(this);

        playMazeSound();

		//mViewPager = (ViewPager) getFragmentView().findViewById(R.id.game_menu_view_pager);
		ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(getActivity());
		//adapter.setOnMazeMenuItemClickListener(this);
		//mViewPager.setAdapter(adapter);

	}

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.maze_game_menu;
	}

	@Override
	public void onGameMenuItemClick(int level) {
		((MazeMenuActivity) getActivity()).AttachGameFragment(level);
	}
	
	@Override
	protected void onAudioComplete(int audioFileId) {
	}
	
    public void setCurrentMenuItem(int level){

		//mViewPager.setCurrentItem(level);
    }

    @Override
    public void onClick(View v) {

        int level = 0;
        switch (v.getId()){
            case R.id.maze_menu_image1:
                level = AppConstant.GAME_LEVEL_0;
                break;
            case R.id.maze_menu_image2:
                level = AppConstant.GAME_LEVEL_1;
                break;
            case R.id.maze_menu_image3:
                level = AppConstant.GAME_LEVEL_2;
                break;
            case R.id.maze_menu_image4:
                level = AppConstant.GAME_LEVEL_3;
                break;
            case R.id.maze_menu_image5:
                level = AppConstant.GAME_LEVEL_4;
                break;
            default:
                break;

        }

        ((MazeMenuActivity) getActivity()).AttachGameFragment(level);
    }

    private void playMazeSound(){
        String uriPath = AppConstant.BASE_RESOURCE_PATH + WeShineApp.sSoundIdMaze;
        Uri uri = Uri.parse(uriPath);
        final MediaPlayer[] mediaPlayer = {MediaPlayer.create(WeShineApp.getInstance(), uri)};
        if(mediaPlayer[0] != null){
            mediaPlayer[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer[0].release();
                    mediaPlayer[0] = null;
                }
            });
            mediaPlayer[0].start();
        }
    }
}
