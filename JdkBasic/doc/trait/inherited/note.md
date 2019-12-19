#继承
- com.budd.java.jdkBasic.trait.inherited.HelloInheritedTest.testLoadOrder
    - 基类派生类加载顺序
        - 基类静态变量+静态代码块
        - 派生类静态变量+静态代码块
        - 基类非静态变量+非静态代码块+无参构造方法
        - 派生类非静态变量+非静态代码块+无参类构造方法

- com.budd.java.jdkBasic.trait.inherited.HelloInheritedTest.testFinalMethod
    - final不可以重写, 打开FinalMethodChildClass.finalSameMethod连编译都过不了