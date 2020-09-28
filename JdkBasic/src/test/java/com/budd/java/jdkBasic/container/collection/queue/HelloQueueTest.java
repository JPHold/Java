package com.budd.java.jdkBasic.container.collection.queue;

import org.junit.Test;

import static com.budd.java.util.Print.*;

import java.util.*;

/**
 * @author budd
 * @desc 入门队列研究
 * @since 2020/9/27 22:41
 **/
public class HelloQueueTest {

    /**
     * 测试优先级队列
     */
    @Test
    public void testPriorityQueue() {

        print("数字优先级队列-offer插入元素");
        PriorityQueue<Integer> priorityQueue =
                new PriorityQueue<>();
        StringBuilder stringBuilder = new StringBuilder();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++) {
            int randomInt = rand.nextInt(i + 10);
            stringBuilder.append(String.format(", %s", randomInt));
            priorityQueue.offer(randomInt);
        }

        /**
         * 通过观察生成的数字数组和toString的输出，发现在插入时offer():有个初步的排序
         */
        printf("生成的数字数组：[%s]", stringBuilder.delete(0, 2));//[8, 1, 1, 1, 5, 14, 3, 1, 0, 1]
        printf("使用toString(原理是迭代器遍历)输出优先级队列的内容：%s", priorityQueue);//[0, 1, 1, 1, 1, 14, 3, 8, 1, 5]

        /**
         * PriorityQueue的toString是继承于java.util.AbstractCollection
         * ，原理是使用遍历迭代器来实现打印内容，但发现打印顺序是并不是按自然排序排列
         *
         */
        stringBuilder = new StringBuilder();
        Iterator<Integer> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            Integer randomInt = iterator.next();
            stringBuilder.append(String.format(", %s", randomInt));
        }
        printf("使用迭代器输出优先级队列的内容：[%s]", stringBuilder.delete(0, 2));//[0, 1, 1, 1, 1, 14, 3, 8, 1, 5]

        poolPriorityQueue(priorityQueue, "使用poll(按自然顺序输出)输出优先级队列的内容：[%s]");

        print();

        print("数字优先级队列-构造器初始化元素");
        List<Integer> ints = Arrays.asList(25, 22, 20,
                18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<>(ints);

        poolPriorityQueue(priorityQueue,"自然排序(小值优先)的优先级队列：%s");

        printf("%n数字优先级队列-addAll初始化元素+倒序比较器");
        priorityQueue = new PriorityQueue<>(
                ints.size(), Collections.reverseOrder());
        priorityQueue.addAll(ints);
        poolPriorityQueue(priorityQueue,"倒序(大值优先)的优先级队列：%s");

        printf("%n字母优先级队列");
        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings =
                Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ =
                new PriorityQueue<>(strings);
        poolPriorityQueue(stringPQ,"自然排序(小值优先)的优先级队列：%s");

        stringPQ = new PriorityQueue<>(
                strings.size(), Collections.reverseOrder());
        stringPQ.addAll(strings);
        poolPriorityQueue(stringPQ,"自然排序(大值优先)的优先级队列：%s");
    }

    /**
     * 弹出队列并打印内容
     * @param priorityQueue
     * @param printFormat
     */
    private void poolPriorityQueue(PriorityQueue priorityQueue, String printFormat) {
        StringBuilder stringBuilder = new StringBuilder();
        Object poll = null;
        while ((poll = priorityQueue.poll()) != null) {
            stringBuilder.append(String.format(", %s", poll));
        }

        printf(printFormat, stringBuilder.delete(0, 2));
    }

}
