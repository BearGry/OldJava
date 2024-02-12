/**
 * 在C盘根目录创建文本文件Hello.txt，并往里写入若干行文本。
 * 从Hello.txt中读取文本并显示在屏幕上。使用字符流和字节流两种模式实现。
 */
package sy6.java1.byte_stream;

import java.io.*;

public class ByteStreamShow {
    public static void main(String[] args) {
        String fileName = "D:\\Hello2.txt";

        // 写入文件
        try (OutputStream os = new FileOutputStream(fileName)) {
            String text = "Hello World!\n" +
                    "Hello Java!\n" +
                    "Goodbye!\n" +
                    "May be see you soon.\n";
            os.write(text.getBytes());
        } catch (IOException e) {
            System.err.println("写入文件出错：" + e.getMessage());
        }

        // 读取文件
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            while(in.available()>0){
                System.out.print((char) in.readByte());
            }
        } catch (IOException e) {
            System.err.println("读取文件出错：" + e.getMessage());
        }
    }
}
