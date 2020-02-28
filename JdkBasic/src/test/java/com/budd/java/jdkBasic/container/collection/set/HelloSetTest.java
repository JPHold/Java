package com.budd.java.jdkBasic.container.collection.set;


import static com.budd.java.util.Print.*;

import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.Pet;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.cat.Cat;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.dog.Pug;
import org.junit.Test;

import java.util.*;

/**
 * @author budd
 * @desc Set入门研究
 * @since 2020/2/26 20:30
 **/
public class HelloSetTest {

    private Set<String> createSet() {
        Set<String> set = new HashSet<String>();
        Collections.addAll(set, "A B C D E F G H I J K L".split(" "));
        return set;
    }

    /**
     * 测试添加元素
     * add(element)
     * addAll(Collection<element>)
     * set是不重复的集合(只是针对基础类型)。其它自定义类型，与List无差，都是要根据hashCode()
     */
    @Test
    public void testAdd() {
        print("添加基础类型");
        Set<String> basicTypeSet = createSet();
        print("添加前：" + basicTypeSet);
        basicTypeSet.add("M");
        print("添加后：" + basicTypeSet);
        basicTypeSet.addAll(Arrays.asList("H", "I", "J", "K", "L"));
        print("重复添加后：" + basicTypeSet);

        print("\n添加自定义类型");
        Set<Pet> customTypeSet = new HashSet<Pet>(Arrays.asList(new Pug()));
        print("添加前：" + customTypeSet);
        customTypeSet.add(new Cat());
        print("添加后：" + customTypeSet);
        customTypeSet.add(new Cat());
        print("重复添加后：" + customTypeSet);
    }

    /**
     * 测试是否完整包含元素
     * contains
     * containsAll()
     */
    @Test
    public void testContains() {
        Set<String> basicTypeSet = createSet();
        basicTypeSet.add("M");
        print("H: " + basicTypeSet.contains("H"));
        print("N: " + basicTypeSet.contains("N"));
        print("M+N：" + basicTypeSet.containsAll(Arrays.asList("M", "N")));
    }

    /**
     * 测试移除
     * remove(element)
     * removeAll(Collection<element>)
     */
    @Test
    public void testRemove() {
        Set<String> basicTypeSet = createSet();
        print(String.format("移除元素：%s,集合：%s", basicTypeSet.remove("H"), basicTypeSet));
        print(String.format("移除元素集：%s,集合：%s", basicTypeSet.removeAll(Arrays.asList("L", "M")), basicTypeSet));
    }

}
