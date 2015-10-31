package com.moderneng.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.moderneng.R;
import com.moderneng.utils.FlavourConstants;
import com.moderneng.utils.UtilityMethods;

/**
 * <p/>
 * Created by: Noor  Alam on 31/10/15.<br/>
 * Email id: noor.alam@tothenew.com<br/>
 * Skype id: mfsi_noora
 * <p/>
 */
public class AdvertisementLogoImageView extends ImageView implements View.OnClickListener{
    public AdvertisementLogoImageView(Context context) {
        super(context);
        init();
    }

    public AdvertisementLogoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdvertisementLogoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        if(FlavourConstants.SHOW_ADVERTISEMENT){
            setVisibility(View.VISIBLE);
            setImageResource(R.drawable.ic_zain);
            //setOnClickListener(this);
        }else {
            setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        UtilityMethods.openAdvertisementInWebView();
    }
}
