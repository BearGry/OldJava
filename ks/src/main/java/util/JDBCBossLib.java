package util;

import java.sql.*;

public class JDBCBossLib {
    public static String URL = "jdbc:mysql://localhost:3306/mylib?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true";
    //数据库驱动
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    //数据库用户名
    public static String USER = "bossCount";
    //数据库密码
    public static String PWD = "boss123";

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

    public static void main(String[] args) {
        Connection con = getConnection();
        close(con,null,null);
        System.out.println("connnect ok");
    }
}
