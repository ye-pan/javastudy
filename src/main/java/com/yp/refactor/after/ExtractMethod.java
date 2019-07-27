package com.yp.refactor.after;

import com.yp.refactor.before.Order;

import java.util.List;

public class ExtractMethod {
    
    private String name;

    private List<Order> orders;

    public void printOwing(double previousAmount) {
        printBanner();
        double outstanding = getOutstanding(previousAmount);
        printDetail(outstanding);
    }

    private void printDetail(double outstanding) {
        System.out.println("name: " + name);
        System.out.println("amount: " + outstanding);
    }

    private double getOutstanding(double previousAmount) {
        double outstanding = previousAmount * 1.2;
        outstanding += orders.stream().mapToDouble(Order::getAmount).sum();
        return outstanding;
    }

    private void printBanner() {
        System.out.println("*******************************");
        System.out.println("******** Customer Owes ********");
        System.out.println("*******************************");
    }

}
