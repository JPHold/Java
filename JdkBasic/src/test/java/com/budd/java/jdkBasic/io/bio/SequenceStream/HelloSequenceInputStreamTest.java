package com.budd.java.jdkBasic.io.bio.SequenceStream;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Vector;

/**
 * @author budd
 * @desc 顺序合并流入门研究
 * @since 2020/3/5 20:54
 **/
public class HelloSequenceInputStreamTest {

    private static String makeTextPath(String fileName) {
        return String.format("src/main/java/com/budd/java/jdkBasic/io/bio/SequenceStream/%s", fileName);
    }

    /**
     * @author HJP
     * @date 2018年5月17日 15:54:00
     * @Description 普通顺序合并流，合并多个输入流，并顺序读取字节
     */
    @Test
    public void testSingleAdd() throws IOException {
        InputStream input1 = new FileInputStream(makeTextPath("text1.txt"));
        InputStream input2 = new FileInputStream(makeTextPath("text2.txt"));

        try (SequenceInputStream sequenceInputStream =
                     new SequenceInputStream(input1, input2)) {

            int data = sequenceInputStream.read();
            while (data != -1) {
                System.out.println(data);
                data = sequenceInputStream.read();
            }
        }
    }


    /**
     * @author HJP
     * @date 2018年5月17日 15:54:00
     * @Description 当多个顺序合并流，合并多个输入流，并顺序读取字节
     */
    @Test
    public void testMoreAdd() throws IOException {
        InputStream input1 = new FileInputStream(makeTextPath("text1.txt"));
        InputStream input2 = new FileInputStream(makeTextPath("text2.txt"));
        InputStream input3 = new FileInputStream(makeTextPath("text3.txt"));

        Vector<InputStream> streams = new Vector<>();
        streams.add(input1);
        streams.add(input2);
        streams.add(input3);
        SequenceInputStream sequenceInputStream =
                new SequenceInputStream(streams.elements());

        int data = sequenceInputStream.read();
        while (data != -1) {
            System.out.println(data);
            data = sequenceInputStream.read();
        }
        sequenceInputStream.close();
    }

    /**
     * @author HJP
     * @date 2018年5月17日 15:54:00
     * @Description SequenceInputStream会自动关闭输入流
     */
    @Test
    public void testAutoClose() throws IOException {
        InputStream input1 = new FileInputStream(makeTextPath("text1.txt"));
        InputStream input2 = new FileInputStream(makeTextPath("text2.txt"));
        try (SequenceInputStream sequenceInputStream =
                     new SequenceInputStream(input1, input2)) {
            int data = sequenceInputStream.read();
            while (data != -1) {
                System.out.println(data);
                data = sequenceInputStream.read();
            }
        }
    }

}
