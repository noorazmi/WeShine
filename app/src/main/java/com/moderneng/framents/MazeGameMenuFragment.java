package com.moderneng.framents;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.moderneng.R;
import com.moderneng.activities.MazeMenuActivity;
import com.moderneng.animation.AnimType;
import com.moderneng.animation.AnimationUtil;
import com.moderneng.listeners.OnMazeMenuItemClickListener;
import com.moderneng.utils.AppConstant;
import com.moderneng.utils.AudioPlayer;
import com.moderneng.utils.GameMusic;
import com.moderneng.utils.ImageAndMediaResources;

public class MazeGameMenuFragment extends BaseFragment implements OnMazeMenuItemClickListener, View.OnClickListener {

    private ImageButton mImageButtonMaze1;
    private ImageButton mImageButtonMaze2;
    private ImageButton mImageButtonMaze3;
    private ImageButton mImageButtonMaze4;
    private ImageButton mImageButtonMaze5;
    private Bitmap mBitmapTitle;
    private GameMusic mp;
    private Bitmap mBitmapBg;
    private AudioPlayer mp4;


    @Override
	public void onResume() {
		super.onResume();
        mImageButtonMaze1 = (ImageButton) getFragmentView().findViewById(R.id.maze_menu_image1);
        mImageButtonMaze2 = (ImageButton) getFragmentView().findViewById(R.id.maze_menu_image2);
        mImageButtonMaze3 = (ImageButton) getFragmentView().findViewById(R.id.maze_menu_image3);
        mImageButtonMaze4 = (ImageButton) getFragmentView().findViewById(R.id.maze_menu_image4);
        mImageButtonMaze5 = (ImageButton) getFragmentView().findViewById(R.id.maze_menu_image5);

        mBitmapTitle =  BitmapFactory.decodeResource(getResources(), ImageAndMediaResources.sImageIdMaze);
        ((ImageView)getFragmentView().findViewById(R.id.imageview_title)).setImageBitmap(mBitmapTitle);
        AnimationUtil.performAnimation(getFragmentView().findViewById(R.id.imageview_title), AnimType.ZOOM_IN, null);

        mImageButtonMaze1.setOnClickListener(this);
        mImageButtonMaze2.setOnClickListener(this);
        mImageButtonMaze3.setOnClickListener(this);
        mImageButtonMaze4.setOnClickListener(this);
        mImageButtonMaze5.setOnClickListener(this);
        mp4 = new AudioPlayer(getActivity(), "homesound");

        playMazeSound();
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
	protected void onAudioComplete(String audioFileName) {
	}
	
    @Override
    public void onClick(View v) {
        if(mp4 != null){
            mp4.release();
            mp4 = null;
        }

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
//        String uriPath = AppConstant.BASE_RESOURCE_PATH + ImageAndMediaResources.sSoundIdMaze;
//        Uri uri = Uri.parse(uriPath);
        mp = new GameMusic(getActivity(), ImageAndMediaResources.sSoundIdMaze);
        mp.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mp4 != null){
                    mp4.start();
                }
            }
        }, 1000);

        mBitmapBg = BitmapFactory.decodeResource(getResources(), ImageAndMediaResources.sImageIdMazeMenuBg);
        getView().findViewById(R.id.linear_layout_container).setBackgroundDrawable(new BitmapDrawable(mBitmapBg));
    }

    @Override
    public void onStop() {
        super.onStop();
        mBitmapTitle.recycle();
        mBitmapTitle = null;
        if(mp !=null){
            mp.release();
        }
        if(mp4 !=null){
            mp4.release();
        }

        if(mBitmapBg != null){
            mBitmapBg.recycle();
            mBitmapBg = null;
        }
    }
}
