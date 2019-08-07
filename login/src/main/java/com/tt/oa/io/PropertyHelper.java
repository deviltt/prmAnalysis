package com.tt.oa.io;

import java.util.Map;

public class PropertyHelper {
//    private static Map<String, String> map = new LinkedHashMap<>();
    public static void propertyHelp(Map<String, String> map, String string) {
        help(map, string);
    }

    //将      sysContact = "support@ndsatcom.com" 这种形式的字符串分割
    private static void help(Map<String, String> map, String string) {
        String[] strings = string.split("=");
        for (int i = 0; i < strings.length; i++) {
            if (i == 0){
                strings[i] = strings[i].trim();
            }
            else {
                int start = strings[i].indexOf("\"");
                int end = strings[i].lastIndexOf("\"");
                strings[i] = strings[i].substring(start + 1, end);
            }
        }
        String key=strings[0];
        String value=strings[1];
        map.put(key, value);
    }
}
