package com.tt.oa.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZTreeNode {
    private String name;
    private Boolean open;
    private List<Map<String, String>> properties;
    private List<ZTreeNode> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<Map<String, String>> getProperties() {
        return properties;
    }

    public void setProperties(List<Map<String, String>> properties) {
        this.properties = properties;
    }

    public List<ZTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ZTreeNode> children) {
        this.children = children;
    }

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public String toString() {
        stringBuilder.append("{");
        stringBuilder.append("name:'");
        stringBuilder.append(name);
        stringBuilder.append("',open:'");
        stringBuilder.append(open);
        stringBuilder.append("',children:[");
        if (properties != null) {
            int count = 0;
            for (Map<String, String> map : properties) {
                stringBuilder.append("{");
                int size = 0;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    stringBuilder.append(entry.getKey());
                    stringBuilder.append(":'");
                    stringBuilder.append(entry.getValue());
                    stringBuilder.append("'");
                    if (!(size == map.entrySet().size() - 1)) {
                        stringBuilder.append(",");
                        ++size;
                    }
                }
                stringBuilder.append("}");
                if (!(count == properties.size() - 1)) {
                    stringBuilder.append(",");
                    ++count;
                }
            }
        }
        if (children != null) {
            int size = 0;
            stringBuilder.append(",");
            for (ZTreeNode zTreeNode : children) {
                stringBuilder.append(zTreeNode.toString());
                if (!(size == children.size() - 1)) {
                    stringBuilder.append(",");
                    ++size;
                }
            }
        }
        stringBuilder.append("]");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setName("SYSTEM");
        zTreeNode.setOpen(true);
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("name", "sysContact");
        map.put("value", "support@ndsatcom.cn");
        list.add(map);
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "sysName");
        map1.put("value", "SkyWAN IDU 2X00/5000 - V5.60");
        list.add(map1);
        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "sysLocation");
        map2.put("value", "master");
        list.add(map2);
        zTreeNode.setProperties(list);

        List<ZTreeNode> list1 = new ArrayList<>();
        ZTreeNode treeNode = new ZTreeNode();
        treeNode.setName("RTRIPADDRESSENTRY");
        treeNode.setOpen(true);
        list1.add(treeNode);

        ZTreeNode treeNode1 = new ZTreeNode();
        treeNode1.setName("RTRIPADDRESSENTRY");
        treeNode1.setOpen(true);
        treeNode1.setProperties(list);

        list1.add(treeNode1);

        zTreeNode.setChildren(list1);

        System.out.println(zTreeNode);
    }
}
