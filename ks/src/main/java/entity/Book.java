package entity;

public class Book {
    private String bname;
    private Integer price;
    private Integer inventory;

    public Book(String bname, Integer price, Integer inventory) {
        this.bname = bname;
        this.price = price;
        this.inventory = inventory;
    }
    public Book(){}

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

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
