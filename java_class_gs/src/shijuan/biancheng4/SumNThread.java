package shijuan.biancheng4;

/**
 * 编写线程程序，在新线程中完成计算从 1 到 n 的和，用 Thread 类实现。
 */
class SumNThread extends Thread{
    private int n;
    public SumNThread(int n){
        this.n = n;
    }
    @Override
    public void run(){
        System.out.println("son thread begin.");
        int sum = 0;
        for(int i=1; i<=n; i++){
            sum += i;
        }
        System.out.println("from 1 to "+n+" sum = "+sum);
        System.out.println("son thread over.");
    }
}
