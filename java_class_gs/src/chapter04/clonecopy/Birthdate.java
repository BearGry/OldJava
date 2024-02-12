package chapter04.clonecopy;

public class Birthdate implements Cloneable{
    private int year;
    private int month;
    private int data;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
    public Birthdate(){}
    public Birthdate(int year, int month, int data){
        this.year = year;
        this.month = month;
        this.data = data;
    }
    @Override
    public String toString() {
        return "Birthday: "+year+"-"+month+"-"+data;
    }
    @Override
    public Birthdate clone() throws CloneNotSupportedException{
        return (Birthdate) super.clone();
    }
}
