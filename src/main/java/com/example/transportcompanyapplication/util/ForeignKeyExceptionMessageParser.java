package com.example.transportcompanyapplication.util;

import com.example.transportcompanyapplication.exceptions.ForeignKeyException;

public class ForeignKeyExceptionMessageParser {
    public static ForeignKeyException parse(String message){
        String detailsWord = "Подробности: ";
        String details = message.substring(message.lastIndexOf(detailsWord)+detailsWord.length());
        return new ForeignKeyException("Foreign key exception", details);
    }
}
