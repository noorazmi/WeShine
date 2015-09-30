package com.moderneng.providers;

import android.net.Uri;

import com.android.vending.expansion.zipfile.APEZProvider;
import com.moderneng.utils.FlavourConstants;

import java.io.File;

/**
 * <p/>
 * Created by: Noor  Alam on 15/09/15.<br/>
 * Email id: noor.alam@tothenew.com<br/>
 * Skype id: mfsi_noora
 * <p/>
 */
public class CustomAPEZProvider extends APEZProvider {


    @Override
    public String getAuthority() {
        return FlavourConstants.AUTHORITY;
    }


    public static Uri buildUri(String path) {
        StringBuilder contentPath = new StringBuilder("content://");

        contentPath.append(FlavourConstants.AUTHORITY);
        contentPath.append(File.separator);
        contentPath.append(path);

        return Uri.parse(contentPath.toString());
    }
}

