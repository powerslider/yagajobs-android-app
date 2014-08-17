package com.ceco.android.yagajobs.app.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.ceco.android.yagajobs.app.R;
import com.ceco.android.yagajobs.app.adapter.VacanciesExpandableListAdapter;
import com.ceco.android.yagajobs.app.adapter.model.VacancyExpandedListItem;
import com.ceco.android.yagajobs.app.adapter.model.VacancyListItem;
import com.ceco.android.yagajobs.app.model.Vacancy;
import com.ceco.android.yagajobs.appabstract.util.WebFetcher;
import com.google.gson.reflect.TypeToken;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ceco on 30.05.14.
 */
public class VacancyListPageFragment extends Fragment {

    private Context context;
    private final String vacancyJsonUrl;

    private VacanciesExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<VacancyListItem> vacancyListItems;
    private AbstractMap.SimpleEntry<VacancyListItem, VacancyExpandedListItem> vacancyExpandedListItem;

    public VacancyListPageFragment(String url) {
        this.vacancyJsonUrl = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vacancy_list, container, false);
        context = container.getContext();
        expListView = (ExpandableListView) view.findViewById(R.id.vacancy_exp_list);
        vacancyListItems = new ArrayList<>();

        YagaJobsVacancyListPageParseTask pageParseTask = new YagaJobsVacancyListPageParseTask();
        pageParseTask.execute();

        return view;
    }

    public static VacancyListPageFragment newInstance(String url) {
        return new VacancyListPageFragment(url);
    }

    private class YagaJobsVacancyListPageParseTask extends AsyncTask<String, Void, List<Vacancy>> {

        private List<Vacancy> vacancyList;

        @Override
        protected List<Vacancy> doInBackground(String... params) {
            WebFetcher vacancyWebFetcher = WebFetcher
                    .newInstance(vacancyJsonUrl)
                    .setListType(new TypeToken<List<Vacancy>>(){}.getType())
                    .parseJsonResponse();

            vacancyList = vacancyWebFetcher.getResponse();

            return vacancyList;
        }

        @Override
        protected void onPostExecute(List<Vacancy> vacancies) {
            super.onPostExecute(vacancies);

            for (Vacancy vacancy : vacancyList) {
                VacancyExpandedListItem vacancyExpandedListItem1 = new VacancyExpandedListItem();
                vacancyExpandedListItem1.setLocation(vacancy.getLocation());
                vacancyExpandedListItem1.setSalary(vacancy.getSalary());
                //vacancyExpandedListItem1.setTimeStamp(vacancy.getDateFound().toString());
                vacancyExpandedListItem1.setSourceJobBoard(vacancy.getJobBoard());

                VacancyListItem vacancyListItem = new VacancyListItem();
                vacancyListItem.setAdvertiserName(vacancy.getAdvertiserName());
                vacancyListItem.setTitle(vacancy.getTitle());
                vacancyExpandedListItem = new AbstractMap.SimpleEntry<>(vacancyListItem, vacancyExpandedListItem1);
                vacancyListItems.add(vacancyListItem);

                listAdapter = new VacanciesExpandableListAdapter(
                    context, vacancyListItems, vacancyExpandedListItem);

                expListView.setAdapter(listAdapter);
            }
        }
    }
}
