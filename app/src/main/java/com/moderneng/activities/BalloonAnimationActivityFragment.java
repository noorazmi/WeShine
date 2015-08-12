package com.moderneng.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import com.game.utils.ConstantValues;
import com.game.utils.UtilityMethods;
import com.moderneng.R;
import com.moderneng.WeShineApp;

/**
 * A placeholder fragment containing a simple view.
 */
public class BalloonAnimationActivityFragment extends Fragment implements View.OnClickListener{

    private double screenSize;
    private View baloonFrame;
    private View balloon1, balloon2, balloon3, balloon4, balloon5, balloon6, balloon7, balloon8, balloon9, balloon10, balloon11, balloon12, balloon13;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private AnimatorSet baloonPanelAnimatorSet;

    private static final int BALOON_POP_PLAYER1 = 101;
    private static final int BALOON_POP_PLAYER2 = 102;
    private static final int BALOON_POP_PLAYER3 = 103;
    private static final int BALOON_POP_PLAYER4 = 104;
    private static final int BALOON_POP_PLAYER5 = 105;
    private int currentPopPlayer = BALOON_POP_PLAYER1;

    private MediaPlayer popPlayer1;
    private MediaPlayer popPlayer2;
    private MediaPlayer popPlayer3;
    private MediaPlayer popPlayer4;
    private MediaPlayer popPlayer5;
    private MediaPlayer heySoundPlayer;

    public BalloonAnimationActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        screenSize = UtilityMethods.getScreenSizeInInches(WeShineApp.getInstance());
        SCREEN_WIDTH = UtilityMethods.getScreenWidth(getActivity());
        SCREEN_HEIGHT = UtilityMethods.getScreenHeight(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_balloon_animation, container, false);

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AbsoluteLayout.LayoutParams alp;
        if (screenSize >= 9.4) {
            baloonFrame = layoutInflater.inflate(R.layout.baloon_frame_large, null);
            ((AbsoluteLayout)rootView).addView(baloonFrame);
            alp = (AbsoluteLayout.LayoutParams) baloonFrame.getLayoutParams();
            alp.y = SCREEN_HEIGHT - (int) UtilityMethods.convertDpToPixel(150, WeShineApp.getInstance());
            alp.x = (int) UtilityMethods.convertDpToPixel(200, WeShineApp.getInstance());
        } else if (screenSize >= 6.9) {

            baloonFrame = layoutInflater.inflate(R.layout.baloon_frame_medium, null);
            ((AbsoluteLayout)rootView).addView(baloonFrame);
            alp = (AbsoluteLayout.LayoutParams) baloonFrame.getLayoutParams();
            alp.y = SCREEN_HEIGHT - (int) UtilityMethods.convertDpToPixel(150, WeShineApp.getInstance());
            alp.x = (int) UtilityMethods.convertDpToPixel(140, WeShineApp.getInstance());
        } else {
            baloonFrame = layoutInflater.inflate(R.layout.baloon_frame_small, null);
            ((AbsoluteLayout)rootView).addView(baloonFrame);
            alp = (AbsoluteLayout.LayoutParams) baloonFrame.getLayoutParams();
            alp.y = SCREEN_HEIGHT - (int) UtilityMethods.convertDpToPixel(60, WeShineApp.getInstance());
            alp.x = (int) UtilityMethods.convertDpToPixel(70, WeShineApp.getInstance());
        }
        balloon1 = baloonFrame.findViewById(R.id.imageView1);
        balloon2 = baloonFrame.findViewById(R.id.imageView2);
        balloon3 = baloonFrame.findViewById(R.id.imageView3);
        balloon4 = baloonFrame.findViewById(R.id.imageView4);
        balloon5 = baloonFrame.findViewById(R.id.imageView5);
        balloon6 = baloonFrame.findViewById(R.id.imageView6);
        balloon7 = baloonFrame.findViewById(R.id.imageView7);
        balloon8 = baloonFrame.findViewById(R.id.imageView8);
        balloon9 = baloonFrame.findViewById(R.id.imageView9);
        balloon10 = baloonFrame.findViewById(R.id.imageView10);
        balloon11 = baloonFrame.findViewById(R.id.imageView11);
        balloon12 = baloonFrame.findViewById(R.id.imageView12);
        balloon13 = baloonFrame.findViewById(R.id.imageView13);
        baloonFrame.setLayoutParams(alp);
        setOnClickListener();

