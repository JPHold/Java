package com.budd.java.jdk7.file.pathfile.directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class DirectoriesUtil {

    private static String ROOT_PATH;

    /**
     * @Author budd
     * @Description
     * 使用IDEA，working directory虽然有$MODULE_WORKING_DIR$，并不是当前类所在的路径(而使用命令执行却是在当前类所在的路径)
     * 因此命令执行执行会报找不到文件、目录的错误，在idea是不会的
     * @Date 2020/11/18 16:21
     * @Param
     * @return
     **/
    static {
        String packageName = DirectoriesUtil.class.getPackage().getName();
        packageName = packageName.replace(".", File.separator);
        String packagePath = String.format("%s%s%s%s%s%s%s"
                , "src", File.separator
                , "main", File.separator
                , "java", File.separator,
                packageName);
        ROOT_PATH = Paths.get(packagePath).toString();
    }

    public static Path test = Paths.get(ROOT_PATH, "test");
    public static String sep = FileSystems.getDefault().getSeparator();
    public static List<String> parts = Arrays.asList("foo", "bar", "baz", "bag");

    /**
     * @return java.nio.file.Path
     * @Author budd
     * @Description //制作目录路径
     * @Date 2020/11/17 11:11
     * @Param []
     **/
    public static Path makeVariantDirectory() {
        Collections.rotate(parts, 1);
        return Paths.get(ROOT_PATH, "test", String.join(sep, parts));
    }

    /**
     * @return void
     * @Author budd
     * @Description 遍历删除
     * @Date 2020/11/17 11:11
     * @Param [path]
     **/
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
                    try {
                        Files.delete(dir);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw e;
                    }
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
     * @Description 循环路径组合，多级创建不同组合路径的目录、复制DirectoriesUtil内容到新文件：File.txt、创建临时文件(.temp)
     * @Date 2020/11/17 11:47
     * @Param []
     **/
    public static void populateTestDir() {
        try {
            for (int i = 0; i < parts.size(); i++) {
                Path variantDirectory = makeVariantDirectory();
                if (!Files.exists(variantDirectory)) {
                    Files.createDirectories(variantDirectory);
                    Files.copy(Paths.get(ROOT_PATH, "DirectoriesUtil.java"),
                            variantDirectory.resolve("File.txt"));
                    Files.createTempFile(variantDirectory, null, null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
