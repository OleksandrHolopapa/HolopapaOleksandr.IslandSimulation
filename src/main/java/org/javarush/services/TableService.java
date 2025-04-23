package org.javarush.services;

import java.util.List;

public class TableService {
    public static String[] readRowElement(List<String> strings, String beginning){
        String[] initParameters = null;
        for (String string : strings) {
            if(string.startsWith(beginning)) initParameters = string.split("\\|");
        }
        return initParameters;
    }
}
