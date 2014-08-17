package com.ceco.android.yagajobs.appabstract.navdrawer;

/**
 * Created by Ceco on 13-May-14.
 */
public interface NavDrawerItem {

    int getId();

    String getLabel();

    int getType();

    boolean isEnabled();

    boolean updateActionBarTitle();
}
