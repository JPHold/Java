#继承
- com.budd.java.jdkBasic.trait.polymorphic.HelloPolymorphicTest.testMethodDynamic
    - 方法多态

- com.budd.java.jdkBasic.trait.polymorphic.HelloPolymorphicTest.testFieldDynamic
    - 字段多态
    - 为FieldBasicClass.field和FieldChildClass.field分配了不同的存储空间

- com.budd.java.jdkBasic.trait.polymorphic.HelloPolymorphicTest.testStaticMethodDynamic
    - 静态方法不存在多态, 在类加载期间就绑定了当前类

- com.budd.java.jdkBasic.trait.polymorphic.HelloPolymorphicTest.testRelationMethodDynamic
    - 基类的方法1调用存在多态的方法2, 那么调用基类.方法1, 调用的方法2是派生类的