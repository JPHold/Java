package com.budd.java.jdkBasic.classes.rtti;

import org.junit.Test;

import static com.budd.java.util.Print.*;

/**
 * @author budd
 * @desc RTTI的入门研究
 * @since 2021/1/19 17:46
 **/
public class HelloRTTITest {


    /**
     * @Author budd
     * @Description //测试Class.forName加载静态代码块
     * @Date 2021/1/19 18:01
     * @Param []
     * @return void
     **/
    @Test
    public void testClassForNameLoadStaticCode() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        print("---加载类信息");
        Class<ClassInfo> classInfoClass = (Class<ClassInfo>) Class.forName("com.budd.java.jdkBasic.classes.rtti.ClassInfo");

        printf("%n---创建实例");
        ClassInfo classInfoTest = classInfoClass.newInstance();
    }
}
