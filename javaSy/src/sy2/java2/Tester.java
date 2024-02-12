/**
 * 定义一个抽象类Shape，它有一个抽象方法calArea代表求图形的面积；分别定义Shape的两个子类Triangle、Rectangle代表三角形、矩形，
 * 这两个类分别具体实现calArea方法求自己的面积，在main方法里，利用这三个类创建对象展示Java的多态性。
 */
package sy2.java2;

public class Tester {
    public static void main(String[] args) {
        Shape triangle = new Triangle(3,4,5);
        Shape rectangle = new Rectangle(4,7);
        System.out.println(triangle.calArea());
        System.out.println(rectangle.calArea());
    }
}
