package com.budd.java.jdk7.file.pathfile;

import static com.budd.java.util.Print.*;

import com.budd.java.jdk7.file.pathfile.basicinfo.PathInfo;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 路径与文件类API初探
 *
 * @author HJP
 * @date 2018/6/12 17:22
 * @Description 路径与文件API初探
 */
public class PathFileHello {

    private final static String ROOT_PATH = ".\\src\\main\\resources\\file\\pathfile\\";

    /**
     * path的基本信息
     */
    @Test
    public void testBasicInfo() {
        PathInfo.testBasicInfo();
    }

    /**
     * Path路径的各部分
     *
     * @date 2020年11月15日 11:03:53
     */
    @Test
    public void testPartOfPath() {
        System.setProperty("user.dir", "F:\\2019\\study\\Java\\Jdk7Platform\\src\\test\\java\\com\\budd\\java\\jdk7\\file\\pathfile");
        print(System.getProperty("os.name"));

        Path p = Paths.get("PathFileHello.java").toAbsolutePath();

        print("获取path各个部分(使用getName(i))");
        printf("%s：%s ：%s", "part", "start with", "end with");
        for (int i = 0; i < p.getNameCount(); i++) {
            Path pp = p.getName(i);
            printf("%s：%s ：%s", pp, p.startsWith(pp), p.endsWith(pp));
        }

        //使用endsWith判断后缀是不行，可以使用toString字符串匹配
        printf("---path.ends with '.java': %s", p.endsWith(".java"));
        printf("---toString().ends with '.java': %s%n", p.toString().endsWith(".java"));

        print("获取path各个部分(直接使用循环)");
        printf("%s：%s ：%s", "part", "start with", "end with");
        for (Path pp : p) {
            printf("%s：%s ：%s", pp, p.startsWith(pp), p.endsWith(pp));
        }
        printf("---Starts with %s %s", p.getRoot(), p.startsWith(p.getRoot()));
    }


    /**
     * @return void
     * @Author budd
     * @Description Path的增减操作
     * @Date 2020/11/16 16:06
     * @Param []
     **/
    Path path = Paths.get(".").toAbsolutePath().normalize();

    void showAddSubtractPathInfo(String title, Path result) {
        print(title);
        printf("待减的路径：%s", result);
        if (result.isAbsolute()) {
            printf("绝对路径转变成相对当前path的相对路径：%s", path.relativize(result));
        } else {
            printf("相对路径(无须转变)：%s", result);
        }
        try {
            printf("真实路径：%s%n", result.toRealPath());
        } catch (IOException e) {
            printf("真实路径报错(不存在)：%s%n", e.getMessage());
        }
    }

    @Test
    public void testAddSubtractPaths() throws IOException {
        String ROOT_PATH = "src\\test\\java\\com\\budd\\java\\jdk7\\file\\pathfile";
        printf("当前系统：%s", System.getProperty("os.name"));
        printf("当前基准路径：%s", path);

        printf("----------------------------------------------------------------绝对路径进行relativize、resolve操作%n");

        //相对路径处理
        Path absolutePath = Paths.get(ROOT_PATH, "PathFileHello.java").toAbsolutePath();
        showAddSubtractPathInfo("相对路径处理", absolutePath);

        //拼接路径 + 相对路径处理
        Path resolvePath = absolutePath.getParent().getParent()
                .resolve("strings").resolve("..")
                .resolve(absolutePath.getParent().getFileName());
        showAddSubtractPathInfo("拼接路径 + 相对路径处理", resolvePath);

        //拼接路径 + 格式化 + 相对路径处理
        showAddSubtractPathInfo("拼接路径 + 格式化 + 相对路径处理", resolvePath.normalize());

        printf("----------------------------------------------------------------../../ 相对路径进行relativize、resolve操作%n");

        Path previousRelativizePath = Paths.get(ROOT_PATH, "..", "..");
        showAddSubtractPathInfo("相对路径处理", previousRelativizePath);
        showAddSubtractPathInfo("格式化 + 相对路径处理", previousRelativizePath.normalize());
        showAddSubtractPathInfo("转成绝对路径，再格式化 + 相对路径处理", previousRelativizePath.toAbsolutePath().normalize());

        printf("----------------------------------------------------------------./ 相对路径进行relativize、resolve操作%n");

        Path currentAbsolutePath = Paths.get(ROOT_PATH, ".").toAbsolutePath();
        Path currRelPath = currentAbsolutePath.resolve(previousRelativizePath);
        showAddSubtractPathInfo("相对路径处理", currRelPath);
        showAddSubtractPathInfo("格式化 + 相对路径处理", currRelPath.normalize());

        printf("----------------------------------------------------------------空 相对路径进行relativize、resolve操作%n");

        Path emptyPath = Paths.get(ROOT_PATH, "").toAbsolutePath();
        showAddSubtractPathInfo("相对路径处理", emptyPath);
        showAddSubtractPathInfo("在当前path的上级目录追加路径 + 相对路径处理", emptyPath.resolveSibling("Jdk8platform"));
        showAddSubtractPathInfo("不存在的路径 + 相对路径处理", Paths.get(ROOT_PATH, "nonexistent"));
    }

