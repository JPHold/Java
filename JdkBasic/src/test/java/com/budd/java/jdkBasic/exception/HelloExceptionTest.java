package com.budd.java.jdkBasic.exception;

import com.budd.java.jdkBasic.exception.implThrows.*;
import org.junit.Test;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static com.budd.java.util.Print.print;
import static com.budd.java.util.Print.printf;

/**
 * 入门异常研究
 *
 * @author budd
 * @since 2020/2/13 21:47
 **/
public class HelloExceptionTest {

    /**
     * start:API测试
     */
    /**
     * start:getStackTrace
     *
     * @author HJP
     * @date 2018年3月9日 下午5:19:54
     * @Description 获取方法调用链上所有方法的名称getStackTrace()
     */
    private class GetStackTraceClass {
        private void a() {
            try {
                throw new Exception();
            } catch (Exception e) {
                for (StackTraceElement se : e.getStackTrace()) {
                    print(se.getMethodName());
                }
            }
        }

        private void b() {
            a();
        }

        private void c() {
            b();
        }
    }

    @Test
    public void getStackTrace() {
        GetStackTraceClass getStackTraceClass = new GetStackTraceClass();

        printf("%n异常,方法调用链上所有方法的名称");
        printf("%n一层调用");
        getStackTraceClass.a();
        printf("%n二层调用");
        getStackTraceClass.b();
        printf("%n三层调用");
        getStackTraceClass.c();
    }
    // end:getStackTrace

