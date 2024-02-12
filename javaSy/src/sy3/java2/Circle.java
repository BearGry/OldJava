package sy3.java2;


class Circle implements Shape{
    public static double PI = 3.14;
    int r;

    public Circle(int r) {
        this.r = r;
    }

    public double perimeter() {
        return PI*2*r;
    }
    public double area(){
        return PI*r*r;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "r=" + r +
                ", area=" + area()+
                ", perimeter" + perimeter()+
                '}';
    }
}

