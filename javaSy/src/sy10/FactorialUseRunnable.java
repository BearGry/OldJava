package sy10;
class Factorial2 implements Runnable{
    int n;

    public Factorial2(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" begin.");
        int result = 1;
        for(int i=1;i<=n;i++){
            result *= i;
        }
        System.out.println(n+"! = "+result);
        System.out.println(Thread.currentThread().getName()+" over.");
    }
}
public class FactorialUseRunnable {
    public static void main(String args[]){
        new Thread(new Factorial2(6),"sonThreadUseRunnable").start();
    }
}
