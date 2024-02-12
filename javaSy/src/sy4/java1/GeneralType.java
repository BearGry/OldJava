/**
 * 编写一泛型类（String、Integer、Double等）的使用，类GeneralType中包含有一泛型类成员变量，
 * 构造函数对这一泛型类对象进行初始化，另外一方法对该泛型类对象进行输出。
 */
package sy4.java1;

public class GeneralType <Type>{
    Type anyclassps;

    public GeneralType(Type anyclassps) {
        this.anyclassps = anyclassps;
    }
    public void show(){
        System.out.println(anyclassps.getClass().getName());
    }
    public static void main(String agrs[]){
        GeneralType<String> type1 = new GeneralType<>("Hello Java");
        GeneralType<Integer> type2 = new GeneralType<>(4);
        GeneralType<Double> type3 = new GeneralType<>(28.0);
        type1.show();
        type2.show();
        type3.show();
    }
}
