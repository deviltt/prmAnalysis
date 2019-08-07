package com.tt.oa.io;

public class StringIntercept {
    public static String interceptString(String string){
        //截取字符串
        int start = string.indexOf("[")+2;
        int end = string.lastIndexOf(" ");
        return string.substring(start, end);
    }
}
