package com.jdk7.pathfile;

import org.junit.Test;

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

    @Test
    public void testNormalize() {
        Path path = Paths.get(".\\src\\com\\jdk7\\pathfile\\testfile\\t1.txt\\..\\t2.txt");
        System.out.println("原始的路径:" + path);
        System.out.println("格式化的真实路径:" + path.normalize());
    }

    @Test
    public void testWalkFileTree() {
        Path path = Paths.get(".\\src\\com\\jdk7\\pathfile\\testfile");
        try {
            Files.walkFileTree(path, new FileVisitor<Path>() {

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.out.println("访问文件失败: " + file + "error:" + exc);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("开始访问文件夹:" + dir);
                    if ("testfile".equals(dir.getFileName().toString())) {
                        return FileVisitResult.CONTINUE;
                    }
                    return FileVisitResult.SKIP_SUBTREE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("访问文件: " + file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    System.out.println("结束访问文件夹: " + dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testExists() {
        Path filePath = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfile\\t1.txt\\..\\t2.txt");
        System.out.println("文件是否存在" + Files.exists(filePath, LinkOption.NOFOLLOW_LINKS));

        Path drPath = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfile");
        System.out.println("目录是否存在" + Files.exists(drPath, LinkOption.NOFOLLOW_LINKS));
    }

    @Test
    public void testCreateDirectory() {
        Path path = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfile\\thirdpathfile\\t1.txt");
        try {
            Path newPath = Files.createDirectory(path);
            System.out.println("创建目录的路径" + newPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCopy() {

        //复制文件
        Path sourceFilePath = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfile\\t1.txt\\..\\t2.txt");
        Path targetFilePath = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfile\\t1.txt\\..\\t2副本.txt");

        try {
//            Path copyPath=Files.copy(sourceFilePath,targetFilePath);
            Path copyPath = Files.copy(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
            System.out.println("复制后的路径" + copyPath.normalize());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //复制目录
        Path sourceDrPath = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfile");
        Path targetDrPath = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfile");

        try {
//            Path copyPath=Files.copy(sourceDrPath,targetDrPath);
            Path copyPath = Files.copy(sourceDrPath, targetDrPath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
            System.out.println("复制后的路径" + copyPath.normalize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMove() {

        //移动文件
        Path sourceFilePath = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfile\\t1.txt\\..\\t4.txt");
        Path targetFilePath = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfile\\t1.txt\\..\\t4move.txt");

        try {
//            Path copyPath=Files.move(sourceFilePath,targetFilePath);
            Path copyPath = Files.move(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
            System.out.println("移动后的路径" + copyPath.normalize());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //移动目录
        Path sourceDrPath = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfilemove");
        Path targetDrPath = Paths.get("E:\\", "\\testfilemove\\testfile副本");

        try {
//            Path copyPath=Files.move(sourceDrPath,targetDrPath);
            Path copyPath = Files.move(sourceDrPath, targetDrPath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
            System.out.println("移动后的路径" + copyPath.normalize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        Path path = Paths.get(".\\src\\com\\jdk7\\pathfile", "\\testfile\\thirdpathfile");
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
