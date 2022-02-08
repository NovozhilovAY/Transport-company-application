package com.example.transportcompanyapplication.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniqueExceptionMessageParser {
    public static String parse(String message){
        Pattern pattern = Pattern.compile("\\((.+?)\\)");
        Matcher matcher = pattern.matcher(message);
        List<String> words = new ArrayList<>();
        while(matcher.find()){
             words.add(matcher.group(1));
        }
        if(words.size() != 2){
            return "";
        }
        return "Field '" + words.get(0) + "' = '" + words.get(1) + "' must be unique!";
    }
}
