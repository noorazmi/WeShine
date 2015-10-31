package com.moderneng;

import android.app.Application;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.android.vending.expansion.zipfile.ZipResourceFile;
import com.moderneng.utils.FlavourConstants;
import com.moderneng.utils.ImageAndMediaResources;

import java.io.IOException;
import java.io.InputStream;

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
            mExpansionFile = APKExpansionSupport.getAPKExpansionZipFile(getApplicationContext(), FlavourConstants.MAIN_EXPANSION_FILE_VERSION, -1/* patch file is not being used hence using patch version number less than 1*/);
            Log.d("TAG", "Print");
        } catch (IOException e) {
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

    public static ZipResourceFile getExpansionFile() {
        return mExpansionFile;
    }

    public static Bitmap getBitmapFromObb(String imageNameWithExtension) {

        InputStream inputStream = null;
        try {
            inputStream = mExpansionFile.getInputStream(IMAGE_FILE_BASE_PATH + imageNameWithExtension);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, bitmapOptions);
        return bitmap;
    }


    public static Bitmap getBitmapFromObb(String imageNameWithExtension, int reqWidth, int reqHeight) {

        InputStream inputStream = null;
        try {
            inputStream = mExpansionFile.getInputStream(IMAGE_FILE_BASE_PATH + imageNameWithExtension);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bitmapOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, bitmapOptions);
        bitmapOptions.inSampleSize = calculateInSampleSize(bitmapOptions, reqWidth, reqHeight);
        try {
            inputStream.close();
            inputStream = null;
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            inputStream = mExpansionFile.getInputStream(IMAGE_FILE_BASE_PATH + imageNameWithExtension);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmapOptions.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, bitmapOptions);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap getBitmapFromResource(int resId, int reqWidth, int reqHeight){
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getSingleton().getResources(), resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getSingleton().getResources(), resId, options);
    }

    public static int getResourceId(String resourceName, String resourceType){
       return singleton.getResources().getIdentifier(resourceName, resourceType, singleton.getPackageName());
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
