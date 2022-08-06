package com.mdrsolutions.jmsdemo.service.jms;

import com.mdrsolutions.jmsdemo.pojos.BookOrder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookStoreOrderService {

    private static final String BOOK_QUEUE = "book.order.queue";

    private final JmsTemplate jmsTemplate;

    public BookStoreOrderService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Transactional
    public void send(BookOrder bookOrder) {
        jmsTemplate.convertAndSend(BOOK_QUEUE, bookOrder);
    }
}
