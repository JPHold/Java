package com.budd.java.jdkBasic.classes.modifier;

import org.junit.Test;

/**
 * @author budd
 * @desc 修饰符-入门研究
 * @since 2020/8/25 16:23
 **/
public class HelloModifierTest {

    /**
     * @return void
     * @Author budd
     * @Description 空白常量初始化：必须在构造器或实例{}完成初始化，不然报错：Variable 'xxx' might not have been initialized
     * @Date 2020/8/25 16:26
     * @Param []
     **/
    @Test
    public void testBlankFinalPropertyInit() {
        new BlankFinalPropertyClass(1);
    }

}
