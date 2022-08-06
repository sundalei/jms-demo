package com.mdrsolutions.jmsdemo.service.jms;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.mdrsolutions.jmsdemo.pojos.BookOrder;
import com.mdrsolutions.jmsdemo.pojos.ProcessedBookOrder;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WarehouseProcessingService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseProcessingService.class);

    private final JmsTemplate jmsTemplate;

    public WarehouseProcessingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Transactional
    public void processOrder(BookOrder bookOrder) {
        ProcessedBookOrder order = new ProcessedBookOrder(bookOrder, new Date(), new Date());
        jmsTemplate.convertAndSend("book.order.processed.queue", order);
    }
}
