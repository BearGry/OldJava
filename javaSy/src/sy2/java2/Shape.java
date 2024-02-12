/**
 * 定义一个抽象类Shape，它有一个抽象方法calArea代表求图形的面积；分别定义Shape的两个子类Triangle、Rectangle代表三角形、矩形，
 * 这两个类分别具体实现calArea方法求自己的面积，在main方法里，利用这三个类创建对象展示Java的多态性。
 */
package sy2.java2;

abstract class Shape {
    public abstract double calArea();
}

class Triangle extends Shape {
    double a;
    double b;
    double c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calArea() {
        //半周长
        double s = (a + b + c) / 2;
        //面积
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}


class Rectangle extends Shape {
    double length;
    double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calArea() {
        return width*length;
    }
}

