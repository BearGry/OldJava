package sy3.java2;

class Rectangle implements Shape{
    double length;
    double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double perimeter() {
        return 2*(width+length);
    }

    @Override
    public double area() {
        return width*length;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                ", area=" + area()+
                ", perimeter=" + perimeter()+
                '}';
    }
}
