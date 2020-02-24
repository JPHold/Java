package com.budd.java.jdkBasic.container.collection.list;

import com.budd.java.jdkBasic.container.collection.list.pets.Pets;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.Manx.Cymric;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.Pet;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.dog.Pug;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.rodent.Mouse;
import org.junit.Test;

import java.util.*;

import static com.budd.java.util.Print.print;

/**
 * List入门研究
 *
 * @author budd
 * @since 2020/2/12 20:54
 **/
public class HelloListTest {

    /**
     * API测试
     * TODO 对元素洗牌打乱，并测试对containsAll是否有影响(没有影响)
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

    /**
     * 测试lsit.add是否替换存在的元素(是否有调用hashCode)
     * 指定位置插入元素
     * 在指定位置之前插入集合
     */
    @Test
    public void testAdd() {

    }

    /**
     * 测试比较的原理，是根据equals方法
     */
    @Test
    public void testContains() {

    }

    /**
     * 查找元素所在位置，indexOf
     * 测试查找不存在元素(返回-1)。remove返回值是true/false(true为找到并删除，false为找不到)
     */
    @Test
    public void testIndexOf() {

    }

    /**
     * 测试删除已存在元素
     * 移除某个元素集合(被移除集合有多个相同元素，只会按顺序移除，而不是全部移除)
     */
    @Test
    public void testRemove() {

    }

    /**
     * 截取某两个位置内的元素(左闭右开)
     */
    @Test
    public void testSub() {

    }

    /**
     * 比对list
     * 测试排序后，对containsAll是否有影响(没有影响)
     */
    @Test
    public void testContainsAll() {

    }

    /**
     * 取交集
     */
    @Test
    public void testRetain() {

    }

    /**
     * 替换指定位置
     */
    @Test
    public void testSet() {

    }

    /**
     * 清空集合
     */
    @Test
    public void testClear() {

    }

    /**
     * 集合转数组，toArray()和toArray(T[])，前者是返回object，后者是返回具体类型
     */
    @Test
    public void testToArray() {

    }


    @Test
    public void main() {
        Random rand = new Random(47);
        //记录Pet的继承类class，并随机生成对象
        List<Pet> pets = Pets.arrayList(7);
        print("1: " + pets);

        //测试lsit.add是否替换存在的元素(是否有调用hashCode)
        Pug h = new Pug();
        pets.add(h); // Automatically resizes
        print("2: " + pets);

        //测试比较的原理，是根据equals方法
        print("3: " + pets.contains(h));//true
        pets.remove(h); // Remove by object

        //查找元素所在位置，indexOf
        Pet p = pets.get(2);
        print("4: " + p + " " + pets.indexOf(p));

        //测试查找不存在元素(返回-1)。remove返回值是true/false(true为找到并删除，false为找不到)
        Pet cymric = new Cymric();
        print("5: " + pets.indexOf(cymric));
        print("6: " + pets.remove(cymric));

        //测试删除已存在元素
        // Must be the exact object:
        print("7: " + pets.remove(p));
        print("8: " + pets);

        //指定位置插入元素
        pets.add(3, new Mouse()); // Insert at an index
        print("9: " + pets);

        //截取某两个位置内的元素(左闭右开)
        List<Pet> sub = pets.subList(1, 4);
        print("subList: " + sub);

        //比对list
        print("10: " + pets.containsAll(sub));
        Collections.sort(sub); // In-place sort
        print("sorted subList: " + sub);//true

        //测试排序后，对containsAll是否有影响(没有影响)
        // Order is not important in containsAll():
        print("11: " + pets.containsAll(sub));

        //对元素洗牌打乱，并测试对containsAll是否有影响(没有影响)
        Collections.shuffle(sub, rand); // Mix it up
        print("shuffled subList: " + sub);
        print("12: " + pets.containsAll(sub));

        //取交集
        List<Pet> copy = new ArrayList<Pet>(pets);
        sub = Arrays.asList(pets.get(1), pets.get(4));
        print("sub: " + sub);
        copy.retainAll(sub);
        print("13: " + copy);

        //移除某个位置
        //移除某个元素集合(被移除集合有多个相同元素，只会按顺序移除，而不是全部移除)
        copy = new ArrayList<Pet>(pets); // Get a fresh copy
        copy.remove(2); // Remove by index
        print("14: " + copy);
        copy.removeAll(sub); // Only removes exact objects
        print("15: " + copy);

        //替换指定位置
        copy.set(1, new Mouse()); // Replace an element
        print("16: " + copy);

        //在指定位置之前插入集合
        copy.addAll(2, sub); // Insert a list in the middle
        print("17: " + copy);

        //清空集合
        print("18: " + pets.isEmpty());//false
        pets.clear(); // Remove all elements
        print("19: " + pets);
        print("20: " + pets.isEmpty());//true

        //集合转数组，toArray()和toArray(T[])，前者是返回object，后者是返回具体类型
        pets.addAll(Pets.arrayList(4));
        print("21: " + pets);
        Object[] o = pets.toArray();
        print("22: " + o[3]);
        Pet[] pa = pets.toArray(new Pet[0]);
        print("23: " + pa[3]);
    }
}
