/**
 *1.创建一学生数据库School，创建一表studb，包含字段：学号、姓名、性别、专业、入学年份。
 * 编写程序对studb表进行学生信息的增、删、改、查操作。
 */
package sy12;


import java.sql.*;

public class JDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
//        2.用户信息和url
        String url = "jdbc:mysql://localhost:3306/DB?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username="root";
        String password="mysql123";
//        3.连接成功，数据库对象 Connection
        Connection connection = DriverManager.getConnection(url,username,password);
//        4.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();
        String sql = null;
//      选定数据库
        sql = "use School";
        statement.execute(sql);
        //增
        sql = "INSERT INTO studb " +
                "values ('20212414','howard','男','math','1998-2-17')";
        statement.execute(sql);
        //删
        sql = "DELETE FROM studb " +
                "where id = '20212412'";
        statement.execute(sql);
        //改
        sql = "UPDATE studb " +
                "set rolltime = '2003-05-06'" +
                "where name = 'Mack'";
        statement.execute(sql);
        //查
//        5.执行SQL的对象去执行SQL，返回结果集
        sql = "SELECT * from studb";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("ID\t\t\tName\t\tSex\t\tSpeciality\t\tRollTime");
        while(resultSet.next()){
            System.out.print(resultSet.getString("id")+"\t");
            System.out.print(resultSet.getString("name")+"\t\t");
            System.out.print(resultSet.getString("sex")+"\t\t");
            System.out.print(resultSet.getString("speciality")+"\t\t\t");
            System.out.println(resultSet.getString("rolltime")+"\t");
        }
//        6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}