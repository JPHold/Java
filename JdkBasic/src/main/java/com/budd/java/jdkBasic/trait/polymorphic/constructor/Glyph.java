package com.budd.java.jdkBasic.trait.polymorphic.constructor;

/**
 * @author budd
 * @desc 图形类-构造器-调用多态方法
 * @since 2020/8/26 15:57
 **/
public class Glyph {

    void draw() {
        System.out.println("Glyph.draw()");
    }

    Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }

}
