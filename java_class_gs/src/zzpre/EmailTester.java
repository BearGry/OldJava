//3.邮箱是否合法
package zzpre;

import java.util.Scanner;

public class EmailTester {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String regex = "^[1-9][0-9]{7,10}@qq\\.com$"; //这里qq邮箱设置的为8~11位
        boolean flag;
        String emailCode;
        for(int i=1; i<=5; i++){
            System.out.println("Please input a email code : ");
            emailCode = sc.nextLine();
            flag = emailCode.matches(regex);
            if(flag)
                System.out.println("It is a email code.");
            else
                System.out.println("It isn't a email code.");
        }
    }
}
