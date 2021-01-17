package com.budd.java.jdkBasic.regex;

import com.budd.java.jdkBasic.regex.replace.AbstractReplaceCallBack;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author budd
 * @desc 正则表达式的进阶研究
 * @since 2021/1/17 11:11
 **/
public class ProRegularExpressionTest {

    /**
     * 测试自定义替换
     * https://blog.csdn.net/weixin_33834075/article/details/91599852
     */
    @Test
    public void testAppendReplacement() {
        String input = "This unusual use!!of exclamation!!points";
        String regex = "\\b(\\w)(\\w*?)\\b(?=!!)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        StringBuffer sbuf = new StringBuffer();

        //实现自定义替换的方法
        AbstractReplaceCallBack abstractReplaceCallBack = new AbstractReplaceCallBack() {
            @Override
            public String doReplace(String text, int index, Matcher matcher) {
                return $(1).toUpperCase() + $(2);
            }
        };

        int index = 0;
        while (matcher.find()) {
            matcher.appendReplacement(sbuf, abstractReplaceCallBack.replace(matcher.group(), index++, matcher));//等同于下面
//            matcher.appendReplacement(sbuf, matcher.group(1).toUpperCase() + matcher.group(2));
        }

        System.out.printf("不加appendTail= %s%n", sbuf);

        matcher.appendTail(sbuf);
        System.out.printf("加appendTail= %s%n", sbuf);
    }


}
