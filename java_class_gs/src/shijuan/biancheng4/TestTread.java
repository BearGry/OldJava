package shijuan.biancheng4;

public class TestTread {
    public static void main(String[] args){
        System.out.println("main thread begin.");
        new SumNThread(5).start();
        new Thread("son2"){
            int n = 5;

            @Override
            public void run() {
                System.out.println(getName()+" thread begin.");
                int sum = 0;
                for(int i=1; i<=n; i++){
                    sum += i;
                }
                System.out.println("from 1 to "+n+" sum = "+sum);
                System.out.println(getName()+" thread over.");
            }
        }.start();
        System.out.println("main thread over.");
    }
}
