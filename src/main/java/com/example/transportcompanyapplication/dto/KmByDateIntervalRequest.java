package com.example.transportcompanyapplication.dto;

import javax.validation.constraints.Past;
import java.sql.Date;

public class KmByDateIntervalRequest extends KmByDateRequest{

    private Date date2;

    public KmByDateIntervalRequest(Long carId, Date date, Date date2) {
        super(carId, date);
        this.date2 = date2;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }
}
