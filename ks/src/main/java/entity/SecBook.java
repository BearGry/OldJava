package entity;

public class SecBook {
    private String bname;
    private Integer id;
    private Integer price;

    public SecBook(String bname, Integer id, Integer price) {
        this.bname = bname;
        this.id = id;
        this.price = price;
    }
    public SecBook(){}

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
