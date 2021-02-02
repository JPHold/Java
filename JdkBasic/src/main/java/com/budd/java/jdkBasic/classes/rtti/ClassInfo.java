package com.budd.java.jdkBasic.classes.rtti;

import static com.budd.java.util.Print.print;

/**
 * @author budd
 * @desc 类信息
 * @since 2021/1/19 17:53
 **/
public class ClassInfo {

    static {
        print("调用静态方法");
    }

    {
        print("调用实例方法");
    }

    public class ChildClassInfo{

    }
}
