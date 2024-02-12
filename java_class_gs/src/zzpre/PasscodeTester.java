//1、密码6～16，包含字母加数字
package zzpre;

import java.util.Scanner;
public class PasscodeTester {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个密码（6～16，包含字母加数字）:");
        String regex1 = "^[0-9a-zA-Z]{6,16}$";
        String regex2 = ".*[0-9].*";
        String regex3 = ".*[a-zA-Z].*";
        String passCode = sc.nextLine();
        while(!passCode.matches(regex1) || !passCode.matches(regex2) || !passCode.matches(regex3)){
            System.out.println("输入错误，请再此输入（6～16，包含字母加数字）:");
            passCode = sc.nextLine();
        }
        System.out.println("输入成功! 您的密码是： "+passCode);
    }
}
