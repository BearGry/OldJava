package shijuan.biancheng1;
/**
 * 编写人类，具有姓名、性别、年龄属性和思考方法。再编写继承人类的教师类，教师
 * 类还拥有职工号字段和教学方法。构造人类和教师类的对象，输出有关信息。
 */
public class Professor extends People{
    private long id;
    public void teach(){
        System.out.println("my id is "+id);
    }
    public Professor(String name,String sex,int age,long id){
        super(name, sex, age);
        this.id = id;
    }
    public static void main(String[] args){
        People people = new People("tyc", "man", 42);
        Professor professor = new Professor("tyc","man",42,00001);
        people.think();
        professor.teach();
        professor.teach();
    }

}
