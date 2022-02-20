package com.example.transportcompanyapplication.util;

import com.example.transportcompanyapplication.exceptions.UniqueFieldException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniqueExceptionMessageParser {
    public static UniqueFieldException parse(String message){
        Pattern pattern = Pattern.compile("\"(.+?)\"");
        Matcher matcher = pattern.matcher(message);
        List<String> words = new ArrayList<>();
        while(matcher.find()){
             words.add(matcher.group(1));
        }
        return new UniqueFieldException(words.get(0), words.get(1));
    }
}
