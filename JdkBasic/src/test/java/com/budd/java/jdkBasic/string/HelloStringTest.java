package com.budd.java.jdkBasic.string;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.junit.Test;

import javax.swing.text.Segment;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * 字符串入门研究
 *
 * @author budd
 * @since 2019/11/3 17:00
 **/
public class HelloStringTest {

    private final String BASIC_CODEPOINT_PATH = ".\\src\\main\\resources\\string\\basicCodepointPath.txt";
    private final String SUPPLEMENT_CHAR_PATH = ".\\src\\main\\resources\\string\\supplementChar.txt";

    /**
     * 测试字符串创建
     */
    @Test
    public void testCreate() {
        String s1 = new String();//创建空串
        String s2 = new String(s1);//复制字符串
        String s3 = "s3";

        new String(new StringBuffer());//等于new StringBuffer().toString()
        new String(new StringBuilder());//等于new StringBuilder().toString()
        System.out.println("copyValueOf：" + String.copyValueOf("123".toCharArray()));
    }

    /**
     * 测试转换
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testTransform() throws UnsupportedEncodingException {
        /**
         * string转byte[]
         */
        String baseStr = "测试字符串转换";
        byte[] b0 = baseStr.getBytes();
        byte[] b1 = baseStr.getBytes("utf-8");
        byte[] b2 = baseStr.getBytes(Charset.forName("utf-8"));
        System.out.println("\n-----------string转byte[]");
        System.out.println(String.format("未指定转换格式：%s", b0));
        System.out.println(String.format("指定转换格式(utf-8)：%s", b0));

        /**
         * byte[]转string
         */
        System.out.println("-----------byte[]转string");
        String s0 = new String(b0);
        String s1 = new String(b0, Charset.forName("utf-8"));

