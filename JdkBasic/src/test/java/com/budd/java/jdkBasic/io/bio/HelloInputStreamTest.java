package com.budd.java.jdkBasic.io.bio;

import static com.budd.java.util.Print.*;

import org.junit.Test;

import java.io.*;

/**
 * @author budd
 * @desc 输入流入门研究
 * @since 2020/3/1 15:02
 **/
public class HelloInputStreamTest {

    /**
     * @author HJP
     * @date 2018年5月18日 16:47:00
     * @Description 获取行号的输入流
     */
    @Test
    public void testLineNumberInputStream() throws IOException {
        //手动设置行号
        print("手动设置行号");
        LineNumberInputStream manualLineNumberIs = new LineNumberInputStream(new StringBufferInputStream("123456789"));
        int numChar;
        int i = 0;
        while ((numChar = manualLineNumberIs.read()) != -1) {
            manualLineNumberIs.setLineNumber(i++);
            printf("%s:%s\n", numChar, manualLineNumberIs.getLineNumber() + 1);
        }

        print("\n自动设置行号");
        //根据\r、\n自动设置行号
        LineNumberInputStream autoLineNumberIs = new LineNumberInputStream(new StringBufferInputStream("1\n2\r3456789"));
        int numChar1;
        while ((numChar1 = autoLineNumberIs.read()) != -1) {
            printf("%s:%s\n", numChar1, autoLineNumberIs.getLineNumber() + 1);
        }
    }

    /**
     * @author HJP
     * @date 2018年5月18日 16:58:00
     * @Description 测试回退流的输入
     */
    @Test
    public void testPushBackInputStream() throws IOException {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream("123456789"));

        print("\n推回到流前");
        int readChar = pushbackInputStream.read();
        int readChar1 = pushbackInputStream.read();
        printf("读取的字节列表：%s,%s\n", (char) readChar, (char) readChar1);

        print("\n推回到流后");
        pushbackInputStream.unread(readChar);
        printf("再次读取的字节列表：%s,%s\n", (char) pushbackInputStream.read(), (char) pushbackInputStream.read());
    }

    /**
     * start
     *
     * @author HJP
     * @date 2018年5月18日 16:58:00
     * @Description BufferedInputStream-API使用
     */
    /**
     * @Description mark和reset
     */
    @Test
    public void testMarkAndReset() {
        try {
            BufferedInputStream in = new BufferedInputStream(new StringBufferInputStream("123456789987654321123456789"), 10);
            int avail = in.available();
            printf("可读字节数：%s\n", avail);
            print("除最后十个字节外，读出所有字节");
            for (int i = 0; i < avail - 10; i++) {
                printf(",%s", in.read());
            }

            printf("\n可读字节数：%s\n", in.available());

            if (!in.markSupported()) {
                print("make/reset not supported!");
                return;
            } else
                print("make/reset supported!");

            print("标记");
            in.mark(2);

            printf("读取的字符集：%s,%s\n", in.read(), in.read());

            print("重置");
            in.reset();
            printf("读取的字符集：%s,%s\n", in.read(), in.read());
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws IOException
     * @Description skip()：跳过指定字节数。有如下情况
     * int avail = count - pos;BufferedInputStream的count和pos。计算当前这批的缓冲区的待读取字节数
     * 1、avail<=0(缓冲区已被读取完全)
     * 1.1、markpos为空，则调用InputStream的skip()
     * 1.2、markpos不为空，填充缓冲区，并重新计算avail:count - pos
     * <p>
     * 比较avail和n(入参)，n超出avail，采用avail，否则采用n。记作skipped
     * pos叠加skipped，作为新的读取位置
     * <p>
     * available(),StringBufferInputStream的count和pos相减，count为字节数
     * ，pos比较特殊，当前这批的缓冲区即使没读取完，pos是下一批缓冲区的开始位置(如果缓冲区大小为10，当前这批为第二批，则pos为2*10=20)
     */
    @Test
    public void testSkip() throws IOException {
        BufferedInputStream in = new BufferedInputStream(new StringBufferInputStream("123456789987654321123456789"), 10);
        int avail = in.available();
        printf("可读字节数：%s\n", avail);
        print("除最后十个字节外，读出所有字节");
        for (int i = 0; i < avail - 10; i++) {
            printf(",%s", in.read());
        }

        print("标记");
        System.out.println("可读字节数：" + in.available());
        System.out.println("使用skip方法跳过两个字节");
        long skip = in.skip(2);
        System.out.println("可读字节数：" + in.available());
        printf("读取的字符集：%s,%s\n", in.read(), in.read());

        System.out.println("可读字节数：" + in.available());
    }

    /**
     * @throws IOException
     * @Description mark对skip和read的影响
     * in.mark(2)，markPos为7
     * <p>
     * in.read(), in.read(), in.read()。读取三个字节
     * <p>
     * in.skip(2)，跳过2个字节：当前这批的缓冲区已经读満，所以需要填充下一批缓冲区；因为markPos大于0
     * int sz = pos - markpos;//10-7=3
     * System.arraycopy(buffer, markpos, buffer, 0, sz);
     * 从buffer的第7个位置开始复制3个字节到buffer的第0~2位置：变化如下：
     * 0 = 56      0 = 49  👈
     * 1 = 55      1 = 49  👈
     * 2 = 54      2 = 50  👈
     * 3 = 53      3 = 53
     * 4 = 52  =>  4 = 52
     * 5 = 51      5 = 51
     * 6 = 50      6 = 50
     * 7 = 49 👆   7 = 49
     * 8 = 49 👆   8 = 49
     * 9 = 50 👆   9 = 50
     * <p>
     * in.read(), in.read()，读取两个字节：
     * 当前这批的缓冲区如下：
     * 0 = 49
     * 1 = 49
     * 2 = 50
     * 3 = 51
     * 4 = 52
     * 5 = 53
     * 6 = 54
     * 7 = 45
     * 8 = 46
     * 9 = 57
     * 前面已经读取三个字节(49、49、50)
     * 前面跳过两个字节(51、52)
     * 所以开始读取的位置为5，读取两个字节为53、54
     * <p>
     * 结论：当前这批的缓冲区満了之后，调用fill()填充新缓冲区，因有mark，而在已满的缓冲区复制替换一部分字节。并没有影响读取、
     *
     */
    @Test
    public void testMarkSkip() throws IOException {
        BufferedInputStream in = new BufferedInputStream(new StringBufferInputStream("123456789987654321123456789"), 10);
        int avail = in.available();
        printf("可读字节数：%s\n", avail);
        print("除最后十个字节外，读出所有字节");
        for (int i = 0; i < avail - 10; i++) {
            printf(",%s", in.read());
        }

        print("标记");
        in.mark(2);

        printf("读取的字符集：%s,%s,%s\n", in.read(), in.read(), in.read());
        System.out.println("可读字节数：" + in.available());
        System.out.println("使用skip方法跳过两个字节");
        long skip = in.skip(2);
        System.out.println("可读字节数：" + in.available());
        printf("读取的字符集：%s,%s\n", in.read(), in.read());

        System.out.println("执行reset方法后，可读字节数：" + in.available() + "\n");
    }
    /**
     * end
     * @author HJP
     * @date 2018年5月18日 16:58:00
     * @Description BufferedInputStream-API使用
     */
}
