/**
 * 在C盘根目录创建文本文件Hello.txt，并往里写入若干行文本。
 * 从Hello.txt中读取文本并显示在屏幕上。使用字符流和字节流两种模式实现。
 */
package sy6.java1.character_stream;

import java.io.*;

public class CharStreamShow {
    public static void main(String[] args) {
        String fileName = "D:\\Hello.txt";

        // 写入文件
        try (Writer writer = new FileWriter(fileName)) {
            writer.write("Hello World!\n");
            writer.write("Hello Java!\n");
            writer.write("Goodbye!\n");
            writer.write("May be see you soon.\n");
        } catch (IOException e) {
            System.err.println("写入文件出错：" + e.getMessage());
        }

        // 读取文件
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("读取文件出错：" + e.getMessage());
        }
    }
}

