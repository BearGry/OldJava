/**
 * 从命令行输入源文件名和目标文件名，将源文件复制为目标文件。
 */
package sy6.java2;

import java.io.*;
public class Coper {
    public static void main ( String[] args ) throws IOException {
        DataInputStream in=null;
        DataOutputStream out=null;
        if ( args.length != 2 ) {
            System.out.println("Input two file name.");
            return;
        }
        try {
            in = new DataInputStream(new
                    BufferedInputStream(new FileInputStream( args[0] )));
            out = new DataOutputStream(new
                    BufferedOutputStream(new FileOutputStream( args[1] )));
        }
        catch ( FileNotFoundException e )
        {   System.out.println("open file error." );    }
        try {
            while ( true ) {
                out.writeByte( in.readByte() ) ;
            }
        }
        catch ( EOFException  eof ) {
            System.out.println("succeed copy.");
        }
        finally {
            out.close();
            in.close();
        }
    }
}

