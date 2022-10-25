package com.example.transportcompanyapplication.correcting.utils;


import com.example.transportcompanyapplication.correcting.exceptions.WrongCorrectingDataException;
import com.example.transportcompanyapplication.correcting.model.CategoryOfExploitationType;

public class CoeffK1 {
    private CoeffK1(){}
    public static double getCoeffK1TO(CategoryOfExploitationType categoryOfExploitationType){
        switch (categoryOfExploitationType){
            case I:
                return 1.0;
            case II:
                return 0.9;
            case III:
                return 0.8;
            case IV:
                return 0.7;
            case V:
                return 0.6;
            default:
                throw new WrongCorrectingDataException("Не удалось получить коэффициент К1 ТО с КУЭ " + categoryOfExploitationType + "!!!");
        }
    }

    public static double getCoeffK1KR(CategoryOfExploitationType categoryOfExploitationType){
        switch (categoryOfExploitationType){
            case I:
                return 1.0;
            case II:
                return 0.9;
            case III:
                return 0.8;
            case IV:
                return 0.7;
            case V:
                return 0.6;
            default:
                throw new WrongCorrectingDataException("Не удалось получить коэффициент К1 КР с КУЭ " + categoryOfExploitationType + "!!!");
        }
    }
}
