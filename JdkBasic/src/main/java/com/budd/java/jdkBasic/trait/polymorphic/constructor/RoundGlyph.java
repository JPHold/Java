package com.budd.java.jdkBasic.trait.polymorphic.constructor;

/**
 * @author budd
 * @desc 圆形-构造器-调用多态方法
 * @since 2020/8/26 15:59
 **/
public class RoundGlyph extends Glyph {

    private int radius = 1;

    public RoundGlyph(int r) {
        radius = r;
        System.out.println("RoundGlyph.RoundGlyph(), radius = " + radius);
    }

    @Override
    void draw() {
        System.out.println("RoundGlyph.draw(), radius = " + radius);
    }

}
