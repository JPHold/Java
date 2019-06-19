package com.jdk7.asyncfile;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Budd
 * @desc 异步读取文本
 * @since 2019/6/19 9:41
 */
public class AsynchronousFileChannelHello {

    @Test
    public void testRead() {
        Path path = Paths.get(".\\src\\com\\jdk7\\asyncfile\\file\\潮剧介绍.txt");

        try {
            AsynchronousFileChannel fileChannel =
                    AsynchronousFileChannel.open(path, StandardOpenOption.READ);

            long fileSize = fileChannel.size();
            System.out.println("文件大小:" + fileSize);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int position = 0, count = 0;

            StringBuilder fileDataStringBuilder = new StringBuilder();

            //循环
            while (position < fileSize) {
                count = read(fileChannel, buffer, position, fileDataStringBuilder);
                position += count;
            }
            System.out.println("文件内容:" + fileDataStringBuilder);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int read(AsynchronousFileChannel fileChannel, ByteBuffer buffer, int position, StringBuilder fileDataStringBuilder) {

        Future<Integer> operation = fileChannel.read(buffer, position);

        while (!operation.isDone()) {
            //不粗暴抢占CPU。
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int readCount = 0;
        try {
            readCount = operation.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        try {
            fileDataStringBuilder.append(new String(data, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        buffer.clear();
        return readCount;
    }

}
