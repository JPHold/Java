package com.budd.java.jdkBasic.io.bio.PipedStream;

import org.junit.Test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author budd
 * @desc 管道输入输出流入门研究。
 * Java IO中的管道为运行在同一个JVM中的两个线程提供了通信的能力。所以管道也可以作为数据源以及目标媒介。
 * 你不能利用管道与不同的JVM中的线程通信(不同的进程)。在概念上，Java的管道不同于Unix/Linux系统中的管道。在Unix/Linux中，运行在不同地址空间的两个进程可以通过管道通信。
 * 在Java中，通信的双方应该是运行在同一进程中的不同线程。
 * @since 2020/3/5 13:30
 **/
public class HelloPipedStreamTest {

    @Test
    public void singleTest() throws IOException {
        final PipedOutputStream pos = new PipedOutputStream();
        final PipedInputStream pis = new PipedInputStream(pos);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    pos.write("Hello PipedStream".getBytes());
                    System.out.println("ready");
                    pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            public void run() {
                try {
                    int data = pis.read();
                    while (data != -1) {
                        System.out.println("data:" + (char) data);
                        data = pis.read();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        pis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * try{}finally{},在另起的线程未执行，就调用了finally{}，导致流提前关闭而报错：
     * java.io.IOException: Pipe closed
     * at java.io.PipedInputStream.read(PipedInputStream.java:307)
     * at com.budd.java.jdkBasic.io.bio.PipedStream.HelloPipedStreamTest$4.run(HelloPipedStreamTest.java:87)
     * at java.lang.Thread.run(Thread.java:748)
     *
     * @throws IOException
     */
    @Test
    public void pipedCloseError() throws IOException {
        final PipedOutputStream pos = new PipedOutputStream();
        final PipedInputStream pis = new PipedInputStream(pos);

        try {

            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        pos.write("Hello PipedStream".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    try {
                        int data = pis.read();
                        while (data != -1) {
                            System.out.println("data:" + (char) data);
                            data = pis.read();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } finally {
            try {
                pos.close();
                pis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输出流没有关闭，导致输入流没有读取，程序直接退出
     *
     * @throws IOException
     */
    @Test
    public void writeEndDeadErrr() throws IOException {
        final PipedOutputStream pos = new PipedOutputStream();
        final PipedInputStream pis = new PipedInputStream(pos);

        new Thread(new Runnable() {
            public void run() {
                try {
                    int data = pis.read();
                    while (data != -1) {
                        System.out.println("data:" + (char) data);
                        data = pis.read();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        pis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    pos.write("Hello PipedStream".getBytes());
                    System.out.println("ready");
                    //pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
