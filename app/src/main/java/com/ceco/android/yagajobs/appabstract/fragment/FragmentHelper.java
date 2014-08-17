package com.ceco.android.yagajobs.appabstract.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Ceco on 18-May-14.
 */
public class FragmentHelper {

    public static void initFragment(Fragment frag, int container, FragmentManager fm) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(container, frag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    public static void swapFragment(int container1, int container2, FragmentManager fm) {
        Fragment f1 = fm.findFragmentById(container1);
        Fragment f2 = fm.findFragmentById(container2);

        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(f1);
        ft.remove(f2);
        ft.commit();
        fm.executePendingTransactions();

        ft = fm.beginTransaction();
        ft.add(container1, f2);
        ft.add(container2, f1);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        ft.commit();
    }

    public static void removeFragment(int container, FragmentManager fm) {
        Fragment f1 = fm.findFragmentById(container);

        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(f1);
        ft.commit();
    }
}