    /**
     * start:printStackTrace
     *
     * @return 打印异常栈printStackTrace()
     * @author HJP
     * @date 2018年3月9日 %下午5:21:23
     * @Description
     */
    @Test
    public void printStackTrace() {

        printf("%n打印异常栈");
        print("以普通信息打印");
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        printf("%n以错误信息打印");
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    // end:printStackTrace

    /**
     * start:fillStackTrace
     *
     * @author HJP
     * @return 将当前线程，当前状态的轨迹线状态保存到Throwabe中。最外层打印的异常栈只到b，不会到a
     * @date 2018年3月10日 下午9:06:10
     * @Description
     */
    private class FillStackTraceClass {
        private void fillA() throws Exception {
            throw new Exception("出问题啦！");
        }

        private void fillB() throws Exception {
            try {
                fillA();
            } catch (Exception e) {
                printf("%nfillInStackTrace()之前");
                e.printStackTrace();
                throw (Exception) e.fillInStackTrace();
            }

        }
    }

    @Test
    public void fillStackTrace() {
        try {
            FillStackTraceClass fillStackTrace = new FillStackTraceClass();
            fillStackTrace.fillB();
        } catch (Exception e) {
            printf("%nfillInStackTrace()之后");
            e.printStackTrace();
        }
    }
    // end:fillStackTrace

    /**
     * start:initCause
     *
     * @author HJP
     * @return
     * @date 2018年3月10日 %下午9:17:06
     * @Description 在fillStackTrace测试方法中，会丢失内层具体异常a。可以通过将a作为cause置入携带(以case by展示)
     */
    class InitCauseClass {
        public void initCauseA() throws Exception {
            throw new Exception("出问题啦！");
        }

        public void initCauseB() throws Exception {
            try {
                initCauseA();
            } catch (Exception e) {
                RuntimeException e2 = new RuntimeException();
                e2.initCause(e);
                throw e2;
            }
        }

        public void newCauseC() throws Exception {
            try {
                initCauseA();
            } catch (Exception e) {
                RuntimeException e2 = new RuntimeException(e);
                throw e2;
            }
        }
    }

    @Test
    public void initCause() {
        InitCauseClass initCauseClass = new InitCauseClass();
        printf("%ncause by置入携带");
        try {
            printf("%ninitCause(Throwable)方式");
            initCauseClass.initCauseB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
            printf("%nnew Exception(Throwable)方式");
            initCauseClass.newCauseC();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // end:initCause

    /**
     * start:特例RunTimeException
     *
     * @return
     * @author HJP
     * @date 2020年10月22日 22:52:58
     * @Description 抛出RunTimeException，无须捕获和在方法上声明
     */
    @Test
    public void testNoThrowsRuntimeException() {
        throw new RuntimeException();
    }

    // end:特例RunTimeException

    /**
     * start:getMessage和getLocalizedMessage
     *
     * @author HJP
     * @return
     * @date 2020年2月17日 21:55:28
     * @Description 前者默认是调用后者，如果要实现国际化(将输出消息翻译成当前语言)，就需要重写前者：
     */
    class LocalizedMessageException extends Exception {
        private static final long serialVersionUID = 1L;

        ResourceBundle labels = ResourceBundle.getBundle("properties文件路径");

        @Override
        public String getMessage() {
            return "错误简单描述：" + super.getMessage();
        }

        @Override
        public String getLocalizedMessage() {
            return labels.getString(getMessage());
        }
    }

    @Test
    public void getLocalizedMessage() {
        try {
            throw new LocalizedMessageException();
        } catch (Exception e) {
            String localizedMessage = e.getLocalizedMessage();
            print(localizedMessage);
        }
    }
    // end:getMessage和getLocalizedMessage
    /**
     * end:API测试
     */

    /**
     * start:异常多态，是否支持声明派生类异常却抛出基类异常
     *
     * @return
     * @author HJP
     * @date 2020年10月21日 21:53:50
     * @Description 在JAVA7以下，会报错：Unhandled exception: com.budd.java.jdkBasic.exception.BaseException
     */
    @Test
    public void testStateExtendThrowBasic() throws DerivedException, DerivedException1 {
        try {
            throw new DerivedException();
        } catch (BaseException e) {
//            throw e;
        }
    }
    // end:异常多态，是否支持声明派生类异常却抛出基类异常

    /**
     * 基础实操
     */
    /**
     * ** ******* ******* * ******** * * * * ******** * /**
     *
     * @author HJP
     * @date 2018年3月10日 上午11:36:42
     * @Description 非恢复模型(抛出异常, 立即退出)
     */
    @Test
    public void unRecoverModel() {
        int count = 5;
        for (int i = count; i > 0; i--) {
            try {
                if (i == 1) {
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * ** ******* ******* * ******** * * * * ******** * /**
     *
     * @author HJP
     * @date 2018年3月10日 上午11:36:42
     * @Description 恢复模型(即使抛出异常, 依然继续跑)
     */
    @Test
    public void recoverModel() {

        int count = 5;
        while (true) {
            try {
                if (count-- < 0) {
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ** ******* ******* * ******** * * * * ******** * /**
     *
     * @author HJP
     * @date 2018年3月10日 下午12:14:36
     * @Description 日志打印
     */
    class LogExceptionClass {
        Logger lg = Logger.getLogger("LoggerException");

        public void log(Exception e) {
            LogRecord logRecord = new LogRecord(Level.INFO, e.getMessage());
            logRecord.setThrown(e);
            lg.log(logRecord);
            lg.info(e.getMessage());
        }
    }

    @Test
    public void logException() {
        LogExceptionClass logExceptionClass = new LogExceptionClass();
        try {
            throw new NullPointerException("空指针");
        } catch (Exception e) {
            logExceptionClass.log(e);
        }
    }

    /**
     * ** ******* ******* * ******** * * * * ******** * /**
     *
     * @author HJP
     * @date 2018年3月11日 上午9:58:24
     * @Description 方法带异常的继承和实现测试
     */
    @Test
    public void testExtendException() {

        StormyInning si = null;
        try {
            si = new StormyInning();
        } catch (RainedOut e) {
            print("Rained out");
        } catch (BaseballException e) {
            print("Generic baseball exception");
        }

        //【7】当派生类引用调用方法时，catch的异常时是派生类预定义的异常
        try {
            si.atBat();
        } catch (PopFoul popFoul) {
            print("Pop foul");
        }

        Inning i = null;
        try {
            i = new StormyInning();
        } catch (RainedOut e) {
            print("Rained out");
        } catch (BaseballException e) {
            print("Generic baseball exception");
        }

        //【7】当基类引用调用方法时，catch的异常时是基类/接口预定义的异常
        try {
            i.atBat();
        } catch (Strike strike) {
            print("Strike");
        } catch (Foul foul) {
            print("Foul");
        }
    }


}
