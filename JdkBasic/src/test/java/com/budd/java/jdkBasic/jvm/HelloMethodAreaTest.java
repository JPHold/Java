package com.budd.java.jdkBasic.jvm;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Budd
 * @since 2018年12月27日22:52:22
 * 方法区空间测试
 * JDK6+JDK7
 */
public class HelloMethodAreaTest {

    /**
     * @since 2018年12月27日22:54:10
     * 在JDK1.6和1.7中,观察常量池空间情况
     * zai
     */
    @Test
    public void constantPoolTest() {
        /**
         * JDK6首先设置 持久代最大和最小内存占用(限定为10M)
         * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
         * JDK7设置 堆上下限
         * VM args: -Xms10M -Xmx10M
         */
        List<String> params = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("java " + params);

        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
        System.out.println("堆内存信息: " + memorymbean.getHeapMemoryUsage());
        System.out.println("方法区内存信息: " + memorymbean.getNonHeapMemoryUsage());


        List<String> list = new ArrayList<String>();

        // 无限循环 使用 list 对其引用保证 不被GC  intern 方法保证其加入到常量池中
        int i = 0;
        while (true) {
            // 此处永久执行，最多就是将整个 int 范围转化成字符串并放入常量池
            list.add(String.valueOf(i++).intern());
            /*System.out.println(i+"---------------------------");
             memorymbean = ManagementFactory.getMemoryMXBean();
            System.out.println("堆内存信息: " + memorymbean.getHeapMemoryUsage());
            System.out.println("方法区内存信息: " + memorymbean.getNonHeapMemoryUsage());
            System.out.println("--------------------------------");*/
        }
    }

}
