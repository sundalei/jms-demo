package com.mdrsolutions.jmsdemo.pojos;

public class BookOrder {

    private final String bookOrderId;
    private final Book book;
    private final Customer customer;

    public BookOrder(String bookOrderId, Book book, Customer customer) {
        this.bookOrderId = bookOrderId;
        this.book = book;
        this.customer = customer;
    }

    public String getBookOrderId() {
        return bookOrderId;
    }

    public Book getBook() {
        return book;
    }

    public Customer getCustomer() {
        return customer;
    }
}
