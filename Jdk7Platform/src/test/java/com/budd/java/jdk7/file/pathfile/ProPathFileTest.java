package com.budd.java.jdk7.file.pathfile;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;
import java.util.Set;

import static com.budd.java.util.Print.print;
import static com.budd.java.util.Print.printf;

/**
 * @author budd
 * @desc Path和File进阶研究
 * @since 2020/11/15 17:12
 **/
public class ProPathFileTest {

    private final static String ROOT_PATH = ".\\src\\main\\resources\\file\\pathfile\\";

    /**
     * 测试软链接
     *
     * @date 2020年11月15日 17:12:56
     */
    @Test
    public void testSymbolicLink() throws IOException, InterruptedException {
        Path filePath = Paths.get(ROOT_PATH, "t2.txt");

        //创建软链接目录
        Path linkDrPath = Paths.get(ROOT_PATH, "testlink");
        Files.createDirectory(linkDrPath);

        //创建软链接
        Path fileLinkPath = Paths.get(ROOT_PATH, "testlink//t2link.txt");
        Path symbolicLinkPath = Files.createSymbolicLink(fileLinkPath, filePath);

        printf("%s文件是否为软链接(符号链接): %s", fileLinkPath.getFileName(), Files.isSymbolicLink(symbolicLinkPath));

        //读取符号链接和该链接文本的内容，必须进行符号链接判断，不然会报错：java.nio.file.NotLinkException: 此文件或目录不是一个重分析点。
        if (Files.isSymbolicLink(symbolicLinkPath)) {

            symbolicLinkPath = Files.readSymbolicLink(fileLinkPath);
            printf("readSymbolicLink：%s", symbolicLinkPath);

            List<String> symbolicLinkContents = Files.readAllLines(symbolicLinkPath);
            printf("符号链接内容：%s", symbolicLinkContents);
        }

        //删除软链接目录及文件
        Thread.sleep(3000);
        Files.walkFileTree(linkDrPath, new FileVisitor<Path>() {

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                printf("%s: %s,error: %s", "访问文件失败", file, exc.getCause().toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                print("删除软链接(符号链接)目录/文件成功");
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * start：{文件系统}
     * http://www.1024sky.cn/blog/article/4889
     * https://www.yuque.com/ican/canjava/managing-metadata-file-file-store-attributes#PRLWz
     */

    /**
     * 获取文件的存储位置(如设备、分区、卷，window下是卷：C盘、D盘等)的信息
     * 下面以window为运行环境
     *
     * @date2020年11月15日 23:36:00
     */
    @Test
    public void testFileStoreInfo() throws IOException {
        Path filePath = Paths.get(ROOT_PATH, "t2.txt");
        FileStore fileStore = Files.getFileStore(filePath);
        printf("卷的名称：%s", fileStore.name());
        printf("卷的总大小(字节)：%s", fileStore.getTotalSpace());
        printf("卷的未使用大小(字节)：%s", fileStore.getUsableSpace());
        printf("卷是否只读：%s", fileStore.isReadOnly());
        printf("卷的文件系统：%s", fileStore.type());

        printf("--------------------------------------------------------------------------------------------属性视图");

        printf("%nFileAttributeView");
        //获取支持属性视图
        BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(filePath, BasicFileAttributeView.class);
        printf("基础属性视图(最后修改时间)：%s", basicFileAttributeView.readAttributes().lastModifiedTime());
        //修改最后修改时间
        basicFileAttributeView.setTimes(FileTime.fromMillis(System.currentTimeMillis()), null, null);
        printf("基础属性视图(修改后的最后修改时间)：%s", basicFileAttributeView.readAttributes().lastModifiedTime());

        printf("%nBasicFileAttributes");
        BasicFileAttributes basicFileAttributes = Files.readAttributes(filePath, BasicFileAttributes.class);
        printf("基础属性视图(最后修改时间)：%s", basicFileAttributes.lastModifiedTime());
    }

    /**
     * 获取全局的文件系统
     *
     * @date 2020年11月17日 20:26:57
     */
    @Test
    public void testGlobalFileStream() {
        printf("系统：%s", System.getProperty("os.name"));
        FileSystem fsys = FileSystems.getDefault();

        print("---------------------------------------------------------------------------------------获取所有存储区域");
        for (FileStore fs : fsys.getFileStores()) {
            printf("File Store：%s", fs);
        }

        print("-----------------------------------------------------------------------------------获取所有存储区域的路径");
        for (Path rd : fsys.getRootDirectories()) {
            printf("Root Directory：%s", rd);
        }

        print("------------------------------------------------------------------------获取当前文件系统支持的属性视图列表");
        Path path = Paths.get(ROOT_PATH, "t2.txt");
        //获取当前文件系统支持的属性视图列表
        Set<String> supportedFileAttributeViews = fsys.supportedFileAttributeViews();
        printf("文件系统所支持的属性视图：%s", supportedFileAttributeViews);
        //UNIX系统、LINUX等操作系统的文件系统
        if (supportedFileAttributeViews.contains("posix")) {
            try {
                printf("PosixFilePermissions：%s", Files.getPosixFilePermissions(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        print("-----------------------------------------------------------------------------------------------基础信息");
        printf("分隔符：%s", fsys.getSeparator());
        printf("当前文件系统是否启用：%s", fsys.isOpen());
        printf("当前文件系统是否只读：%s", fsys.isReadOnly());

        print("---------------------------------------------------------------------用于查询用户或用户组的用户主体查询服务");
        UserPrincipalLookupService userPrincipalLookupService = fsys.getUserPrincipalLookupService();
        try {
            printf("UserPrincipalLookupService：%s", userPrincipalLookupService.lookupPrincipalByName("budd"));
            //用户不存在，报错：java.nio.file.attribute.UserPrincipalNotFoundException
            printf("UserPrincipalLookupService：%s", userPrincipalLookupService.lookupPrincipalByName("orange"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        print("--------------------------------------------------------------文件、目录操作的底层实现-FileSystemProvider");
        FileSystemProvider fileSystemProvider = fsys.provider();
        try {
            printf("判断是否隐藏：%s", fileSystemProvider.isHidden(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * end：{文件系统}
     */

}
