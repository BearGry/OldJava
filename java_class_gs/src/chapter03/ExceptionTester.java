package chapter03;

import chapter04.clonecopy.Birthdate;
import chapter04.clonecopy.Student;

import java.util.InputMismatchException;
import java.util.Scanner;
import chapter04.clonecopy.Student;
public class ExceptionTester {
    public static void main(String args[]){
        boolean ok = false;
        while (!ok){
            try{
                Scanner scanner = new Scanner(System.in);
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                double c = a/b;
                System.out.println(c);
                ok = true;
            }catch (InputMismatchException formatException){
                System.out.println("Number format exception!");
            }catch (ArithmeticException arithmeticException){
                System.out.println("除数不能为0");
            }
        }
        Student student = new Student(1,"jack",new Birthdate(1999,8,8));
    }
}
