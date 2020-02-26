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

    private List<Pet> randomCreatePet(int size) {
        //记录Pet的继承类class，并随机生成对象
        List<Pet> pets = Pets.arrayList(size);
        return pets;
    }

    /**
     * 测试lsit.add是否替换存在的元素(是否有调用hashCode)
     * 指定位置插入元素
     * 在指定位置之前插入集合
     */
    @Test
    public void testAdd() {
        List<Pet> pets = randomCreatePet(7);
        print("1: " + pets);
        Pug h = new Pug();
        pets.add(h); // 自动调整大小
        print("2: " + pets);

        pets.add(3, new Mouse());
        print("3: " + pets);

        List<Pet> sub = Arrays.asList(pets.get(1), pets.get(2));
        pets.addAll(2, sub);
        print("4: " + pets);
    }

    /**
     * 测试比较的原理，是根据equals方法
     * containsAll()，无关顺序进行对比
     */
    @Test
    public void testContains() {
        List<Pet> pets = randomCreatePet(7);
        print("1：" + pets);

        Pug pug = new Pug();
        pets.add(pug);
        print("2: " + pets.contains(pug));//true

        List<Pet> sub = Arrays.asList(pets.get(4), pets.get(1));
        print("3：" + sub);
        print("4: " + pets.containsAll(sub));//true
    }

    /**
     * 查找元素所在位置，indexOf
     * 不存在元素(返回-1)
     * 存在元素(返回所在位置)
     */
    @Test
    public void testIndexOf() {
        List<Pet> pets = randomCreatePet(7);
        Pet p = pets.get(2);
        print("1: " + p + " " + pets.indexOf(p));

        Pet cymric = new Cymric();
        print("2: " + pets.indexOf(cymric));
    }

    /**
     * 测试删除已存在元素
     * remove返回值是true/false(true为找到并删除，false为找不到)
     * 移除某个元素集合(被移除集合有多个相同元素，只会按顺序移除，而不是全部移除)
     */
    @Test
    public void testRemove() {
        List<Pet> pets = randomCreatePet(7);
        print(String.format("集合：%s", pets));

        Pug pug = new Pug();
        print(String.format("%s：%s,移除后的集合：%s", "移除某个元素", pets.remove(pug), pets));

        print(String.format("%s：%s,移除后的集合：%s", "移除指定位置", pets.remove(2), pets));

        List<Pet> sub = Arrays.asList(pets.get(1), pets.get(4), new Pug());
        print("待移除集合：" + sub);
        print(String.format("%s：%s,集合：%s", "移除集合", pets.removeAll(sub), pets));
    }

    /**
     * 截取某两个位置内的元素(左闭右开)
     */
    @Test
    public void testSub() {
        List<Pet> pets = randomCreatePet(7);
        print("1: " + pets);
        List<Pet> sub = pets.subList(1, 4);
        print("subList: " + sub);
    }

    /**
     * 比对list
     * 测试排序后，对containsAll是否有影响(没有影响)
     */
    @Test
    public void testContainsAll() {
        List<Pet> pets = randomCreatePet(7);
        List<Pet> sub = pets.subList(1, 4);
        print("subList: " + sub);
        print("1: " + pets.containsAll(sub));//true

        Collections.sort(sub);
        print("sorted subList: " + sub);
        print("排序对containsAll是否没影响：" + pets.containsAll(sub));//true

        Random rand = new Random(47);
        Collections.shuffle(sub, rand);
        print("shuffled subList: " + sub);
        print("随机排序对containsAll是否没影响：" + pets.containsAll(sub));//true
    }

    /**
     * 取交集
     */
    @Test
    public void testRetain() {
        List<Pet> pets = randomCreatePet(7);
        List<Pet> sub = Arrays.asList(pets.get(1), pets.get(4));
        print("sub: " + sub);
        pets.retainAll(sub);
        print("交集集合: " + pets);
    }

    /**
     * 替换指定位置
     */
    @Test
    public void testSet() {
        List<Pet> pets = randomCreatePet(7);
        print("1：" + pets);
        pets.set(1, new Mouse());
        print("set后的集合：" + pets);
    }

    @Test
    public void testReplace() {
        List<Pet> pets = randomCreatePet(7);
        print("1：" + pets);

        Collections.replaceAll(pets, pets.get(1), new Pug("pug1"));
        print("2：" + pets);
    }

    /**
     * 清空集合
     */
    @Test
    public void testClear() {
        List<Pet> pets = randomCreatePet(7);
        print("1: " + pets.isEmpty());//false
        pets.clear(); // Remove all elements
        print("2: " + pets);//[],为空
        print("3: " + pets.isEmpty());//true
    }

    /**
     * 集合转数组：
     * toArray()
     * 返回object类型
     * toArray(T[] a)
     * 返回具体类型
     * 如果a的长度小于集合size，则直接复制size个元素到a
     * 如果a的长度大于集合size，则从0复制size个元素到a,并将第size个元素置为null
     */
    @Test
    public void testToArray() {
        List<Pet> pets = randomCreatePet(7);
        print("1: " + pets);

        Object[] o = pets.toArray();
        print("1: " + o[3]);

        Pet[] pa = pets.toArray(new Pet[0]);
        print("2: " + pa[3]);
    }

}
