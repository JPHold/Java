package com.budd.java.jdkBasic.file;

import static com.budd.java.util.Print.*;

import org.junit.Test;

/**
 * @author budd
 * @desc 路径的入门研究
 * @since 2020/11/14 17:50
 **/
public class HelloPathTest {

    private static void showUserDir() {
        print(System.getProperty("user.dir"));
    }

    /**
     * 指定相对路径
     */
    @Test
    public void testAppointRelativePath() {
        System.setProperty("user.dir", "F:\\2019\\study\\Java\\JdkBasic");
        showUserDir();
    }

    /**
     * 没有指定，如果使用idea执行则由Working directory配置
     */
    @Test
    public void testRelativePath() {
        showUserDir();
    }

    /**
     * 没有指定，如果使用idea执行则由Working directory配置
     */
    public static void main(String[] args) {
        showUserDir();
    }
}
