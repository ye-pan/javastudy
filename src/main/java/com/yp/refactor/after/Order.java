package com.yp.refactor.after;

import java.util.Collection;

public class Order {

    private double amount;

    private CustomerObject customer;

    public Order(String customer) {
        this.customer = CustomerObject.newInstance(customer);
    }

    public double getAmount() {
        return amount;
    }

    public double basePrice() {
        return amount;
    }

    public String getCustomer() {
        return customer.getName();
    }

    public void setCustomer(String customer) {
        this.customer = CustomerObject.newInstance(customer);
    }

    private static int numberOfOrderFor(Collection<Order> orders, final String customer) {
        long result = orders.stream().filter(order->order.getCustomer().equals(customer)).count();
        return (int)result;
    }
}
