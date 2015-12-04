package com.jixstreet.crimeapps.utils;

import com.jixstreet.crimeapps.R;

/**
 * Created by M Agung Satrio - agung.satrio@inmagine.com on 12/4/2015.
 */
public class Utility {

    public static int getImageCrimeType(String imageType) {
        int imgType;
        switch (imageType) {
            case CommonConstants.ARSON:
                imgType = R.drawable.arson;
                break;
            case CommonConstants.ASSAULT:
                imgType = R.drawable.assault;
                break;
            case CommonConstants.BURGLARY:
                imgType = R.drawable.burglary;
                break;
            case CommonConstants.HANDCUFFS:
                imgType = R.drawable.handcuffs;
                break;
            case CommonConstants.ROBBER:
                imgType = R.drawable.robber;
                break;
            case CommonConstants.SHOOT:
                imgType = R.drawable.shoot;
                break;
            case CommonConstants.THEFT:
                imgType = R.drawable.theft;
                break;
            case CommonConstants.VANDALISM:
                imgType = R.drawable.vandalism;
                break;

            default:
                imgType = R.drawable.arson;
        }

        return imgType;
    }
}
