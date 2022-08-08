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
    public void processOrder(BookOrder bookOrder, String orderState, String storeId) {
        ProcessedBookOrder order = new ProcessedBookOrder(bookOrder, new Date(), new Date());

        if ("NEW".equalsIgnoreCase(orderState)) {
            add(bookOrder, storeId);
        } else if ("UPDATE".equalsIgnoreCase(orderState)) {
            update(bookOrder, storeId);
        } else if ("DELETE".equalsIgnoreCase(orderState)) {
            delete(bookOrder, storeId);
        }

        jmsTemplate.convertAndSend("book.order.processed.queue", order);
    }

    private void add(BookOrder bookOrder, String storeId) {
        LOGGER.info("ADDING A NEW ORDER TO THE DB");
    }

    private void update(BookOrder bookOrder, String storeId) {
        LOGGER.info("UPDATING AN ORDER TO THE DB");
    }

    private void delete(BookOrder bookOrder, String storeId) {
        LOGGER.info("DELETING THE ORDER FROM DB");
    }
}
