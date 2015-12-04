package com.jixstreet.crimeapps;

import com.jixstreet.crimeapps.utils.CommonConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M Agung Satrio - agung.satrio@inmagine.com on 12/4/2015.
 */
public class Seeder {

    public static List<String> getCrimeType() {
        List<String> crimeTypeList = new ArrayList<>();

        crimeTypeList.add(CommonConstants.ARSON);
        crimeTypeList.add(CommonConstants.ASSAULT);
        crimeTypeList.add(CommonConstants.BURGLARY);
        crimeTypeList.add(CommonConstants.HANDCUFFS);
        crimeTypeList.add(CommonConstants.ROBBER);
        crimeTypeList.add(CommonConstants.SHOOT);
        crimeTypeList.add(CommonConstants.THEFT);

        return crimeTypeList;
    }
}
