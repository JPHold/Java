package com.budd.java.jdkBasic.dataType;


import org.junit.Test;

public class BaseDataType {
	
	/**
	 * 
	 * @author HJP
	 * @date 2018年11月4日21:24:05
	 * @Description 基本类型之间转换：byte,short,char—> int —> long—> float —> double
	 * @return
	 */
	@Test
	public void tran(){
		byte b=127;
		int i=b;
		System.out.println(+i);
	}
	
	/**
	 * 
	 * @author HJP
	 * @date 2018年11月4日21:32:00
	 * @Description  基本类型之间运算,先转成同个类型再运算：byte,short,char—> int —> long—> float —> double
	 * @return
	 */
	@Test
	public void calc(){
		
		byte b=127;
		short s=127;
		char c='A';//等于65
		int i=-127;
		long l=1111111111111111L;
		float f=10.1f;
		double d=10.1111222222222222222222222222222222222D;
		
		//转化
		byte b1=(byte) i;
		float c2= (float) d;
		
		System.out.println(c2);
	}

}
