package com.yp.refactor.before;

import java.util.List;

public class ExtractMethod {

    private String name;

    private List<Order> orders;

    public void printOwing(double previousAmount) {

        System.out.println("*******************************");
        System.out.println("******** Customer Owes ********");
        System.out.println("*******************************");

        double outstanding = previousAmount * 1.2;
        outstanding += orders.stream().mapToDouble(Order::getAmount).sum();
        System.out.println("name: " + name);
        System.out.println("amount: " + outstanding);
    }

}
