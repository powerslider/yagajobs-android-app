package com.ceco.android.yagajobs.app.adapter.model;

/**
 * Created by ceco on 25.05.14.
 */
public class VacancyExpandedListItem {

    private String sourceJobBoard;
    private String timeStamp;
    private String salary;
    private String location;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSourceJobBoard() {
        return sourceJobBoard;
    }

    public void setSourceJobBoard(String sourceJobBoard) {
        this.sourceJobBoard = sourceJobBoard;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
