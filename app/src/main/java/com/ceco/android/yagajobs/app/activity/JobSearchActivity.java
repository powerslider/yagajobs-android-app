package com.ceco.android.yagajobs.app.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ceco.android.yagajobs.app.R;
import com.ceco.android.yagajobs.app.fragment.VacancyListPageFragment;
import com.ceco.android.yagajobs.appabstract.fragment.FragmentHelper;

/**
 * Created by ceco on 01.06.14.
 */
public class JobSearchActivity extends ActionBarActivity {

    private EditText what;
    private EditText where;
    private Button searchButton;
    private String vacancyListPageJsonUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);

//        what = (EditText) findViewById(R.id.what_box);
//        where = (EditText) findViewById(R.id.where_box);
//        loginButton = (Button)findViewById(R.id.remove_button);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                String usernameStr = username.getText().toString();
//                String passwordStr = password.getText().toString();
//
//                Intent intent = new Intent(view.getContext(), JobSearchActivity.class);
//                intent.putExtra(USER_KEY, usernameStr);
//                intent.putExtra(PASS_KEY, passwordStr);
//
//                printToastIfNeeded(usernameStr, passwordStr);
//                startActivity(intent);
//            }
//        });

        ActionBar actionBar = getSupportActionBar();

        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
         if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
             String query = intent.getStringExtra(SearchManager.QUERY);
             doMySearch(query);
         }
    }

    private void doMySearch(String query) {
        this.vacancyListPageJsonUrl = "http://yagajobs.co.uk/api/vacancies.json/search?title="
            + query +
            "&api_key=OWMyZjQ5Y2EyMzliZDUxNzU0YTk0NzMxMjFiZDEzMzEgIC0K&sortBy=DATEPOSTED&";


        FragmentHelper.initFragment(VacancyListPageFragment.newInstance(vacancyListPageJsonUrl),
            R.id.container1, getSupportFragmentManager());

    }
}
