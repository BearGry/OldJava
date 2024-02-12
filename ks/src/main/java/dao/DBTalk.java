package dao;

import entity.*;
import util.JDBCLib;
import util.JDBCUtils;
import view.MyLibView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBTalk {
    /**
     * 用户登录时，是否存在该用户，登陆成功返回1,密码错误返回2,不存在返回3,数据库错误返回0
     * @param id
     * @param password
     * @return
     */
    public int logIn(Integer id, String password) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = JDBCLib.getConnection();
            String sql = "select password from login where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString("password")))
                    result = 1;
                else
                    result = 2;
            }
            else result = 3;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCLib.close(con, pstmt, rs);
        }
        return result;
    }

    /**
     * 用户登录后，返回当前用户
     * @param id
     * @return
     */
    public static User getNowUser(Integer id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = JDBCLib.getConnection();
            String sql = "select * from user where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(id,rs.getString("password"),Integer.valueOf(rs.getString("money")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCLib.close(con, pstmt, rs);
        }
        return user;
    }

    /**
     * 获取每次程序运行时的最大ID
     * @return int
     */
    public static int maxID(){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = JDBCLib.getConnection();
            String sql = "select MAX(id) from user";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            else result = 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCLib.close(con, pstmt, rs);
        }
        return result;
    }

    /**
     * 用户注册，输入密码后注册成功返回true，否则返回false
     * @param password
     * @return
     */
    public static boolean register(String password){
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JDBCLib.getConnection();
            String sql = "insert into user(password) values (?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, password);
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCLib.close(con, pstmt, null);
        }
        return false;
    }

    /**
     * 模糊查询相关书籍,返回名字里面有bname而库存不小于0的书籍列表
     * @param bname
     * @return
     */
    public static List<Book> likeLoad(String bname){

        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCLib.getConnection();
            String sql = "select * from now_lib where bname like '%"+bname+"%' ";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBname(rs.getString("bname"));
                book.setPrice(rs.getInt("price"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return bookList;
    }

    /**
     * 模糊查询secbook书籍,返回名字里面有bname的书籍列表
     * @param bname
     * @return
     */
    public static List<Book> seclikeLoad(String bname){

        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCLib.getConnection();
            String sql = "select bname,price,id from secbook where id != ? and bname like '%"+bname+"%'";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1,MyLibView.nowUser.getId());

            rs = pstmt.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBname(rs.getString("bname"));
                book.setPrice(rs.getInt("price"));
                book.setInventory(rs.getInt("id"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return bookList;
    }

    /**
     * 精确查询相关书籍,返回名字为bname而库存不小于0的书籍列表
     * @param bname
     * @return
     */
    public static List<Book> exLoad(String bname){

        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCLib.getConnection();
            String sql = "select * from now_lib where bname = '"+bname+"' ";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBname(rs.getString("bname"));
                book.setPrice(rs.getInt("price"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return bookList;
    }

    public static List<Book> loadHistory(){

        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCLib.getConnection();
            String sql = "select bname,number from buy where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1,MyLibView.nowUser.getId());

            rs = pstmt.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBname(rs.getString("bname"));
                book.setInventory(rs.getInt("number"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return bookList;
    }

    /**
     * 加载猜你喜欢，即和你买了相同书的人还买了什么
     * @return
     */
    public static List<Book> load(){

        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCLib.getConnection();
            String sql = "select distinct buy.bname as bookname, price, inventory " +
                    "from buy , book " +
                    "where buy.bname = book.bname and inventory > 0 " +
                    " and id in ( " +
                    "    select b.id " +
                    "    from buy as a, buy as b " +
                    "    where a.id = ? and b.id != ? and a.bname = b.bname " +
                    ") and buy.bname not in ( " +
                    "    select c.bname " +
                    "    from buy as c " +
                    "    where id = ? " +
                    ")";
            pstmt = con.prepareStatement(sql);
            Integer id = MyLibView.nowUser.getId();
            pstmt.setObject(1,id);
            pstmt.setObject(2,id);
            pstmt.setObject(3,id);

            rs = pstmt.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBname(rs.getString("bookname"));
                book.setPrice(rs.getInt("price"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return bookList;

    }

    /**
     * 加载二手书市场里的猜你喜欢，即在书店里和你买了相同书的人还买了什么，这里有没有
     * @return
     */
    public static List<Book> secload(){

        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCLib.getConnection();
            String sql = "select distinct bname,price,id " +
                    "from secbook " +
                    "where id != ? " +
                    "and bname not in (select buy.bname " +
                    "                  from buy, book " +
                    "                  where buy.bname = book.bname " +
                    "                  and inventory > 0 " +
                    "                  and id in (select b.id " +
                    "                             from buy as a, buy as b " +
                    "                               where a.id = ? and b.id != ? " +
                    "                               and a.bname = b.bname " +
                    "                               and buy.bname not in (select c.bname " +
                    "                                                     from buy as c " +
                    "                                                     where id = ?)))";
            pstmt = con.prepareStatement(sql);
            Integer id = MyLibView.nowUser.getId();
            pstmt.setObject(1,id);
            pstmt.setObject(2,id);
            pstmt.setObject(3,id);
            pstmt.setObject(4,id);

            rs = pstmt.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBname(rs.getString("bname"));
                book.setPrice(rs.getInt("price"));
                book.setInventory(rs.getInt("id"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return bookList;

    }


    public static boolean userSaveMoney(int pay){

        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            con = JDBCLib.getConnection();
            String sql = "update user_count set money = money + ? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1,pay);
            pstmt.setObject(2,MyLibView.nowUser.getId());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCLib.close(con, pstmt, null);
        }
        if(result > 0)
            return true;
        return false;
    }

    /**
     * !!!事务处理
     * 重置用户的值，在买书交易过后，更新用户的存款和老板的营业额（jdbc编程中的事务处理）
     * @param user
     * @return 成功返回true,否则false
     */
    public static boolean setUser(User user, int pay) {
        Connection con = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        boolean success = false;

        try {
            con = JDBCLib.getConnection();
            con.setAutoCommit(false); // 关闭自动提交

            // 第一个更新语句
            String sql1 = "UPDATE user_count SET money = ? WHERE id = ?";
            pstmt1 = con.prepareStatement(sql1);
            pstmt1.setDouble(1, user.getMoney());
            pstmt1.setInt(2, user.getId());
            pstmt1.executeUpdate();

            // 第二个更新语句
            String sql2 = "UPDATE boss_count SET money = money + ? ";
            pstmt2 = con.prepareStatement(sql2);
            pstmt2.setDouble(1, pay);
            pstmt2.executeUpdate();

            con.commit(); // 提交事务
            success = true;
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // 回滚事务
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCLib.close(con, pstmt1,null);
            JDBCLib.close(null,pstmt2,null);
        }

        return success;
    }


    /**
     * 重置书籍的值，多用于买书交易后，书的库存更新
     * @param bname
     * @param sellNumb
     * @return
     */
    public static boolean setBook(String bname, int sellNumb){

        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            con = JDBCLib.getConnection();
            String sql = "update book set inventory = inventory - ? where bname = '"+bname+"'";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, sellNumb);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCLib.close(con, pstmt, null);
        }
        if(result > 0)
            return true;
        return false;
    }

    public static boolean setBuy(String bname, int sellNum){

        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            con = JDBCLib.getConnection();
            //FIXME:再次购买会导致更新失败需要提前检查
            String sql = "select * from buy where id = ? and bname = '"+bname+"'";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1,MyLibView.nowUser.getId());
            var rows = pstmt.executeQuery();

            if(rows.next()){
                sql = "update buy set number = number + ? where id = ? and bname = '"+bname+"'";
                pstmt = con.prepareStatement(sql);
                pstmt.setObject(1,sellNum);
                pstmt.setObject(2,MyLibView.nowUser.getId());

                result = pstmt.executeUpdate();
            }
            else {
                sql = "insert into buy values (?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setObject(1, MyLibView.nowUser.getId());
                pstmt.setObject(2, bname);
                pstmt.setObject(3, sellNum);

                result = pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCLib.close(con, pstmt, null);
        }
        if(result > 0)
            return true;
        return false;
    }

    public static int getBalance(){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = JDBCLib.getConnection();
            String sql = "select money from boss_count";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            else result = 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCLib.close(con, pstmt, rs);
        }
        return result;
    }

    public static boolean setSecBook(String bname, int price){
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JDBCLib.getConnection();
            String sql = "insert into secbook values ('"+bname+"',?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1,MyLibView.nowUser.getId());
            pstmt.setObject(2,price+2);

            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCLib.close(con, pstmt, null);
        }
        return false;
    }

    public static boolean secTrade(int sellerID, int money, String bname) {

        Connection con = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        PreparedStatement pstmt4 = null;
        boolean success = false;

        try {
            con = JDBCLib.getConnection();
            con.setAutoCommit(false); // 关闭自动提交

            // 第一个更新语句-更新Boss的营业额
            String sql1 = "UPDATE boss_count SET money = money + 2";
            pstmt1 = con.prepareStatement(sql1);
            pstmt1.executeUpdate();

            // 第二个更新语句-更新买家的存款
            String sql2 = "UPDATE user_count SET money = ? WHERE id = ?";
            pstmt2 = con.prepareStatement(sql2);
            pstmt2.setInt(1, MyLibView.nowUser.getMoney());
            pstmt2.setInt(2, MyLibView.nowUser.getId());
            pstmt2.executeUpdate();

            // 第三个更新语句-更新卖家的存款：注意，Boss在中间收了2元手续费，在secbook表中存的售价是原定价+2
            //     这里卖家应该收到现交易额-2元
            String sql3 = "UPDATE user_count SET money = money + ? WHERE id = ?";
            pstmt3 = con.prepareStatement(sql3);
            pstmt3.setInt(1, money-2);
            pstmt3.setInt(2, sellerID);
            pstmt3.executeUpdate();

            // 第四个更新语句-删除更新secbook表中该出售数据
            String sql4 = "DELETE FROM secbook where id = ? and bname = '"+bname+"'";
            pstmt4 = con.prepareStatement(sql4);
            pstmt4.setInt(1, sellerID);
            pstmt4.executeUpdate();

            con.commit(); // 提交事务
            success = true;
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // 回滚事务
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCLib.close(con, pstmt1, null);
            JDBCLib.close(null, pstmt2, null);
            JDBCLib.close(null, pstmt3, null);
            JDBCLib.close(null, pstmt4, null);
        }

        return success;
    }
}





























