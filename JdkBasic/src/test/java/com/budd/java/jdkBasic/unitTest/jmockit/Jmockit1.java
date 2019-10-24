package com.budd.java.jdkBasic.unitTest.jmockit;

import com.budd.java.jdkBasic.unitTest.jmockit.MyObject;
import mockit.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 单元测试mock框架-jmockit-hello world
 *
 * @since 2019年2月19日22:49:30
 */
public class Jmockit1 {
    @Mocked  //用@Mocked标注的对象，不需要赋值，jmockit自动mock
            MyObject obj;

    /**
     * 官方：http://jmockit.github.io/
     * 中文教程：http://jmockit.cn/index.htm、https://www.jianshu.com/c/cfe7310a18ff
     * 主教程：https://www.hollischuang.com/archives/568
     */
    /**
     * 搭建
     * 1、依赖的包(jmockit必须在junit前,不然报错)
     *  -- jmockit
     *  -- junit
     * 2、可选的包
     *  -- jmockit-coverage 代码覆盖率
     */
    /**
     * JMockit requires a Java 5 VM or later.
     * Expectations期望块内定义的mock对象的方法调用顺序和次数,必须严格遵守
     *  --调用的方法的结果不一致是ok的
     *  --returns(XXX),result=XXX是在期望块中最近一个调用方法的返回值
     *  --天然支持验证是否调用
     * NonStrictExpectations内定义的mock对象的方法调用顺序,随意调用
     *  --期望块代码与Expectations一样
     *  --通过Verifications验证是否调用，times是调用次数
     * 在期望块后的代码编写按Expectations类型分为严格和宽松
     * 严格：存在对比结果的代码。这代码中调用的方法要与期望块的returns期望的方法要一致
     * 宽松：无代码要求
     *
     * 总结：
     * 基于行为的mock方式是黑盒,专注于功能整体
     * 基于状态的mock方式是白盒,专注于内部逻辑
     */
    /**
     * 注解讲解
     * @Mocked 1、是自动创建实例,默认将构造器、非静态方法都mock掉
     *         2、value指定被mock的方法
     *         3、mock的方法中的逻辑被屏蔽
     *         4、jomckit-1.7版本,@Mocked的methods属性已经被value取代
     *         5、@Mocked修饰需要放在@Test方法的形参上
     */
    /**
     * 非局部模拟-严格期望
     */
    @Test
    public void testStrictExpectationHello() {
        new Expectations() {//录制预期模拟行为
            {
                obj.hello("Zhangsan");
                obj.hello1("Zhangsan");
                returns("Hello1 Zhangsan");
                //也可以使用：result = "Hello1 Zhangsan";
            }
        };

        obj.hello("Zhangsan");
        assertEquals("Hello1 Zhangsan", obj.hello1("Zhangsan"));//调用测试方法
    }

    /**
     * 非局部模拟-宽松期望
     */
    @Test
    public void testNonLocalNonStrictExpectationHello() {
        new NonStrictExpectations() {//录制预期模拟行为
            {

                obj.hello("Zhangsan");
                obj.hello1("Zhangsan");
                returns("Hello1 Zhangsan");
                //也可以使用：result = "Hello1 Zhangsan";
            }
        };

        obj.hello("Zhangsan");
        obj.hello("Zhangsan");
        assertEquals("Hello1 Zhangsan", obj.hello1("Zhangsan"));//调用测试方法
        new Verifications() {//验证预期Mock行为被调用
            {
                obj.hello("Zhangsan");
                times = 3;
            }
        };
    }

    /**
     * 局部模拟-宽松期望
     */
    @Test
    public void testLocalNonStrictExpectationHello() {
        final MyObject obj = new MyObject();
        new NonStrictExpectations(obj) {//录制预期模拟行为
            {

                obj.hello("Zhangsan");
                returns("Hello Zhangsan");
            }
        };

        assertEquals("Hello Zhangsan", obj.hello("Zhangsan"));//调用测试方法

        new Verifications() {//验证预期Mock行为被调用
            {
                obj.hello("Zhangsan");
                times = 1;
            }
        };
    }


