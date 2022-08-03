package com.mdrsolutions.jmsdemo.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookOrder {

    private final String bookOrderId;
    private final Book book;
    private final Customer customer;

    @JsonCreator
    public BookOrder(
            @JsonProperty("bookOrderId") String bookOrderId,
            @JsonProperty("book") Book book,
            @JsonProperty("customer") Customer customer) {
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
