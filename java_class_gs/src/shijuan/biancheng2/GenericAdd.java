package shijuan.biancheng2;

/**
 * 编写一个泛型类 GenericAdd，具有一方法 add()，当传入不同数字类型的值时，
 * 能够进行加法运算，如：可以传入 int、long、float、double 类型，但要对传入的
 * 值做一定的限定，如必须是数字。
 * @param <T>
 */
public class GenericAdd <T extends Number>{
    public double add(T n1, T n2){
        return n1.doubleValue()+n2.doubleValue();
    }
    public static void main(String[] args){
        GenericAdd genericAdd = new GenericAdd<Integer>();
        System.out.println(genericAdd.add(6,8));
    }
}
