package chapter07.sometry;

import java.util.HashSet;
import java.util.Set;

public class Why2 {
    public static void main(String args[]){
        Set<String> set = new HashSet<String>();
        String s1 = new String("hello");
        String s2 = new String("hello");
        String s3 = new String("mack");
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode()+"\n"+s2.hashCode());
        System.out.println(6^6);
        set.add(s1);
        set.add(s2);
        set.add(s3);
        System.out.println(set.size());
    }
}
