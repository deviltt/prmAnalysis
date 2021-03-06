package com.tt.oa.util;

import com.tt.oa.entity.TreeNode;
import com.tt.oa.io.FileReaderTest;

import java.util.*;

public class BuildTree {
    public static List<TreeNode> buildTree(List<Map<String, List<String>>> list) {
        //什么时候不迭代返回呢，就遍历到最底层时候的逻辑
        if (list == null)
            return null;
        //当前深度的节点个数
        int count = 0;
        //构建一个n叉树的节点
        List<TreeNode> resultList = new ArrayList<>();
        for (Map<String, List<String>> map : list) {
            for (String key : map.keySet()) {
                //设置节点的key
                TreeNode subNode = new TreeNode(key);
                subNode.setCount(count++);
                //设置节点的Property
                subNode.setListValue(FileReaderTest.traverseProperty(key, map.get(key)).get(key));

                subNode.setNext(buildTree(FileReaderTest.traverseRoot(map.get(key))));

                resultList.add(subNode);
            }
        }
        return resultList;
    }

    /*
    遍历list，对于每一个TreeNode都先序遍历出来
    遍历显示出来，返回的值是一个node根节点，因此需要遍历显示出来
 */
    public static String preOrderTraverse(List<TreeNode> roots, int depth, StringBuilder stringBuilder) {
        if (roots == null)
            return "";
        for (TreeNode node : roots) {
            int tempDepth = depth;
            //设置当前节点的深度
            node.setDepth(depth + 1);
            stringBuilder.append("<div class=\"node\" count=\"" + node.getCount() + "\" depth=\"" + node.getDepth() + "\">");
            while (tempDepth != 0) {
                for (int i = 0; i < 4 * tempDepth; i++)
                    stringBuilder.append("&nbsp");
                --tempDepth;
            }
            stringBuilder.append(node.getKeyRoot());
            stringBuilder.append("</div>");
            if (node.getListValue() != null) {
                //遍历属性map
                for (String string : node.getListValue().keySet()) {
                    tempDepth = depth + 1;
                    stringBuilder.append("<div class=\"keyValue\">");
                    while (tempDepth != 0) {
                        for (int i = 0; i < 4 * tempDepth; i++)
                            stringBuilder.append("&nbsp");
                        --tempDepth;
                    }
                    stringBuilder.append(string);
                    stringBuilder.append(" : <input value=\"");
                    stringBuilder.append(node.getListValue().get(string));
                    stringBuilder.append("\"/></div>");
                }
            }
            preOrderTraverse(node.getNext(), depth + 1, stringBuilder);
        }
        return stringBuilder.toString();
    }

    /**
     * @param roots         存放TreeNode的集合
     * @param depth
     * @param stringBuilder 只需要遍历到自己的Property即可
     * @return
     */
    public static String preOrderTraverseWithSubmit(List<TreeNode> roots, int depth, StringBuilder stringBuilder) {
        if (roots == null)
            return "";
        for (int j = 0; j < roots.size(); j++) {
            TreeNode node = roots.get(j);
            int tempDepth = depth;
            //设置当前节点的深度
//            node.setDepth(depth + 1);
//            stringBuilder.append("<form action=\"update\" method=\"post\"/>");
            stringBuilder.append("<form id=\"form");
            stringBuilder.append(j);
            stringBuilder.append("\">");
            stringBuilder.append("<div><span class=\"node\" count=\"");
            stringBuilder.append(node.getCount());
            stringBuilder.append("\" depth=\"");
            stringBuilder.append(node.getDepth());
            stringBuilder.append("\">");
            while (tempDepth != 0) {
                for (int i = 0; i < 4 * tempDepth; i++)
                    stringBuilder.append("&nbsp");
                --tempDepth;
            }
            stringBuilder.append(node.getKeyRoot());
            stringBuilder.append("</span></div>");
            if (node.getListValue() != null) {
                //遍历属性map
                for (String string : node.getListValue().keySet()) {
                    tempDepth = depth + 1;
                    stringBuilder.append("<div class=\"keyValue\">");
                    stringBuilder.append("<span>");
                    while (tempDepth != 0) {
                        for (int i = 0; i < 4 * tempDepth; i++)
                            stringBuilder.append("&nbsp");
                        --tempDepth;
                    }
                    stringBuilder.append(string);
                    stringBuilder.append("&nbsp:&nbsp</span>");
                    stringBuilder.append("<input name=\"");
                    stringBuilder.append(string);
                    stringBuilder.append("\" value=\"");
                    stringBuilder.append(node.getListValue().get(string));
                    stringBuilder.append("\"/></div>");
                }
            }
            stringBuilder.append("<button type=\"button\" value=\"update\" onclick=\"clickFunction(");
            stringBuilder.append(j);
            stringBuilder.append(")\">");
            stringBuilder.append("修改</button>");
            stringBuilder.append("</form>");
//            preOrderTraverse(node.getNext(), depth + 1, stringBuilder);
        }
        return stringBuilder.toString();
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
