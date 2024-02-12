package chapter03.fanxinglei;

public class GenericAdd <T extends Number>{
    public double add(T number1, T number2){
        return number1.doubleValue() + number2.doubleValue();
    }
}
