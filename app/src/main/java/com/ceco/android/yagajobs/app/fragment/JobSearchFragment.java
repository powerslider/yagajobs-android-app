package com.ceco.android.yagajobs.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ceco.android.yagajobs.app.R;

/**
 * Created by ceco on 01.06.14.
 */
public class JobSearchFragment extends Fragment {

    private static final String WHAT_KEY = "what";
    private static final String WHERE_KEY = "where";

    private EditText what;
    private EditText where;
    private Button searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_job_search, container, false);

        what = (EditText) rootView.findViewById(R.id.what_box);
        where = (EditText) rootView.findViewById(R.id.where_box);
        searchButton = (Button)rootView.findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String whatStr = what.getText().toString();
                String whereStr = where.getText().toString();

                Intent intent = new Intent(view.getContext(), VacancyListPageFragment.class);
                intent.putExtra(WHAT_KEY, whatStr);
                intent.putExtra(WHERE_KEY, whereStr);

                startActivity(intent);
            }
        });

        return rootView;
    }

    public static JobSearchFragment newInstance() {
        return new JobSearchFragment();
    }

}

