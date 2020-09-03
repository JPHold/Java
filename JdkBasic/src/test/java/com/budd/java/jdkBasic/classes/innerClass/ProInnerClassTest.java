package com.budd.java.jdkBasic.classes.innerClass;

import static com.budd.java.util.Print.*;

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

}
