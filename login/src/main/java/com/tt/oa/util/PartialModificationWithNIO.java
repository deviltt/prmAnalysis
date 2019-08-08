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
    private static Map<String, String> targetMap;

    /**
     * @param map
     * @param position
     * @param count
     * @param depth
     * @throws IOException
     */
    public static void changeTxt(Map<String, String> map, int position, int count, int depth, String key, List<TreeNode> roots) throws IOException {
        //第一步首先要找到原来树中这个节点的位置
        TreeNode resultNode = findThisKey(count, key, roots);
        targetMap = resultNode.getListValue();

        //使用NIO的内存映射功能快速修改文件信息
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\prmAnalysis\\login\\file\\hello.txt", "rw");

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        //知道节点，拼接由root和property组成的字符串
        stringBuilder1 = splicingRootAndProperty(stringBuilder1, depth, roots);

        for (int i = 0; i < 3 * depth; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("[ ");
        stringBuilder.append(key);
        stringBuilder.append(" ]\r\n");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            for (int i = 0; i < 3 * (depth + 1); i++) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(entry.getKey());
            stringBuilder.append(" = \"");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\"\r\n");
        }
        for (int i = 0; i < 3 * depth; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("[end ");
        stringBuilder.append(key);
        stringBuilder.append(" ]\r\n");

        System.out.print(stringBuilder.toString());

        int newPosition = position + stringBuilder1.toString().getBytes().length;

        randomAccessFile.seek(newPosition);

        //从newPosition开始的后面所有字符串都复制到临时文件夹中
        File temp = File.createTempFile("temp", ".txt");
        FileOutputStream outputStream = new FileOutputStream(temp);
        FileInputStream inputStream = new FileInputStream(temp);
        String string = "";
        while ((string = randomAccessFile.readLine()) != null) {
            outputStream.write(string.getBytes());
        }
        //使得文件指针回到需要修改的地方
        randomAccessFile.seek(position);
        randomAccessFile.writeChars(stringBuilder.toString());

        int available = inputStream.available();
        byte[] bytes = new byte[available];
        inputStream.read(bytes, 0, available);
        randomAccessFile.write(bytes);

        randomAccessFile.close();
        inputStream.close();
        outputStream.close();
    }

    private static StringBuilder splicingRootAndProperty(StringBuilder stringBuilder, int depth, List<TreeNode> roots) {
        for (int i = 0; i < 3 * depth; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("[ ");
        stringBuilder.append(nodeFind.getKeyRoot());
        stringBuilder.append(" ]\r\n");
        for (Map.Entry<String, String> entry : targetMap.entrySet()) {
            for (int i = 0; i < 3 * (depth + 1); i++) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(entry.getKey());
            stringBuilder.append(" = \"");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\"\r\n");
        }
        for (int i = 0; i < 3 * depth; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("[end ");
        stringBuilder.append(nodeFind.getKeyRoot());
        stringBuilder.append(" ]\r\n");
        return stringBuilder;
    }

    private static TreeNode findThisKey(int count, String key, List<TreeNode> roots) {
        if (roots == null) {
            return null;
        }
        for (TreeNode node : roots) {
            if ((!node.getKeyRoot().toLowerCase().equals(key)) || (count != node.getCount() && node.getKeyRoot().toLowerCase().equals(key))) {
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
