package com.budd.java.jdkBasic.string;

import org.junit.Test;

import javax.swing.text.Segment;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * 2018年11月8日21:46:00
 * Budd
 *
 * @author Holis
 */
public class StringApiDescMain {

    public static void main(String[] args) {

        // String s=new String(null);该构造器对参数加了@NotNull限定
        //String 的字符(char[])是用utf-16(unicode)标示,在jdk以后增加增补字符:https://www.aliyun.com/jiaocheng/348736.html（难懂）

        //String无继承,实现java.io.Serializable, Comparable<String>, CharSequence

        /**
         * 创建String
         */
        //String是用char[]存储的,并且private final的,所以无须担心外部代码修改会影响string的值
        String s1 = new String();
        String s2 = new String(s1);//将源s1的value和hash值给目标s2,因为string.value是private final(定义String后其内容是不可以改变的)
        // ,所以无须担心s1会影响s2

        //StringBuilder和StringBuffer构建string,很少用,因为他们有toString方法来构建string
        //StringBuffer的toString是加了syncchronized,牺牲效率但保证线程安全,所以使用StringBuilder构建String比StringBuffer快
        new String(new StringBuffer());//等于new StringBuffer().toString()
        new String(new StringBuilder());//等于new StringBuilder().toString()

        /**
         * 创建String
         */

        /**
         * 转换string
         */
        //string转byte[]
        //在中文机器环境是GBK或GB2312编码,英文机器环境是ISO-8859-1编码。跟环境有强关联,所以最好指定编码格式(一般是utf-8)
        "".getBytes();
        try {
            byte[] b0 = "".getBytes("utf-8");
            byte[] b1 = "".getBytes(Charset.forName("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //byte[]转String,如果没指定编码格式charset,会获取本地环境默认的编码格式,如果还没有则采用ISO-8859-1
        new String(new byte[]{});
        /**
         * 转换string
         */
    }

    /**
     * 深入理解String
     */
    @Test
    public void depthString() {

        //重载运算符是根据不同基本类型转换为不同方法,运算对象成为方法的参数,在编译过程完成
        //Java唯一的重载运算符
//        String s1="1";
//        String s2=s1+"2";
//        String s3=s1+3;

        //String创建与常量池的关系(分为三个阶级)
        //  1、编译期：编译成class,有个contact pool将字符串的字面量和符号引用维护进来
        //  2、类加载期：从编译器的contact pool取出字符串,在JVM的常量池查找是否存在该字面量,存在则直接返回引用,不存在则创建字面量对象并返回该引用
        //  3、运行期：new String("1")在堆上创建对象,指向常量池的字面量对象,而s符号引用保存在栈上,指向堆上的对象

        // 变量、实例、字面量实例的关系,变量是对实例的引用,实例是对字面量的引用,字面量实例所在的常量池存放着实例的引用
//        String s4=null;//创建了一个变量,没有创建对象实例
        //创建两个变量s5和s6,创建了两个对象实例,一个字面量5的实例(即使字面量一样,依然会创建不同对象实例)
//        String s5=new String("5");
//        String s6=new String("5");
//        System.out.println(s5==s6);

        //intern对创建对象实例和字面量的影响
        String s7 = new String(new String("he") + new String("llo"));//或new String("he")+new String("llo")
        s7.intern();   //将堆中新建的对象"hello" 存入字符串常量池
        String s8 = "hello";
        System.out.println("比较\"\"和new String(\"\")构建字符串：" + (s7 == s8));//输出是true

       /* String s7="hello";
        String s8="hello";
        System.out.println(s7==s8);//输出是true。*/
        //总结：上面的例子是一样的效果。+重载运算符组合会在堆创建对象实例,在intern()后将he和llo组合的字面量存到常量池
        //,后面如果有用到hello,不会在堆创建对象实例和在常量池创建字面量,而是返回已存在的对象实例和字面量符号引用
        //,因为是同个对象实例,所以相等

        //字面量与对象实例的差别
        //第二个例子
        String s9 = new String("he") + new String("llo");
        String s10 = new String("h") + new String("ello");

        String s11 = s9.intern();//创建hello字面量并返回它的符号引用s11,s11指向常量池的hello字面量
        String s12 = s10.intern();//上一步已保存hello字面量,直接返回符号引用
        System.out.println("比较通过+重载运算符组合和其先intern():" + (s9 == s11));//false：s9先intern()
        System.out.println("比较通过+重载运算符组合和其后intern():" + (s10 == s12));//false：s12等于s9.intern()返回的字面符号引用
        System.out.println("比较字面量实例:" + (s11 == s12));//true：(s9先intern(),所以s10.intern()其实是s9的字面量符号引用,所以相等)
        //总结：hashCode相同的字符串,谁先intern(),后面的intern()返回的符号引用都等于前面intern()返回的符号引用

        //第三个例子
        String s13 = new String("Budd");//返回堆中的对象实例
        String s14 = new String("Budd").intern();//返回该字面量的符号引用
        System.out.println("比较对象实例与其intern()" + (s13 == s14));//false
        String s15 = new String("Bu") + new String("dd");
        String s16 = s15.intern();
        System.out.println("通过+重载运算符组合和其intern()" + s15 == s16);//false
        //总结：通过+重载运算符组合多个new String()与其intern()是相等的

        //第四个例子
        String s1 = /*new StringBuilder("漠").append("然").toString()*/new String(new char[]{'漠','然'},0,2);
        System.out.println(s1.intern() == s1);
        System.out.println(s1.hashCode()+","+new String("漠然").hashCode());

        String s2 = new StringBuilder("漠").append("然").toString();
        System.out.println(s2.intern() == s2);
    }

    /**
     * 比较string
     */
    @Test
    public void equals() {

        //equals(Object),比较规则如下：
        //  1、先比较是否同个对象,否走下一步
        //  2、判断是否为String,否返回false,是走下一步
        //  2、比较长度是否一致,是走下一步
        //  3、遍历两者的内部数组的字符,做比较
        String s1 = new String("1");
        String s2 = new String(s1);
        System.out.println("equals:" + s2.equals(s1));

        //
        //contentEquals(CharSequence),比较规则如下：
        //  1、对StringBuffer对象加锁
        //  2、调用contentEquals(CharSequence cs)
        //  3、先比较长度,否走下一步
        //  4、如果是像StringBuffer或StringBuilder一样继承AbstractStringBuilder的类,则通过getValue拿到字符数组,遍历比较
        //  5、通过equals(this)判断是否是String且内容是否一致,否走一步
        //  6、如果是继承CharSequence的类(非String),则遍历字符数组,逐个判断
        System.out.println("contentEquals(StringBuffer):" + "contentEquals".contentEquals(new StringBuffer("contentEquals")));//同步比较
        System.out.println("contentEquals(String):" + "contentEquals".contentEquals("contentEquals"));//类型为String
        System.out.println("contentEquals(Segment):" + "contentEquals".contentEquals(new Segment("contentEquals".toCharArray(), 0, 13)));//类型为除String以外的CharSequence

        //忽略大小写比较
        //equalsIgnoreCase(String)
        System.out.println("equalsIgnoreCase(String):" + "A".equalsIgnoreCase("a"));

        //按Unicode值字典比较内部字符数组,如果不同会返回两者Unicode值之差,如果相同返回两字符串的长度之差
        //compareTo(String)、compareToIgnoreCase(String)
        System.out.println("compareTo(String):" + "A".compareTo("Aa"));

        //局部比较
        // regionMatches(int toffset, String other, int ooffset,int len),从当前字符串的第toffset位置和other字符串的第ooffset位置,比较len长度的字符串
        // regionMatches(boolean ignoreCase, int toffset,String other, int ooffset, int len),忽略大小写
        System.out.println("regionMatches(not ignoreCase):" + "abc".regionMatches(0, "A", 0, 1));
        System.out.println("regionMatches(yes ignoreCase):" + "abc".regionMatches(true, 0, "A", 0, 1));
    }


    /**
     * hashCode计算
     * 1、31系数：http://jm-blog.aliapp.com/?p=1501
     */
    @Test
    public void hashCodeTest() {
        /**
         int h = hash;
         *         if (h == 0 && value.length > 0) {
         *             char val[] = value;
         *
         *             for (int i = 0; i < value.length; i++) {
         *                 h = 31 * h + val[i];
         *             }
         *             hash = h;
         *         }
         *         return h;
         */
        //质数的概念：除1和它本身外,无法被其他自然数整除的数
        //为何hashCode采用31(质数)作为系数:
        // 1、系数要尽量长且乘法尽量不会溢出的数(在Java乘法中,如果数字相乘过大会溢出造成数据丢失)
        // ,长的系数计算出来的hash地址就越大,相同的hash地址的可能性就越小,hash链就越短,查询效率就越高
        // 2、为了更好分配hash地址,31只占5bit
        //hashCode保证相同字符串,hash值相同;hash值相同,字符串不一定相同
        System.out.println("hashCode:" + "222222222222".hashCode());
    }

    /**
     * 2018年11月28日15:51:27
     * hashCode与equals的区别
     */
    @Test
    public void hashCodeEquals() {
        HashCodeEqualsClass he1 = new HashCodeEqualsClass("red");
        System.out.println(he1.hashCode());
        HashMap<HashCodeEqualsClass, String> map = new HashMap<>();
        map.put(he1, "red");
        map.put(new HashCodeEqualsClass("red"), "blue");
        System.out.println(map);
        System.out.println(map.get(new HashCodeEqualsClass("red")));
    }

    /**
     * 替换字符串
     */
    @Test
    public void replace() {

        //根据正则表达式,替换首个满足的字符串(如果regex不是正则表达式跟repalce一样)
        //String replaceFirst(String regex, String replacement)
        System.out.println("replaceFirst:" + "replaceFirst".replaceFirst("[r]", "a"));
        //根据正则表达式,扫描并替换所有满足的字符串(如果regex不是正则表达式跟repalce一样)
        //String replaceAll(String regex, String replacement)
        System.out.println("replaceAll:" + "replaceAll".replaceAll("[l]", "a"));

        //直接替换相等的字符串
        //String replace(CharSequence target, CharSequence replacement)
        System.out.println("replace:" + "replace".replace("r", "a"));
    }


    /**
     * 基本类型转String
     */
    public void valueOf() {

        //拷贝数组并构建新String(因为早期String构造器实现是不拷贝数组的,所以才提供这方法。现在就不需要用这个方法了)
        System.out.println("copyValueOf" + String.copyValueOf("123".toCharArray()));

        //六种类型转String
    }

    //为什么数组获取长度是length，字符串获取长度是length()
    @Test
    public void length() {
        int l1 = "string".length();
        int l2 = new String[]{"char[]"}.length;
        System.out.println(new int[]{1}.getClass());

    }

    @Test
    public void performanceOptimized() {
        /**
         *  JDK6和JDK7的subString()
         */

        //JDK7增加:保护类型的构造方法String(char[] value, boolean share) (private权限）
        //JDK6有个方法：String(int offset, int count, char value[]),跟上面的方法一样的效果

        //这些方法,我记作抠方法
        // 好处：这些方法直接指向字符数组的引用,因此共享数组、节省内存
        // 坏处：在JDK6,subString方法采用String(int offset, int count, char value[])来截取方法
        //,会导致内存泄露:因为在这个方法是直接引用外部字符串的数组,并且只是截取一段数组来使用
        //,外部字符串可以被回收,但因为他的内部数组不能被回收且其中有些字符没有被用到却留在内存中,会导致内存溢出/性能下降

        //除了subString方法这种会使得内部数组的长度变短外
        // ,其他方法如replace是遍历内部数组的字符进行替换,所以replace后的string的内部数组长度和源String的内部数组长度一致,不会导致内部溢出,所以可以使用抠方法
        // ,concat是源string的内部数组和新string的内部数组拼接起来,所有字符都被用到,不会导致内存溢出,可以使用抠方法
        //new String(new char[]{},false);

        //测试subString造成内存泄露(无法模拟出)
        MemoryLeak.main(null);
        MemoryLeak2.main(null);
    }

    /**
     * 2018年12月1日10:35:21
     * switch对所有类型的支持
     */
    @Test
    public void switchAllType() {

        int i = 1;
        switch (i) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
        }

        char c = '1';
        switch (c) {
            case '1':
                System.out.println(1);
                break;
            case '2':
                System.out.println(2);
                break;
        }
        String str = "1";
        switch (str) {
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("2");
                break;
        }
    }

}
