package com.budd.java.jdkBasic.developMode.singleInstance;

import org.junit.Test;

import static com.budd.java.util.Print.*;

/**
 * @author budd
 * @desc 设计模式-单例模式进阶研究
 * @since 2020/4/5 20:06
 **/
public class ProSingleOnTest {

    /**
     * @author HJP
     * @date 2017年10月06日 下午23:38:00
     * @Description 静态单例
     */
    static class DclSingleton {
        private static DclSingleton sl = null;

        private DclSingleton() {
        }

        public static DclSingleton getInstance() {
            if (sl == null) {//去掉不必要 的同步
                synchronized (DclSingleton.class) {
                    if (sl == null) {
                        sl = new DclSingleton();
                    }
                }
            }
            return sl;
        }

    }

    @Test
    public void tstDclInstance() {
        print("通过dcl模式达到单例模式");
        print(DclSingleton.getInstance());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                print(DclSingleton.getInstance());
            }
        });
        t.run();
    }

}
