package com.jobplus.utils;

import java.util.ResourceBundle;

/**
 * Created by eric on 2016/9/20.
 */
public class ParsProperFile {
    private static ResourceBundle configProcBud = ResourceBundle.getBundle("config");
    private static ResourceBundle qqConfigProcBud = ResourceBundle.getBundle("qqconnectconfig");

    public static String getString(String key){
        return configProcBud.getString(key);
    }

    public static Integer getInt(String key){
        return Integer.parseInt(configProcBud.getString(key));
    }

    public static String getQQConfigString(String key){
        return qqConfigProcBud.getString(key);
    }
}
