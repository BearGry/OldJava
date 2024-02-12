package chapter08.txt;

import java.io.FileWriter;
import java.io.IOException;

public class WriteTester {
    public static void main(String args[]) throws IOException {
        FileWriter writer = new FileWriter("test");
        writer.write("我在这！\n");
        writer.write("Hello file");
        writer.close();
    }
}
