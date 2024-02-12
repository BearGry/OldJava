/**
 * 1.编写一Person类，通过重写toString()、equals()、hashcode()方法，
 * 实现可以对Person类实例化出的对象进行equals和==的比较。
 */
package sy5;

import java.util.Objects;

public class People {
    String name;
    int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }

    @Override
    //判定名字和年龄一致的人为同一个人
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return age == people.age && name.equals(people.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode()*age;
    }
    public static void main(String args[]){
        People people1 = new People("Jack",26);
        People people2 = new People("Mack",21);
        People people3 = new People("David", 27);
        People people4 = new People("Mack",21);
        System.out.println(people1);
        System.out.println(people2);
        System.out.println(people3);
        System.out.println("people2==people4: "+(boolean)(people2==people4));
        System.out.println("people2.equals(people4): "+(boolean)people2.equals(people4));
    }
}










