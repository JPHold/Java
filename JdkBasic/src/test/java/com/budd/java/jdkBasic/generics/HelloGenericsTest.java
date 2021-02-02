package com.budd.java.jdkBasic.generics;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static com.budd.java.util.Print.print;

/**
 * @author budd
 * @desc 泛型入门研究
 * @since 2020/3/6 13:30
 **/
public class HelloGenericsTest {

    /**
     * @return
     * @author HJP
     * @date 2018年3月20日 %下午10:57:54
     * @Description
     *
     * 【1】通过对象调用generate()返回不声明泛型，Map变量声明，将Map作为参数传入调用，不报错
     * 【2】通过对象调用generate()返回默认是Map<Object,Object>,将Map<Object,Object>作为参数传入调用,报错
     *      //Required type:Map=<String,String>
     *      //Provided:Map<Object,Object>
     * 【3】通过对象调用generate(),直接作为入参调用,报错
     *      //Required type:Map=<String,String>
     *      //Provided:Map<Object,Object>
     * 【4】通过类调用generate(),直接作为入参调用,报错
     *      //Required type:Map=<String,String>
     *      //Provided:Map<Object,Object>
     *
     * 通过【3】和【4】,得出在JDK1.8之前,类型推断并没有得到升级,导致报错,所以需要显示指定泛型(【5】和【6】)
     */
    /**
     * @param m
     */
    void testMap(Map<String, String> m) {
        m.put("1", "1");
        m.put("2", "2");
        print(m);
    }

    static class InferGenericsCreateClass<K, V> {
        public static <K, V> Map<K, V> generate() {
            return new HashMap<K, V>();
        }
    }

    @Test
    public void testInferGenerics() {
        InferGenericsCreateClass inferGenericsCreateClass = new InferGenericsCreateClass();
        //泛型方法testMap可以接收Map入参，不可以接收Map<Object,Object>
        //不可以接收generate()，因为返回的是Map<Object,Object>
        Map unGenericsMap = inferGenericsCreateClass.generate();
        testMap(unGenericsMap);//【1】
        Map<Object, Object> genericsMap = inferGenericsCreateClass.generate();
//        testMap(genericsMap);//【2】
//        testMap(inferGenericsCreateClass.generate());//【3】
//        testMap(InferGenericsCreateClass.generate());//【4】
        testMap(inferGenericsCreateClass.<String, String>generate());//【5】
        testMap(InferGenericsCreateClass.<String, String>generate());//【6】
    }

    /**
     * @author HJP
     * @return
     * @date 2018年3月22日 %下午8:37:57
     * @Description 如果类没有指明泛型类型，那么所有方法都需要指明<T>
     */
    class UnAppointGenericsClass {
        public <T> List<T> appoint(List<T> list, T value) {
            list.add(value);
            return list;
        }
        //Cannot resolve symbol 'T'
        /*public List<T> unAppoint(List<T> list, T value) {
            list.add(value);
            return list;
        }*/
    }

    @Test
    public void testUnAppointGenericsClass() {
        UnAppointGenericsClass unAppointGenericsClass = new UnAppointGenericsClass();
        List<String> appointList = unAppointGenericsClass.appoint(new ArrayList<String>(), "appoint");
        print(appointList);
    }

    /**
     * @author HJP
     * @date 2018年3月22日 下午9:31:20
     * @Description 接口与实现类，泛型指明
     */
    interface InterfaceImplement<T> {
        T create();
    }

    class ClassImplement implements InterfaceImplement<String> {
        @Override
        public String create() {
            return "123";
        }
    }

    @Test
    public void testInterfaceImplement() {
        ClassImplement classImplement = new ClassImplement();
        print(classImplement.create());
    }

    /**
     * @author HJP
     * @date 2018年3月22日 下午10:29:02
     * @Description 使用泛型
     */
    class BasicClassExtend<T> {
        private T t;

        public void set(T t) {
            this.t = t;
        }

        public T get() {
            return t;
        }
    }

    class ClassExtend extends BasicClassExtend {
    }

    @Test
    public void testClassExtend() {
        ClassExtend classExtend = new ClassExtend();
        classExtend.set(new Object());
        print(classExtend.get());
    }

    /**
     * {start 擦除
     * @author HJP
     * @date 2018年3月20日 %下午10:59:43
     */