    @Test
    public void testNormalize() {
        Path path = Paths.get(ROOT_PATH, "t1.txt\\..\\t2.txt");
        printf("%s: %s", "原始的路径", path);
        printf("%s: %s", "格式化的真实路径", path.normalize());
    }

    @Test
    public void testExists() {
        //测试文件存在
        Path filePath = Paths.get(ROOT_PATH, "t2.txt");
        printf("文件是否存在: %s", Files.exists(filePath, LinkOption.NOFOLLOW_LINKS));

        //测试目录存在
        Path drPath = Paths.get(ROOT_PATH);
        printf("目录是否存在: %s", Files.exists(drPath, LinkOption.NOFOLLOW_LINKS));

        //测试文件不存在
        printf("文件是否不存在: %s", Files.notExists(drPath, LinkOption.NOFOLLOW_LINKS));
    }

    /**
     * 获取文件/目录的状态
     *
     * @date 2020年11月15日 17:53:56
     */
    private void showStateInfo(String title, Path path) throws IOException {
        print(title);
        //根据不同文件系统不同，采取不同的方式(读取文件权限、访问控制列表、其他文件属性)，测试改文件\目录是否能被访问
        //isExecutable，可能有些文件系统，该操作不是原子性操作
        printf("是否可执行：%s", Files.isExecutable(path));
        printf("是否可读：%s", Files.isReadable(path));
        printf("是否可写：%s", Files.isWritable(path));
        printf("是否隐藏：%s", Files.isHidden(path));
        printf("是否符号链接：%s", Files.isSymbolicLink(path));
        printf("最后修改时间：%s", Files.getLastModifiedTime(path));
        printf("拥有者：%s", Files.getOwner(path));
        printf("类型：%s%n", Files.probeContentType(path));
    }

    @Test
    public void testState() throws IOException {
        Path filePath = Paths.get(ROOT_PATH, "t2.txt");
        Path drPath = Paths.get(ROOT_PATH);

        showStateInfo("文件", filePath);
        showStateInfo("目录", drPath);
    }

    /**
     * 获取大小(字节单位)
     *
     * @date 2020年11月15日 23:20:18
     */
    @Test
    public void testSize() throws IOException {
        Path filePath = Paths.get(ROOT_PATH, "t2.txt");
        printf("大小：%s", Files.size(filePath));
    }




    @Test
    public void testCopy() throws IOException, InterruptedException {

        //复制文件
        Path sourceFilePath = Paths.get(ROOT_PATH, "t1.txt\\..\\t2.txt");
        Path targetFilePath = Paths.get(ROOT_PATH, "t1.txt\\..\\t2副本.txt");

//            Path copyFilePath=Files.copy(sourceFilePath,targetFilePath);//默认不覆盖,存在时报错:java.nio.file.FileAlreadyExistsException
        Path copyFilePath = Files.copy(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);//覆盖内容
        printf("复制后的路径: %s", copyFilePath.normalize());

        //复制目录
        Path sourceDrPath = Paths.get(ROOT_PATH, "thirdpathfile");
        Path targetDrPath = Paths.get(ROOT_PATH, "copypath");

//            Path copyDirectoryPath=Files.copy(sourceDrPath,targetDrPath);//默认不覆盖,存在时报错:java.nio.file.FileAlreadyExistsException
        Path copyDirectoryPath = Files.copy(sourceDrPath, targetDrPath, StandardCopyOption.REPLACE_EXISTING);//覆蓋目录
        printf("复制后的路径: %s", copyDirectoryPath.normalize());

        Thread.sleep(3000);

        Files.delete(targetFilePath);
        Files.delete(targetDrPath);

        printf("还原成功");
    }

