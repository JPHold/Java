package com.budd.java.jdkBasic.dataType;

import static com.budd.java.util.Print.*;
import org.junit.Test;

public class HelloOperationTest {
	
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

	/**
	 * @author budd
	 * @desc 使用“_”作为大数字的视觉分隔符，java7及以上才支持
	 * @since 2020年4月13日 22:50:10
	 */
	@Test
	public void testBigNumberVisionSplit(){
//		int bigNumberVisionSplit= 1_0000_0000;//一亿
	}

	/**
	 * 运算符
	 */
	/**
	 * 关系运算符=
	 */
	@Test
	public void testIntegerCompare(){
		/**
		 * Integer 内部维护着一个 IntegerCache 的缓存，默认缓存范围：[-128, 127]
		 * 因此在该范围内，即使使用==、!= 比较Integer引用，依然能得出正确结果(本来应该是比较对象地址，结果应为不相等)
		 * 最好使用equals进行比较
		 */

		printf("[-128, 127] 的数比较结果\n");
		Integer n1 = 47;
		Integer n2 = 47;
		printf("%s\n",n1 == n2);
		printf("%s\n",n1 != n2);

		printf("\n<>=127 的数比较结果\n");
		Integer n3 = 128;
		Integer n4 = 128;
		printf("%s\n",n3 == n4);
		printf("%s\n",n3 != n4);

		printf("\n<=-128的数比较结果\n");
		Integer n5 = -129;
		Integer n6 = -129;
		printf("%s\n",n5 == n6);
		printf("%s\n",n5 != n6);
	}
}
