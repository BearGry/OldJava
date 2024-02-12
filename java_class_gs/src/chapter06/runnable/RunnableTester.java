package chapter06.runnable;

public class RunnableTester {
    public static void main(String args[]){
        new Thread(new WaitThread(),"Thread1").start();
        new Thread(new WaitThread(),"Thread2").start();
        new Thread(new WaitThread(),"Thread3").start();
    }
}
