package com.example.dulanjana.movieapplication.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter{

    int noOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.noOfTabs = numOfTabs;
    }


    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                PopularFragment popularFragment = new PopularFragment();
                return popularFragment;
            case 1:
                upComingFragment upComingFragment = new upComingFragment();
                return upComingFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
