package com.tt.oa.util;


import com.tt.oa.entity.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 功能：遍历n叉树的同时计数当前节点的起始字节位置，
 * 方便RandomAccessFile的seek函数进行快速的定位，
 * 从而对配置文件进行修改
 */
public class TraversAndCount {
    public int position = 23;
    private final int STARTTAG = 6; //开始标签[ MIB ]  \n\t
    private final int ENDTAG = STARTTAG + 3; //结束标签 [end MIB] \n\t
    private final int PROPERTY = 7; //属性的常量  = " " 一共是5个字符 再加上 \n\t
    private boolean isFind = false;

    /**
     * @param count 第几个key，因为会有名称相同的key
     * @param key   要修改的属性key
     * 需要修改属性的位置
     */
    public void countPosition(int count, String key, List<TreeNode> roots) {
        if (roots == null) {
            return;
        }

        for (int i = 0; i < roots.size(); i++) {
            TreeNode node = roots.get(i);
            //如果不是要查找的key，或者是找的key但是位置不同，即count不同
            if ((!node.getKeyRoot().toLowerCase().equals(key)) || (node.getKeyRoot().toLowerCase().equals(key) && count != node.getCount())) {
                //计算key的字节长度
                position += (node.getDepth() - 1) * 3 + node.getKeyRoot().length() + STARTTAG;
                //遍历key对应的property，有的key是没有properties的
                Map<String, String> map = node.getListValue();
                if (map!=null){
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        position += node.getDepth() * 3 + PROPERTY + entry.getKey().length() + entry.getValue().length();
                    }
                }
                //递归遍历该节点的next属性子节点
                countPosition(count, key, node.getNext());
            } else {
                isFind = true;
                return;
            }
            //加上结束标签的字节长度
            if (!isFind) {
                position += ENDTAG + (node.getDepth() - 1) * 3 + node.getKeyRoot().length();
            } else {
                return;
            }
        }
    }
}
