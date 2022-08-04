package com.mdrsolutions.jmsdemo.pojos;

import java.util.Date;

public class ProcessedBookOrder {

    private final BookOrder bookOrder;
    private final Date processingDateTime;
    private final Date expectedShippingDateTime;

    public ProcessedBookOrder(BookOrder bookOrder, Date processingDateTime, Date expectedShippingDateTime) {
        this.bookOrder = bookOrder;
        this.processingDateTime = processingDateTime;
        this.expectedShippingDateTime = expectedShippingDateTime;
    }

    public BookOrder getBookOrder() {
        return bookOrder;
    }

    public Date getProcessingDateTime() {
        return processingDateTime;
    }

    public Date getExpectedShippingDateTime() {
        return expectedShippingDateTime;
    }

    @Override
    public String toString() {
        return "ProcessedBookOrder{" +
                "bookOrder=" + bookOrder +
                ", processingDateTime=" + processingDateTime +
                ", expectedShippingDateTime=" + expectedShippingDateTime +
                "}";
    }
}
