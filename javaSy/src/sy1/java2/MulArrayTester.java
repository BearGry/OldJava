/**
 * 2.声明一个二维数组，为数组的每个元素赋值，并输出数组的值。
 */
package sy1.java2;

public class MulArrayTester {
    public static void main(String args[]){
        int[][] array = new int[9][];
        for(int i=0; i<9; i++){
            array[i] = new int[i+1];
            for(int j=0; j<i+1; j++)
                array[i][j]=i+1;
        }
        for(var line: array){
            for(var elem: line)
                System.out.print(elem+" ");
            System.out.println();
        }
    }
}
