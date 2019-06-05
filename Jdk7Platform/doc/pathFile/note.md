- 获取路径
    - com.jdk7.pathfile.PathFileHello.testNormalize
        > path.normalize,获取最终真实的路径
- 深度遍历文件/文件夹
    - com.jdk7.pathfile.PathFileHello.testWalkFileTree
        > 1. 可以遍历文件、文件夹
        > 2. 主要方法有visitFileFailed、preVisitDirectory、visitFile、postVisitDirectory,分别是访问文件失败、开始访问文件夹、访问文件、结束访问文件夹
        > 3. 上述方法的返回值都是java.nio.file.FileVisitResult,是个枚举:CONTINUE、TERMINATE、SKIP_SUBTREE、SKIP_SIBLINGS
        > - CONTINUE,允许访问内部的文件
        > - SKIP_SUBTREE,只在preVisitDirectory方法中返回才有意义(在其他使用,跟CONTINUE同等效果),与CONTINUE相反
        > - SKIP_SIBLINGS,不访问当前文件夹和同级文件夹的所有内容,也就是完全不访问
        > - TERMINATE,跟SKIP_SIBLINGS一样