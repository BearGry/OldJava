/**
 * 1.声明一个表示圆的类，包含计算周长和面积的方法，保存在文件Circle.java 中。
 * 然后编写测试类，保存在文件ShapeTester.java中，并与Circle.java放在相同的目录下，进行测试。
 */
package sy2.java1;

public class Circle {
    public static double PI = 3.14;
    private int r;

    public Circle(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
    public double perimeter() {
        return PI*2*r;
    }
    public double area(){
        return PI*r*r;
    }
}
