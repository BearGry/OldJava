package entity;
import java.util.Date;

/**
 * 学生实体类
 * @author Lenovo
 *
 */
public class Student {

    /**
     * 学生ID
     */
    private Integer id;

    /**
     * 学号
     */
    private String stuno;

    /**
     * 姓名
     */
    private String name;

    /**
     * 班级
     */
    private String grade;

    /**
     * 添加时间
     */
    private Date creatTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
