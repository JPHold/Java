package com.budd.java.jdkBasic.typeInfer;

import org.junit.Test;

import java.util.Map;

/**
 * *
 * *******    ******* *
 * ********	  *
 * *      *      *
 * ********      *
 * /**
 *
 * @author HJP
 * @date 2018年1月6日 上午11:27:37
 * @Description JDK1.8之前的类型推断
 */
public class HelloTypeInferTest {

    /**
     * @param maps
     * @return
     * @author HJP
     * @Description JDK1.8之前的类型推断，类型是必填的
     */
    public void testMapConstructor(Map<String, Object> maps) {
    }

    @Test
    public void testMapTypeInfer() {
        //Required type:Map<String,Object>
        //Provided:HashMap<Object,Object>
//        testMapConstructor(new HashMap<>());
    }

    /*
     * @author HJP
     * @date 2018年3月14日 下午9:25:58
     * @Description .class测试
     */
    class BasicClass {
    }

    class ChildClass extends BasicClass {
        void msg() {
            System.out.println("ChildClass");
        }
    }

    class OtherClass {
    }

    /**
     * @author HJP
     * @date 2018年3月14日 下午9:25:58
     * @Description .class类型转换(强转)
     */
    @Test
    public void cast() {

        ChildClass childClass = new ChildClass();
        Class<BasicClass> basicClassClass = BasicClass.class;

        //正常
        ChildClass childClass1 = (ChildClass) basicClassClass.cast(childClass);
        childClass1.msg();

        //正常
        ChildClass childClass2 = (ChildClass) childClass;
        childClass2.msg();

        //运行时报错
        OtherClass otherClass = new OtherClass();
        ChildClass childClass3 = (ChildClass) basicClassClass.cast(otherClass);

        //编译时报错
//        ChildClass childClass4 = otherClass;
    }

    class ToStringClass {
        @Override
        public String toString() {
            return "";
        }
    }

    void testToStringInfer(String... a) {

    }

    /**
     * 虽然打印对象是调用toString()方法，但无法做到类型自动转换
     */
    @Test
    public void testString() {
        //Required type:String...
        //Provided:ToStringClass
//        testToStringInfer(new ToStringClass());
    }
}
