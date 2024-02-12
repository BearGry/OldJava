package entity;

import dao.DBTalk;

/**
 * 用户类
 */
public class User {
    private Integer id;
    private String password;
    private Integer money;

    public User(Integer id, String password, Integer money) {
        this.id = id;
        this.password = password;
        this.money = money;
    }
    public User(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
