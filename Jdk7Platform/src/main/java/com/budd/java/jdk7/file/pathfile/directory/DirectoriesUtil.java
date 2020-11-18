package com.budd.java.jdk7.file.pathfile.directory;

import static com.budd.java.util.Print.*;

import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.nio.file.*;


public class DirectoriesUtil {

    public static Path test = Paths.get("test");
    public static String sep = FileSystems.getDefault().getSeparator();
    public static List<String> parts = Arrays.asList("foo", "bar", "baz", "bag");

    /**
     * @return java.nio.file.Path
     * @Author budd
     * @Description //创建目录路径
     * @Date 2020/11/17 11:11
     * @Param []
     **/
    public static Path makeVariantDirectory() {
        Collections.rotate(parts, 1);
        print(parts);
        return Paths.get("test", String.join(sep, parts));
    }

    public static void walkDelete(Path path) {
        try {
            Files.walkFileTree(path, new FileVisitor<Path>() {
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
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.TERMINATE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Author budd
     * @Description //创建测试目录(存在则重建)
     * @Date 2020/11/17 11:21
     * @Param []
     **/
    public static void refreshTestDir() {
        try {
            if (Files.exists(test)) {
                walkDelete(test);
            }
            if (!Files.exists(test)) {
                Files.createDirectory(test);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @Author budd
     * @Description 循环路径组合，建立不同分组的目录、复制DirectoriesUtil内容到新文件：File.txt、创建临时文件(.temp)
     * @Date 2020/11/17 11:47
     * @Param []
     **/
    public static void populateTestDir() {
        try {
            for (int i = 0; i < parts.size(); i++) {
                Path variantDirectory = makeVariantDirectory();
                if (!Files.exists(variantDirectory)) {
                    Files.createDirectories(variantDirectory);
                    Files.copy(Paths.get("DirectoriesUtil.java"),
                            variantDirectory.resolve("File.txt"));
                    Files.createTempFile(variantDirectory, null, null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
