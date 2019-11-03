

# 获取路径
 - com.budd.java.jdk7.file.pathfile.PathFileHello.testNormalize
 1. Path.get(String first, String... more) 
   > first，root路径
   
   > more，文件/目录的相对roor路径的路径
 2. path，获取原始路径
 3. path.normalize，获取最终真实的路径

# 创建目录/文件
- com.budd.java.jdk7.file.pathfile.PathFileHello#testCreateDirectory

# 复制目录/文件
- com.budd.java.jdk7.file.pathfile.PathFileHello#testCopy
1. 默认不覆盖，存在时会报错：java.nio.file.FileAlreadyExistsException
2. 复制目录，不复制该目录下的文件/目录
3. Files.copy(Path source, Path target, CopyOption... options)，第三个参数为StandardCopyOption
4.  <span id = "copy">StandardCopyOption参数如下：</span>
   > REPLACE_EXISTING，覆盖
   
   > COPY_ATTRIBUTES，只复制属性(不覆盖)
   
   > ATOMIC_MOVE，原子性(不覆盖)
 
# 移动目录/文件
 - com.budd.java.jdk7.file.pathfile.PathFileHello#testMove
 1. Files.move(Path source, Path target, CopyOption... options)，第三个参数为StandardCopyOption
 2. [参数参照copy](#copy)

# 删除目录/文件
- com.budd.java.jdk7.file.pathfile.PathFileHello#testDelete
1. Files.delete(Path path) 

# 判断是否存在
   -  com.budd.java.jdk7.file.pathfile.PathFileHello.testExists
   1. 支持是否存在\不存在的判断
   2. LinkOption参数
        > 有个概念: 文件分为普通文件、软链接、硬链接。window平台下叫符号链接(快捷方式)
        > 在window平台下，使用java创建符号链接报错：客户端没有所需的特权,解决：确保window为专业版、window10需添加本地策略和创建符号链接权限。
        
        [设置本地策略](http://www.pc0359.cn/article/win10/87762.html) [创建符号链接权限](http://www.thinkbase.net/main/blog/thinkbase-20181028-1329)
    3. **软链接只支持判断目录下，不支持判断指定文件**
    4. 延伸
```java
//创建软链接
Path linkDrPath = Paths.get(ROOT_PATH, "testlink");
Files.createDirectory(linkDrPath);//创建软链接目录
Path fileLinkPath = Paths.get(ROOT_PATH, "testlink//t2link.txt");
Path symbolicLinkPath = Files.createSymbolicLink(fileLinkPath, filePath);
//判断是否为软链接
boolean isLink = Files.isSymbolicLink(symbolicLinkPath);
```
# 深度遍历文件/文件夹
   - com.budd.java.jdk7.file.pathfile.PathFileHello.testWalkFileTree
   1. 可以遍历文件、文件夹
   2. 主要方法有visitFileFailed、preVisitDirectory、visitFile、postVisitDirectory,分别是访问文件失败、开始访问文件夹、访问文件、结束访问文件夹
   3. 上述方法的返回值都是java.nio.file.FileVisitResult,是个枚举:CONTINUE、TERMINATE、SKIP_SUBTREE、SKIP_SIBLINGS
     	>   CONTINUE，允许访问内部的文件
        
        >   SKIP_SUBTREE，只在preVisitDirectory方法中返回才有意义(在其他使用,跟CONTINUE同等效果)，与CONTINUE相反
        
        >   SKIP_SIBLINGS，不访问当前文件夹和同级文件夹的所有内容，也就是完全不访问
        
        >   TERMINATE，跟SKIP_SIBLINGS一样