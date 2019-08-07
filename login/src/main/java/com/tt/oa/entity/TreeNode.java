package com.tt.oa.entity;

import java.util.List;
import java.util.Map;

public class TreeNode {
    //表示该节点的值
    private String keyRoot;
    //表示该节点的所有属性值
    private Map<String, String> listValue;
    //因为一个root下面可能对应多个子root
    private List<TreeNode> next;
    //用来后面输出到前端时打印空格时使用
    private int depth;
    //是第几个节点
    private int count;

    public TreeNode() {
    }

    public TreeNode(String keyRoot) {
        this.keyRoot = keyRoot;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "keyRoot='" + keyRoot + '\'' +
                ", listValue=" + listValue +
                ", next=" + next +
                ", depth=" + depth +
                '}';
    }

    public String getKeyRoot() {
        return keyRoot;
    }

    public void setKeyRoot(String keyRoot) {
        this.keyRoot = keyRoot;
    }

    public Map<String, String> getListValue() {
        return listValue;
    }

    public void setListValue(Map<String, String> listValue) {
        this.listValue = listValue;
    }

    public List<TreeNode> getNext() {
        return next;
    }

    public void setNext(List<TreeNode> next) {
        this.next = next;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
