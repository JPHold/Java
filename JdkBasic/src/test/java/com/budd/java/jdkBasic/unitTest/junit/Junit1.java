package com.budd.java.jdkBasic.unitTest.junit;

import org.junit.*;

/**
 * https://www.hollischuang.com/archives/1760
 * 单元测试
 * @author budd
 * @since 2019年2月26日22:20:34
 */
public class Junit1 {

    /**
     * 在整个类执行前只执行一次
     */
    @BeforeClass
    public static void testBeforeClass(){
        System.out.println("\nbeforeClass");
    }

    /**
     * 每个@Test方法执行前执行
     */
    @Before
    public void testBefore(){
        System.out.println("before");
    }

    /**
     * 测试超时
     */
    @Test(timeout = 3000)
    public void testTimeOut() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试是否抛出期望的异常
     * 与期望的异常不一致时报错
     */
    @Test(expected = NullPointerException.class)
    public void testException() {
        throw new NullPointerException();
    }

    /**
     * 测试Junit提供的断言工具
     */
    @Test()
    public void testAssert(){
        Assert.assertEquals(1 + 2, 3);//测试内容相等
        Assert.assertTrue(1 + 2 == 3);//测试比较结果为true
        Assert.assertFalse(1 + 2 == 4);//测试比较结果为false
        Assert.assertNotNull("not null");//测试对象不为空
        Assert.assertNull(null);//测试对象为空
    }

    /**
     * 单纯使测试不通过
     */
    @Ignore
    @Test()
    public void testAssertFail(){
        Assert.fail();
    }

    /**
     *  每个@Test方法执行后执行
     */
    @After
    public void testAfter(){
        System.out.println("after");
    }

    /**
     * 在整个类执行后只执行一次
     */
    @AfterClass
    public static void testAfterClass(){
        System.out.println("afterClass");
    }


}
