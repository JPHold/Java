package com.budd.java.jdkBasic.container.collection.set;

import static com.budd.java.util.Print.*;

import com.budd.java.jdkBasic.container.set.TextFile;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

/**
 * @author budd
 * @desc Set进阶研究
 * @since 2020/2/27 13:29
 **/
public class ProSetTest {

    /**
     * 利用set的不重复元素的特性，查找出文件内容的单词
     * headSet的原理是treeMap的key比较：
     * 按照字典顺序比较，找到比toElement低的元素集
     */
    @Test
    public void findWords() {
        TreeSet<String> set = new TreeSet<>(
                new TextFile("src/test/java/com/budd/java/jdkBasic/container/collection/set/HelloSetTest.java", "\\W+"));
        print(String.format("源集合：%s", set));
        print(String.format("高点集合：%s", set.headSet("Ba")));
    }
}
