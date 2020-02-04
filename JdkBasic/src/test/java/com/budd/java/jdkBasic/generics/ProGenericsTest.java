package com.budd.java.jdkBasic.generics;

import com.budd.java.jdkBasic.generics.covariant.Apple;
import com.budd.java.jdkBasic.generics.covariant.CovariantDesignContainer;
import com.budd.java.jdkBasic.generics.covariant.Fruit;
import com.budd.java.jdkBasic.generics.covariant.Orange;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 进阶泛型
 *
 * @author budd
 * @since 2018/3/4 22:57:54
 **/
public class ProGenericsTest {


    /**
     * 协变
     */
    @Test
    public void covariant() {

        //1、通过编译，但运行时报错:java.lang.ArrayStoreException(idea会提示)
        /* Fruit[] fList = new Apple[3];
        fList[0] = new Orange();*/

        //2、无法通过编译 ，提示类型不对，因为泛型没有协变类型
        //List<Fruit> list=new ArrayList<Apple>();

        //3、
        // 为了使的2通过编译。修改成这样
        // list2并没有指定具体类型(可以是Fruit、Orange、Apple),无法添加这三种对象和Object，最终造成了只能添加null
        List<? extends Fruit> list2 = new ArrayList<Apple>();
        //list2.add(new Apple());
        list2.add(null);
        //list2.add(new Object());


        //4、
        //setValue2方法存在边界向上转型陷阱：
        //      明显covariantDesignContainer被定义为只能存储Apple类型，但covariantDesignContainer声明为Fruit类型或其子类型，造成了可以存储除Apple以外的对象
        //为了防止<? extends XX>这样的泛型定义，设计时要决定泛型类中哪些方法是不安全的，参数列表就要设为泛型(见setValue方法)，如果是安全的就设为Object(见equals方法)
        CovariantDesignContainer<? extends Fruit> covariantDesignContainer = new CovariantDesignContainer<Apple>();
//        covariantDesignContainer.setValue(new Orange());
        covariantDesignContainer.setValue(null);
        covariantDesignContainer.setValue2(new Orange());
        Apple orange = (Apple) covariantDesignContainer.getValue();


    }
}
