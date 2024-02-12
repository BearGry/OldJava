package chapter06.thread;

public class ThreadTester {
    public static void main(String args[]){
        WaitThread thread1 = new WaitThread("Thread1");
        WaitThread thread2 = new WaitThread("Thread2");
        WaitThread thread3 = new WaitThread("Thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
