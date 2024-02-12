package chapter06.runnable;

public class WaitThread implements Runnable{
    private int waittime;
    public WaitThread(){
        waittime = (int) (Math.random()*6000);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" sleep "+waittime);
        try {
            Thread.sleep(waittime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" over.");
    }
}
