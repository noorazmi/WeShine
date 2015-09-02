package com.moderneng;

import android.app.Application;

import com.game.utils.AppConstant;

public class WeShineApp extends Application {
	private static WeShineApp singleton;
	private static String sLanguage = null;


    //Sound Ids
	public static int sSoundIdGameOver;
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

    //DrawableIds
    public static int sImageIdWellDone;
    public static int sImageIdCongratulations;
    public static int sImageIdYouAreSmart;


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
		assignSoundIds();
		assignDrawableIds();
	}

	private static void assignDrawableIds() {
		if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
            sImageIdWellDone = R.drawable.well_done;
            sImageIdCongratulations = R.drawable.congrats;
            sImageIdYouAreSmart = R.drawable.you_are_smart;
		}else if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ARABIC)){
            sImageIdWellDone = R.drawable.arb_well_done;
            sImageIdCongratulations = R.drawable.congrats;
            sImageIdYouAreSmart = R.drawable.you_are_smart_arb;
		}
	}

	private static void assignSoundIds(){
		if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ENGLISH)){
			sSoundIdFindTheSimilarCards = R.raw.findthesimiliarcards;
			sSoundIdGameOver = R.raw.gameover;
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
		}else if(WeShineApp.getLanguage().equals(AppConstant.LANGUAGE_ARABIC)){
			sSoundIdFindTheSimilarCards = R.raw.find_the_similar_cards_arb;
			sSoundIdGameOver = R.raw.gameover;
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
		}
	}
}