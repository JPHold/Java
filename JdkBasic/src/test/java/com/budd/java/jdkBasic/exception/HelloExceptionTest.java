package com.budd.java.jdkBasic.exception;

import org.junit.Test;

/**
 * 入门异常研究
 *
 * @author budd
 * @since 2020/2/13 21:47
 **/
public class HelloExceptionTest {

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
                    System.out.println(se.getMethodName());
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

        System.out.println("\n异常,方法调用链上所有方法的名称");
        System.out.println("\n一层调用");
        getStackTraceClass.a();
        System.out.println("\n二层调用");
        getStackTraceClass.b();
        System.out.println("\n三层调用");
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

        System.out.println("\n打印异常栈");
        System.out.println("以普通信息打印");
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        System.out.println("\n以错误信息打印");
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
                System.out.println("\nfillInStackTrace()之前");
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
            System.out.println("\nfillInStackTrace()之后");
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
        System.out.println("\ncause by置入携带");
        try {
            System.out.println("\ninitCause(Throwable)方式");
            initCauseClass.initCauseB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
            System.out.println("\nnew Exception(Throwable)方式");
            initCauseClass.newCauseC();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // end:initCause

}
