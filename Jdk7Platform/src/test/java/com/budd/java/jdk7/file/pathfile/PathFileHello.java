package com.budd.java.jdk7.file.pathfile;

import org.junit.Test;

import javax.lang.model.element.NestingKind;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 路径与文件类API初探
 *
 * @author HJP
 * @date 2018/6/12 17:22
 * @Description 路径与文件API初探
 */
public class PathFileHello {

    private final static String ROOT_PATH = ".\\src\\main\\resources\\file\\pathfile\\";

    @Test
    public void testNormalize() {
        Path path = Paths.get(ROOT_PATH, "t1.txt\\..\\t2.txt");
        System.out.println(String.format("%s: %s", "原始的路径", path));
        System.out.println(String.format("%s: %s", "格式化的真实路径", path.normalize()));
    }

    @Test
    public void testWalkFileTree() throws IOException {
        Path path = Paths.get(ROOT_PATH);
        Files.walkFileTree(path, new FileVisitor<Path>() {

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println(String.format("%s: %s,error: %s", "访问文件失败", file, exc.getCause().toString()));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println(String.format("%s: %s", "开始访问文件夹", dir));
                if ("pathfile".equals(dir.getFileName().toString())) {
                    return FileVisitResult.CONTINUE;
                }
                return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(String.format("%s: %s", "访问文件", file));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println(String.format("%s: %s", "结束访问文件夹", dir));
                return FileVisitResult.CONTINUE;
            }
        });
    }


    @Test
    public void testExists() throws IOException, InterruptedException {
        //测试文件存在
        Path filePath = Paths.get(ROOT_PATH, "t2.txt");
        System.out.println("文件是否存在: " + Files.exists(filePath, LinkOption.NOFOLLOW_LINKS));

        //测试目录存在
        Path drPath = Paths.get(ROOT_PATH);
        System.out.println("目录是否存在: " + Files.exists(drPath, LinkOption.NOFOLLOW_LINKS));

        /**
         * 测试软链接存在
         */
        Path linkDrPath = Paths.get(ROOT_PATH, "testlink");
        Files.createDirectory(linkDrPath);//创建软链接目录
        Path fileLinkPath = Paths.get(ROOT_PATH, "testlink//t2link.txt");
        Path symbolicLinkPath = Files.createSymbolicLink(fileLinkPath, filePath);//创建软链接
        System.out.println(String.format("文件是否为软链接(符号链接): %s", Files.isSymbolicLink(symbolicLinkPath)));
        System.out.println(String.format("文件是否存在软链接(符号链接): %s", Files.exists(linkDrPath)));

        //删除软链接目录及文件
        Thread.sleep(3000);
        Files.walkFileTree(linkDrPath, new FileVisitor<Path>() {

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println(String.format("%s: %s,error: %s", "访问文件失败", file, exc.getCause().toString()));
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
                System.out.println("删除软链接(符号链接)目录/文件成功");
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void testCreateDirectory() {
        Path path = Paths.get(ROOT_PATH, "thirdpathfile\\t1.txt");
        try {
            Path newPath = Files.createDirectory(path);
            System.out.println("创建目录的路径" + newPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCopy() throws IOException, InterruptedException {

        //复制文件
        Path sourceFilePath = Paths.get(ROOT_PATH, "t1.txt\\..\\t2.txt");
        Path targetFilePath = Paths.get(ROOT_PATH, "t1.txt\\..\\t2副本.txt");

//            Path copyFilePath=Files.copy(sourceFilePath,targetFilePath);//默认不覆盖,存在时报错:java.nio.file.FileAlreadyExistsException
        Path copyFilePath = Files.copy(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);//覆盖内容
        System.out.println(String.format("复制后的路径: %s", copyFilePath.normalize()));

        //复制目录
        Path sourceDrPath = Paths.get(ROOT_PATH, "thirdpathfile");
        Path targetDrPath = Paths.get(ROOT_PATH, "copypath");

//            Path copyDirectoryPath=Files.copy(sourceDrPath,targetDrPath);//默认不覆盖,存在时报错:java.nio.file.FileAlreadyExistsException
        Path copyDirectoryPath = Files.copy(sourceDrPath, targetDrPath, StandardCopyOption.REPLACE_EXISTING);//覆蓋目录
        System.out.println(String.format("复制后的路径: %s", copyDirectoryPath.normalize()));

        Thread.sleep(3000);

        Files.delete(targetFilePath);
        Files.delete(targetDrPath);

        System.out.println("还原成功");
    }

    @Test
    public void testMove() throws IOException, InterruptedException {

        //移动文件
        Path sourceFilePath = Paths.get(ROOT_PATH, "t1.txt\\..\\t4.txt");
        Path targetFilePath = Paths.get(ROOT_PATH, "t1.txt\\..\\t4move.txt");

//            Path copyFilePath=Files.move(sourceFilePath,targetFilePath);
        Path copyFilePath = Files.move(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
        System.out.println("文件移动后的路径" + copyFilePath.normalize());


        //移动目录
        Path sourceDrPath = Paths.get(ROOT_PATH, "thirdpathfile");
        Path targetDrPath = Paths.get(ROOT_PATH, "testfilemove");

//            Path copyDirectoryPath=Files.move(sourceDrPath,targetDrPath);
        Path copyDirectoryPath = Files.move(sourceDrPath, targetDrPath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
        System.out.println("目录移动后的路径" + copyDirectoryPath.normalize());

        Thread.sleep(3000);

        //还原文件和目录
        Files.move(targetFilePath, sourceFilePath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
        Files.move(targetDrPath, sourceDrPath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
        System.out.println("还原成功");
    }

    @Test
    public void testDelete() throws IOException, InterruptedException {

        Path deletePath = Paths.get(ROOT_PATH, "t5delete.txt");
        Files.createFile(deletePath);
        System.out.println(String.format("创建文件: %s", deletePath.normalize()));
        Thread.sleep(3000);
        Files.delete(deletePath);
        System.out.println("删除成功");
    }

}