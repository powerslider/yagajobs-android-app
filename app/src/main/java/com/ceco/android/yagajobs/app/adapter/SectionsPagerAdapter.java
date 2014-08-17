package com.ceco.android.yagajobs.app.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ceco.android.yagajobs.app.R;
import com.ceco.android.yagajobs.app.fragment.JobSearchFragment;
import com.ceco.android.yagajobs.app.fragment.TabPlaceholderFragment;

import java.util.Locale;

/**
 * Created by Ceco on 19-May-14.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context context;
    private int sectionCount;

    public SectionsPagerAdapter(int sectionCount, Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.sectionCount = sectionCount;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
//            case 0:
//                return JobSearchFragment.newInstance();
            default:
                return TabPlaceholderFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        return sectionCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale locale = Locale.getDefault();
        switch (position) {
            case 0:
                return context.getString(R.string.playground).toUpperCase(locale);
            case 1:
                return context.getString(R.string.pricing).toUpperCase(locale);
            case 2:
                return context.getString(R.string.use_cases).toUpperCase(locale);
            case 3:
                return context.getString(R.string.documentation);
        }
        return null;
    }
}

