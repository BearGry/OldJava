package shijuan.biancheng3;

/**
 * （3）定义圆类 Circle 实现接口 Shape2D，该类中包含变量：半径 radius。 在
 *  * Circle 类中重写 toString()方法，输出 Circle 的半径、面积和周长。
 */
public class Circle implements Shape2D{
    private double radius;
    public Circle(double r){
        radius = r;
    }

    @Override
    public double area() {
        return PI*radius*radius;
    }

    @Override
    public double perimeter() {
        return PI*2*radius;
    }
    @Override
    public String toString(){
        return "r="+radius+" area="+area()+" perimeter="+perimeter();
    }
}