    /**
     * 静态方法-宽松期望
     */
    @Test
    public void testStaticNonStrictExpectationHello() {
        new NonStrictExpectations(MyObject.class) {//录制预期模拟行为
            {

                MyObject.hello2("Zhangsan");
                returns("Hello2 Zhangsan");
            }
        };

        System.out.println(MyObject.hello2("Zhangsan"));
//        assertEquals("Hello2 Zhangsan", MyObject.hello2("Zhangsan"));//调用测试方法

        new Verifications() {//验证预期Mock行为被调用
            {
                MyObject.hello2("Zhangsan");
                times = 1;
            }
        };
    }

    /**
     * 私有方法-宽松期望
     * https://blog.csdn.net/n8765/article/details/40896757
     */
    @Test
    public void testPrivateMethodNonStrictExpectationHello() {
        final MyObject obj = new MyObject();
        new NonStrictExpectations(obj) {//录制预期模拟行为
            {
                //如果私有方法是静态的，可以使用：invoke(null, method,value)
                Deencapsulation.invoke(MyObject.class, "hello4", "Zhangsan");
//                Deencapsulation.invoke(obj,"hello3","Zhangsan");//私有非静态方法
                returns("Hello3 Zhangsan");
            }
        };

        assertEquals("Hello3 Zhangsan", Deencapsulation.invoke(obj, "hello3", "Zhangsan"));//调用测试方法
        new Verifications() {//验证预期Mock行为被调用
            {
                Deencapsulation.invoke(obj, "hello3", "Zhangsan");
                times = 1;
            }
        };
    }

    /**
     * 私有属性设置-宽松期望
     * https://blog.csdn.net/n8765/article/details/40896757
     */
    @Test
    public void testPrivateFieldNonStrictExpectationHello() {
        final MyObject obj = new MyObject();
        new NonStrictExpectations(obj) {//录制预期模拟行为
            {
                Deencapsulation.setField(obj, "name", "name has been change!");
            }
        };

        assertEquals("name has been change!", obj.getName());//调用测试方法

    }

    /**
     * 基于状态的mock方式
     */
    /**
     * 修改内部逻辑-宽松期望
     * 补充说明:http://outofmemory.cn/code-snippet/3275/Jmockit-tips
     */
    @Test
    public void testInnerLogicNonStrictExpectationHello() {

        //普通方法
        MockUp mockUp = new MockUp<MyObject>() {//使用MockUp修改被测试方法内部逻辑,不能静态方法
            @Mock
            private String hello5(String name) {
                return "Hello5 " + name + " name has been change!";
            }
        };

        //普通方法调用
        assertEquals("Hello5 Zhangsan name has been change!", obj.hello5("Zhangsan"));//调用测试方法(内容相等)
//        assertEquals("Hello5 Zhangsan", obj.hello5("Zhangsan"));//调用测试方法(内容不等)

        // 做还原动作, 确保mock实例在下一次使用时是原始逻辑
        // Mockit.tearDownMocks();注意：在JMockit1.5之后已经没有Mockit这个类，使用MockUp代替(mockUp和tearDown方法)
        mockUp.tearDown();
        //测试tearDown还原操作是否生效,原始逻辑结果是"Hello5 Zhangsan"
//        assertEquals("Hello5 Zhangsan name has been change!", obj.hello5("Zhangsan"));//调用测试方法(内容相等)


        //静态方法
        mockUp = new MockUp<MyObject>() {//使用MockUp修改被测试方法内部逻辑,不能静态方法
            @Mock
            private String hello6(String name) {
                return "Hello6 " + name + " name has been change!";
            }
        };

        //静态方法调用
        assertEquals("Hello6 Zhangsan name has been change!", MyObject.hello6("Zhangsan"));//调用测试方法(内容相等)
    }


}
