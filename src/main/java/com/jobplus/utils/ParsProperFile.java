package com.jobplus.utils;

import java.util.ResourceBundle;

/**
 * Created by eric on 2016/9/20.
 */
public class ParsProperFile {
    private static ResourceBundle configProcBud = ResourceBundle.getBundle("config");

    public static String getString(String key){
        return configProcBud.getString(key);
    }
}
