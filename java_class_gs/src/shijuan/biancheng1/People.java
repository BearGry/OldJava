package shijuan.biancheng1;

/**
 * 编写人类，具有姓名、性别、年龄属性和思考方法。再编写继承人类的教师类，教师
 * 类还拥有职工号字段和教学方法。构造人类和教师类的对象，输出有关信息。
 */
public class People {
    private String name;
    private String sex;
    private int age;
    public void think(){
        System.out.println("my name is "+name+" and i can think.");
    }
    public People(String name, String sex, int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
