package chapter07.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Point implements Comparable {
    private int x;
    private int y;
    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Point){
            Point p = (Point) o;
            return x*x+y*y-p.x*p.x-p.y*p.y;
        }
        return -1;
    }

    public static void main(String[] args){
        Set<Point> set = new TreeSet<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.x-o2.x;
            }
        });
        set.add(new Point(2,4));
        set.add(new Point(18,4));
        set.add(new Point(2,3));
        set.add(new Point(2,1));
        Iterator<Point> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
