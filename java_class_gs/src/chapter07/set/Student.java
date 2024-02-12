package chapter07.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student){
            Student stu = (Student) obj;
            if(age == stu.age && name.equals(stu.name))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name+" name's hashcode is "+name.hashCode();
    }

    public static void main(String[] args){
        Set<Student> set = new HashSet<Student>();
        Student s1 = new Student("Tom", 18);
        Student s2 = new Student("Tom", 19);
        Student s3 = new Student("Mack", 18);
        set.add(s1);
        set.add(s2);
        set.add(s3);
        Iterator<Student> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
