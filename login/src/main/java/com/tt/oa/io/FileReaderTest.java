package com.tt.oa.io;

import com.tt.oa.entity.ZTreeNode;

import java.io.*;
import java.util.*;

public class FileReaderTest {
    private static BufferedReader reader;
    private static List<String> root = new ArrayList<>();

    public static List<String> getRoot() throws IOException {
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\tt\\hello.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String string;
        while ((string = reader.readLine()) != null) {
            //处理带有[ ]标题的逻辑
            if (string.contains("[") && !string.contains("end")) {
                if (string.indexOf("[") == 0) {
                    root.add(StringIntercept.interceptString(string));
                }
            }
        }
        return root;
    }

    public static List<String> getString(InputStream fileStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(fileStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String string;
        while ((string = reader.readLine()) != null) {
            stringBuilder.append(string + "\n");
        }
        return Arrays.asList(stringBuilder.toString().split("\n"));
    }

    public static List<String> getZTreeString(InputStream fileStream, ZTreeNode root) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(fileStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String string;
        while ((string = reader.readLine()) != null) {
            if (string.contains("VERSION")){
                String[] strings=string.split("=");
                for (int i = 0; i < strings.length; i++) {
                    if (i==strings.length-1){
                        root.setName(strings[i].trim());
                    }
                }
            }
            stringBuilder.append(string + "\n");
        }
        return Arrays.asList(stringBuilder.toString().split("\n"));
    }

    /**
     * 将数据结构变为 Map<String, List<Map<String, List<String>>>>
     * @param strings
     * @return
     */
    public static List<Map<String, List<String>>> traverseRoot(List<String> strings) {
        int start = 0, end = 0;
        String currentRoot = null;
        List<String> list = null;
        Map<String, List<String>> map;
        List<Map<String, List<String>>> result = new ArrayList<>();
        //判断是否有子root
        boolean hasSubRoot=false;
        //遍历List中的每一个String
        for (int j = 0; j < strings.size(); j++) {
            list=new ArrayList<>();
            int k=j;
            for (; k < strings.size(); k++) {
                //这里还有一种可能性就是，该root下并没有子root的情况？？？？？
                if (strings.get(k).contains("[")) {
                    hasSubRoot=true;
                    currentRoot = StringIntercept.interceptString(strings.get(k));
                    j=k+1;
                    break;
                }
            }
            if (!hasSubRoot)
                j=k;
            if(hasSubRoot){
                for (int i = j; i < strings.size(); i++) {
                    String temp=strings.get(i);
                    list.add(temp);
                    int s=temp.indexOf("[");
                    int e=temp.lastIndexOf("]");
                    if ((temp.contains("end")) && (temp.contains(currentRoot))&& ((e-s+1)==currentRoot.length()+7)) {
                        end = i;
                        break;
                    }
                }
                list.remove(list.size()-1);
                map=new LinkedHashMap<>();
                map.put(currentRoot, list);
                result.add(map);
                j=end;
            }
        }
        if (!hasSubRoot)
            return null;
        else
            return result;
    }

    public static Map<String, Map<String, String>> traverseProperty(String string, List<String> strings){
        boolean flag=false; //用来标记是否是子root标签里面的内容
        Map<String, Map<String, String>> map=new LinkedHashMap<>();
        Map<String, String> temp=new LinkedHashMap<>();
        for (int i = 0; i < strings.size(); i++) {
            //如果某一行里面没有"["，说明该子节点是有自己的属性行的
            if (!strings.get(i).contains("[")){
                flag=true;
                PropertyHelper.propertyHelp(temp, strings.get(i));
            }else {
                break;
            }
        }
        if (flag)
            map.put(string, temp);
        else
            map.put(string, null);

        //该root没有属性节点的情况，返回null
        return map;
    }

    public static Map<String, List<Map<String, String>>> traverseZTreeProperty(String string, List<String> strings){
        boolean flag=false; //用来标记是否是子root标签里面的内容
        Map<String, List<Map<String, String>>> map=new LinkedHashMap<>();
        List<Map<String, String>> list=new LinkedList<>();
//        Map<String, String> temp=new LinkedHashMap<>();
        for (int i = 0; i < strings.size(); i++) {
            //如果某一行里面没有"["，说明该子节点是有自己的属性行的
            if (!strings.get(i).contains("[")){
                flag=true;
                PropertyHelper.ZTreePropertyHelp(list, strings.get(i));
            }else {
                break;
            }
        }
        if (flag)
            map.put(string, list);
        else
            map.put(string, null);

        //该root没有属性节点的情况，返回null
        return map;
    }
}
