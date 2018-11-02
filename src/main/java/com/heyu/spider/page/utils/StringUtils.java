package com.heyu.spider.page.utils;

public class StringUtils {

    public static Boolean isEmpty(String str){
        if(str == null || "".equals(str)){
            return true;
        }
        return false;
    }
}
