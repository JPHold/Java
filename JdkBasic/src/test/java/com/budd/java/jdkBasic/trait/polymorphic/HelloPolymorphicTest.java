package com.budd.java.jdkBasic.trait.polymorphic;

import static com.budd.java.util.Print.*;

import com.budd.java.jdkBasic.trait.inherited.*;
import com.budd.java.jdkBasic.trait.polymorphic.constructor.RoundGlyph;
import com.budd.java.jdkBasic.trait.polymorphic.covariant.Grain;
import com.budd.java.jdkBasic.trait.polymorphic.covariant.Mill;
import com.budd.java.jdkBasic.trait.polymorphic.covariant.WheatMill;
import org.junit.Test;

import java.util.Random;

/**
 * 多态特性测试
 *
 * @author budd
 * @since 2019/12/18 23:00
 **/
public class HelloPolymorphicTest {

    /**
     * 测试方法多态
     */
    class ShapeGenerator {
        private Random rd = new Random(47);

        public Shape generator() {
            int no = rd.nextInt(2);
            switch (no) {
                default:
                case 0:
                    return new Circle();
                case 1:
                    return new Square();
            }
        }
    }

    @Test
    public void testMethodDynamic() {
        ShapeGenerator shapeGenerator = new ShapeGenerator();
        Shape generator = shapeGenerator.generator();
        generator.draw();
    }

    /**
     * 测试字段多态
     */
    @Test
    public void testFieldDynamic() {
        //直接访问field并不是访问派生类的field，而是访问基类的field
        System.out.println("\n基类多态获取字段");
        FieldBasicClass fieldBasicClass = new FieldChildClass();
        System.out.println(String.format("基类.field=%s", fieldBasicClass.field));//0
        System.out.println(String.format("基类.getField()=%s", fieldBasicClass.getField()));//1

        System.out.println("--------------");
        System.out.println("派生类多态获取字段");
        //fieldChildClass.field=1,fieldChildClass.getField()=1,fieldChildClass.getSuperField()=0
        FieldChildClass fieldChildClass = new FieldChildClass();
        System.out.println(String.format("派生类.field=%s", fieldChildClass.field));//1
        System.out.println(String.format("派生类.getField()=%s", fieldChildClass.getField()));//1
        System.out.println(String.format("派生类.getSuperField()=%s", fieldChildClass.getSuperField()));//1
    }

    /**
     * 测试静态方法多态问题
     */
    @Test
    public void testStaticMethodDynamic() {
        System.out.println("\n基类.静态方法");
        StaticBasicClass staticBasicClass = new StaticChildClass();
        staticBasicClass.getStaticGet();
        staticBasicClass.getNormalGet();

        System.out.println("--------------");
        System.out.println("\n派生类.静态方法");
        StaticChildClass staticChildClass = new StaticChildClass();
        staticChildClass.getStaticGet();
        staticChildClass.getNormalGet();
    }

    /**
     * 测试方法调用方法时的多态问题
     */
    @Test
    public void testRelationMethodDynamic() {
        System.out.println("\n方法调用方法时的多态");
        RelationMethodBasicClass relationMethodBasicClass = new RelationMethodChildClass();
        relationMethodBasicClass.method1();
    }

    /**
     * @return void
     * @Author budd
     * @Description 基类构造器调用多态方法
     * @Date 2020/8/26 16:00
     * @Param []
     **/
    @Test
    public void testConstructorInvokeDynamicMethod() {
        new RoundGlyph(5);
    }

    /**
     * @return void
     * @Author budd
     * @Description JAVA5新增的协变返回类型
     * @Date 2020/8/26 17:23
     * @Param []
     **/
    @Test
    public void testCovariant() {
        Mill mill = new Mill();
        Grain grain = mill.process();
        printf("无协变：%s", grain);

        mill = new WheatMill();
        grain = mill.process();
        printf("协变：%s", grain);
    }


}
