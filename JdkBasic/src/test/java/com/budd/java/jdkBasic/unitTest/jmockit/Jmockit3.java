package com.budd.java.jdkBasic.unitTest.jmockit;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

/**
 * Jmockit的进阶
 * @since 2019年2月25日22:02:21
 * @author budd
 */
public class Jmockit3 {

    @Tested
    GuessChld guessChld =new GuessChld(3);
    @Injectable
    GuessDAO guessDAO;

    /**
     * 測試继承方法(待研究)
     * @param guessParent
     */
    @Test
    public void testExtendMethod(@Mocked(value = {"tryIt"}) final GuessParent guessParent){
        new Expectations(guessChld){
            {
                guessParent.tryIt();
                result=true;
            }
        };
        guessChld.doit();
    }
}

class GuessParent{
    public boolean tryIt(){
        return true;
    }
}

class GuessChld extends GuessParent {
    private int maxTryTime;                         // 最大重试次数
    private int tryTime = 0;                        // 当前重试次数
    private int number = (int) (Math.random() * 6); // 目标数字
    private GuessDAO guessDAO;                      // 持久化依赖

    public GuessChld(int maxRetryTime) {
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

    @Override
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

