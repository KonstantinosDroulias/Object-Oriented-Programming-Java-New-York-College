package weeksTasks.week04;

public class CustomerOrder {
    private Customer customer;
    private Order order;

    public CustomerOrder(Customer customer, Order order) {
        this.customer = customer;
        this.order = order;
    }

    public Customer getCustomer() { return customer; }
    public Order getOrder() { return order; }

    @Override
    public String toString() {
        return order.getOrderID() + ". " + customer.getName() + " (" + customer.getEmail() + ") → $" + order.getAmount();
    }

}