    @Test
    public void testMove() throws IOException, InterruptedException {

        //移动文件
        Path sourceFilePath = Paths.get(ROOT_PATH, "t1.txt\\..\\t4.txt");
        Path targetFilePath = Paths.get(ROOT_PATH, "t1.txt\\..\\t4move.txt");

//            Path copyFilePath=Files.move(sourceFilePath,targetFilePath);
        Path copyFilePath = Files.move(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
        printf("文件移动后的路径：%s", copyFilePath.normalize());


        //移动目录
        Path sourceDrPath = Paths.get(ROOT_PATH, "thirdpathfile");
        Path targetDrPath = Paths.get(ROOT_PATH, "testfilemove");

//            Path copyDirectoryPath=Files.move(sourceDrPath,targetDrPath);
        Path copyDirectoryPath = Files.move(sourceDrPath, targetDrPath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
        printf("目录移动后的路径：%s", copyDirectoryPath.normalize());

        Thread.sleep(3000);

        //还原文件和目录
        Files.move(targetFilePath, sourceFilePath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
        Files.move(targetDrPath, sourceDrPath, StandardCopyOption.REPLACE_EXISTING);//强制替换(包括内容)
        printf("还原成功");
    }

    @Test
    public void testDelete() throws IOException, InterruptedException {

        Path deletePath = Paths.get(ROOT_PATH, "t5delete.txt");
        Files.createFile(deletePath);
        printf("创建文件: %s", deletePath.normalize());
        Thread.sleep(3000);
        Files.delete(deletePath);
        printf("删除成功");
    }

    /**
     * 测试jdk7-Path/Files写入/读取码点
     *
     * @throws IOException
     */
    /**
     * 制作码点组
     *
     * @return
     */
    private final String BASIC_CODEPOINT_PATH = ".\\src\\main\\resources\\string\\basicCodepointPath.txt";

    private char[] makeBasicCodepoint(int[] codePointsIn) {
        int[] codePoints = null;
        if (codePointsIn == null)
            codePoints = new int[]{0xd801, 0xd802, 0xdf00, 0xdf01, 0x34};
        else codePoints = codePointsIn;

        String codePointStr = new String(codePoints, 0, codePoints.length);
        char[] codePointChars = codePointStr.toCharArray();

        for (char singleChar : codePointChars) {
            printf("字符=%s,16进制=%s", singleChar, Integer.toHexString(singleChar));
        }
        return codePointChars;
    }

    @Test
    public void testFilesBasicCodePoint() throws IOException, InterruptedException {
        Path plusCharPath = Paths.get(BASIC_CODEPOINT_PATH);
        Files.createFile(plusCharPath);
        try {
            char[] codePointChars = makeBasicCodepoint(null);

            List<String> codePointCharList = new ArrayList<String>();
            for (char singleChar : codePointChars) {
                codePointCharList.add(String.valueOf(singleChar));
            }

            Files.write(plusCharPath, codePointCharList, Charset.forName("utf-16"), StandardOpenOption.WRITE);
            byte[] bytes = Files.readAllBytes(plusCharPath);
            printf(new String(bytes));

        } finally {
            //还原
            Thread.sleep(5000);
            Files.delete(plusCharPath);
        }

    }


    /**
     * start：{目录}
     */
    /**
     * 测试创建目录、判断是否为目录，并删除该目录以多次测试
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testCreateDirectory() throws IOException, InterruptedException {
        Path path = Paths.get(ROOT_PATH, "createDirectory");
        try {
            Path newPath = Files.createDirectory(path);
            printf("创建目录的路径：%s", newPath);
            printf("是否为目录：%s", Files.isDirectory(newPath));
            Thread.sleep(3000);
        } finally {
            Files.deleteIfExists(path);
        }
    }

    /**
     * 遍历目录和文件
     * @date 2019年11月3日 12:48:00
     * @throws IOException
     */
    @Test
    public void testWalkFileTree() throws IOException {
        Path path = Paths.get(ROOT_PATH);
        Files.walkFileTree(path, new FileVisitor<Path>() {

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                printf("%s: %s,error: %s", "访问文件失败", file, exc.getCause().toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                printf("%s: %s", "开始访问文件夹", dir);
                if ("pathfile".equals(dir.getFileName().toString())) {
                    return FileVisitResult.CONTINUE;
                }
                return FileVisitResult.SKIP_SUBTREE;
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
    }

    /**
     * end：{目录}
     */

}
