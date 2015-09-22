package com.moderneng;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;

import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.android.vending.expansion.zipfile.ZipResourceFile;
import com.moderneng.utils.ImageAndMediaResources;

import java.io.IOException;

public class WeShineApp extends Application {
    private static WeShineApp singleton;
    private static String sLanguage = null;
    private static ZipResourceFile mExpansionFile = null;
    public static final String MEDIA_FILE_BASE_PATH = "main/raw/";
    public static final String IMAGE_FILE_BASE_PATH = "main/drawable/";

    public static WeShineApp getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        try {
            //mExpansionFile = APKExpansionSupport.getAPKExpansionZipFile(getApplicationContext(), APP_VERSION, -1);
            //mainExpansionVersion == version code of the apk.   Only main expansion file is being used
            int mainExpansionVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            mExpansionFile = APKExpansionSupport.getAPKExpansionZipFile(getApplicationContext(), mainExpansionVersion, -1/* patch file is not being used hence using patch version number less than 1*/);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
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
        ImageAndMediaResources.assignSoundAndVideosIds();
        ImageAndMediaResources.assignDrawableIds();
        ImageAndMediaResources.assignVideoDurations();
    }

    public static AssetFileDescriptor getAssetFileDescriptor(String fileName) {
        return mExpansionFile.getAssetFileDescriptor(MEDIA_FILE_BASE_PATH + fileName);
    }
}
