package com.budd.java.jdkBasic.jvm;

import static com.budd.java.util.Print.*;

import org.junit.Test;

/**
 * @author budd
 * @desc 引用入门研究
 * @since 2020/4/5 21:16
 **/
public class HelloReferenceTest {

    /**
     * @author HJP
     * @date 2017年09月28日 下午17:10:00
     * @Description 测试如下
     *
     * 1、修改入参引用
     * 2、修改入参内容
     * 得出结论：
     * 1、无法将方法内修改的引用传递到外部
     * 2、方法内修改的内容能传递到外部
     */
    @Test
    public void testMethodModifyInParamReference() {
        print("修改入参引用");
        StringBuilder sb = new StringBuilder("hello world");
        changeReference(sb);
        print(sb);

        print("修改入参内容");
        changeContent(sb);
        print(sb);
    }

    private void changeReference(StringBuilder s) {
        s = new StringBuilder("hello budd");
    }

    private void changeContent(StringBuilder s) {
        s.append(",hello budd");
    }
}
