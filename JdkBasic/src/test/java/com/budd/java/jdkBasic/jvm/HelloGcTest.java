package com.budd.java.jdkBasic.jvm;


import com.budd.java.jdkBasic.jvm.gc.GcA;
import com.budd.java.jdkBasic.jvm.gc.GcB;

import static com.budd.java.util.Print.*;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author Budd
 * @since 2019年1月5日16:23:07
 * 內存回收入门研究
 * JDK7
 * https://www.jianshu.com/p/50be08b54bee 第五章
 */
public class HelloGcTest {


    /**
     * @author HJP
     * @date 2017年09月22日 下午22:08:00
     * @Description 测试对类实例的回收
     */
    @Test
    public void testInstanceGc() throws InterruptedException {
        GcB gcB = new GcB();
        gcB.setGcA(GcA.getInstance());

        int gcTimes = 200;//执行清理次数
        for (int i = 0; i < gcTimes; i++) {

            printf("第%s次执行清理：", i + 1);

            GcA gcA = gcB.getGcA();
            gcA = null;
            System.gc();
            Thread.sleep(1000);
            if (gcB.getGcA() != null) {
                gcB.getGcA().live();
            } else {
                print("我死了");
            }
        }
    }

    /**
     * start:{引用回收}
     */
    /**
     * @author HJP
     * @date 2017年09月21日 下午18:20:00
     * @Description 虚引用
     */
    @Test
    public void testPhantomReference() {
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        String obj = new String("hello reference!!!");
        PhantomReference<String> pf = new PhantomReference<>(obj, queue);
        obj = null;

        while (true) {
            System.out.printf("pf.get() = %d, isEnqueued: %b\r\n", pf.get(), pf.isEnqueued());
            if (pf.isEnqueued())
                break;
            System.gc();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * end:{引用回收}
     */
}
