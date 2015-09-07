package com.moderneng;

import android.app.Application;

import com.game.utils.AppConstant;

public class WeShineApp extends Application {
	private static WeShineApp singleton;
	private static String sLanguage = null;


    //Sound Ids
    public static int sSoundIdGameOver;
    public static int sSoundIdGameOverTingTing;
	public static int sSoundIdFindTheSimilarCards;
	public static int sSoundIdGolfCart;
	public static int sSoundIdGreenBilli;
	public static int sSoundIdRedBilli;
	public static int sSoundIdSolarStation;
	public static int sSoundIdSolarLight;
	public static int sSoundIdSolarSignal;
	public static int sSoundIdLovelySun;
	public static int sSoundIdWellDone;
	public static int sSoundIdCongratulationsShort;
	public static int sSoundIdSolarCamera;
	public static int sSoundIdYouAreSmart;
	public static int sSoundIdPopTheBalloons;
	public static int sSoundIdPerfect;
	public static int sSoundIdGoodjob;
	public static int sSoundIdFabulous;
	public static int sSoundIdGreat;
	public static int sSoundIdExcellent;
	public static int sSoundIdSuper;
	public static int sSoundIdCongratulationsAndSound;
	public static int sSoundIdBatteryFullThankYou;
	public static int sSoundIdCatchUpTheSun;
	public static int sSoundIdMatching;
	public static int sSoundIdMatching1;
	public static int sSoundIdMatching2;
	public static int sSoundIdMatching3;
	public static int sSoundIdMatching4;
	public static int sSoundIdMatching5;
	public static int sSoundIdPuzzle;
	public static int sSoundIdBravo;
	public static int sSoundIdAwsome;
	public static int sSoundIdMaze;
	public static int sSoundIdMaze1;
	public static int sSoundIdMaze2;
	public static int sSoundIdMaze3;
	public static int sSoundIdMaze4;
	public static int sSoundIdMaze5;
	public static int sSoundIdTerrific;
	public static int sSoundIdIncredible;
	public static int sSoundIdMemoryGames;
	public static int sSoundIdStory;




    //DrawableIds
    public static int sImageIdWellDone;
    public static int sImageIdCongratulations;
    public static int sImageIdYouAreSmart;
	public static int sImageIdEduMenuImg1;
	public static int sImageIdEduMenuImg2;
	public static int sImageIdEduMenuImg3;
	public static int sImageIdEduMenuImg4;
	public static int sImageIdEduMenuImg5;
	public static int sImageIdEduBg;
	public static int sImageIdGameOver;
	public static int sImageIdBatteryIsFullThanYou;
	public static int sImageIdAwesome;
	public static int sImageIdPerfect;
	public static int sImageIdGreat;
	public static int sImageIdGoogJob;
	public static int sImageIdTerrific;
	public static int sImageIdFabulous;
	public static int sImageIdSuper;
	public static int sImageIdExcellent;
	public static int sImageIdMatchingMenuBg;
	public static int sImageIdPuzzleMenuBg;
	public static int sImageIdIncredible;
	public static int sImageIdMatching;
	public static int sImageIdPuzzle;
	public static int sImageIdMaze;
	public static int sImageIdMemoryGame;
	public static int sImageIdMemoryGames;
	public static int sImageIdMemoryBg;
	public static int sImageIdMemoryBgHotspot;
	public static int sImageIdMemoryGamesLevel1;
	public static int sImageIdMemoryGamesLevel2;
	public static int sImageIdMemoryGamesLevel3;
	public static int sImageIdEducation;
	public static int sImageIdHomeScreen;
	public static int sImageIdStoryEndThankYouImage;


	//Video Durations
	public static int sStoryVideoDuration;


	public static WeShineApp getInstance() {
		return singleton;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;
	}

	public static WeShineApp getSingleton() {
		return singleton;
	}

	public static void setSingleton(WeShineApp singleton) {
		WeShineApp.singleton = singleton;
	}

