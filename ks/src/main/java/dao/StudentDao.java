package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Student;
import util.JDBCUtils;

/**
 * 学生数据库操作
 * @author Lenovo
 *
 */
public class StudentDao {



    /**
     * 查询学生列表
     * @param name 学生姓名
     * @return
     */
    public List<Student> queryList(String name){


        List<Student> list = new ArrayList<Student>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            List<Object> params = new ArrayList<>();
            StringBuffer sb = new StringBuffer("select * from t_student where 1=1 ");
            if(name != null && !"".equals(name)){
                sb.append("and name like ? ");
                params.add(name);
            }
            sb.append("order by create_time desc");
            pstmt = con.prepareStatement(sb.toString());
            if(params != null && params.size()>0){
                for(int i=0; i<params.size(); i++){
                    pstmt.setObject(i, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStuno(rs.getString("stuno"));
                student.setName(rs.getString("name"));
                student.setGrade(rs.getString("grade"));
                student.setCreatTime(rs.getDate("create_time"));
                student.setUpdateTime(rs.getDate("update_time"));
                list.add(student);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return list;


    }


    /**
     * 保存学生信息
     * @param student
     * @return
     */
    public boolean save(Student student){

        Connection con = null;
        String sql = "insert into t_student(stuno,name,grade,create_time,update_time) values(?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, student.getStuno());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGrade());
            Date date = new Date();
            pstmt.setTimestamp(4, new Timestamp(date.getTime()));
            pstmt.setTimestamp(5, new Timestamp(date.getTime()));
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }



    /**
     * 修改学生信息
     * @param student
     * @return
     */
    public boolean update(Student student){

        Connection con = null;
        String sql = "update t_student set stuno=?,name=?,grade=?,update_time=? where id=?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, student.getStuno());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGrade());
            Date date = new Date();
            pstmt.setTimestamp(4, new Timestamp(date.getTime()));
            pstmt.setInt(5, student.getId());
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }




    /**
     * 删除学生信息
     * @return
     */
    public boolean delete(int id){

        Connection con = null;
        String sql = "delete from t_student where id=?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }



    /**
     * 根据ID查询学生
     * @param id 学生ID
     * @return
     */
    public Student getById(int id){


        Student student = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from t_student where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setStuno(rs.getString("stuno"));
                student.setName(rs.getString("name"));
                student.setGrade(rs.getString("grade"));
                student.setCreatTime(rs.getDate("create_time"));
                student.setUpdateTime(rs.getDate("update_time"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return student;


    }

}