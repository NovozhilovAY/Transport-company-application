package com.example.transportcompanyapplication.dto;


public class NextMaintDates {

    private String nextTo1Date;
    private String nextTo2Date;
    private String nextKrDate;

    public NextMaintDates() {
    }

    public NextMaintDates(String nextTo1Date, String nextTo2Date, String nextKrDate) {
        this.nextTo1Date = nextTo1Date;
        this.nextTo2Date = nextTo2Date;
        this.nextKrDate = nextKrDate;
    }

    public String getNextTo1Date() {
        return nextTo1Date;
    }

    public void setNextTo1Date(String nextTo1Date) {
        this.nextTo1Date = nextTo1Date;
    }

    public String getNextTo2Date() {
        return nextTo2Date;
    }

    public void setNextTo2Date(String nextTo2Date) {
        this.nextTo2Date = nextTo2Date;
    }

    public String getNextKrDate() {
        return nextKrDate;
    }

    public void setNextKrDate(String nextKrDate) {
        this.nextKrDate = nextKrDate;
    }
}
