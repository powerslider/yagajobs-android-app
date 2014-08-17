package com.ceco.android.yagajobs.app.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ceco.android.yagajobs.app.R;
import com.ceco.android.yagajobs.app.adapter.SectionsPagerAdapter;
import com.ceco.android.yagajobs.app.fragment.VacancyListPageFragment;
import com.ceco.android.yagajobs.appabstract.fragment.FragmentHelper;


public class WebNavigationActivity extends ActionBarActivity implements ActionBar.TabListener {

    private static final int SECTION_COUNT = 4;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter sectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager viewPager;
    private String vacancyListPageJsonUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_navigation);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        sectionsPagerAdapter = new SectionsPagerAdapter(SECTION_COUNT, this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(actionBar.newTab()
                            .setText(sectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getSupportActionBar().getSelectedNavigationIndex());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);

        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                vacancyListPageJsonUrl = "http://yagajobs.co.uk/api/vacancies.json/search?title="
                    + query +
                    "&api_key=OWMyZjQ5Y2EyMzliZDUxNzU0YTk0NzMxMjFiZDEzMzEgIC0K&sortBy=DATEPOSTED&"; 
//                Intent intent = new Intent(getApplicationContext(), VacancyListPageActivity.class);
//                intent.putExtra("url", vacancyListPageJsonUrl);
                Intent intent = new Intent(getApplicationContext(), VacancyListPageActivity.class);
                intent.putExtra("url", vacancyListPageJsonUrl);
//                FragmentHelper.initFragment(VacancyListPageFragment.newInstance(vacancyListPageJsonUrl),
//                    R.id.container1, getSupportFragmentManager());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newQuery) {
                vacancyListPageJsonUrl = "http://yagajobs.co.uk/api/vacancies.json/search?title="
                    + newQuery +
                    "&api_key=OWMyZjQ5Y2EyMzliZDUxNzU0YTk0NzMxMjFiZDEzMzEgIC0K&sortBy=DATEPOSTED&";
//                FragmentHelper.initFragment(VacancyListPageFragment.newInstance(vacancyListPageJsonUrl),
//                    R.id.container2, getSupportFragmentManager());
                //FragmentHelper.removeFragment(R.id.container1, getSupportFragmentManager());
                Intent intent = new Intent(getApplicationContext(), VacancyListPageActivity.class);
                intent.putExtra("url", vacancyListPageJsonUrl);
//                FragmentHelper.initFragment(
//                    VacancyListPageFragment.newInstance(vacancyListPageJsonUrl),
//                    R.id.container1, 
//                    getSupportFragmentManager());
                return true;
            }
        };

        searchView.setOnQueryTextListener(textChangeListener);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

//    @Override
//    protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
//        NavDrawerItem[] menu = new NavDrawerItem[] {
//                NavMenuSection.create(100, "General"),
//                NavMenuItem.create(101, "Smile", "ic_smile", true, this),
//                NavMenuItem.create(102, "Stackoverflow Viewer", "ic_stackoverflow", true, this),
//                NavMenuSection.create(200, "Options"),
//                NavMenuItem.create(201, "Settings", "ic_settings", true, this),
//                NavMenuItem.create(202, "Logout", "navdrawer_quit", false, this)};
//
//        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
//        navDrawerActivityConfiguration.setMainLayout(R.layout.activity_web_navigation);
//        navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
//        navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
//        navDrawerActivityConfiguration.setNavItems(menu);
//        navDrawerActivityConfiguration.setDrawerShadow(R.drawable.drawer_shadow);
//        navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
//        navDrawerActivityConfiguration.setDrawerCloseDesc(R.string.drawer_close);
//        navDrawerActivityConfiguration.setBaseAdapter(new NavDrawerAdapter(this, R.layout.navdrawer_item, menu));
//
//        return navDrawerActivityConfiguration;
//    }
//
//    @Override
//    protected void onNavItemSelected(int id) {
//
//    }
}
