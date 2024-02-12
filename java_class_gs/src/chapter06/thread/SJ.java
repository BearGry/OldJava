package chapter06.thread;

import java.util.Scanner;

public class SJ {
    public static void main(String args[]){
        System.out.println("main thread begin.");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        new Thread(){
            @Override
            public void run() {
                System.out.println(getName()+" begin.");
                int result = 0;
                for(int i=1;i<=n;i++)
                    result += i;
                System.out.println(result);
                System.out.println(getName()+" over.");
            }
        }.start();
        System.out.println("main thread over.");
    }
}
