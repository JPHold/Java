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
     * @return void
     * @Author budd
     * @Description //测试Class.forName加载静态代码块
     * @Date 2021/1/19 18:01
     * @Param []
     **/
    @Test
    public void testClassForNameLoadStaticCode() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        print("---加载类信息");
        Class<ClassInfo> classInfoClass = (Class<ClassInfo>) Class.forName("com.budd.java.jdkBasic.classes.rtti.ClassInfo");

        printf("%n---创建实例");
        ClassInfo classInfoTest = classInfoClass.newInstance();
    }

    /**
     * @return void
     * @Author budd
     * @Description //测试class的方法
     * @Date 2021/1/20 15:38
     * @Param []
     **/
    private void showClassMethodInfo(Class<?> aClass) {
        printf("getName：%s", aClass.getName());
        printf("getSimpleName：%s", aClass.getSimpleName());
        printf("getCanonicalName：%s", aClass.getCanonicalName());
    }

    @Test
    public void testClassMethod() {
        printf("---数组Class");
        String[] array = {};
        showClassMethodInfo(array.getClass());

        printf("%n---内部类Class");
        showClassMethodInfo(ClassInfo.ChildClassInfo.class);

        printf("%n---普通类Class");
        showClassMethodInfo(ClassInfo.class);
    }
}
