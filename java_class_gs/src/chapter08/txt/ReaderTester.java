package chapter08.txt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderTester {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader("test")
        );
        String s;
        while ((s = reader.readLine()) != null){
            System.out.println(s);
        }
        reader.close();
    }
}
