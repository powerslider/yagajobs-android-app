package com.ceco.android.yagajobs.app.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ceco.android.yagajobs.app.R;
import com.ceco.android.yagajobs.app.adapter.model.VacancyExpandedListItem;
import com.ceco.android.yagajobs.app.adapter.model.VacancyListItem;

import java.util.AbstractMap;
import java.util.List;

/**
 * Created by ceco on 25.05.14.
 */
public class VacanciesExpandableListAdapter extends BaseExpandableListAdapter {

    public static final int CHILDREN_COUNT = 1;
    private Context context;

    private List<VacancyListItem> vacancyListItems;

    private AbstractMap.SimpleEntry<VacancyListItem, VacancyExpandedListItem> vacancyChildListItems;

    public VacanciesExpandableListAdapter(Context context, List<VacancyListItem> vacancyListItems,
                                          AbstractMap.SimpleEntry<VacancyListItem, VacancyExpandedListItem> vacancyChildListItems) {
        this.context = context;
        this.vacancyListItems = vacancyListItems;
        this.vacancyChildListItems = vacancyChildListItems;
    }

    @Override
    public int getGroupCount() {
        return this.vacancyListItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return CHILDREN_COUNT;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.vacancyListItems
            .get(groupPosition);
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.vacancyChildListItems.getValue();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        VacancyListItemHolder groupHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.exp_list_group, null);
            groupHolder = VacancyListItemHolder.newInstance();
            groupHolder.vacancyTitleView = (TextView) convertView.findViewById(R.id.vacancy_title);
            groupHolder.byView = (TextView) convertView.findViewById(R.id.advertiser_by);
            groupHolder.advertiserView = (TextView) convertView.findViewById(R.id.vacancy_advertiser);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (VacancyListItemHolder) convertView.getTag();
        }

        VacancyListItem vacancyListItem = (VacancyListItem) getGroup(groupPosition);
        groupHolder.vacancyTitleView.setText(vacancyListItem.getTitle());
        groupHolder.advertiserView.setText(vacancyListItem.getAdvertiserName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        VacancyExpandedListItemHolder childHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.exp_list_item, null);
            childHolder = VacancyExpandedListItemHolder.newInstance();
            childHolder.foundAtView = (TextView) convertView.findViewById(R.id.jobboard_field);
            childHolder.salaryFieldView = (TextView) convertView.findViewById(R.id.salary_field);
            childHolder.locationFieldView = (TextView) convertView.findViewById(R.id.location_field);
            childHolder.sourceJobBoardView = (TextView) convertView.findViewById(R.id.jobboard_value);
          //  childHolder.timeStampView = (TextView) convertView.findViewById(R.id.timestamp);
            childHolder.salaryValueView = (TextView) convertView.findViewById(R.id.salary_value);
            childHolder.locationValueView = (TextView) convertView.findViewById(R.id.location_value);
            convertView.setTag(childHolder);
        } else {
            childHolder = (VacancyExpandedListItemHolder) convertView.getTag();
        }

        VacancyExpandedListItem vacancyExpandedListItem = (VacancyExpandedListItem) getChild(groupPosition, childPosition);
        childHolder.sourceJobBoardView.setText(vacancyExpandedListItem.getSourceJobBoard());
    //    childHolder.timeStampView.setText(vacancyExpandedListItem.getTimeStamp());
        childHolder.salaryValueView.setText(vacancyExpandedListItem.getSalary());
        childHolder.locationValueView.setText(vacancyExpandedListItem.getLocation());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    protected static class VacancyListItemHolder {

        TextView vacancyTitleView;
        TextView byView;
        TextView advertiserView;

        public static VacancyListItemHolder newInstance() {
            return new VacancyListItemHolder();
        }
    }

    protected static class VacancyExpandedListItemHolder {

        TextView foundAtView;
        TextView salaryFieldView;
        TextView locationFieldView;
        TextView sourceJobBoardView;
        TextView timeStampView;
        TextView salaryValueView;
        TextView locationValueView;

        public static VacancyExpandedListItemHolder newInstance() {
            return new VacancyExpandedListItemHolder();
        }
    }
}
