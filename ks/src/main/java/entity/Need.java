package entity;

import java.sql.Timestamp;

public class Need {
    private String bname;
    private Integer price;
    private Integer lack;
    private Timestamp maketime;

    public Need(String bname, Integer price, Integer lack, Timestamp maketime) {
        this.bname = bname;
        this.price = price;
        this.lack = lack;
        this.maketime = maketime;
    }

    public Need(){}

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getLack() {
        return lack;
    }

    public void setLack(Integer lack) {
        this.lack = lack;
    }

    public Timestamp getMaketime() {
        return maketime;
    }

    public void setMaketime(Timestamp maketime) {
        this.maketime = maketime;
    }
}
