package com.example.transportcompanyapplication.correcting.utils;

import com.example.transportcompanyapplication.correcting.exceptions.WrongCorrectingDataException;
import com.example.transportcompanyapplication.correcting.model.ClimateType;

public class CoeffK3 {
    private CoeffK3(){}

    public static double getCoeffK3TO(ClimateType climateType){
        switch (climateType){
            case TEMPERATE:
                return 1.0;
            case WARM_TEMPERATE:
                return 1.0;
            case HOT_DRY:
                return 0.9;
            case MODERATELY_COLD:
                return 0.9;
            case COLD:
                return 0.8;
            case VERY_COLD:
                return 0.7;
            default:
                throw new WrongCorrectingDataException("Не удалось получить коэффициент К3 ТО с типом климата " + climateType + "!!!");
        }
    }

    public static double getCoeffK3KR(ClimateType climateType) {
        switch (climateType){
            case TEMPERATE:
                return 1.0;
            case WARM_TEMPERATE:
                return 1.0;
            case HOT_DRY:
                return 0.9;
            case MODERATELY_COLD:
                return 0.9;
            case COLD:
                return 0.9;
            case VERY_COLD:
                return 0.8;
            default:
                throw new WrongCorrectingDataException("Не удалось получить коэффициент К3 КР с типом климата " + climateType + "!!!");
        }
    }
}
