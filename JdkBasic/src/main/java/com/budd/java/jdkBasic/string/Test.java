package com.budd.java.jdkBasic.string;

public class Test {

    public static void main(String[] args) {
        String s1 = new String("1");
        s1.intern();
        String s2 = "1";
        System.out.println(s1 == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);


        //jdk1.6中，s1.intern()运行时，首先去常量池查找，发现有该常量，返回常量池的地址
        //第三行中，s2则使栈中空间直接指向常量池，所以s1和s2不想等
        //
        //jdk1.7中，由于常量池在堆空间中，所以在s1.intern()运行时，发现常量池有常量,返回常量池的地址
        //这时s2通过查找常量池中的常量，同样也指向了堆空间地址，所以s1和s2相等。
        //---------------------
        //作者：YesNoBut
        //来源：CSDN
        //原文：https://blog.csdn.net/qq_36357995/article/details/79985538
        //版权声明：本文为博主原创文章，转载请附上博文链接！

        String s5=new String("5");
        String s6="5";
        System.out.println(s5.intern()==s6);
        //jdk6和7
        //intern()返回结果分两种,如果字符串存在则返回在常量池的地址,如果不存在则在堆新建对象地址、常量池新建字面量和对象引用(指向堆的对象地址)
    }

}
