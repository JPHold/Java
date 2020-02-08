package com.budd.java.jdkBasic.classes.classReference;

import org.junit.Test;

/**
 * Class进阶研究
 *
 * @author budd
 * @since 2020/2/8 16:29
 **/
public class ProClassTest {
    /**
     * 基础类型和包装类的class和type比较
     * 基础类型class与包装类class不相等
     * 包装类type对应基础类型class，相等的
     */
    @Test
    public void basicWrapClassAndType() {
        Class<Integer> intClass = int.class;
        Class<Integer> integerClass = Integer.class;
        Class<Integer> integerType = Integer.TYPE;

        System.out.println("\n基础类型和包装类的class和type");

        System.out.println(String.format("基础类型class与包装类class比较：%s", intClass == integerClass));//false
        System.out.println(String.format("包装类type与包装类class比较：%s", integerType == integerClass));//false
        System.out.println(String.format("包装类type与基础类型class比较：%s", integerType == intClass));//true
    }

    /**
     * 继承下，instanceof与==、equals比较
     * instanceof可以识别继承链
     * class引用只基于当前类型，无法得知继承信息
     */
    @Test
    public void testInstanceOf() {
        ExtendBasicClass basicClass = new ExtendBasicClass();
        ExtendChildClass childClass = new ExtendChildClass();

        System.out.println("\ninstance of与==、equals比较");
        System.out.println(String.format("派生类与基类instanceof：%s", childClass instanceof ExtendBasicClass));//true
        System.out.println(String.format("派生类class与基类class ==比较：%s"
                , childClass.getClass() == basicClass.getClass()));//false
        System.out.println(String.format("派生类class与基类class equals比较：%s"
                , childClass.getClass().equals(basicClass.getClass())));//false
    }

}