    /**
     * @author HJP
     * @date 2018年4月16日 %下午9:37:14
     * @Description 擦除带来的影响：类无法实现同一个泛型接口而泛型类型不同的两种变体,报错如下：
     * com.budd.java.jdkBasic.generics.HelloGenericsTest.UnionInterfaceImplement' cannot be inherited with different type arguments: 'java.lang.Double' and 'java.lang.String
     * @return
     */
    interface UnionInterfaceImplement<T> {
    }

    class UnionClassImplement implements UnionInterfaceImplement<Double> {
    }

    //Double类型参数与String类型参数相冲,编译不过
    /*class UnionClass extends UnionClassImplement implements UnionInterfaceImplement<String> {
    }*/

    //都是Double类型参数编译可行
    class UnionClass2 extends UnionClassImplement implements UnionInterfaceImplement<Double> {
    }

    /**
     * @Description 泛型擦除，比对class类型，获取所有泛型声明的类型参数
     * ,输出的都是些参数占位符的标识符，并不是new XXX声明的具体类型
     */
    @Test
    public void testTypeParameters() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        print(list1.getClass() == list2.getClass());//true
        print(Arrays.toString(list1.getClass().getTypeParameters()));//[E]
        print(Arrays.toString(list2.getClass().getTypeParameters()));//[E]
    }

    /**
     * 测试指定泛型的List赋值给未指定泛型的list，证明擦除的存在
     */
    @Test
    public void testUnAppointGenericsRecept() {
        List<Integer> appointGenericsList = new ArrayList<>();
        appointGenericsList.add(1);
        List unAppointGenericsList = appointGenericsList;
        print(unAppointGenericsList);
    }

    /**
     * 测试擦除后，自动反射获取准确类型
     * 因擦除原因，编译后，所有泛型消失。
     * 如何得知准确类型：
     * 基于反射的运行时获得类型的信息，从内存获取值，从Object转为String(也可叫做RTTI)
     */
    class BasicReflectEraseClass<T> {

        private T t;

        public void set(T t) {
            this.t = t;
        }

        public T get() {
            return t;
        }
    }

    class ReflectEraseClass<T> extends BasicReflectEraseClass<T> {
    }

    @Test
    public void testEraseAutoReflectType() {
        ReflectEraseClass<String> reflectEraseClass = new ReflectEraseClass<>();
        reflectEraseClass.set("1");
        String str = reflectEraseClass.get();
        print(str.getClass());
    }

    /**
     * 通过Class.newInstance()创建指定泛型的普通类
     */
    class GenericsClassNew<T> {
        public T t;

        public T create(Class<T> c) throws InstantiationException, IllegalAccessException {
            t = c.newInstance();
            return t;
        }
    }

    @Test
    public void testGenericsClassNew() throws InstantiationException, IllegalAccessException {
        ArrayList list = new GenericsClassNew<ArrayList>().create(ArrayList.class);
        list.add("1");
        list.add(2);
        print(list);
    }

    class EraseTrapClass<T> {
        private T n;
        private T[] array;
        private Object[] mockEraseArray;

        public void createArray(int size) {
            array = (T[]) new Object[size];
        }

        public void put(int i, T v) {
            array[i] = v;
        }

        public T get(int i) {
            return array[i];
        }

        public T[] getArray() {
            return array;
        }

        public void normalGenerics() {
            n = (T) new Object();
        }

        public T getBasicValue() {
            return n;
        }

        public void createMockEraseArray(int size) {
            mockEraseArray = new Object[size];
        }

        public T[] getMockEraseArray() {
            return (T[]) mockEraseArray;
        }
    }

    /**
     * 普通对象擦除陷阱【1】
     * 报错
     * java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
     */
    @Test
    public void testSimpleErase() {
        EraseTrapClass<Integer> ga = new EraseTrapClass<Integer>();
        ga.normalGenerics();
        Integer value = ga.getBasicValue();//【1】
    }

    /**
     * 数组类型擦除陷阱
     * 获取单个值,不报错【1】
     * 数组类型是Object[],数据类型为Integer【2】报错
     * java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
     */
    @Test
    public void testEraseArray() {
        EraseTrapClass<Integer> ga = new EraseTrapClass<Integer>();
        ga.createArray(3);
        for (int i = 0, size = 3; i < size; i++) {
            ga.put(i, i);
        }
        Integer value = ga.get(0);//【1】
        Integer[] array = ga.getArray();//【2】
    }

    /**
     * 模拟数组类型擦除
     * 具体类型在编译时都被擦除为Object，如GenericsArray.t2,那么【2】再转型为Integer[]，运行时肯定报错:
     * java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
     */
    @Test
    public void testMockEraseArray() {
        EraseTrapClass<Integer> ga = new EraseTrapClass<Integer>();
        ga.createMockEraseArray(1);//[1]
        Integer[] value = ga.getMockEraseArray();//[2]
    }

    /**
     * 数组null陷阱不报错，即使是擦除了数组具体类型，但因为没有createArray1方法初始化GenericsArray.t，所以null转为Integer[]不会报错
     */
    @Test
    public void testNullArray() {
        EraseTrapClass<Integer> ga = new EraseTrapClass<Integer>();
        Integer[] array = ga.getArray();
    }

    /**
     * 正确创建泛型数组
     */
    class GenericsArrayNew<T> {
        private T[] array;

        public void correctCreateArray(int size, Class<T> componentType) {
            array = (T[]) Array.newInstance(componentType, size);//【1】
        }

        public void put(int i, T v) {
            array[i] = v;
        }

        public T[] getArray() {
            return array;
        }
    }

    @Test
    public void testCorrectNewGenericsArray() {
        GenericsArrayNew<Integer> ga = new GenericsArrayNew<Integer>();
        ga.correctCreateArray(2, Integer.class);
        for (int i = 0, size = 2; i < size; i++) {
            ga.put(i, i);
        }
        Integer[] array = ga.getArray();
        print(Arrays.toString(array));
    }
    //}end擦除

    /**
     * @author HJP
     * @date 2018年3月25日 上午11:23:02
     * @Description start泛型边界
     */
    /**
     * 测试边界定义顺序【1】和解决擦书问题
     */
    class BorderBasicClass {
    }

    interface BorderInterface {
    }

    //【1】
    class BorderClass<T extends BorderBasicClass & BorderInterface> {
        private T t;

        public BorderClass(T t) {
            this.setT(t);
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

    }

    //2018年3月25日11:20:59【8-1】 泛型边界的定义顺序，跟一个类继承类和实现接口的顺序一样，先类后接口
    //class BorderTest4<T extends BorderInterface & BorderClass> {}//Interface expected here
    class Border extends BorderBasicClass implements BorderInterface {
        private void msg() {
            System.out.println("边界类");
        }
    }

    class NoBorder {
        private void msg() {
            print("无边界类");
        }
    }

    @Test
    public void testBorderGenericsNoErase() {
        //2018年3月25日11:21:02 【8-2】不报错，因为限定了Border边界，所以正确转型
        BorderClass<Border> borderClass = new BorderClass<Border>(new Border());
        Border border = (Border) borderClass.getT();
        border.msg();

        //Type parameter 'com.budd.java.jdkBasic.generics.HelloGenericsTest.NoBorder' is not within its bound
        // ; should extend 'com.budd.java.jdkBasic.generics.HelloGenericsTest.BorderBasicClass'
       /* BorderClass<NoBorder> borderClass1 = new BorderClass<NoBorder>(new Border());
        NoBorder NoBorder = (NoBorder) borderClass1.getT();
        NoBorder.msg();*/
    }


    /**
     * ?和T的区别
     *
     * @author HJP
     * @date 2018年3月25日 下午4:27:40
     * @Description ?和T的区别
     */
    class QuestionGenericsClass<T> {

    }

    /**
     * ?通配符
     */
    class QuestionClass<k> {
        //error:Unexpected wildcard
        /*class QuestionInnerClass<? extends k> {
            void m(List<? extends String> a) {
            }
        }*/
        void m(List<? extends k> a) {
        }
    }

    /**
     * T通配符
     */
    class AppointClass<K> {
        public class AppointInnerClass<T extends K> {
        }//内部类才能使用

        //Unexpected bound
//        void m(QuestionGenericsClass<K extends String> list) {
//        }//error编译不过
    }

    /**
     * 测试各类边界对象存储和获取
     */
    class Holder<T> {
        private T value;

        public Holder() {
        }

        public Holder(T val) {
            value = val;
        }

        public void set(T val) {
            value = val;
        }

        public T get() {
            return value;
        }

        public boolean equals(Object obj) {
            return value.equals(obj);
        }
    }

    public class Wildcards {

    }

    Holder newBound = null;
    Holder statementNewUnBound = null;
    Holder<Long> statementNewBound = null;
    Holder<?> statementQuestionBound = null;
    Holder<? extends Long> statementQuestionExtendBound = null;
    Holder<? super Long> statementQuestionSuperBound = null;

    void makeGenericsBoundInstance() {
        newBound = new Holder<Long>();//变量未声明泛型，对象实例声明泛型
        statementNewUnBound = new Holder();//变量和对象实例都未声明泛型
        statementNewBound = new Holder<Long>();//变量和对象实例声明具体泛型
        statementQuestionBound = new Holder<Long>();//变量声明?通配符泛型，对象实例声明具体泛型
        statementQuestionExtendBound = new Holder<Long>();//变量声明 ? extend通配符泛型，对象实例声明具体泛型
        statementQuestionSuperBound = new Holder<Long>();//变量声明 ? super通配符泛型，对象实例声明具体泛型
    }

    /**
     * 2018年3月28日12:40:23 HJP
     * 测试不同边界入参，调用无泛型参数(Object)的方法
     *
     * 1、存入不是该边界泛型的对象，不会报错，但会提示：
     * Unchecked call to 'set(T)' as a member of raw type 'com.budd.java.jdkBasic.generics.HelloGenericsTest.Holder'
     * 原因：都擦除为Object
     *
     * 2、存入不是该边界泛型的对象后，取出值是什么类型:
     *  -- 无边界：【1】、【2】
     *      返回Object
     *  -- 具体类型边界：【3】
     *      返回Long，报错：java.lang.ClassCastException: com.budd.java.jdkBasic.generics.HelloGenericsTest$Wildcards cannot be cast to java.lang.Long
     *  -- 不确定类型边界(?)：【4】
     *      返回Object
     *  -- 继承类型边界(extend)：【5】
     *      返回Long，报错：java.lang.ClassCastException: com.budd.java.jdkBasic.generics.HelloGenericsTest$Wildcards cannot be cast to java.lang.Long
     *  -- 基类类型边界(super)：【6】
     *      返回Object
     */
    /**
     * @param holder Object泛型
     * @param arg    Object对象
     */
    void objectBoundedArgSet(Holder holder, Object arg) {
        holder.set(arg); // Warning:
        holder.set(new Wildcards()); // Same warning
    }

    @Test
    public void testObjectBoundEraseSetGet() {
        makeGenericsBoundInstance();

        //Long泛型
        Long aLong = 1L;
        objectBoundedArgSet(newBound, aLong);//【1】
        objectBoundedArgSet(statementNewUnBound, aLong);//【2】
        objectBoundedArgSet(statementNewBound, aLong);//【3】
        objectBoundedArgSet(statementQuestionBound, aLong);//【4】
        objectBoundedArgSet(statementQuestionExtendBound, aLong);//【5】
        objectBoundedArgSet(statementQuestionSuperBound, aLong);//【6】

        //Double泛型
        Double aDouble = 1.0d;
        objectBoundedArgSet(newBound, aDouble);
        objectBoundedArgSet(statementNewUnBound, aDouble);
        objectBoundedArgSet(statementNewBound, aDouble);
        objectBoundedArgSet(statementQuestionBound, aDouble);
        objectBoundedArgSet(statementQuestionExtendBound, aDouble);
        objectBoundedArgSet(statementQuestionSuperBound, aDouble);

        Object r1 = newBound.get();
        Object r2 = statementNewUnBound.get();
//        Long r3 = statementNewBound.get();/error
        Object r4 = statementQuestionBound.get();
//        Long r5 = statementQuestionExtendBound.get();//error
        Object r6 = statementQuestionSuperBound.get();
    }

    /**
     * 2018年3月28日12:41:49 HJP
     * 测试不同边界入参，调用不确定泛型(?)参数的方法
     *
     * 1、存入值，报错：
     * Required type: capture of ?
     * Provided: Object
     * 原因： ?为无边界泛型(任何类型都接受),但又不局限于其中一个具体类型，矛盾了，自然什么类型数据都无法set到Holder
     *
     * 2、取出值是什么类型：
     *  -- 无边界：【1】、【2】
     *      返回Object
     *  -- 具体类型边界：【3】
     *      返回Long
     *  -- 不确定类型边界(?)：【4】
     *      返回Object
     *  -- 继承类型边界(extend)：【5】
     *      返回Long
     *  -- 基类类型边界(super)：【6】
     *      返回Object
     */
    /**
     * @param holder
     * @param arg
     */
    void questionUnBoundedArgSet(Holder<?> holder, Object arg) {
//        holder.set(arg); // Error:
//        holder.set(new Wildcards()); // Same error
    }

    @Test
    public void testQuestionUnBoundEraseSetGet() {
        makeGenericsBoundInstance();

        Long aLong = 1L;
        questionUnBoundedArgSet(newBound, aLong);//【1】
        questionUnBoundedArgSet(statementNewUnBound, aLong);//【2】
        questionUnBoundedArgSet(statementNewBound, aLong);//【3】
        questionUnBoundedArgSet(statementQuestionBound, aLong);//【4】
        questionUnBoundedArgSet(statementQuestionExtendBound, aLong);//【5】
        questionUnBoundedArgSet(statementQuestionSuperBound, aLong);//【6】

        Object r1 = newBound.get();
        Object r2 = statementNewUnBound.get();
        Long r3 = statementNewBound.get();
        Object r4 = statementQuestionBound.get();
        Long r5 = statementQuestionExtendBound.get();
        Object r6 = statementQuestionSuperBound.get();
    }

    /**
     * 2018年3月28日12:48:46 HJP
     * 测试不同边界入参，调用具体泛型边界参数的方法
     *
     * 1、接收情况：
     *  不确定类型边界(?)和继承类型边界(extend)无法调用方法【4】、【5】，报错：
     *      Required type:capture of ?
     *      Provided:Long
     *  剩余边界类型可以调用
     *
     * 2、取出值是什么类型：
     *  -- 无边界：【1】、【2】
     *      返回Object
     *  -- 具体类型边界：【3】
     *      返回Long
     *  -- 不确定类型边界(?)：【4】
     *      返回Object
     *  -- 继承类型边界(extend)：【5】
     *      返回Long
     *  -- 基类类型边界(super)：【6】
     *      返回Object
     *
     * 通过【1】发现左侧对象变量未声明泛型，右侧对象实例声明泛型，调用exact1()的返回值类型为Object,得出:
     * 左侧对象变量声明的泛型，用于表面上肉眼可见的声明，具有象征意义
     * 右侧对象实例声明的泛型，用于运行时识别
     */
    /**
     * @param holder
     * @param <T>
     * @return
     */
    <T> void specificBoundedArgSet(Holder<T> holder, T arg) {
        holder.set(arg);
        //Required type:T
        //Provided:Wildcards
//        holder.set(new Wildcards());//error
    }

    @Test
    public void testSpecificBoundedEraseSetGet() {

        makeGenericsBoundInstance();

        Long aLong = 1L;
        specificBoundedArgSet(newBound, aLong);//【1】
        specificBoundedArgSet(statementNewUnBound, aLong);//【2】
        specificBoundedArgSet(statementNewBound, aLong);//【3】
//        specificBoundedArgSet(statementQuestionBound, aLong);//【4】，error
//        specificBoundedArgSet(statementQuestionExtendBound, aLong);//【5】,error
        specificBoundedArgSet(statementQuestionSuperBound, aLong);//【6】

        Object r1 = newBound.get();
        Object r2 = statementNewUnBound.get();
        Long r3 = statementNewBound.get();
        Object r4 = statementQuestionBound.get();
        Long r5 = statementQuestionExtendBound.get();
        Object r6 = statementQuestionSuperBound.get();
    }

    /**
     * 2018年3月28日13:07:22 HJP
     * 测试不同边界入参，调用下限泛型(? extend)参数的方法
     *
     * 1、存入值，报错：
     *  在questionExtendBoundArgsSet无法set值，报错：
     *      Required type:capture of ? extends T
     *      Provided:T
     * 原因: ? extend T,设置的值最低限度为T的派生类，而不是T
     *  ,比如<? extend T>,定T为Fruit,那么调用questionExtendBoundArgsSet(Holder<Apple>,Orange)
     *  ,注意第二参数为Orange,明显与Apple不是同类型或子类型，所以编译器杜绝set带来的风险
     *
     * 2、取出值是什么类型
     *  -- 无边界：【1】、【2】
     *      返回Object
     *  -- 具体类型边界：【3】
     *      返回Long
     *  -- 不确定类型边界(?)：【4】
     *      返回Object
     *  -- 继承类型边界(extend)：【5】
     *      返回Long
     *  -- 基类类型边界(super)：【6】
     *      返回Object
     */
    /**
     * @param holder
     * @param arg
     * @param <T>
     */
    <T> void questionExtendBoundArgsSet(Holder<? extends T> holder, T arg) {
//        holder.set(arg); //Error
    }

    @Test
    public void testQuestionExtendBoundEraseSetGet() {
        makeGenericsBoundInstance();

        Long aLong = 1L;
        questionExtendBoundArgsSet(newBound, aLong);//【1】
        questionExtendBoundArgsSet(statementNewUnBound, aLong);//【2】
        questionExtendBoundArgsSet(statementNewBound, aLong);//【3】
        questionExtendBoundArgsSet(statementQuestionBound, aLong);//【4】
        questionExtendBoundArgsSet(statementQuestionExtendBound, aLong);//【5】
        questionExtendBoundArgsSet(statementQuestionSuperBound, aLong);//【6】

        Object r1 = newBound.get();
        Object r2 = statementNewUnBound.get();
        Long r3 = statementNewBound.get();
        Object r4 = statementQuestionBound.get();
        Long r5 = statementQuestionExtendBound.get();
        Object r6 = statementQuestionSuperBound.get();
    }

    /**
     * 2018年3月28日13:07:22 HJP
     * 测试不同边界入参，调用不确定泛型(? super T)参数的方法
     *
     * 1、存入值
     *  不确定类型边界(?)和继承类型边界(extend)无法调用(【4】、【5】)，报错：
     *      Required type:HelloGenericsTest.Holder<? super Long>
     *      Provided:HelloGenericsTest.Holder<capture of ?>
     *          、
     *      Required type:HelloGenericsTest.Holder<? super Long>
     *      Provided:HelloGenericsTest.Holder<capture of ? extends Long>
     *  其它都能正常调用
     *
     * 2、取出值是什么类型
     *  在superBoundArgsSet无法返回T,只能返回Object
     *  原因：? super T,T的基类可能有多个。所以无法确定
     *  -- 无边界：【1】、【2】
     *      返回Object
     *  -- 具体类型边界：【3】
     *      返回Long
     *  -- 不确定类型边界(?)：【4】
     *      返回Object
     *  -- 继承类型边界(extend)：【5】
     *      返回Long
     *  -- 基类类型边界(super)：【6】
     *      返回Object
     */
    /**
     * @param holder
     * @param arg
     * @param <T>
     */
    <T> void questionSuperBoundArgsSet(Holder<? super T> holder, T arg) {
        holder.set(arg);
//        T t = holder.get();  // Error:
        Object obj = holder.get();
    }

    @Test
    public void testSuperBoundArgsSetGet() {

        Long aLong = 1L;

        makeGenericsBoundInstance();
        questionSuperBoundArgsSet(newBound, aLong);//【1】
        questionSuperBoundArgsSet(statementNewUnBound, aLong);//【2】
        questionSuperBoundArgsSet(statementNewBound, aLong);//【3】
//        questionSuperBoundArgsSet(statementQuestionBound, aLong);//【4】,error
//        questionSuperBoundArgsSet(statementQuestionExtendBound, aLong);//【5】,error
        questionSuperBoundArgsSet(statementQuestionSuperBound, aLong);//【6】

        Object r1 = newBound.get();
        Object r2 = statementNewUnBound.get();
        Long r3 = statementNewBound.get();
        Object r4 = statementQuestionBound.get();
        Long r5 = statementQuestionExtendBound.get();
        Object r6 = statementQuestionSuperBound.get();
    }

    //}end泛型边界

}
