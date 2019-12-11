#基础知识
1. 层级结构
    - 无继承,实现java.io.Serializable,Comparable<String>, CharSequence
    - 是用char[]存储的,并且private final的,所以无须担心外部代码修改会影响string的值

#创建字符串
- com.budd.java.jdkBasic.string.HelloStringTest.testCreate
1. new String(s1),将源s1的value和hash值给目标s2,因为string.value是private final,所以无须担心s1会影响s2
2. 很少用StringBuilder和StringBuffer构建string(通过toString方法来构建string)(后者toString是同步方式,则前者比后者更快创建字符串)

#转换字符串
- com.budd.java.jdkBasic.string.HelloStringTest.testTransform
1.String转byte[],跟环境有强关联(win10中文是UTF-8),所以最好指定编码格式(一般是utf-8)
2.byte[]转String,如果没指定编码格式charset,会获取本地环境默认的编码格式(win10中文是UTF-8),如果还没有则采用ISO-8859-1
# 空提示
- com.budd.java.jdkBasic.string.HelloStringTest#testTipNull
1. 属于编译级别的问题发现，通常由IDE发现并提示
2. **在File | Settings | Editor | Inspections | Java | Probable bugs | Constant conditions & exceptions，去除就不校验空。可修改Severity的严重等级(建议Error，显示明显)**
3. 使用IDEA自带的NotNull，IDEA会在编译时加了@NotNull上加上断言，运行时进行判断
4. **其它NotNull如Java，不支持运行时断言，但会提示及运行时报错**
5. **不加NotNull，IDEA依然会提示，只是提示不同罢了**
6. [IDEA空提示，会标注字符串，鼠标放上去会有提示](https://www.jetbrains.com/help/idea/nullable-and-notnull-annotations.html)
7. [空处理](https://www.jianshu.com/p/ae741950c516)

- 遗留
1. [@Contract预判结果](https://www.w3cschool.cn/intellij_idea_doc/intellij_idea_doc-u4jy2ems.html)
[JetBrains的@Contract批注问问题](https://stackoverflow.com/questions/34620494/jetbrains-contract-annotation)，没找到该注解

#重载运算符
- com.budd.java.jdkBasic.string.HelloStringTest.testPlusOverload
1. java唯一的重载运算符为"+"
2. 在编译期间,根据不同类型,采用不同类+方法转换,将运算对象作为参数传入
2. 在jdk5之后,在编译期间采用StringBuilder来拼接,之前版本都是新建String对象再相加。该特性叫做静态字符串连接优化(a static string concatenation optimization)
3. 虽然+采用StringBuilder拼接,但循环时不能使用,因为字节码可以看到每循环一次都新建StringBuilder,增加GC(IDEA会提示)。在外层新建全局StringBuilder,append操作

#比较
- com.budd.java.jdkBasic.string.HelloStringTest.testCompare
    - eqauls
        - 内容比较只能比较String
        - 源码逻辑
        `
          //  1、先比较是否同个对象,否走下一步
          //  2、判断是否为String类型,否返回false,是走下一步
          //  2、比较长度是否一致,是走下一步
          //  3、遍历两者的内部数组的字符,做字符比较
        `
    - contentEquals
        - 内容比较，除了比较String,也支持其它类型：StringBuilder、StringBuffer以及其它CharSequence实现
        - String比较采用equals比较，其它类型都采用遍历数组做字符比较
        - 源码逻辑
        `
          //  1、是否AbstractStringBuilder实现,否走下一步
          //    1.1、是StringBuffer,对StringBuffer对象加锁
          //    1.2、不是StringBuffer(jdk8目前是StringBuilder)
          //    1.3、上述两种类型的比较处理逻辑都一样：遍历字符数组，做字符比较
          //  2、是否String实现,否走下一步
          //    2.1、查看equals逻辑
          //  3、是否其他CharSequence实现(非String非AbstractStringBuilder)
          //    3.1、遍历数组，做字符比较
        `
    - equalsIgnoreCase
        - 忽略大小写的比较
        - 源码逻辑
        `
          // 1、最主要的是regionMatches方法
          //    1.1、比较Character.toUpperCase和Character.toLowerCase结果
        `
    - compareTo(compareToIgnoreCase)
        - 比较String的字符数组：按Unicode值字典比较内部字符数组
        - 返回结果说明，=0(完全相同)、<0(比参数小(在Unicode字典顺序排名低))、>0(比参数大(在Unicode字典顺序排名高)))
        - 源码逻辑
        `
          // 如果不同会返回两个字符的Unicode值之差,如果完全相同返回两字符串的长度之差
        `
    - regionMatches(regionMatches(boolean))
        - 局部比较，可支持从哪个个位置开始、比较字符个数、忽略大小比较
        
#hashCode
- com.budd.java.jdkBasic.string.HelloStringTest.testHashCode
1. 质数的概念：除1和它本身外,无法被其他自然数整除的数
2. 用于计算hash地址，多用于Map等
3. hashCode保证相同字符串,hash值相同; hash值相同,字符串不一定相同
4. 为何hashCode采用31(质数)作为系数: 
    - 系数要尽量长且乘法尽量不会溢出的数(在Java乘法中,如果数字相乘过大会溢出造成数据丢失),长的系数计算出来的hash地址就越大
    ,相同的hash地址的可能性就越小,hash链就越短,查询效率就越高
    - 为了更好分配hash地址,31只占5bit
5. 源码逻辑
`
int h = hash;
if (h == 0 && value.length > 0) {
    char val[] = value;

    for (int i = 0 ; i < value.length; i++) {
        h = 31 * h + val[i];
    }
    hash = h;
}
return h;
`
6. 资料
- [31系数](http://jm-blog.aliapp.com/?p=1501)
 
#hashCode与equals
- com.budd.java.jdkBasic.string.HelloStringTest.testEqualsHashCode
1. 重写hashCode则必须重写equals
2. 在HashMap、HashTable等链表-数组结构, put操作是比较hashCode判断是否要放在同个数组中,比较equals判断是否要放入到数组(重复则不放入)
    get操作是根据hashCode拿到数组,逐个比较equals是否相同(相同则取出)   
    
#常量池
- String创建与常量池的关系,com.budd.java.jdkBasic.string.HelloStringTest.testCreateRelContact
1. 编译期：编译成class,有个contact pool将字符串的字面量和符号引用维护进来
2. 类加载期：从编译器的contact pool取出字符串,在JVM的常量池查找是否存在该字面量,存在则返回直接引用,不存在则创建字面量对象并返回该直接引用
3. 运行期：String s = new String("hello")在栈上创建变量。如果常量池已存在"hello"则不创建字面量但会创建对象实例和符号引用
    ,否则在堆上创建对象实例+在常量池上创建字面量和符号引用。String s = "hello2",如果常量池已存在"hello"则直接复用对象实例、字面量和符号引用
    (常量池存储着对象实例引用)(变量=>对象实例,对象实例=>字面量)
4. new +""与""不同
-- intern对创建对象实例和字面量的影响,com.budd.java.jdkBasic.string.HelloStringTest.testIntern
# 增补字符
- com.budd.java.jdkBasic.string.HelloStringTest.testFilesBasicCodePoint、com.budd.java.jdkBasic.string.HelloStringTest.testStreamBasicCodePoint
1. 在Unicode出现前的现状:
    > 每个国家都有自己的字符编码标准,带来的问题是:一个编码能对应几个字符
    > 每个字符编码标准中字符的长度不一,有的采用单字节,而有的采用多字节
2. Unicode出现后的现状:
    > 20世纪80年代设计之初采用两个字节的编码长度对应每个国家的语言字符,可支持65536个字符编码,并且有足够空间预备
    > 但因为大量汉语、日语和韩语的表意字符加入,65536空间无法满足
3. Java对Unicode的支持
    > 设计之初采用16位Unicode字符集
    > 字符大量加入,Java对此的解决方案如下:
4. Java解决方案
    > 码点(代码单元):对应字符编码表的某个字符的代码值,采用U+16进制书写
    > 码点分维护分为17个代码级别,第一个是基础多语言(U+0000到U+FFFF),剩余代码级别(U+10000到U+10FFFF,包括一些增补字符),总共1114112码位
    > 基础多语言字符
        - U+D800到U+DBFF不能分配给字符
    > JDK5之后,更新增补字符,采用两个字节。增补字符采用一对连续的码点,即高位代理和低位代理
        - 高位代理,U+D800到U+DBFF(共1024个码位)
        - 低位代理,U+DC00到u+DFFF(共1024个码位)
        - 可支持1048576个字符(1024的平方),加上BMP的65536个码位,去掉2048个非法的码位,正好是1,112,064个码位    
5. 日常使用
    > 使用UTF-8基本满足,只有使用增补字符,才会使用UTF-16
    > 基础多语言级别的U+D800到U+DBFF,只能通过stream方式(如FileWriter、FileReader)写入/读取,其它方式不行(入如Files.write、Files.readAllBytes)
        - jdk7,使用Path和Files写入会报错:java.nio.charset.MalformedInputException: Input length = 1,不是合法的16位Unicode编码,找不到对应字符
        - FileWriter将U+D800到U+DBFF写入文件是?,FileReader读取是3f
- com.budd.java.jdkBasic.string.HelloStringTest.testSupplementChar
1. length(),因一个增补字符有高低位代理字符,所以占两个长度
2. 写入和读取,采用UTF-32和readInt
3. 通过Character.isSupplementaryCodePoint判断是否为增补字符

- 资料
1. [增补字符](https://www.aliyun.com/jiaocheng/348736.html)
1. [unicode字符集](http://graphemica.com/)   
2. [oracle官方详细讲解unicode渊源](https://www.oracle.com/technical-resources/articles/javase/supplementary.html)     