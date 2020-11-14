package com.budd.java.jdk7.file.pathfile.basicinfo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.budd.java.util.Print.print;
import static com.budd.java.util.Print.printf;

public class PathInfo {
    private static void show(String id, Object p) {
        printf("%s: %s", id, p);
    }

    private static void info(Path p) {
        show("toString", p);
        //判断文件是否存在
        show("Exists", Files.exists(p));
        //是否常规文件(还有符号链接文件)
        show("RegularFile", Files.isRegularFile(p));
        //判断是否目录
        show("Directory", Files.isDirectory(p));
        //判断绝对路径
        show("Absolute", p.isAbsolute());
        //获取字段名
        show("FileName", p.getFileName());
        //获取上级目录(如果是相对路径则为null)
        show("Parent", p.getParent());
        //获取根目录(如果是相对路径则为null)
        show("Root", p.getRoot());
        print("******************");
    }

    public static void testBasicInfo() {

        System.setProperty("user.dir", "F:\\2019\\study\\Java\\Jdk7Platform\\src\\main\\java\\com\\budd\\java\\jdk7\\file\\pathfile\\basicinfo");

        print(System.getProperty("os.name"));
        printf("%n不存在的文件");
        info(Paths.get("C:", "path", "to", "nowhere", "NoFile.txt"));

        printf("%n存在的文件(相对路径)");
        Path p = Paths.get("PathInfo.java");
        info(p);

        printf("%n存在的文件(绝对路径)");
        Path ap = p.toAbsolutePath();
        info(ap);

        printf("%n存在的目录(绝对路径)");
        info(ap.getParent());

        printf("%n存在的文件(真实路径)");
        try {
            info(p.toRealPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        printf("%n使用URI创建Path");
        URI u = p.toUri();
        printf("URI: %s", u);
        Path pUri = Paths.get(u);
        print(Files.exists(pUri));

        //该方法只是为了兼容旧版本，新版本使用Files+Path完成文件\目录操作，不要使用File类
        File f = ap.toFile();

    }
}