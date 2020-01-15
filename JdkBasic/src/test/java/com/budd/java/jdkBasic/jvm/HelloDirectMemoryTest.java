package com.budd.java.jdkBasic.jvm;

import org.junit.Test;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author Budd
 * @since 2019年1月5日14:49:46
 * 直接内存分配测试
 * JDK7
 * https://www.jianshu.com/p/50be08b54bee 前四章
 */
public class HelloDirectMemoryTest {

    /**
     * ByteBuffer分配默认直接内存
     */
    @Test
    public void nomarlDirectTest() throws InterruptedException {
        //未配置直接内存大小,以-Xmx大小为准
        //通过-XX:MaxDirectMemorySize配置直接内存大小
        //-Xmx100m 或 -Xmx100m -XX:MaxDirectMemorySize=100M
        ByteBuffer.allocateDirect(1024 * 1024 * 100);//字节单位
        TimeUnit.SECONDS.sleep(10);
        System.out.println("\nok");
    }

    /**
     * ByteBuffer分配指定直接内存
     *
     * @throws InterruptedException
     */
    @Test
    public void specialDirectTest() throws InterruptedException {

        //清除直接缓存
        // -Xmx768m
        // 验证clean()回收废弃空间后，内存释放。使用内存分析工具看不到释放痕迹
        ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 402);
        TimeUnit.SECONDS.sleep(100);
        ((DirectBuffer) bb).cleaner().clean();
        TimeUnit.SECONDS.sleep(100);
        System.out.println("ok");
    }

}
