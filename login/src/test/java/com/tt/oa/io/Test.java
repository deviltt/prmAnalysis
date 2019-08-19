package com.tt.oa.io;

import java.io.*;

import static com.tt.oa.io.FileReaderTest.getRoot;
import static com.tt.oa.io.FileReaderTest.getString;

public class Test {
    @org.junit.Test
    public void test1() throws IOException {
        File file = new File("D:\\prm\\login\\file\\hello1.txt");
        File destFile = new File("D:\\prm\\login\\file\\hello3.txt");
        File tempFile = File.createTempFile("hello", ".txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        RandomAccessFile randomAccessFile1 = new RandomAccessFile(tempFile, "rw");
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(destFile, "rw");

        //先写到临时文件，再从临时文件写到目标文件
        randomAccessFile.seek(445);
        byte[] bytes = new byte[1024];

        int len = 0;
        while ((len = randomAccessFile.read(bytes)) != -1) {
            randomAccessFile1.write(bytes, 0, len);
        }

        randomAccessFile1.seek(0);
        len = 0;
        while ((len = randomAccessFile1.read(bytes)) != -1) {
            randomAccessFile2.write(bytes, 0, len);
        }

        randomAccessFile.close();
        randomAccessFile1.close();
        randomAccessFile2.close();
        tempFile.deleteOnExit();
//        bufferedInputStream.close();
//        bufferedOutputStream.close();
    }
}
