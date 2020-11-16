package com.budd.java.jdk7.file.pathfile;

import static com.budd.java.util.Print.*;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

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

}
