package com.tt.oa.util;

import com.tt.oa.entity.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;

public class PartialModificationWithNIO {
    private static boolean isFind = false;
    private static TreeNode nodeFind;
    private static TreeNode resultNode;
    private static Map<String, String> targetMap;

    /**
     *
     * @param map
     * @param position
     * @param count
     * @param depth
     * @param key
     * @param roots
     * @throws IOException
     */
    public static void changeTxt(Map<String, String> map, int position, int count, int depth, String key, List<TreeNode> roots) throws IOException {
        //第一步首先要找到原来树中这个节点的位置
        resultNode = findThisKey(count, key, roots);
        targetMap = resultNode.getListValue();

        File sourceFile = new File("D:\\prm\\login\\file\\hello.txt");
        File tempFile = File.createTempFile("hello", ".txt");
        File tempResultFile = File.createTempFile("resultFile", ".txt");

        //使用NIO的内存映射功能快速修改文件信息
        RandomAccessFile randomAccessFile = new RandomAccessFile(sourceFile, "rw");
        RandomAccessFile tempRA = new RandomAccessFile(tempFile, "rw");
        RandomAccessFile resultRA = new RandomAccessFile(tempResultFile, "rw");

        StringBuilder newStringBuilder = new StringBuilder();
        StringBuilder originStringBuilder = new StringBuilder();

        //知道节点，拼接由root和property组成的字符串
        originStringBuilder = splicingRootAndProperty(originStringBuilder, depth, resultNode.getListValue(), resultNode);
        newStringBuilder = splicingRootAndProperty(newStringBuilder, depth, map, resultNode);

        int newPosition = position + originStringBuilder.toString().getBytes().length;

        int len = 0;
        byte[] bytes = new byte[1024];

        //把0~position-1位置的字节都保存到resultFile中去
        byte[] tempBytes = new byte[position];
        randomAccessFile.read(tempBytes);
        resultRA.write(tempBytes, 0, position);
//        //首先定位到后面不动的地方
        randomAccessFile.seek(newPosition);
        //将后面不动的内容存放到临时文件中去
        while ((len = randomAccessFile.read(bytes)) != -1) {
            tempRA.write(bytes, 0, len);
        }
//        //临时文件的内容指针归零，方便拷贝时从头开始
        tempRA.seek(0);

        //将要修改的内容追加
        resultRA.write(newStringBuilder.toString().getBytes());    //直接在后面追加

//        //把临时文件的内容复制过来
        while ((len = tempRA.read(bytes)) != -1) {
            resultRA.write(bytes, 0, len);
        }
        tempRA.close();
        randomAccessFile.close();
        if (sourceFile.exists()) {
            sourceFile.delete();
        } else {
            System.out.println("delete fail");
        }
        File endFile = new File("D:\\prm\\login\\file\\hello.txt");
        RandomAccessFile randomAccessFile1 = new RandomAccessFile(endFile, "rw");
        resultRA.seek(0);
        while ((len = resultRA.read(bytes)) != -1) {
            randomAccessFile1.write(bytes, 0, len);
        }
        randomAccessFile1.close();
        resultRA.close();
        tempResultFile.delete();
        tempFile.delete();
    }

    private static StringBuilder splicingRootAndProperty(StringBuilder stringBuilder, int depth, Map<String, String> map, TreeNode treeNode) {
        for (int i = 0; i < 3 * depth; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("[ ");
        stringBuilder.append(resultNode.getKeyRoot());
        stringBuilder.append(" ]\r\n");
        if (map != null && map.entrySet() != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                for (int i = 0; i < 3 * (depth + 1); i++) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(entry.getKey());
                stringBuilder.append(" = \"");
                stringBuilder.append(entry.getValue());
                stringBuilder.append("\"\r\n");
            }
        }
        for (int i = 0; i < 3 * depth; i++) {
            stringBuilder.append(" ");
        }
        //如果该节点没有子节点了，则添加end尾键标签
        if (treeNode != null && treeNode.getNext() == null) {
            stringBuilder.append("[end ");
            stringBuilder.append(resultNode.getKeyRoot());
            stringBuilder.append(" ]\r\n");
        }
        return stringBuilder;
    }

    private static TreeNode findThisKey(int count, String key, List<TreeNode> roots) {
        if (roots == null) {
            return null;
        }
        for (TreeNode node : roots) {
            if ((!node.getKeyRoot().equals(key)) || (count != node.getCount() && node.getKeyRoot().equals(key))) {
                findThisKey(count, key, node.getNext());
            } else {
                isFind = true;
                nodeFind = node;
            }
            if (isFind) {
                break;
            }
        }
        return nodeFind;
    }
}
