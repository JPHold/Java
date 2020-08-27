package com.budd.java.jdkBasic.interfaces.interfaceimpl;

import com.budd.java.jdkBasic.interfaces.interfaceimpl.extendImplCoexist.sameMethodType.EaiBasicClass;
import com.budd.java.jdkBasic.interfaces.interfaceimpl.extendImplCoexist.sameMethodType.EaiClass;
import com.budd.java.jdkBasic.interfaces.interfaceimpl.extendImplCoexist.sameMethodType.EaiInterface;
import com.budd.java.jdkBasic.interfaces.interfaceimpl.extendImplCoexist.shamMultipleExtend.AdapterRandomDoubles;
import com.budd.java.jdkBasic.interfaces.interfaceimpl.innerInterfaceImpl.InnerInterfaceImplContainer;
import com.budd.java.jdkBasic.interfaces.interfaceimpl.mehodtype.MethodTypeDowngradeClass;
import org.junit.Test;

import java.util.Scanner;

/**
 * 接口进阶研究
 *
 * @author budd
 * @since 2020/2/6 21:48
 **/
public class ProInterfaceTest {

    /**
     * 实现的方法，方法权限，支不支持向下转型
     */
    @Test
    public void methodTypeDowngrade() {
        //test()默认public实现
        //123三种向下转型的写法,是为了验证接口方法定义的权限，无法在实现类中做向低级权限转换(不能向下转型)
        //4是正确的写法
        MethodTypeDowngradeClass implementClass = new MethodTypeDowngradeClass();
        implementClass.test();
    }

    /**
     * 继承的方法和实现的方法,同名同参数同返回类型
     */
    @Test
    public void sameMethodExtendImplCoexist() {
        //EaiBasicClass为被继承类，EaiInterface为被实现类
        EaiInterface eaiInterface = new EaiClass();
        eaiInterface.test();

        EaiBasicClass eaiBasicClass = new EaiBasicClass();
        eaiBasicClass.test();
    }

    /**
     * 实现的方法,方法属性不同(如形参、返回类型)，能不能运行
     */
    @Test
    public void differentMethodExtendImplCoexist() {
        //查看Implement13.java，代码注释部分会看到编译错误
        //在实现和继承中，接口与接口之间，接口与类，避免方法同名不同属性(返回类型、形参)
    }


    /**
     * 伪多重继承
     */
    @Test
    public void shamMultipleExtend() {
        Scanner s = new Scanner(new AdapterRandomDoubles(7));
        while (s.hasNextDouble()) {
            System.out.print(s.nextDouble() + " ");
        }
    }

    /**
     * 类嵌套接口的设计
     */
    @Test
    public void innerInterfaceImplInvoke() {
        InnerInterfaceImplContainer container = new InnerInterfaceImplContainer();
        InnerInterfaceImplContainer.PrivateInterfaceImpl2 privateInterfaceImpl2
                = (InnerInterfaceImplContainer.PrivateInterfaceImpl2) container.getPrivateInterface();//无法得知DImpl2实现了D接口
        container.receiveD(container.getPrivateInterface());//访问f方法由InnerInterfaceImplContainer协助调用

        //看似很绕，为何要这么做
        //因为接口a是在类内部定义，对于在类外，是无法得知实现类实现了接口a，所以应该交类内部去解决
        //解决的方式：提供方法，协助外部调用
    }
}