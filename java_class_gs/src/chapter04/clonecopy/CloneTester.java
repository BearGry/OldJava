package chapter04.clonecopy;

public class CloneTester {
    public static void main(String args[]) throws CloneNotSupportedException {
        Birthdate date = new Birthdate(2003,7,31);
        Student stu1 = new Student(1,"jack",date);
        Student stu2 = stu1.clone();
        System.out.println(stu2 == stu1);
        System.out.println(stu2.getBirthdate().equals(stu1.getBirthdate()));
    }
}
