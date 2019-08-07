package com.tt.oa.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogbackDemo {
    private static Logger logger=LoggerFactory.getLogger(LogbackDemo.class);

    public static void main(String[] args) {
        logger.trace("==========trace");
        logger.debug("==========debug");
        logger.info("==========info");
        logger.warn("==========warn");
        logger.error("=========error");

//        String name="tt";
//        Map<String, String> map=new HashMap<>();
//        List<Integer> map=new ArrayList<>();
//        map.add(3);
//        map.put("name", "tt");
//        logger.info("hello,{}!",map);
    }
}
