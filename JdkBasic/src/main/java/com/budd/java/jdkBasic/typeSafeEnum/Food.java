package com.budd.java.jdkBasic.typeSafeEnum;

public interface Food {
    enum Coffee implements Food {
        BLACK, LATTE;

        @Override
        public void desc() {
            System.out.println(name());
        }
    }

    enum Fruit implements Food {
        SNOW, BANANA;

        @Override
        public void desc() {
            System.out.println(name());
        }
    }

    void desc();

}
