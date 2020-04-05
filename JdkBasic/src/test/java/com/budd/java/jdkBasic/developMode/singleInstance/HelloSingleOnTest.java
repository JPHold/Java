package com.budd.java.jdkBasic.developMode.singleInstance;

import static com.budd.java.util.Print.*;

import org.junit.Test;

/**
 * @author budd
 * @desc 设计模式-单例模式入门研究
 * @since 2020/4/5 19:45
 **/
public class HelloSingleOnTest {

    /**
     * @author HJP
     * @date 2017年10月06日 下午23:21:00
     * @Description 静态单例
     */
    static class StaticSingleton {
        private StaticSingleton() {
        }

        public static StaticSingleton getInstance() {
            return StaticSingleton.SingletonHolder.instance;
        }

        private static class SingletonHolder {
            private static final StaticSingleton instance = new StaticSingleton();
        }
    }

    @Test
    public void testStaticInstance() {
        print("通过static修饰达到单例模式");
        printf("静态单例实例：%s\n",StaticSingleton.getInstance());
    }

    /**
     * @author HJP
     * @date 2017年10月06日 下午23:38:00
     * @Description 枚举单例
     */
    public static class EnumSingleton {

        private EnumSingleton() {
        }

        private static EnumSingleton getInstance() {
            return SingletonEnum.INSTANCE.getInstance();
        }

        private void msg() {
            System.out.println("你好??枚举单例模式");
        }

        private static enum SingletonEnum {

            INSTANCE;

            private EnumSingleton sle;

            private SingletonEnum() {
                sle = new EnumSingleton();
            }

            public EnumSingleton getInstance() {
                return sle;
            }
        }
    }

    @Test
    public void testEnumInstance(){
        //枚举实际是静态类
        print("通过枚举达到单例模式");
        EnumSingleton.getInstance().msg();
    }

}
