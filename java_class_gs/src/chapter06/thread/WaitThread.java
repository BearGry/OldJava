package chapter06.thread;

public class WaitThread extends Thread{
    int waittime;
    public WaitThread(String name){
        super(name);
        waittime = (int)(Math.random()*6000);
    }

    @Override
    public void run() {
        System.out.println(getName()+" sleep "+waittime);
        try {
            sleep(waittime);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println(getName()+" over.");
    }
}
