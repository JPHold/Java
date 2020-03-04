package com.budd.java.jdk8.typeInfer;

import org.junit.Test;

import java.util.HashMap;
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
     * @author HJP4
     * @Description JDK1.8之前的类型推断，类型是必填的
     */
    public void testMapConstructor(Map<String, Object> maps) {
    }

    @Test
    public void testMapTypeInfer() {
        testMapConstructor(new HashMap<>());
    }
}
