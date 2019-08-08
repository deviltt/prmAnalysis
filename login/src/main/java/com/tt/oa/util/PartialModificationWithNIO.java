package com.tt.oa.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class PartialModificationWithNIO {
    /**
     * @param map
     * @param position
     * @param count
     * @param depth
     * @throws IOException
     */
    public static void changeTxt(Map<String, String> map, int position, int count, int depth, String key) throws IOException {
        //使用NIO的内存映射功能快速修改文件信息
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\prm\\login\\file\\hello1.txt", "rw");
//        FileOutputStream fileOutputStream = new FileOutputStream("D:\\prm\\login\\file\\hello2.txt");

//        randomAccessFile.seek(3);
//        System.out.println(randomAccessFile.readLine());
//        randomAccessFile.write("tt".getBytes());

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 3 * depth; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("[ ");
        stringBuilder.append(key);
        stringBuilder.append(" ]\n");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            for (int i = 0; i < 3 * (depth + 1); i++) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(entry.getKey());
            stringBuilder.append(" = \"");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\"\n");
        }
        for (int i = 0; i < 3 * depth; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("[end ");
        stringBuilder.append(key);
        stringBuilder.append(" ]\n");

        System.out.print(stringBuilder.toString());

        int newPosition = position + stringBuilder.toString().getBytes().length;

        randomAccessFile.seek(newPosition);

        //从newPosition开始的后面所有字符串都复制到临时文件夹中
        File temp = File.createTempFile("temp", ".txt");
        FileOutputStream outputStream = new FileOutputStream(temp);

        System.out.println(randomAccessFile.readLine());

        randomAccessFile.close();

//        randomAccessFile.write("tt".getBytes());


        //获取文件长度
//        long totalLength = randomAccessFile.length();
//        System.out.println("totalLength " + totalLength);
//        //获取通道
//        FileChannel fileChannel = randomAccessFile.getChannel();
//        //映射缓冲区
//        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 2);
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//        int bytes = fileChannel.read(buffer);
//        int count = 0;
//        while (bytes != -1) {
//            buffer.flip();  //由写入模式变为读出模式
//            while (buffer.hasRemaining()) {
//                byte b = buffer.get();
//                if (count >= start && count <= end) {
//                    fileOutputStream.write(b + 32);
//                    count++;
//                } else {
//                    fileOutputStream.write(b);
//                }
//
//                if (b==0x0a){
//                    System.out.println("hello");
//                }
//                System.out.println((char) buffer.get());
//            }
//            buffer.clear();
//            bytes = fileChannel.read(buffer);
//        }
//        System.out.println("count " + count);
//        for (int i = 0; i < 2; i++) {
//            byte src = mappedByteBuffer.get(i);
//            mappedByteBuffer.putChar('t');
//            mappedByteBuffer.put(i, (byte) (src - 31));
//            System.out.println(src);
//        }
//        //关闭
//        mappedByteBuffer.clear();
//        fileChannel.close();
//        randomAccessFile.close();
    }
}
