package com.budd.java.jdkBasic.trait.encapsulation;

//import com.budd.java.jdkBasic.trait.encapsulation.otherPackage.DefaultClassPublicConstructor;
import org.junit.Test;

/**
 * @author budd
 * @desc 封装-入门研究
 * @since 2020/8/24 15:38
 **/
public class HelloEncapsulationTest {


    /**
     * @Author budd
     * @Description 默认权限的类，主类的类型可以是public和默认
     * @Date 2020/8/24 15:45
     * @Param []
     * @return void
     **/
    @Test
    public void testDefaultClass(){
        new DefaultClass();
    }

    /**
     * @Author budd
     * @Description 包权限-公有构造器：
     * Error:(3, 63) java: com.budd.java.jdkBasic.trait.encapsulation.otherPackage.DefaultClassPublicConstructor
     * 在com.budd.java.jdkBasic.trait.encapsulation.otherPackage中不是公共的; 无法从外部程序包中对其进行访问
     *
     * 结论：即使将构造器的权限设为public，并不能使这个类被其它包访问
     * @Date 2020/8/24 17:02
     * @Param []
     * @return void
     **/
    @Test
    public void testDefaultClassPublicConstructor(){
//        new DefaultClassPublicConstructor();
    }


}
