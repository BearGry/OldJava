/**
 * 1.用两个线程模拟存票、售票过程。
 * 假定开始售票处并没有票，一个线程往里存票，另外一个线程则往外卖票
 * 新建一个票类对象，让存票和售票线程都访问它。本例采用两个线程共享同一个数据对象来实现对同一份数据的操作。
 */
package sy11;

public class Ticket {
    //生产到多少号了
    int currentNum = 0;
    //买到多少号了
    int selledMaxNum = 0;
    int size;

    public Ticket(int size) {
        this.size = size;
    }
    public synchronized void sell(){
        if(selledMaxNum < currentNum){
            System.out.println("Sell the "+(++selledMaxNum)+" ticket.");
        }
    }
    public synchronized void produce(){
        if(currentNum < size){
            System.out.println("Produce the "+(++currentNum)+" ticket.");
        }
    }
}
