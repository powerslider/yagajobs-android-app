package com.ceco.android.yagajobs.appabstract.navdrawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ceco.android.yagajobs.app.R;

/**
 * Created by Ceco on 13-May-14.
 */
public abstract class AbstractDrawerActivity extends ActionBarActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private ListView drawerList;

    private CharSequence drawerTitle;
    private CharSequence title;

    private NavDrawerActivityConfiguration navConf ;
    private int[] menuItemTitles;

    protected abstract NavDrawerActivityConfiguration getNavDrawerConfiguration();

    protected abstract void onNavItemSelected( int id );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navConf = getNavDrawerConfiguration();

        setContentView(navConf.getMainLayout());

        title = drawerTitle = getTitle();

        drawerLayout = (DrawerLayout) findViewById(navConf.getDrawerLayoutId());
        drawerList = (ListView) findViewById(navConf.getLeftDrawerId());
        drawerList.setAdapter(navConf.getBaseAdapter());
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        this.initDrawerShadow();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerToggle = initDrawerToggle();
        drawerLayout.setDrawerListener(drawerToggle());
    }

    private ActionBarDrawerToggle drawerToggle() {
        return drawerToggle;
    }

    private ActionBarDrawerToggle initDrawerToggle() {
         return new ActionBarDrawerToggle(
                this,
                drawerLayout,
                getDrawerIcon(),
                navConf.getDrawerOpenDesc(),
                navConf.getDrawerCloseDesc()
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }
        };
    }

    protected void initDrawerShadow() {
        drawerLayout.setDrawerShadow(navConf.getDrawerShadow(), GravityCompat.START);
    }

    protected int getDrawerIcon() {
        return R.drawable.ic_drawer;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle().onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (navConf.getActionMenuItemsToHideWhenDrawerOpen() != null) {
            boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
            for( int iItem : navConf.getActionMenuItemsToHideWhenDrawerOpen()) {
                menu.findItem(iItem).setVisible(!drawerOpen);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle().onOptionsItemSelected(item)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_MENU ) {
            if ( this.drawerLayout.isDrawerOpen(this.drawerList)) {
                this.drawerLayout.closeDrawer(this.drawerList);
            }
            else {
                this.drawerLayout.openDrawer(this.drawerList);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    protected ActionBarDrawerToggle getDrawerToggle() {
        return drawerToggle();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    public void selectItem(int position) {
        NavDrawerItem selectedItem = navConf.getNavItems()[position];

        this.onNavItemSelected(selectedItem.getId());
        drawerList.setItemChecked(position, true);

        if ( selectedItem.updateActionBarTitle()) {
            setTitle(selectedItem.getLabel());
        }

        if ( this.drawerLayout.isDrawerOpen(this.drawerList)) {
            drawerLayout.closeDrawer(drawerList);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getSupportActionBar().setTitle(this.title);
    }
}