	public static String getLanguage() {
		return sLanguage;
	}

	public static void setLanguage(String sLanguage) {
		WeShineApp.sLanguage = sLanguage;
		assignSoundAndVideosIds();
		assignDrawableIds();
		assignVideoDurations();
	}

	private static void assignDrawableIds() {
		if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
            sImageIdWellDone = R.drawable.well_done;
            sImageIdCongratulations = R.drawable.congratulations;
            sImageIdYouAreSmart = R.drawable.you_are_smart;
			sImageIdEduMenuImg1 = R.drawable.edu_menu_img1;
			sImageIdEduMenuImg2 = R.drawable.edu_menu_img2;
			sImageIdEduMenuImg3 = R.drawable.edu_menu_img3;
			sImageIdEduMenuImg4 = R.drawable.edu_menu_img4;
			sImageIdEduMenuImg5 = R.drawable.edu_menu_img5;
            sImageIdEduBg = R.drawable.edubg;
            sImageIdGameOver = R.drawable.gameover;
            sImageIdBatteryIsFullThanYou = R.drawable.battery_is_full_thankyou;
            sImageIdAwesome = R.drawable.awsome;
            sImageIdPerfect = R.drawable.perfect;
            sImageIdGreat = R.drawable.great;
            sImageIdGoogJob = R.drawable.good_job;
            sImageIdTerrific = R.drawable.terrific;
            sImageIdFabulous = R.drawable.fabulous;
            sImageIdSuper = R.drawable.super_img;
            sImageIdExcellent = R.drawable.excellent;
			//sImageIdMatchingMenuBg = R.drawable.matchingmenu;
			sImageIdMatchingMenuBg = R.drawable.maching_menu_bg;
			//sImageIdPuzzleMenuBg = R.drawable.puzzlemenu;
			sImageIdPuzzleMenuBg = R.drawable.puzzle_menu_bg;
            sImageIdIncredible = R.drawable.incredible;
			sImageIdMatching = R.drawable.matching;
			sImageIdPuzzle = R.drawable.puzzle_text;
			sImageIdMaze = R.drawable.maze_text;
			sImageIdMemoryGame = R.drawable.memory_game;
			sImageIdMemoryGames = R.drawable.memory_games;
			sImageIdMemoryBg = R.drawable.memory_games_bg;
			sImageIdMemoryBgHotspot = R.drawable.memory_games_bg_hotspot;
			sImageIdMemoryGamesLevel1 = R.drawable.memory_games_bg_level1;
			sImageIdMemoryGamesLevel2 = R.drawable.memory_games_bg_level2;
			sImageIdMemoryGamesLevel3 = R.drawable.memory_games_bg_level3;
            sImageIdEducation = R.drawable.education;
			sImageIdHomeScreen = R.drawable.home_screen;
			sImageIdStoryEndThankYouImage = R.drawable.story_end_image;



        }else if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ARABIC)){
            sImageIdWellDone = R.drawable.arb_well_done;
            sImageIdCongratulations = R.drawable.congratulations_arb;
            sImageIdYouAreSmart = R.drawable.you_are_smart_arb;
            sImageIdEduMenuImg1 = R.drawable.edu_menu_img1_arb;
            sImageIdEduMenuImg2 = R.drawable.edu_menu_img2_arb;
            sImageIdEduMenuImg3 = R.drawable.edu_menu_img3_arb;
            sImageIdEduMenuImg4 = R.drawable.edu_menu_img4_arb;
            sImageIdEduMenuImg5 = R.drawable.edu_menu_img5_arb;
            sImageIdEduBg = R.drawable.edubg;
            sImageIdGameOver = R.drawable.gameover_arb;
            sImageIdBatteryIsFullThanYou = R.drawable.battery_is_full_thankyou_arb;
            sImageIdAwesome = R.drawable.awesome_arb;
            sImageIdPerfect = R.drawable.perfect_arb;
            sImageIdGreat = R.drawable.great_arb;
            sImageIdGoogJob = R.drawable.good_job_arb;
            sImageIdTerrific = R.drawable.terrific_arb;
            sImageIdFabulous = R.drawable.fabulous_arb;
            sImageIdSuper = R.drawable.super_img_arb;
            sImageIdExcellent = R.drawable.excellent_arb;
			//sImageIdMatchingMenuBg = R.drawable.maching_menu_bg_arb;
			sImageIdMatchingMenuBg = R.drawable.maching_menu_bg;
			//sImageIdPuzzleMenuBg = R.drawable.puzzle_menu_bg_arb;
			sImageIdPuzzleMenuBg = R.drawable.puzzle_menu_bg;
            sImageIdIncredible = R.drawable.incredible_arb;
			sImageIdMatching = R.drawable.matching_arb;
			sImageIdPuzzle = R.drawable.puzzle_text_arb;
			sImageIdMaze = R.drawable.maze_text_arb;
			sImageIdMemoryGame = R.drawable.memory_game_arb;
            sImageIdMemoryGames = R.drawable.memory_game_arb;
			sImageIdMemoryBg = R.drawable.memory_games_bg;
			sImageIdMemoryBgHotspot = R.drawable.memory_games_bg_hotspot;
			sImageIdMemoryGamesLevel1 = R.drawable.memory_games_bg_level1;
			sImageIdMemoryGamesLevel2 = R.drawable.memory_games_bg_level2;
			sImageIdMemoryGamesLevel3 = R.drawable.memory_games_bg_level3;
            sImageIdEducation = R.drawable.education_arb;
			sImageIdHomeScreen = R.drawable.home_screen_arb;
			sImageIdStoryEndThankYouImage = R.drawable.arb_story_end_image;
		}
	}

	private static void assignSoundAndVideosIds(){
		if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
			sSoundIdFindTheSimilarCards = R.raw.findthesimiliarcards;
            sSoundIdGameOver = R.raw.game_over;
			sSoundIdGameOverTingTing = R.raw.game_over_ting_ting;
			sSoundIdGolfCart = R.raw.golf_cart;
			sSoundIdGreenBilli = R.raw.green_bouy;
			sSoundIdWellDone = R.raw.well_done;
            sSoundIdSolarStation = R.raw.solar_station;
            sSoundIdSolarLight = R.raw.solar_light;
            sSoundIdSolarSignal = R.raw.solar_signal;
            sSoundIdLovelySun = R.raw.lovely_sun;
            sSoundIdCongratulationsShort = R.raw.congratulations_short;
            sSoundIdRedBilli = R.raw.red_bouy;
            sSoundIdSolarCamera = R.raw.solar_camera;
            sSoundIdYouAreSmart = R.raw.you_are_smart;
			sSoundIdPopTheBalloons = R.raw.pop_the_balloons;
			sSoundIdPerfect = R.raw.perfect;
			sSoundIdGoodjob = R.raw.goodjob;
			sSoundIdFabulous = R.raw.fabulous;
			sSoundIdGreat = R.raw.great;
			sSoundIdExcellent = R.raw.excellent;
            sSoundIdSuper = R.raw.super_sound;
            sSoundIdCongratulationsAndSound = R.raw.congratulations_and_sound;
            sSoundIdBatteryFullThankYou = R.raw.battery_full_thankyou;
            sSoundIdCatchUpTheSun = R.raw.catchup_the_sun;
            sSoundIdMatching = R.raw.matching;
            sSoundIdMatching1 = R.raw.matching1_video;
            sSoundIdMatching2 = R.raw.matching2_video;
            sSoundIdMatching3 = R.raw.matching3_video;
            sSoundIdMatching4 = R.raw.matching4_video;
            sSoundIdMatching5 = R.raw.matching5_video;
            sSoundIdPuzzle = R.raw.puzzle;
            sSoundIdBravo = R.raw.bravo;
            sSoundIdAwsome = R.raw.awesome;
            sSoundIdMaze = R.raw.maze_sound;
            sSoundIdMaze1 = R.raw.maze1;
            sSoundIdMaze2 = R.raw.maze2;
            sSoundIdMaze3 = R.raw.maze3;
            sSoundIdMaze4 = R.raw.maze4;
            sSoundIdMaze5 = R.raw.maze5;
            sSoundIdTerrific = R.raw.terrific;
            sSoundIdIncredible = R.raw.incredible;
            sSoundIdMemoryGames = R.raw.memmory_games;
			sSoundIdStory = R.raw.story;

		}else if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ARABIC)){
			sSoundIdFindTheSimilarCards = R.raw.find_the_similar_cards_arb;
            sSoundIdGameOver = R.raw.game_over_ting_ting_arb;
			sSoundIdGameOverTingTing = R.raw.game_over_ting_ting_arb;
			sSoundIdGolfCart = R.raw.golf_cart_arb;
			sSoundIdGreenBilli = R.raw.green_bouy_arb;
			sSoundIdWellDone = R.raw.well_done_arb;
			sSoundIdSolarLight = R.raw.solar_light_arb;
            sSoundIdSolarStation = R.raw.solar_station_arb;
            sSoundIdSolarSignal = R.raw.solar_signal_arb;
            sSoundIdLovelySun = R.raw.lovely_sun_arb;
            sSoundIdCongratulationsShort = R.raw.congratulations_short_arb;
            sSoundIdRedBilli = R.raw.red_bouy_arb;
            sSoundIdSolarCamera = R.raw.solar_camera_arb;
            sSoundIdYouAreSmart = R.raw.you_are_smart_arb;
			sSoundIdPopTheBalloons = R.raw.pop_the_balloons_arb;
			sSoundIdPerfect = R.raw.perfect_arb;
			sSoundIdGoodjob = R.raw.goodjob_arb;
			sSoundIdFabulous = R.raw.fabulous_arb;
			sSoundIdGreat = R.raw.great_arb;
			sSoundIdExcellent = R.raw.excellent_arb;
            sSoundIdSuper = R.raw.super_sound_arb;
            sSoundIdCongratulationsAndSound = R.raw.congratulations_and_sound_arb;
            sSoundIdBatteryFullThankYou = R.raw.battery_full_thankyou_arb;
            sSoundIdCatchUpTheSun = R.raw.catchup_the_sun_arb;
            sSoundIdMatching = R.raw.matching_arb;
            sSoundIdMatching1 = R.raw.matching1_arb;
            sSoundIdMatching2 = R.raw.matching2_arb;
            sSoundIdMatching3 = R.raw.matching3_arb;
            sSoundIdMatching4 = R.raw.matching4_arb;
            sSoundIdMatching5 = R.raw.matching5_arb;
            sSoundIdPuzzle = R.raw.puzzle_arb;
            sSoundIdBravo = R.raw.bravo_arb;
            sSoundIdAwsome = R.raw.awesome_arb;
            sSoundIdMaze = R.raw.maze_sound_arb;
            sSoundIdMaze1 = R.raw.maze1_arb;
            sSoundIdMaze2 = R.raw.maze2_arb;
            sSoundIdMaze3 = R.raw.maze3_arb;
            sSoundIdMaze4 = R.raw.maze4_arb;
            sSoundIdMaze5 = R.raw.maze5_arb;
            sSoundIdTerrific = R.raw.terrific_arb;
            sSoundIdIncredible = R.raw.incredible_arb;
            sSoundIdMemoryGames = R.raw.memmory_games_arb;
			sSoundIdStory = R.raw.story_arb;
        }
	}

	private static void assignVideoDurations(){
		if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
			sStoryVideoDuration = AppConstant.STORY_VIDEO_DURATION;

		}else if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ARABIC)){
			sStoryVideoDuration = AppConstant.ARB_STORY_VIDEO_DURATION;
		}

	}
}
