# 空提示
- com.budd.java.jdkBasic.string.HelloStringTest#testTipNull
1. 属于编译级别的问题发现，通常由IDE发现并提示
2. **在File | Settings | Editor | Inspections | Java | Probable bugs | Constant conditions & exceptions，去除就不校验空**
3. 使用IDEA自带的NotNull，IDEA会在编译时加了@NotNull上加上断言，运行时进行判断
4. **其它NotNull如Java，不支持运行时断言，但会提示**
5. **不加NotNull，IDEA依然会提示，只是提示不同罢了**
6. [IDEA空提示，会标注字符串，鼠标放上去会有提示](https://www.jetbrains.com/help/idea/nullable-and-notnull-annotations.html)
7. [空处理](https://www.jianshu.com/p/ae741950c516)
- 遗留
8. [@Contract预判结果](https://www.w3cschool.cn/intellij_idea_doc/intellij_idea_doc-u4jy2ems.html)
[JetBrains的@Contract批注问问题](https://stackoverflow.com/questions/34620494/jetbrains-contract-annotation)，没找到该注解