package chapter05.neibuclass;

public class NeibuclassTester {
    public static void main(String args[]){
        double area = new Shape2(4){
            int a,b;
            {
                a=4;
                b=5;
            }
            @Override
            public double area() {
                return a*b;
            }
        }.area();
        System.out.println(area);
        System.out.println(new Shape(){
            int r = 2;

            @Override
            public double area() {
                return PI*r*r;
            }
        }.area());
    }
}
