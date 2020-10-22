package com.budd.java.jdk7.exception;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author budd
 * @desc 异常入门研究
 * @since 2020/4/13 22:41
 **/
public class HelloExceptionTest {

    /**
     * @author budd
     * @desc java6之前，对于文件打开阅读的异常处理是try{}finally{}来处理
     * ，在java7及之后，采用try(xxx代码){}来实现自动关闭流。通过用逗号分隔的方式，用一个try语句来打开多个资源
     * @since 2020年4月13日 22:43:55
     */
    String oldProcessClose(String path)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            if (br != null) br.close();
        }
    }

    String newProcessClose(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    @Test
    public void testTryFinallyProcessClose() throws IOException {
        oldProcessClose("111");
        newProcessClose("111");
    }

    /**
     * start:异常多态，是否支持声明派生类异常却抛出基类异常
     *
     * @return
     * @author HJP
     * @date 2020年10月21日 21:53:50
     * @Description
     */

    @Test
    public void testStateExtendThrowBasic() throws DerivedException,DerivedException1{
        try {
            throw new DerivedException();
        } catch (BaseException e) {
            throw e;
        }
    }
    // end:异常多态，是否支持声明派生类异常却抛出基类异常
}