        System.out.println(String.format("未指定转换格式：%s", s0));
        System.out.println(String.format("指定转换格式(utf-8)：%s", s1));

    }

    /**
     * 测试java的唯一重载运算符"+"
     */
    @Test
    public void testPlusOverloadOperators() {
        //重载运算符是根据不同基本类型转换为不同方法,运算对象成为方法的参数,在编译过程完成
        //Java唯一的重载运算符
        String s1 = "1";
        String s2 = s1 + "2";
        String s3 = s1 + 3;
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println("循环直接拼接(不推荐)");
        String str = "";
        for (int i = 0, size = 5; i < size; i++) {
            str += i;
        }
        System.out.println(str);

        System.out.println("循环StringBuilder.append");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, size = 5; i < size; i++) {
            stringBuilder.append(i);
        }
        System.out.println(stringBuilder);
    }

    /**
     * 测试null
     */
    public class TipNullString {
        private String s;

        TipNullString(@NotNull String test) {
            s = test;
        }
    }
    @Test
    public void testTipNull() {
        String nullStr = null;
        String s1 = new String(nullStr);
        TipNullString testString = new TipNullString(nullStr);
    }

    /**
     * 测试比较
     */
    @Test
    public void testCompare() {
        System.out.println("equals比较(只比较String)");
        String s1 = "budd12";
        String s2 = s1;
        System.out.println("equals比较：" + (s1.equals(s2)));

        System.out.println("-----------------");
        System.out.println("contentEquals比较(比较CharSequence)");
        String s3 = "budd3";
        String s4 = "budd4";
        StringBuffer s5 = new StringBuffer("budd5");
        Segment s6 = new Segment("budd5".toCharArray(), 0, 13);
        System.out.println("contentEquals(StringBuffer)：" + s3.contentEquals(s5));//同步比较
        System.out.println("contentEquals(String)：" + s3.contentEquals(s4));//类型为String
        System.out.println("contentEquals(Segment)：" + s3.contentEquals(s6));//类型为除String以外的CharSequence

        System.out.println("-----------------");
        System.out.println("equalsIgnoreCase比较(忽略大小写)");
        String s7 = "budd7";
        String s8 = "budd7";
        System.out.println("equalsIgnoreCase(String)：" + s7.equalsIgnoreCase(s8));

        System.out.println("-----------------");
        System.out.println("compare(compareToIgnoreCase)");
        String s9 = "budd9";
        String s10 = "budd10";
        System.out.println("compare(存在字符不同)：" + s9.compareTo(s10));
        String s11 = "budd11";
        String s12 = "budd123";
        System.out.println("compare(字符相同,但长度不一致)：" + s11.compareTo(s12));

        System.out.println("-----------------");
        System.out.println("regionMatches(regionMatches(boolean))");
        String s13 = "budd13";
        String s14 = "Budd14";
        System.out.println("regionMatches(not ignoreCase)：" + s13.regionMatches(0
                , "b", 0, 1));
        System.out.println("regionMatches(not ignoreCase)：" + s13.regionMatches(0
                , "budd14", 0, 6));
        System.out.println("regionMatches(yes ignoreCase)：" + s14.regionMatches(true, 0
                , "B", 0, 1));
    }

    /**
     * 测试替换
     */
    @Test
    public void testReplace() {
        System.out.println("\nreplaceFirst");
        System.out.println("指定正则替换首个字符：" + "budd".replaceFirst("[b]"
                , "老猿b"));

        System.out.println("-----------------");
        System.out.println("replaceAll");
        System.out.println("指定正则替换所有字符：" + "budd".replaceAll("[d]"
                , "bu"));

        System.out.println("-----------------");
        System.out.println("replace");
        System.out.println("指定字符替换字符：" + "budd".replace("b"
                , "老猿b"));
        System.out.println("替换字符串带有特殊字符(通过replace保证转义)：" + "budd".replace("b"
                , "\\b"));
        System.out.println("替换字符串带有特殊字符(没有转义会报错)：" + "budd".replaceAll("b"
                , "\\"));
    }

    /**
     * 测试数组/字符串的长度
     */
    @Test
    public void testLength() {
        System.out.println("\n为什么数组获取长度是length，字符串获取长度是length()");
        int length1 = "budd".length();
        int length2 = new String[]{"budd"}.length;
    }

    /**
     * 测试subString
     */
    public class SubStringMemoryLeak1 {
        private static final int NUMBER_OF_BUFFERS = 15;
        private static final int BUFFER_SIZE = 256 * 1024;
        private static final String DUMMY_TEXT = "Hello world! This is a memory leak!";
        private String longString;
        private int size;

        /**
         * Creates a new instance of SubStringMemoryLeak1
         */
        public SubStringMemoryLeak1() {
            StringBuffer sb = new StringBuffer(BUFFER_SIZE);
            int count = (BUFFER_SIZE / DUMMY_TEXT.length());
            for (int i = 0; i < count; i++) {
                sb.append(DUMMY_TEXT);
                this.size += DUMMY_TEXT.length();
            }
            this.longString = sb.toString();
        }

        public String getSubString() {
            double rand1 = Math.random();
            int begin = (int) Math.round((this.size - 10) * rand1);
            int end = begin + 8;
            return this.longString.substring(begin, end);
        }

        public void test() {
            List subStrings = new ArrayList(NUMBER_OF_BUFFERS);
            for (int i = 0; i < NUMBER_OF_BUFFERS; i++) {

                //This call creates memory leaking
                String subString = getSubString();

                //This call avoids memory leaking
                //String subString = new String(leak.getSubString());

                System.out.println("Extracted substring: " + subString);
                subStrings.add(subString);
            }
            //No release of buffer objects!!
            System.out.println("Keeping the substrings means keeping the whole buffer!");
            for (int i = 0; i < NUMBER_OF_BUFFERS; i++) {
                System.out.println("List of subStrings: " + subStrings);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    System.exit(1);
                }
                System.gc();
            }
            //Releasing substring triggers release of buffer objects!!
            System.out.println("Only releasing the substrings releases the whole buffer!");
            for (int i = 0; i < NUMBER_OF_BUFFERS; i++) {
                System.out.println("List of subStrings: " + subStrings);
                subStrings.remove(0);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    System.exit(1);
                }
                System.gc();
            }
            System.exit(0);
        }
    }

    public class SubStringMemoryLeak2 {

        private static final int BUFFER_SIZE = 10 * 1024 * 1024;

        private static final char DUMMY_CHAR = 'a';

        public void test() {

            //Create dummy char array
            char[] bigCharArray = new char[BUFFER_SIZE];
            Arrays.fill(bigCharArray, DUMMY_CHAR);

            //Create String from char array, release dummy char array
            String longString = new String(bigCharArray);
            bigCharArray = null;

            //Extract first char of long String and release long String
            String shortString = longString.substring(0, 1);
            longString = null;

            //Perform GC and wait for further automatic GCs
            System.gc();
            try {
                Thread.sleep(3000);
                System.gc();
            } catch (InterruptedException ignored) {
            }

            //Memory of longString is not released!!
            System.out.println("Memory of long string is not released!");

            //Release the shot String reference and perform GC
            shortString = null;
            System.gc();

            //Memory of longString will be released!!
            System.out.println("Memory of long string will be released!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("Memory of long string should be released!");
            System.exit(0);
        }

    }

    @Test
    public void testSubString() {
        /**
         * 测试在JDK6：subString造成内存泄露(无法模拟出)
         */
        SubStringMemoryLeak1 memoryLeak1 = new SubStringMemoryLeak1();
        memoryLeak1.test();
        SubStringMemoryLeak2 memoryLeak2 = new SubStringMemoryLeak2();
        memoryLeak2.test();

    }

    /**
     * 测试hashCode
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode：" + "budd".hashCode());
    }

    /**
     * 测试hashCode与equals
     */
    @Getter
    @ToString
    public class HashCodeEqualsClass {

        private String color;

        public HashCodeEqualsClass(String color) {
            this.color = color;
        }

        @Override
        public int hashCode() {
//            return this.color.hashCode();
            //验证是否hashCode和equals同时判断
            return 111;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof HashCodeEqualsClass)) return false;
            if (this == obj) return true;
//            return this.color.equals(((HashCodeEqualsClass) obj).getColor());
            //验证是否hashCode和equals同时判断
            return true;
        }
    }

    @Test
    public void testEqualsHashCode() {
        Map<HashCodeEqualsClass, String> map = new HashMap<>();

        map.put(new HashCodeEqualsClass("blue"), "red");
        map.put(new HashCodeEqualsClass("blue"), "blue");
        System.out.println("存储Map：" + map);
        System.out.println("匹配到的值：" + map.get(new HashCodeEqualsClass("blue")));
    }

    /**
     * 测试switch
     */
    @Test
    public void testSwitch() {
        System.out.println("\nint-switch");
        int i = 1;
        switch (i) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            default:
                break;
        }

        System.out.println("-----------------");
        System.out.println("char-switch");

        char c = '2';
        switch (c) {
            case '2':
                System.out.println(2);
                break;
            default:
                break;
        }

        System.out.println("-----------------");
        System.out.println("String-switch");

        String str = "3";
        switch (str) {
            case "3":
                System.out.println("3");
                break;
            default:
                break;
        }

    }


    /**
     * 测试创建与常量池的影响
     */
    @Test
    public void testCreateRelContact() {
        /**
         * 比较""和""
         */
        String s1 = "hello12";
        String s2 = "hello12";
        System.out.println("比较\"\"和\"\"：" + (s1 == s2));//输出是true

        /**
         * 比较new+""和""
         */
        String s3 = new String("hello34");
        String s4 = "hello34";
        System.out.println("比较new+\"\"和+\"\"：" + (s3 == s4));//输出是false
    }

    /**
     * 测试intern方法
     * intern对创建对象实例和字面量的影响
     */
    @Test
    public void testIntern() {

        /**
         * new.intern()与""比较
         */
        System.out.println("new.intern()与\"\"比较");
        String s1 = new String("he") + "llo12";
        String s1Reference = s1.intern();
        String s2 = "hello12";
        System.out.println("比较new和\"\"：" + (s2 == s1));//输出是true
        System.out.println("比较new的intern引用和+\"\"：" + (s2 == s1Reference));//输出是true

        System.out.println("------------------");

        /**
         * ""与new.intern()比较
         */
        System.out.println("\"\"与new.intern()比较");
        String s3 = "hello345";
        String s4 = new String("he") + "llo345";
        String s4Reference = s4.intern();
        String s5 = "hello345";
        System.out.println("比较\"\"与intern的源对象：" + (s5 == s4));//输出是false
        System.out.println("比较\"\"与intern返回引用：" + (s5 == s4Reference));//输出是true
        System.out.println("比较\"\"与intern前已存在对象：" + (s5 == s3));//输出是true

        System.out.println("------------------");

        /**
         * new与new比较
         */
        System.out.println("new与new比较");
        String s6 = new String("hello678");
        String s7 = new String("hello678");
        String s7Reference = s7.intern();
        String s8 = "hello678";
        System.out.println("比较new.intern()与new(与s2 == s1对比)：" + (s7Reference == s6));//输出是false
        System.out.println("比较new.intern()与new(与s2 == s1对比)：" + (s7Reference == s7));//输出是false
        System.out.println("比较new.intern()与源\"\"(与s7Reference == s7对比)：" + (s7Reference == s8));//输出是true
        System.out.println("比较new与源\"\"(与s7Reference == s8对比)：" + (s6 == s8));//输出是false

        System.out.println("------------------");

        /**
         * 特殊new与new.intern()比较
         */
        System.out.println("特殊new与new.intern()比较");
        String s9 = new String(new char[]{'漠', '然'}, 0, 2);
        System.out.println("比较new String(char[]).intern()与new String(char[])：" + (s9.intern() == s9));//输出是true

        String s10 = new StringBuilder("漠").append("然").toString();
        System.out.println("比较new StringBuilder与源new StringBuilder.intern()：" + (s10.intern() == s10));//输出是false
        System.out.println("比较new StringBuilder与源\"\"：" + (s10.intern() == s9));//输出是true

    }

    /**
     * 测试jdk7-Path/Files写入/读取码点
     *
     * @throws IOException
     */
    @Test
    public void testFilesBasicCodePoint() throws IOException, InterruptedException {
        Path plusCharPath = Paths.get(BASIC_CODEPOINT_PATH);
        Files.createFile(plusCharPath);
        try {
            char[] codePointChars = makeBasicCodepoint(null);

            List<String> codePointCharList = new ArrayList<>();
            for (char singleChar : codePointChars) {
                codePointCharList.add(String.valueOf(singleChar));
            }

            Files.write(plusCharPath, codePointCharList, Charset.forName("utf-16"), StandardOpenOption.WRITE);
            byte[] bytes = Files.readAllBytes(plusCharPath);
            System.out.println(new String(bytes));

        } finally {
            //还原
            Thread.sleep(5000);
            Files.delete(plusCharPath);
        }

    }

    /**
     * 测试字节写入/读取码点
     */
    @Test
    public void testStreamBasicCodePoint() throws IOException, InterruptedException {
        char[] codePointChars = makeBasicCodepoint(null);

        FileWriter out = new FileWriter(BASIC_CODEPOINT_PATH);
        out.write(codePointChars);
        out.close();
        System.out.print("\n***********************\n");
        FileReader in = new FileReader(BASIC_CODEPOINT_PATH);
        int c;
        /**
         *对比结果发现非代理范围的字符可以正常写入与读出,但是来自高代理与低代理范围的
         *字符无法正常写入，而是被转化为0x3f
         */
        while ((c = in.read()) != -1) {
            System.out.print(Integer.toHexString(c) + "\n");//为什么是3f?
        }
        in.close();


        //还原
        Thread.sleep(5000);
        Path plusCharPath = Paths.get(BASIC_CODEPOINT_PATH);
        Files.delete(plusCharPath);

    }

    /**
     * 制作码点组
     *
     * @return
     */
    private char[] makeBasicCodepoint(int[] codePointsIn) {
        int[] codePoints = null;
        if (codePointsIn == null)
            codePoints = new int[]{0xd801, 0xd802, 0xdf00, 0xdf01, 0x34};
        else codePoints = codePointsIn;

        String codePointStr = new String(codePoints, 0, codePoints.length);
        char[] codePointChars = codePointStr.toCharArray();

        for (char singleChar : codePointChars) {
            System.out.println(String.format("字符=%s,16进制=%s", singleChar, Integer.toHexString(singleChar)));
        }
        return codePointChars;
    }

    /**
     * 测试增补字符
     *
     * @throws IOException
     */
    @Test
    public void testSupplementChar() throws IOException, InterruptedException {
        int[] supplementCodePoints = {0x100001, 0x100002}; //增补字符
        String supplementStrs = new String(supplementCodePoints, 0, supplementCodePoints.length);

        System.out.print("\n***********************\n");
        System.out.println(String.format("%s=%s", "字符串", supplementStrs));
        System.out.println(String.format("%s=%s", "长度", supplementStrs.length()));//增补字符采用两个字节(高代理和低代理),所以总长度是4
        System.out.println(String.format("%s=%s", "单个增补字符(charAt)", Integer.toHexString(supplementStrs.charAt(0))));//高代理字符
        System.out.println(String.format("%s=%s", "单个增补字符(charAt)", Integer.toHexString(supplementStrs.charAt(1))));//低代理字符
        System.out.println(String.format("%s=%s", "单个增补字符(charAt)", Integer.toHexString(supplementStrs.charAt(2))));//高代理字符
        System.out.println(String.format("%s=%s", "单个增补字符(charAt)", Integer.toHexString(supplementStrs.charAt(3))));//低代理字符
        System.out.println(String.format("%s=%s", "单个增补字符(codePointAt)", Integer.toHexString(supplementStrs.codePointAt(0))));

        System.out.print("\n***********************\n");
        Path supplementPath = Paths.get(SUPPLEMENT_CHAR_PATH);
        byte[] supplementBytes = supplementStrs.getBytes("utf-32");
        Files.write(supplementPath, supplementBytes);

        InputStream supplementCIs = Files.newInputStream(supplementPath);
        DataInputStream supplementDis = new DataInputStream(supplementCIs);

        int readSize = supplementDis.available() / 4;

        for (int i = 0; i < readSize; i++) {
            int supplementCodePoint = supplementDis.readInt();
            if (Character.isSupplementaryCodePoint(supplementCodePoint))
                System.out.println(String.format("%s是增补字符", supplementCodePoint));
            else System.out.println(String.format("%s不是增补字符", supplementCodePoint));
        }

        //还原
        Thread.sleep(5000);
        Files.delete(supplementPath);
    }

    /**
     * 测试好玩的码点
     */
    @Test
    public void testFunCodePoint() {
        makeBasicCodepoint(new int[]{0x0B8A});
        System.out.println("\u0B8A");
        System.out.println("ஊ");
    }

}
