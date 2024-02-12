package entity;

public class Buy {
    private Integer id;
    private String bname;
    private Integer number;

    public Buy(Integer id, String bname, Integer number) {
        this.id = id;
        this.bname = bname;
        this.number = number;
    }
    public Buy(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
