package week03.courierapp;

import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orders;

    public OrderList() {
        orders = new ArrayList<>();
    }

    public void addOrder(int id, String name, String address) {
        Order order = new Order(id, name, address);
        orders.add(order);
    }

    public void completeOrder(int id) {
        for(Order order : orders) {
            if(order.getId() == id) {
                order.complete();
                break;
            }
        }
    }

    public void printIncomplete() {
        for(Order order : orders) {
            if(!order.isCompleted()) {
                System.out.println(order);
            }
        }
    }
}