/**
 *1.编写线程程序，在新线程中完成计算某个整数的阶乘。分别用Thread类和Runnable接口实现。
 */
package sy10;
class Factorial1 extends Thread{
    int n;

    public Factorial1(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println(getName()+" begin.");
        int result = 1;
        for(int i=1;i<=n;i++){
            result *= i;
        }
        System.out.println(n+"! = "+result);
        System.out.println(getName()+" over.");
    }
}
public class FactorialUseThread {
    public static void main(String args[]){
        new Factorial1(5).run();
    }
}
