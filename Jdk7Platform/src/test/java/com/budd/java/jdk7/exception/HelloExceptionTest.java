package com.budd.java.jdk7.exception;

import com.budd.java.jdk7.exception.trywithresources.*;

import static com.budd.java.util.Print.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
     * ，在java7及之后，采用try(xxx代码){}来实现自动关闭流。通过用分号号分隔的方式，用一个try语句来打开多个资源
     * @since 2020年4月13日 22:43:55
     */
    String oldProcessClose(String path) {
        BufferedReader br = null;
        String result = null;
        try {
            br = new BufferedReader(new FileReader(path));
            try {
                result = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    String newProcessClose(String path) {
        String result = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            result = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 深入了解try-with-resources的使用
     *
     * @Date 2020年10月26日 22:31:19
     * 观察【1】
     * Creating First
     * Creating Second
     * Closing Second
     * Closing First
     * close的顺序，跟c++的析构函数一样，按创建顺序的倒序清理
     * <p>
     * 观察【2】
     * 资源规范头内必须实现AutoCloseable接口，不然编译报错：
     * Required type:
     * AutoCloseable
     * Provided:
     * NoAutoCloseableImpl
     * <p>
     * 观察【3】
     * 期间报错的类未完成初始化，因此该类不会调用清理
     * 因为前一个类报错，后面的类自都不会被执行，自然没有创建和清理
     */
    @Test
    public void testUseAutoCloseable() {

        //【1】
        print("测试清理顺序");
        try (
                AutoCloseableFirstImpl first = new AutoCloseableFirstImpl();
                AutoCloseableSecondImpl second = new AutoCloseableSecondImpl()
        ) {

        }

        //【2】
        /*try (
                NoAutoCloseableImpl noAutoCloseable = new NoAutoCloseableImpl();
        ) {
        }*/

        //【3】
        printf("%n测试其中某个类报错的清理情况");
        try (
                AutoCloseableFirstImpl first = new AutoCloseableFirstImpl();
                AutoCloseableExceptionImpl autoCloseableException = new AutoCloseableExceptionImpl();
                AutoCloseableSecondImpl second = new AutoCloseableSecondImpl();

        ) {

        } catch (AutoCloseableException e) {
            e.printStackTrace();
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
    public void testStateExtendThrowBasic() throws DerivedException, DerivedException1 {
        try {
            throw new DerivedException();
        } catch (BaseException e) {
            throw e;
        }
    }
    // end:异常多态，是否支持声明派生类异常却抛出基类异常
}
