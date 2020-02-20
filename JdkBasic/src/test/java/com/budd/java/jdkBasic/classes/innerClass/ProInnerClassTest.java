package com.budd.java.jdkBasic.classes.innerClass;


import org.junit.Test;

/**
 * 内部类进阶研究
 *
 * @author budd
 * @since 2020年2月18日 22:52:31
 **/
public class ProInnerClassTest {
    /**
     * ** ******* ******* * ******** * * * * ******** * /**
     *
     * @author HJP
     * @date 2018年2月13日 下午5:47:12
     * @Description TODO 匿名内部类测试(无法看到匿名内部类使用变量数，自动编译升级到对应形参数的构造器)
     */
    @Test
    public void testAnonymousInterface() {
        InvokeAnonymousInterface invokeAnonymousInterface = new InvokeAnonymousInterface();
        invokeAnonymousInterface.f0("内部类-接口例子-1").f0();
        invokeAnonymousInterface.f1("内部类-接口例子-2").f0();
    }

    @Test
    public void testAnonymousClass() {
        InvokeAnonymousClass anonymousInvokeClass = new InvokeAnonymousClass();
        System.out.println("\n-----------内部类-类例子-1");
        anonymousInvokeClass.f0("内部类-类例子-1").f0();

        System.out.println("\n-----------内部类-类例子-2");
        anonymousInvokeClass.f1("内部类-类例子-2").f0();

        System.out.println("\n-----------内部类-类例子-3");
        anonymousInvokeClass.f2("内部类-类例子-3-参数1", "内部类-类例子-3-参数2").f0();

        System.out.println("\n-----------内部类-类例子-4");
        anonymousInvokeClass.f3("内部类-类例子-4-参数1").f0();
    }

    //TODO 没啥用，只是想证明匿名内部类和内部类的使用是否有区别：匿名内部类无法新建/重写构造器
    interface Counter {
        int next();
    }

    public class LocalInnerClass {
        private int count = 0;

        Counter getCounter(final String name) {
            // A local inner class:
            class LocalCounter implements Counter {
                public LocalCounter() {
                }

                public int next() {
                    return count++;
                }
            }

            class LCounter implements Counter {
                public LCounter(int i) {
                }

                public int next() {
                    return count++;
                }
            }
            return new LocalCounter();
        }

        // The same thing with an anonymous inner class:
        Counter getCounter2(final String name) {
            new Counter() {
                // Anonymous inner class cannot have a named
                // constructor, only an instance initializer:
                {
                }

                public int next() {
                    return count++;
                }
            };
            return new Counter() {
                // Anonymous inner class cannot have a named
                // constructor, only an instance initializer:
                {
                }

                public int next() {
                    return count++;
                }
            };
        }

        public int getCount() {
            return count;
        }
    }

    @Test
    public void test() {
        LocalInnerClass lic = new LocalInnerClass();
        Counter c1 = lic.getCounter("Local inner "), c2 = lic.getCounter2("Anonymous inner ");
        for (int i = 0; i < 5; i++) {
            c1.next();
        }
        System.out.println(lic.count);
        for (int i = 0; i < 5; i++) {
            c2.next();
        }
        System.out.println(lic.count);
    }

}
