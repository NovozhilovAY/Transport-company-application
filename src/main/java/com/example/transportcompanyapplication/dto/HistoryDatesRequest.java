package com.example.transportcompanyapplication.dto;

import java.sql.Date;
import java.util.List;

public class HistoryDatesRequest {
    private Long id;
    private List<Date> dates;

    public HistoryDatesRequest() {
    }

    public HistoryDatesRequest(Long id, List<Date> dates) {
        this.id = id;
        this.dates = dates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

}
