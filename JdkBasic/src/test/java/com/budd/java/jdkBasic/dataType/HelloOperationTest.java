package com.budd.java.jdkBasic.dataType;

import static com.budd.java.util.Print.*;
import org.junit.Test;

/**
 * @author budd
 * @desc 运算符入门研究
 * @since 2020/4/13 22:47
 **/
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
		/*运行结果：
		* [-128, 127] 的数比较结果
			true
			false

		  <>=127 的数比较结果
			false
			true

	      <=-128的数比较结果
			false
			true
		* */

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

	/**
	 * 各类字面量
	 * 各类进制换算(二进制、八进制、十六进制)
	 * 各类辅助字面量
	 * 2020年7月4日 16:09:27
	 */
	@Test
	public void testVariousLiteral(){
		printf("16进制转二进制：");
		int hexUpperCase = 0x2f; // 16进制 (小写)
		printf("16进制 (小写)：%s",Integer.toBinaryString(hexUpperCase));
		int hexLowerCase = 0X2F; // 16进制 (大写)
		printf("16进制 (小写)：%s",Integer.toBinaryString(hexLowerCase));

		printf("--------------------------");

		printf("8进制转二进制(前面加0)：");
		int decZero = 0177;
		printf("8进制 (前导0)：%s",Integer.toBinaryString(decZero));

		printf("--------------------------");

		printf("字符的进制：");
		char maxCharHex = 0xffff;
		printf("最大 char 型16进制值：%s",Integer.toBinaryString(maxCharHex));

		byte maxByteHex = 0x7f;
		printf("最大 byte 型16进制值：%s",Integer.toBinaryString(maxByteHex));

		short maxShortHex = 0x7fff; // 最大 short 型16进制值
		printf("最大 short型16进制值：%s",Integer.toBinaryString(maxShortHex));

		printf("--------------------------");

		printf("基本类型的辅助字面量：");
        long n1 = 200L; // long 型后缀
        long n2 = 200l; // long 型后缀 (容易与数值1混淆)
        long n3 = 200;

        float f1 = 1;
        float f2 = 1F; // float 型后缀
        float f3 = 1f; // float 型后缀

        double d1 = 1d; // double 型后缀
        double d2 = 1D; // double 型后缀
	}

}
