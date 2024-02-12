package shijuan.biancheng4;

class SumNRunnable implements Runnable{
    private int n;
    public SumNRunnable(int n){
        this.n = n;
    }

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
}
