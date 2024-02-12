package chapter04.clonecopy;

import jdk.jfr.Period;

public class Student implements Cloneable{
    int id;
    protected String name;
    private Birthdate birthdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //需要修改
    public Birthdate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Birthdate birthdate) {
        this.birthdate = birthdate;
    }

    public Student(){}
    public Student(int id, String name, Birthdate birthdate){
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }
    @Override
    public Student clone() throws CloneNotSupportedException{
        Student stu = (Student) super.clone();
        stu.birthdate = birthdate.clone();
        return stu;
    }
}
