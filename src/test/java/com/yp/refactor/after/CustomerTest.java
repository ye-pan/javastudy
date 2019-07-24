package com.yp.refactor.after;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void testStatement() {
        Customer customer = new Customer("test");
        Movie movie = new Movie("恶人传", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie, 5);
        customer.addRental(rental);
        movie = new Movie("蝙蝠侠：缄默", Movie.REGULAR);
        rental = new Rental(movie, 6);
        customer.addRental(rental);
        movie = new Movie("阿丽塔：战斗天使", Movie.CHILDRENS);
        rental = new Rental(movie, 3);
        customer.addRental(rental);
        System.out.println(customer.statement());

        System.out.println(customer.htmlStatement());
    }
}