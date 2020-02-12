package com.budd.java.jdkBasic.container.collection.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * List入门研究
 *
 * @author budd
 * @since 2020/2/12 20:54
 **/
public class HelloListTest {

    /**
     * API测试
     */
    @Test
    public void testShuffle() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println("\n测试shuffle随机洗牌");
        for (int i = 1; i <= 10; i++) {
            Collections.shuffle(list);
            System.out.println(String.format("第%s洗牌,结果:%s", i, list));
        }
    }
}
