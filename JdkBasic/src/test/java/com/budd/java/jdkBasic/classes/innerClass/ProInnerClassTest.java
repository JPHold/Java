package com.budd.java.jdkBasic.classes.innerClass;

import static com.budd.java.util.Print.*;

import com.budd.java.jdkBasic.classes.innerClass.closure.Callee1;
import com.budd.java.jdkBasic.classes.innerClass.closure.Callee2;
import com.budd.java.jdkBasic.classes.innerClass.closure.Caller;
import com.budd.java.jdkBasic.classes.innerClass.closure.MyIncrement;
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
     * @Description 匿名内部类测试
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
        printf("\n-----------内部类-类例子-1");
        anonymousInvokeClass.f0("内部类-类例子-1").f0();

        printf("\n-----------内部类-类例子-2");
        anonymousInvokeClass.f1("内部类-类例子-2").f0();

        printf("\n-----------内部类-类例子-3");
        anonymousInvokeClass.f2("内部类-类例子-3-参数1", "内部类-类例子-3-参数2").f0();

        printf("\n-----------内部类-类例子-4");
        anonymousInvokeClass.f3("内部类-类例子-4-参数1").f0();
    }

    /**
     * @return
     * @Author budd
     * @Description On Java 8的例子 闭包和回调
     * @Date 2020/9/7 10:16
     * @Param
     **/
    @Test
    public void testClosure() {
        //[1]
        Callee1 c1 = new Callee1();
        Caller caller1 = new Caller(c1);
        print("直接实现回调接口");
        caller1.go();


        //[2]
        Callee2 c2 = new Callee2();
        print("不实现回调接口，直接操作处理类");
        MyIncrement.f(c2);

        //[3]
        Caller caller2 = new Caller(c2.getCallbackReference());
        print("通过内部类实现回调接口");
        caller2.go();
    }


}
