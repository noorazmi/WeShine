package com.game.utils;

import com.moderneng.WeShineApp;

public interface AppConstant {
	//public static final String BASE_RESOURCE_PATH = "android.resource://com.moderneng/";
	String BASE_RESOURCE_PATH = "android.resource://"+ WeShineApp.getInstance().getPackageName()+"/";
	String VIDEO_FILE_NAME = "VIDEO_FILE_NAME";
	String APP_TAG = "[WeShine]";
	boolean APP_DEBUG = true;
	String POSITION = "position";
	int POINTS_COUNT = 200;
	int STROKE_WIDTH = 9;
	int GAME_LEVEL_0 = 0;
	int GAME_LEVEL_1 = 1;
	int GAME_LEVEL_2 = 2;
	int GAME_LEVEL_3 = 3;
	int GAME_LEVEL_4 = 4;

	String EXTRA_GREETING_IMAGE_RESOURCE_ID = "greeting_image_resource_id";
	String EXTRA_GREETING_SOUND_ID = "greeting_sound_id";
	String EXTRA_BALLOON_ANIMATION_SOUND_ID = "balloon_animation_sound_id";
	String EXTRA_BALLOON_ANIMATION_SOUND_DELAY = "balloon_animation_delay";
	String EXTRA_VIDEO_ID = "vid";

	int BALLOON_ANIMATION_SOUND_DELAY = 1000;

	String BUNDLE_EXTRA_VIDEO_DURATION = "video_duration";
	int MAZE_ONE_VIDEO_DURATION = 10000;//Milli Seconds
	int MAZE_TWO_VIDEO_DURATION = 11000;//Milli Seconds
	int MAZE_THREE_VIDEO_DURATION = 14000;//Milli Seconds
	int MAZE_FOUR_VIDEO_DURATION = 13000;//Milli Seconds
	int MAZE_FIVE_VIDEO_DURATION = 13000;//Milli Seconds


	int MACHING_ONE_VIDEO_DURATION = 5000;//Milli Seconds
	int MACHING_TWO_VIDEO_DURATION = 7000;// Milli Seconds
	int MACHING_THREE_VIDEO_DURATION = 9000;//Milli Seconds
	int MACHING_FOUR_VIDEO_DURATION = 9000;//Milli Seconds
	int MACHING_FIVE_VIDEO_DURATION = 9000;// Milli Seconds

	int STORY_VIDEO_DURATION =241000 ;// Milli Seconds
	int ARB_STORY_VIDEO_DURATION = 256000;//Milli Seconds

//	public static final int MACHING1_VIDEO_DURATION = 5000;
//	public static final int MACHING2_VIDEO_DURATION = 5000;
//	public static final int MACHING3_VIDEO_DURATION = 9000;
//	public static final int MACHING4_VIDEO_DURATION = 9000;
//	public static final int MACHING5_VIDEO_DURATION = 9000;



	String LANGUAGE_ARABIC = "arabic";
	String LANGUAGE_ENGLISH = "english";

}
