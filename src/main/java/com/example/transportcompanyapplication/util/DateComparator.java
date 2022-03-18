package com.example.transportcompanyapplication.util;

import com.example.transportcompanyapplication.dto.DateInterval;

import java.sql.Date;

public class DateComparator {
    private DateComparator(){}
    public static boolean equal(Date date1, Date date2){
        return date1.toString().equals(date2.toString());
    }
    public static boolean intervalIncludesDate(DateInterval interval, Date date){
        return interval.getFrom().getTime() <= date.getTime() && interval.getTo().getTime() >= date.getTime();
    }
}
