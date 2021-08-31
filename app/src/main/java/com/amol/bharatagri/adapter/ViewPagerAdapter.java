package com.amol.bharatagri.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.amol.bharatagri.fragment.FavouritesFragment;
import com.amol.bharatagri.fragment.PopularFragment;
import com.amol.bharatagri.fragment.TopRatedFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static TopRatedFragment topRatedFragment;
    private static PopularFragment popularFragment;
    private static FavouritesFragment favouritesFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public static void reloadFavouriteMovies(Context context) {

        if (favouritesFragment != null) {
            favouritesFragment.loadMovies(context);
        }
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return TopRatedFragment.newInstance();
            case 1:
                return PopularFragment.newInstance();
            case 2:
                return FavouritesFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return TopRatedFragment.TITLE;
            case 1:
                return PopularFragment.TITLE;
            case 2:
                return FavouritesFragment.TITLE;

        }
        return super.getPageTitle(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);

        switch (position) {
            case 0:
                topRatedFragment = (TopRatedFragment) fragment;
                break;
            case 1:
                popularFragment = (PopularFragment) fragment;
                break;
            case 2:
                favouritesFragment = (FavouritesFragment) fragment;
                break;
        }
        return fragment;
    }
}