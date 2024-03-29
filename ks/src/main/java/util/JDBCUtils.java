package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {

    //数据库连接地址
    public static String URL = "jdbc:mysql://localhost:3306/db_demo?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true";
    //数据库驱动
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    //数据库用户名
    public static String USER = "root";
    //数据库密码
    public static String PWD = "mysql123";

    /*
     * 数据库连接
     */
    public static Connection getConnection() {

        Connection con = null;
        try {
            // 加载驱动
            Class.forName(DRIVER);
            // 获取连接对象
            con = DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 关闭连接资源
     * @param con	连接对象
     * @param pstmt	预编译对象
     * @param rs	结果集
     */
    public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {

        try {
            if (rs != null){
                rs.close();
            }
            if (pstmt != null){
                pstmt.close();
            }
            if (con != null){
                con.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
