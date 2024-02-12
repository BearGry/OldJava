package sy3.java1;

class B{
    public void f(){
        System.out.println("this is f functiion.");
    }
}
public class A {
    public static void main(String args[]){
        new B(){
            @Override
            public void f() {
                System.out.println("this is a new f function.");
            }
        }.f();
    }
}
