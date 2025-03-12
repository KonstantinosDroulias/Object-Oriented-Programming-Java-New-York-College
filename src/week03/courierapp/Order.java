package week03.courierapp;

public class Order {
    private int id;
    private String name;
    private String address;
    private boolean completed;


    public Order(int id, String name, String address) {
        this.id= id;
        this.name = name;
        this.address = address;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public void complete() {
        this.completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String toString() {
        return id + " " + name + " Addr: " + address;
    }
}