package shijuan.biancheng4;

public class TestRunnable {
    public static void main(String[] args){
        System.out.println("main thread begin.");
        SumNRunnable sumNRunnable = new SumNRunnable(6);
        Thread thread = new Thread(sumNRunnable,"sonThread1");
        thread.start();
        new Thread(new Runnable() {
            int n = 6;
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" thread begin.");
                int sum = 0;
                for(int i=1; i<=n; i++){
                    sum += i;
                }
                System.out.println("from 1 to "+n+" sum = "+sum);
                System.out.println(Thread.currentThread().getName()+" thread over.");
            }
        },"sonThread2").start();
        System.out.println("main thread over.");
    }
}
