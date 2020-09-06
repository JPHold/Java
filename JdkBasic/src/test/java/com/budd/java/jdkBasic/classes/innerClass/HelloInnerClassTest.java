package com.budd.java.jdkBasic.classes.innerClass;

import static com.budd.java.util.Print.*;

import com.budd.java.jdkBasic.classes.innerClass.classtype.substaticclass.TestBed;
import org.junit.Test;

/**
 * 内部类入门研究
 *
 * @author budd
 * @since 2020年2月18日 22:52:31
 **/
public class HelloInnerClassTest {
    /**
     * @return
     * @author HJP
     * @date 2018年1月6日 %上午11:56:08
     * @Description JDK1.8之前，局部类操作局部变量， 需要显示添加final
     */
    @Test
    public void testLocalVarFinal() {
        //Variable 'i' is accessed from within inner class, needs to be declared final
        /*int i = 0;
        Runnable rn = new Runnable() {
            public void run() {
                print(i);
            }
        };*/
    }

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
            return new LocalCounter();
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * @return void
     * @Author budd
     * @Description 普通内部类
     * @Date 2020/9/3 17:21
     * @Param []
     **/
    @Test
    public void testSimpleInnerClass() {
        LocalInnerClass lic = new LocalInnerClass();
        Counter c1 = lic.getCounter("Local inner ");
        for (int i = 0; i < 5; i++) {
            c1.next();
        }
        printf("普通内部类，循环次数: %s", lic.count);
    }

    /**
     * @author HJP
     * @date 2018年2月14日 12:36:28
     * @Description 内部类继承
     */
    class ExtendOuter {
        public class Inner {
            protected void m1() {
                printf("内部类方法调用");
            }

            protected ExtendOuter outer() {
                return ExtendOuter.this;
            }
        }

        public void f() {
            printf("外部类方法调用");
        }
    }

    public class ExtendInnerClass extends ExtendOuter.Inner {
        public ExtendInnerClass(ExtendOuter ot) {
            ot.super();
        }

        @Override
        protected void m1() {
            super.m1();
        }
    }

    /**
     * @return void
     * @Author budd
     * @Description 简单创建内部类的对象
     * @Date 2020/9/3 15:40
     * @Param []
     **/
    @Test
    public void singleCreate() {
        ExtendOuter extendOuter = new ExtendOuter();
        ExtendOuter.Inner inner = extendOuter.new Inner();
    }

    /**
     * @return void
     * @Author budd
     * @Description 封装创建内部类的对象：隐式创建外部类的对象
     * @Date 2020/9/3 15:41
     * @Param []
     **/
    @Test
    public void testExtendsCreate() {
        ExtendInnerClass extendInnerClass = new ExtendInnerClass(new ExtendOuter());
        extendInnerClass.m1();
    }

    /**
     * @return void
     * @Author budd
     * @Description 内部类获得外部类的对象引用，调用外部类的方法
     * @Date 2020/9/3 15:47
     * @Param []
     **/
    @Test
    public void testOuterMethodInvoke() {
        ExtendOuter extendOuter = new ExtendOuter();
        ExtendOuter.Inner inner = extendOuter.new Inner();
        inner.outer().f();
    }


    /**
     * 研究嵌套静态类
     */
    /**
     * @return void
     * @Author budd
     * @Description On Java 8作者推荐每个类都写main方法，来达到测试。
     * 但编译出来的class文件，这些测试代码就会跟着一起发布，打出的包会过大，增加部署时间，而这些应该只停留到测试阶段
     * 要想停留在测试阶段，
     * 有个特性可利用：嵌套静态类，通过java -cp .;xxxPath\classes\; com.budd.java.jdkBasic.classes.innerClass.classtype.substaticclass.TestBed$Tester
     * 测试完毕后，将这个class删除。
     *
     * 通过上述描述，会发现很繁琐，所以就有了maven项目管理+test注解+test目录。跟真实逻辑代码区分开来。因此就不采用这种方式了
     * @Date 2020年9月6日 18:11:22
     * @Param []
     **/
    @Test
    public void testSubStaticClass() {
        TestBed.Tester testBed = new TestBed.Tester();
    }

}
