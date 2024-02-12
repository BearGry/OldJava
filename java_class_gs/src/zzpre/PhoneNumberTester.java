//2.手机号是否合法（例如长度是否为11位，第一位是否为1)
package zzpre;

import java.util.Scanner;

public class PhoneNumberTester {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String regex = "^1[0-9]{10}$";
        boolean flag;
        for(int i=1; i<=5; i++){
            System.out.println("Please input a phone : ");
            String phoneNumber = sc.nextLine();
            flag = phoneNumber.matches(regex);
            if(flag)
                System.out.println("It is a phone number.");
            else
                System.out.println("It isn't a phone number.");
        }
    }
}
