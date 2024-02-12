package other;
import java.util.Calendar;

public class Test {
    int a = 1;
    public static void main(String[] args) {
        Calendar c= Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour=c.get(Calendar.HOUR);
        System.out.println(year+"/"+month+"/"+date+"/"+hour);
    }
    public void show(){
        System.out.println(a);
    }
}