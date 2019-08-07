package com.tt.oa.util;

import com.tt.oa.entity.TreeNode;
import com.tt.oa.entity.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildTrie {
    /**
     * @param root n叉树的根节点
     * @param trie trie树的根节点
     */
    public static void buildTrie(TreeNode root, Trie trie) {
        build(root.getNext(), trie);
    }

    public static void searchTrie(String word, Trie trie, Map<String, String> map) {
        search(word, trie, map);
    }

    /**
     * @param word 要查找的单词
     * @param trie trie树的根节点
     * @param map
     */
    private static void search(String word, Trie trie, Map<String, String> map) {
        List<String> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //如果trie树中不存在这个字符串，则直接返回
            if (!trie.getNext().containsKey(c)) {
//                map.put(word, "");
                return;
            } else { //有匹配字符的情况，先把匹配的拼接起来
                stringBuilder.append(c);
                trie = trie.getNext().get(c); //更新trie的位置
            }
        }
        //把剩余的可能匹配的字符串都找到保存起来
        searchAll(trie, list, new StringBuilder());

        for (String string : list) {
            StringBuilder temp = new StringBuilder(stringBuilder.toString());
            temp.append(string);
            map.put(temp.toString(), temp.toString());
        }
    }

    /**
     * 递归
     *
     * @param trie
     * @param list
     */
    private static void searchAll(Trie trie, List<String> list, StringBuilder stringBuilder) {
        //如果没有该节点后面没有单词了，则直接返回
        if (trie.getNext().keySet().size() == 0)
            return;
        for (Character character : trie.getNext().keySet()) {
            StringBuilder temp=new StringBuilder(stringBuilder);
            temp.append(character);
            if (trie.getNext().get(character).isWord()) {
//                list.add(stringBuilder.reverse().toString());
                list.add(temp.toString());
                stringBuilder = new StringBuilder();
            }
            searchAll(trie.getNext().get(character), list, new StringBuilder(temp));
        }
    }

    /**
     * 先序遍历n叉树，把node的值以及node对应的属性值都保存到trie树中去
     *
     * @param roots
     * @param node
     */
    private static void build(List<TreeNode> roots, Trie node) {
        if (roots == null)
            return;
        //遍历n叉树的根节点
        for (TreeNode treeNode : roots) {
            //把根节点的词放入到trie树中
            insert(treeNode.getKeyRoot().toLowerCase(), node);
            //root对应的property不为空
            if (treeNode.getListValue() != null) {
                //把该node的所有属性key值都添加进trie树
                for (String string : treeNode.getListValue().keySet())
                    insert(string.toLowerCase(), node);
            }
            build(treeNode.getNext(), node);
        }
    }

    private static void insert(String word, Trie trie) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!trie.getNext().containsKey(c))
                trie.getNext().put(c, new Trie());
            trie = trie.getNext().get(c);
        }
        trie.setWord(true);
    }
}
