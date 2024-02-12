/**
 * 3.编写一程序，查找某一字符串中是否包含又“abc”。
 */
package sy1.java3;

public class HasABCTester {
    public static boolean hasABC(String s){
        return s.matches(".*abc.*");
    }

    public static void main(String[] args) {
        System.out.println((boolean) HasABCTester.hasABC("hello world"));
        System.out.println((boolean) HasABCTester.hasABC("abc"));
        System.out.println((boolean) HasABCTester.hasABC("hello java"));
        System.out.println((boolean) HasABCTester.hasABC("helloabc java"));
    }
}
