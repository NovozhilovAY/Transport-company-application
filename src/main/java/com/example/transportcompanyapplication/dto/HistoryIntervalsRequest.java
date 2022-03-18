package com.example.transportcompanyapplication.dto;

import java.util.List;

public class HistoryIntervalsRequest {
    private Long id;
    private List<DateInterval> dateIntervals;

    public HistoryIntervalsRequest(Long id, List<DateInterval> dateIntervals) {
        this.id = id;
        this.dateIntervals = dateIntervals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DateInterval> getDateIntervals() {
        return dateIntervals;
    }

    public void setDateIntervals(List<DateInterval> dateIntervals) {
        this.dateIntervals = dateIntervals;
    }
}
