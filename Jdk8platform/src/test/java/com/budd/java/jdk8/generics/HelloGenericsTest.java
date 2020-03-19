package com.budd.java.jdk8.generics;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.budd.java.util.Print.print;

/**
 * @author budd
 * @desc 泛型入门研究
 * @since 2020/3/6 13:30
 **/
public class HelloGenericsTest {

    /**
     * @return
     * @author HJP
     * @date 2018年3月20日 %下午10:57:54
     * @Description 显式的泛型参数推断
     *
     * 【1】通过对象调用generate()返回不声明泛型，Map变量声明，将Map作为参数传入调用，不报错
     * 【2】通过对象调用generate()返回默认是Map<Object,Object>,将Map<Object,Object>作为参数传入调用,报错
     *      //Required type:Map=<String,String>
     *      //Provided:Map<Object,Object>
     * 【3】通过对象调用generate(),直接作为入参调用,不报错
     * 【4】通过类调用generate(),直接作为入参调用,不报错
     *
     * 通过【3】和【4】,得出在JDK1.8之后,类型推断得到升级,所以不会报错
     */
    /**
     * @param m
     */
    void testMap(Map<String, String> m) {
        m.put("1", "1");
        m.put("2", "2");
        print(m);
    }

    static class InferGenericsCreateClass<K, V> {
        public static <K, V> Map<K, V> generate() {
            return new HashMap<K, V>();
        }
    }

    @Test
    public void testInferGenerics() {
        InferGenericsCreateClass inferGenericsCreateClass = new InferGenericsCreateClass();
        //JDK1.8之前编译不过,需要显式泛型参数推断
        //JDK1.8之后编译可行
        //在JDK1.8之后，可以接收generate()，返回的是Map<Object,Object>。但赋值=generate(),却不可以
        Map unGenericsMap = inferGenericsCreateClass.generate();
        testMap(unGenericsMap);//【1】
        Map<Object, Object> genericsMap = inferGenericsCreateClass.generate();
//        testMap(genericsMap);//【2】error
        testMap(inferGenericsCreateClass.generate());//【3】
        testMap(InferGenericsCreateClass.generate());//【4】
    }


}
