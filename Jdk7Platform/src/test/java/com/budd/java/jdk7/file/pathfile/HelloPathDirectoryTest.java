package com.budd.java.jdk7.file.pathfile;

import com.budd.java.jdk7.file.pathfile.directory.DirectoriesUtil;
import com.budd.java.util.Print;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static com.budd.java.util.Print.print;
import static com.budd.java.util.Print.printf;

/**
 * @author budd
 * @desc 目录路径的入门研究
 * @since 2020/11/18 17:31
 **/
public class HelloPathDirectoryTest {

    /**
     * 测试创建单级目录、判断是否为目录，并删除该目录以多次测试
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testCreateSingleDirectory() throws IOException, InterruptedException {
        Path singleDirectoryPath = DirectoriesUtil.test.resolve("singleDirectory");
        DirectoriesUtil.walkDelete(singleDirectoryPath);
        try {
            Path newPath = Files.createDirectory(singleDirectoryPath);
            printf("创建目录的路径：%s", newPath);
            printf("是否为目录：%s", Files.isDirectory(newPath));
            Thread.sleep(3000);
        } finally {
            DirectoriesUtil.walkDelete(singleDirectoryPath);
        }
    }

    /**
     * 测试创建多级目录
     *
     * @date 2020年11月18日 11:34:05
     */
    @Test
    public void testCreateMultipleDirectory() throws IOException, InterruptedException {
        Path directoryPath = DirectoriesUtil.test.resolve("multipleDirectory");
        Path multipleDirectoryPath = directoryPath.resolve("error");
        DirectoriesUtil.walkDelete(directoryPath);
        try {
            Path newPath = Files.createDirectories(multipleDirectoryPath);
            printf("创建目录的路径：%s", newPath);
            Thread.sleep(3000);
        } finally {
            DirectoriesUtil.walkDelete(directoryPath);
        }
    }

    /**
     * 测试创建临时目录
     *
     * @date 2020年11月18日 11:34:05
     */
    @Test
    public void testCreateTempDirectory() throws IOException, InterruptedException {
        Path tempDirectoryPath = DirectoriesUtil.test.resolve("tempDirectory");
        DirectoriesUtil.walkDelete(tempDirectoryPath);
        try {
            Files.createDirectories(tempDirectoryPath);
            Path newPath = Files.createTempDirectory(tempDirectoryPath, "DIR_");
            printf("创建目录的路径：%s", newPath);
            Thread.sleep(3000);
        } finally {
            DirectoriesUtil.walkDelete(tempDirectoryPath);
        }
    }

    /**
     * 遍历目录和文件
     *
     * @throws IOException
     * @date 2019年11月3日 12:48:00
     */
    //通用方法：创建路径遍历所需的测试目录和文件
    public void createPathWalkTestDirectoriesFiles() {
        try {
            //创建测试目录test
            DirectoriesUtil.refreshTestDir();
            //在test目录下创建文件
            Files.createFile(DirectoriesUtil.test.resolve("Hello.txt"));
            //在test目录下创建多个组合路径的目录
            DirectoriesUtil.populateTestDir();
            //创建临时目录和临时文件
            Path tempDir = Files.createTempDirectory(DirectoriesUtil.test, "DIR_");
            Files.createTempFile(tempDir, "pre", ".non");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Author budd
     * @Description 删除测试目录
     * @Date 2020/11/18 17:20
     * @Param []
     **/
    public void deleteTestDirectory() {
        try {
            Thread.sleep(3000);
            DirectoriesUtil.walkDelete(DirectoriesUtil.test);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原生方式，java帮我们区分出文件、目录、报错回调
     **/
    @Test
    public void testWalkFileTree() throws IOException {
        createPathWalkTestDirectoriesFiles();

        Files.walkFileTree(DirectoriesUtil.test, new FileVisitor<Path>() {

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                printf("%s: %s,error: %s", "访问文件失败", file, exc.getCause().toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                printf("%s: %s", "开始访问文件夹", dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                printf("%s: %s", "访问文件", file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                printf("%s: %s", "结束访问文件夹", dir);
                return FileVisitResult.CONTINUE;
            }
        });

        deleteTestDirectory();
    }

    /**
     * @return void
     * @Author budd
     * @Description 测试其它方式的路径遍历：Files.newDirectoryStream、Files.walk
     * Files.newDirectoryStream不支持深度遍历
     * Files.walk支持深度遍历
     * 与walkFileTree的区别：完全自定义，比如自己要区分文件或目录、报错处理等
     * @Date 2020/11/17 11:04
     * @Param []
     **/
    @Test
    public void testNewDirectoryStream() throws IOException {
        createPathWalkTestDirectoriesFiles();
        //不支持深度遍历
        Files.newDirectoryStream(DirectoriesUtil.test).forEach(Print::print);
        deleteTestDirectory();
    }

    @Test
    public void testWalkStream() throws IOException {
        createPathWalkTestDirectoriesFiles();
        //支持深度遍历
        Files.walk(DirectoriesUtil.test).forEach(Print::print);
        deleteTestDirectory();
    }
}
