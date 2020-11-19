package com.budd.java.jdk7.file.pathfile;

import com.budd.java.jdk7.file.pathfile.directory.DirectoriesUtil;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.budd.java.util.Print.printf;

/**
 * @author budd
 * @desc 目录路径的进阶研究
 * @since 2020/11/18 17:32
 **/
public class ProPathDirectoryTest {

    /**
     * start：路径监听
     */
    /**
     * 测试单个path的状态变更
     *
     * @date 2020年11月17日 23:49:30
     */
    @Test
    public void testSinglePathStateChange() throws IOException, InterruptedException {
        Path stateChangePath = DirectoriesUtil.test.resolve("stateChange");
        //创建测试目录test
        DirectoriesUtil.refreshTestDir();
        //在test目录下创建多个组合路径的目录
        DirectoriesUtil.populateTestDir();
        //创建stateChange目录
        Files.createDirectories(stateChangePath);
        Files.createFile(stateChangePath.resolve("Hello.txt"));
        Files.createFile(stateChangePath.resolve("Hello2.txt"));

        WatchService watcher = FileSystems.getDefault().newWatchService();
        //当前path监听删除时间
        stateChangePath.register(watcher, StandardWatchEventKinds.ENTRY_DELETE);

        //等待3000毫秒执行删除目录
        Executors.newSingleThreadScheduledExecutor()
                .schedule(() -> DirectoriesUtil.walkDelete(stateChangePath),
                        3000, TimeUnit.MILLISECONDS);

        //阻塞操作，等删除事件发生完毕
        WatchKey key = watcher.take();
        for (WatchEvent event : key.pollEvents()) {
            printf("event.context(): %s, event.count()：%s, event.kind(): %s", event.context(), event.count(), event.kind());
        }

        DirectoriesUtil.walkDelete(DirectoriesUtil.test);
    }

    /**
     * 测试多个path(深度)的状态变更
     *
     * @param dir
     * @date 2020年11月18日 00:00:56
     */
    private static void watchDirStateChange(Path dir) {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            dir.register(watcher, StandardWatchEventKinds.ENTRY_DELETE);
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    WatchKey key = watcher.take();
                    for (WatchEvent evnet : key.pollEvents()) {
                        printf("%nevnet.context(): %s, evnet.count(): %s, evnet.kind(): %s", evnet.context(), evnet.count(), evnet.kind());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMultiplePathStateChange() throws IOException, InterruptedException {
        //创建测试目录test
        DirectoriesUtil.refreshTestDir();
        //在test目录下创建多个组合路径的目录
        DirectoriesUtil.populateTestDir();

        Files.walk(DirectoriesUtil.test)
                .filter(Files::isDirectory)
                .forEach(ProPathDirectoryTest::watchDirStateChange);

        Files.walk(DirectoriesUtil.test).filter((path) -> path.toString()
                .endsWith(".txt")).forEach(path -> {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 如果删除顶级目录：test会报错java.nio.file.DirectoryNotEmptyException: src\main\java\com\budd\java\jdk7\file\pathfile\directory\test\bag
     * 猜测原因是：没有按照进入的顺序，逆序删除目录，而是提前删除更前的目录，而且偶尔发生
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testExceptionMultiplePathStateChange() throws IOException, InterruptedException {
        //创建测试目录test
        DirectoriesUtil.refreshTestDir();
        //在test目录下创建多个组合路径的目录
        DirectoriesUtil.populateTestDir();

        Files.walk(DirectoriesUtil.test)
                .filter(Files::isDirectory)
                .forEach(ProPathDirectoryTest::watchDirStateChange);

        Thread.sleep(3000);
        DirectoriesUtil.walkDelete(DirectoriesUtil.test);
    }

    /**
     * end：路径监听
     */
}
