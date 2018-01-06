package com.capton.fc;

import com.google.gson.Gson;

/**
 * Created by capton on 2017/11/28.
 */

public class JsonUtil {
    public static String objToString(Object object){
        return new Gson().toJson(object);
    }
    public static Object strToObject(String str,Class cls){
        return new Gson().fromJson(str,cls);
    }
}