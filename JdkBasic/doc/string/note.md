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
1. [unicode字符集](http://graphemica.com/)   
2. [oracle官方详细讲解unicode渊源](https://www.oracle.com/technical-resources/articles/javase/supplementary.html)     