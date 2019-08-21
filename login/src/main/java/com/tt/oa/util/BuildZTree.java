package com.tt.oa.util;

import com.tt.oa.entity.TreeNode;
import com.tt.oa.entity.ZTreeNode;
import com.tt.oa.io.FileReaderTest;

import java.util.*;

public class BuildZTree {
    public static List<ZTreeNode> buildZTree(List<Map<String, List<String>>> list, int depth) {
        //什么时候不迭代返回呢，就遍历到最底层时候的逻辑
        if (list == null)
            return null;
        //当前深度的节点个数
        int count = 0;
        //构建一个n叉树的节点
        List<ZTreeNode> resultList = new ArrayList<>();
        for (Map<String, List<String>> map : list) {
            for (String key : map.keySet()) {
                //设置节点的key
                ZTreeNode subNode = new ZTreeNode(key);
                subNode.setDepth(depth);
                subNode.setCount(count++);
                subNode.setOpen(true);
                //设置节点的Property
                subNode.setProperties(FileReaderTest.traverseZTreeProperty(key, map.get(key)).get(key));

                subNode.setChildren(buildZTree(FileReaderTest.traverseRoot(map.get(key)), depth + 1));

                resultList.add(subNode);
            }
        }
        return resultList;
    }

    /**
     * 层序遍历进行查找，把所有符合条件的结果都保存起来
     *
     * @param root n叉树的根节点
     * @param key  Node
     * @param list 用来存放符合条件的结果
     * @return
     */
    public static List<TreeNode> doSearch(TreeNode root, String key, List<TreeNode> list) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean canReturn = false;
        while (!queue.isEmpty()) {
            TreeNode temp = ((LinkedList<TreeNode>) queue).pop();
            List<TreeNode> tempList = temp.getNext();
            if (tempList != null) {
                for (TreeNode treeNode : tempList) {
                    //如果root的值等于key，需要观察这个root是否有属性property
                    if (key.equals(treeNode.getKeyRoot().toLowerCase()) && treeNode.getListValue() != null) {
                        list.add(treeNode);
                        canReturn = true;
                    }
                    if (treeNode.getListValue() != null) {
                        for (String string : treeNode.getListValue().keySet()) {
                            if (string.toLowerCase().equals(key))
                                list.add(treeNode);
                        }
                    }
                    queue.add(treeNode);
                }
            }
            if (canReturn)
                break;
        }
        return list;
    }
}
