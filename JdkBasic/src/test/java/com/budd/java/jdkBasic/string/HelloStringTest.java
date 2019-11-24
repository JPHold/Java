package com.budd.java.jdkBasic.string;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println(String.format("未指定转换格式:%s", b0));
        System.out.println(String.format("指定转换格式(utf-8):%s", b0));

        /**
         * byte[]转string
         */
        System.out.println("-----------byte[]转string");
        String s0 = new String(b0);
        String s1 = new String(b0, Charset.forName("utf-8"));

        System.out.println(String.format("未指定转换格式:%s", s0));
        System.out.println(String.format("指定转换格式(utf-8):%s", s1));

    }


    public class TipNullString {
        private String s;

        TipNullString(@NotNull String test) {
            s = test;
        }
    }

    /**
     * 测试null
     */
    @Test
    public void testTipNull() {
        String nullStr = null;
        String s1 = new String(nullStr);
        TipNullString testString = new TipNullString(nullStr);
    }

    /**
     * 测试jdk7-Path/Files写入/读取码点
     *
     * @throws IOException
     */
    @Test
    public void testFilesBasicCodePoint() throws IOException {
        char[] codePointChars = makeBasicCodepoint(null);

        List<String> codePointCharList = new ArrayList<>();
        for (char singleChar : codePointChars) {
            codePointCharList.add(String.valueOf(singleChar));
        }

        Path plusCharPath = Paths.get(BASIC_CODEPOINT_PATH);
        Files.write(plusCharPath, codePointCharList, Charset.forName("utf-16"), StandardOpenOption.WRITE);
        byte[] bytes = Files.readAllBytes(plusCharPath);
        System.out.println(new String(bytes));
    }

    /**
     * 测试字节写入/读取码点
     */
    @Test
    public void testStreamBasicCodePoint() throws IOException {
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
    public void testSupplementChar() throws IOException {
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
