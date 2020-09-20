package com.budd.java.jdkBasic.container.collection.list;

import com.budd.java.jdkBasic.container.collection.list.pets.Pets;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.Pet;
import org.junit.Test;

import java.util.List;
import java.util.ListIterator;

import static com.budd.java.util.Print.print;
import static com.budd.java.util.Print.printf;

/**
 * @author budd
 * @desc 进阶List研究
 * @since 2020/9/20 11:09
 **/
public class ProListTest {

    /**
     * 正序遍历：previousIndex()其实是当前位置，nextIndex()
     * 倒序遍历：previousIndex()，nextIndex()其实是当前位置
     * 截取：listIterator(index)，从第index个位置(包含在内)截取集合。set(ele)替换当前元素
     */
    @Test
    public void testListIterator() {
        List<Pet> pets = randomCreatePet(8);
        ListIterator<Pet> iterator = pets.listIterator();

        printf("%n正序遍历");
        print(pets);
        while (iterator.hasNext()) {
            Pet pet = iterator.next();
            printf("元素：%s，前一个index(其实是当前位置)：%s，后一个index：%s", pet, iterator.previousIndex(), iterator.nextIndex());
        }

        printf("%n倒序遍历");
        print(pets);
        while (iterator.hasPrevious()) {
            Pet pet = iterator.previous();
            printf("元素：%s，前一个index：%s，后一个index(其实是当前位置)：%s", pet, iterator.previousIndex(), iterator.nextIndex());
        }

        printf("%n截取");
        print(pets);
        ListIterator<Pet> subIterator = pets.listIterator(3);
        while (subIterator.hasNext()) {
            subIterator.next();
            subIterator.set(Pets.randomPet());
        }
        print(pets);
    }

    private List<Pet> randomCreatePet(int size) {
        //记录Pet的继承类class，并随机生成对象
        List<Pet> pets = Pets.arrayList(size);
        return pets;
    }

}
