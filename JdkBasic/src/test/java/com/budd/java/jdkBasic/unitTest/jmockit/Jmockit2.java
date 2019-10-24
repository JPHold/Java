package com.budd.java.jdkBasic.unitTest.jmockit;

import mockit.*;
import org.junit.Test;

import java.util.HashMap;

/**
 * 单元测试mock框架-jmockit-例子
 * https://blog.csdn.net/biren_wang/article/details/80028014
 *
 * @since 2019年2月21日22:30:02
 */
public class Jmockit2 {

    /**
     * 总结：
     *
     * @Tested 判断对象是否为null, 为null则创建实例
     * @Injectable 依赖注入到@Tested类实例中,这种依赖注入有两种情况：
     * 1、构造器注入
     * 直接注入到@Tested类实例中,并且可以通过@Mocked指定mock规则
     * 2、方法注入(set方法)
     * 直接注入到@Tested类实例中,并且可以通过@Mocked指定mock规则
     * 3、无构造器、无方法
     * 自动创建依赖对象实例,该实例的行为遵循默认的mock规则(静态方法和构造方法不会被mock掉,其他一律mock掉)
     * <p>
     * <p>
     * Expectation中声明的成员变量默认带有@Mocked
     * Expectation中不能存在@Mocked对象,需要写在@Test方法参数上：
     * JMockit: loaded external tool mockit.coverage.CodeCoverageWARNING:
     * * Local mock field "g" should be moved to the test class or converted to a parameter of the test method
     * 被测试的类如果写在Expectation构造器参数上(xxxClass.class或xxxClassInstance xxx).那么Expectation对象块{}中使用的被测试类的方法
     * 都被mock掉
     *
     * MockUp的泛型参数必须是Interface接口类型,不然getMockInstance为null
     */
    @Tested // 表明被修饰实例是将会被自动构建和注入的实例
            Guess guess = new Guess(3);
    @Injectable// 表明被修饰实例将会自动注入到@Tested修饰的实例中，并且会自动mock掉，除非在测试前被赋值
            GuessDAO guessDAO;

    /**
     * https://blog.csdn.net/biren_wang/article/details/80028014
     * <p>
     * 3次都猜错
     *
     * @param g
     */
    @Test
    public void testFail3Times(final @Mocked(value = {"tryIt"}) Guess g) {
        /**
         * 1、1和2步骤是屏蔽tryInt方法的逻辑,使其结果都是false
         * 2、调用1和2步骤三次
         * 3、3次都猜错,调用4步骤记录错误信息
         */
        new Expectations() {
            {
                g.tryIt();//1
                result = false;//2
                times = 3;//3
                guessDAO.saveResult(false, anyInt);//4
            }
        };
        guess.doit();//5
    }

    /**
     * 3次猜测,第3次猜对
     */
    @Test
    public void testSuccess3Times() {

        /**
         * //4,5,6步骤是通过修改number强行使得第3次猜测成功
         * 第5个步骤必须写上,number值才真正生效
         */
        new Expectations(Guess.class) {
            {
                guess.tryIt();//1
                result = false;//2
                times = 2;//3
                Deencapsulation.invoke(guess, "randomGuess", new Object[]{});//4
                result = Deencapsulation.getField(guess, "number");//5
                guessDAO.saveResult(true, (Integer) Deencapsulation.getField(guess, "number"));//6
            }
        };
        guess.doit();//5
    }

    /**
     * 黑盒測試,模拟GuessDAO-saveResult逻辑
     * 需要注意的是：每次循环必须初始化1、2步骤,不能写在以外的地方,不然MockUp模拟的逻辑只会生效一次
     */
    @Test
    public void testFunctionRandom() {

        //记录猜测成功/失败数
        final HashMap<String,Integer> resultCountMap = new HashMap();
        resultCountMap.put("0",0);
        resultCountMap.put("1",0);
         MockUp<GuessDAO> mockUp = new MockUp<GuessDAO>(){
             @Mock
             public void saveResult(boolean resultSign, int guessNumber) {
                 if (!resultSign) {
                     resultCountMap.put("0", resultCountMap.get("0") + 1);
                 } else {
                     resultCountMap.put("1", resultCountMap.get("1") + 1);
                 }
             }
         };
        GuessDAO mockInstance = mockUp.getMockInstance();

        for(int i=0,count=10000;i<count;i++){
            Guess guess=new Guess(3);//1
            guess.setGuessDAO(mockInstance);//2
            guess.doit();
        }
        System.out.println("\n失败猜测数:"+resultCountMap.get("0"));
        System.out.println("成功猜测数:"+resultCountMap.get("1"));
        double successRate=(double) (resultCountMap.get("1"))/(resultCountMap.get("0")+resultCountMap.get("1"));
        System.out.println("成功率:"+successRate);
    }

}

interface GuessDAO {
    public void saveResult(boolean resultSign, int guessNumber);

}

class Guess {
    private int maxTryTime;                         // 最大重试次数
    private int tryTime = 0;                        // 当前重试次数
    private int number = (int) (Math.random() * 6); // 目标数字
    private GuessDAO guessDAO;                      // 持久化依赖

    public Guess(int maxRetryTime) {
        this.maxTryTime = maxRetryTime;
    }

    public void doit() {
        while (tryTime++ < maxTryTime && !tryIt()) {
            // 到达最大尝试次数仍不成功则调用handle
            if (tryTime == maxTryTime) {
                failHandle();
            }
        }
    }

    public boolean tryIt() {                        // 最坏情况下调用maxRetryTime次tryIt(),猜中则保存信息
        if (number == randomGuess()) {
            guessDAO.saveResult(true, number);
            return true;
        }
        return false;
    }

    public void failHandle() {                      // 失败处理，猜不中时调用
        guessDAO.saveResult(false, number);
    }

    private int randomGuess() {                      // 猜的随机过程
        return (int) (Math.random() * 6);
    }

    public GuessDAO getGuessDAO() {
        return guessDAO;
    }

    public void setGuessDAO(GuessDAO guessDAO) {
        this.guessDAO = guessDAO;
    }
}