        return rootView;

    }

    private void setOnClickListener() {
        balloon1.setOnClickListener(this);
        balloon2.setOnClickListener(this);
        balloon3.setOnClickListener(this);
        balloon4.setOnClickListener(this);
        balloon5.setOnClickListener(this);
        balloon6.setOnClickListener(this);
        balloon7.setOnClickListener(this);
        balloon8.setOnClickListener(this);
        balloon9.setOnClickListener(this);
        balloon10.setOnClickListener(this);
        balloon11.setOnClickListener(this);
        balloon12.setOnClickListener(this);
        balloon13.setOnClickListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        startBaloonFrameAnimation();
        playWelldoneSound();
    }

    private void startBaloonFrameAnimation() {
        ObjectAnimator balloonPanelAnimY = ObjectAnimator.ofFloat(baloonFrame, "translationY", 0, (int) (-1.7 * SCREEN_HEIGHT));
        if (screenSize >= 9.4) {
            balloonPanelAnimY.setDuration(4000);
        } else {
            balloonPanelAnimY.setDuration(5000);
        }
        baloonPanelAnimatorSet = new AnimatorSet();
        baloonPanelAnimatorSet.play(balloonPanelAnimY);
        baloonPanelAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //sunrayDownImageView.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
//                Intent intent = new Intent();
//                getActivity().setResult(Activity.RESULT_OK, intent);
//                getActivity().finish();
            }
        });
        baloonPanelAnimatorSet.start();
    }

    @Override
    public void onClick(View v) {
        v.setVisibility(View.INVISIBLE);
        playBalloonSound();
    }

    private void playBalloonSound() {

        String uriPath = ConstantValues.BASE_RESOURCE_PATH + R.raw.balloon_sound;
        Uri uri = Uri.parse(uriPath);
        if (currentPopPlayer == BALOON_POP_PLAYER1) {
            popPlayer2 = MediaPlayer.create(WeShineApp.getInstance(), uri);
            popPlayer2.start();
            currentPopPlayer = BALOON_POP_PLAYER2;
            if (popPlayer5 != null) {
                popPlayer5.release();
                popPlayer5 = null;
            }
        } else if (currentPopPlayer == BALOON_POP_PLAYER2) {
            popPlayer3 = MediaPlayer.create(WeShineApp.getInstance(), uri);
            popPlayer3.start();
            currentPopPlayer = BALOON_POP_PLAYER3;
            if (popPlayer1 != null) {
                popPlayer1.release();
                popPlayer1 = null;
            }
        } else if (currentPopPlayer == BALOON_POP_PLAYER3) {
            popPlayer4 = MediaPlayer.create(WeShineApp.getInstance(), uri);
            popPlayer4.start();
            currentPopPlayer = BALOON_POP_PLAYER4;
            if (popPlayer2 != null) {
                popPlayer2.release();
                popPlayer2 = null;
            }
        } else if (currentPopPlayer == BALOON_POP_PLAYER4) {
            popPlayer5 = MediaPlayer.create(WeShineApp.getInstance(), uri);
            popPlayer5.start();
            currentPopPlayer = BALOON_POP_PLAYER5;
            if (popPlayer3 != null) {
                popPlayer3.release();
                popPlayer3 = null;
            }
        } else if (currentPopPlayer == BALOON_POP_PLAYER5) {
            popPlayer1 = MediaPlayer.create(WeShineApp.getInstance(), uri);
            popPlayer1.start();
            currentPopPlayer = BALOON_POP_PLAYER1;
            if (popPlayer4 != null) {
                popPlayer4.release();
                popPlayer4 = null;
            }
        }
    }

    private void playWelldoneSound() {
        String uriPath = ConstantValues.BASE_RESOURCE_PATH + R.raw.hey;
        Uri uri = Uri.parse(uriPath);
        heySoundPlayer = MediaPlayer.create(getActivity(), uri);
        heySoundPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                heySoundPlayer.release();
                heySoundPlayer = null;
                Intent intent = new Intent();
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });
        heySoundPlayer.start();

    }
}
