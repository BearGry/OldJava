package shijuan.biancheng2;

public class GenericAdd2 {
    public static <T extends Number> double add(T n1,T n2){
        return n1.doubleValue()+ n2.doubleValue();
    }
    public static void main(String[] args){
        System.out.println(GenericAdd2.add(6.2,8));
    }
}
