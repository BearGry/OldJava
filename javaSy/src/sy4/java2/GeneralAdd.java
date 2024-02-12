/**
 * 2.编写一个泛型方法add()，当传入不同数字类型的值时，能够进行加法运算，
 * （如可以传入int、long、float、double类型，但要对传入的值做一定的限定，如必须是数字）
 */
package sy4.java2;

public class GeneralAdd {
    public static <T extends Number> double add(T t1, T t2) {
        return t1.doubleValue() + t2.doubleValue();
    }
    public static void main(String[] args) {
        System.out.println("Integer:" +GeneralAdd.add(4, 7));//整数相加
        System.out.println("Float:" + GeneralAdd.add(5.4f, 6.8f));//浮点数相加
        System.out.println("Double:" + GeneralAdd.add(8.4, 11.7));
    }
}
