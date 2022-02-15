package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.AdCat;

import java.util.List;

public interface AdCats {
    // get a list of all the ads
    List<AdCat> all();
    // insert a new ad and return the new ad's id
    Long insert(long catId, long adId);
}
