package com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod;


class ProtectedBasicBob {
    protected void bob() {
    }

}

class PublicBasicBob {
    public void bob() {
    }

}

/**
 * @author budd
 * @desc 继承protected方法+实现多个默认方法的类
 * 编译报错：
 * 'bob()' in 'com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod.ProtectedBasicBob' clashes with 'bob()'
 * in 'com.budd.java.jdk8.interfaces.interfaceimpl.defaultMethod.Bob1';
 * attempting to assign weaker access privileges ('protected'); was 'public'
 *
 * 基类的方法与接口的默认方法一模一样，则基类会代为实现，但因接口方法都是public权限，因此基类方法不能降低权限
 * @since 2020/8/28 10:22
 **/
/*public class ExtendMoreImlBob extends ProtectedBasicBob implements Bob1, Bob2 {
}*/

/**
 * @Author budd
 * @Description 继承public方法+实现多个默认方法的类
 * @Date 2020/8/28 10:34
 * @Param 
 * @return 
 **/
public class ExtendMoreImlBob extends PublicBasicBob implements Bob1, Bob2 {
}