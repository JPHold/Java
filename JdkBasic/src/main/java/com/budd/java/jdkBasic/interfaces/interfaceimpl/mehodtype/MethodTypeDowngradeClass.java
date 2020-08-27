package com.budd.java.jdkBasic.interfaces.interfaceimpl.mehodtype;

/**
 * 					 **
 * *******    ******* *
 *		********	  *
 *		*      *      *
 *		********      *
 /**
 *
 * @author HJP
 * @date 2018年1月27日 下午6:16:01
 */
public class MethodTypeDowngradeClass implements MethodTypeDowngradeInterface {

    //1、不能转为private
//  @Override
//  private void test() {}

    //2、不能转为default
//  @Override
//  void test() {}

    //3、不能转为protected
//  @Override
//  protected void test() {}

    //4、默认public实现
    @Override
    public void test() {
    }
}