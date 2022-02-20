package com.example.transportcompanyapplication.util;

import java.sql.Date;

public class DateComparator {
    private DateComparator(){}
    public static boolean equal(Date date1, Date date2){
        return date1.toString().equals(date2.toString());
    }
}
