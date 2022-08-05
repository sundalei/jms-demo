package com.mdrsolutions.jmsdemo.service.jms;

import com.mdrsolutions.jmsdemo.pojos.BookOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class WarehouseReceiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseReceiverService.class);

    private final WarehouseProcessingService warehouseProcessingService;

    public WarehouseReceiverService(WarehouseProcessingService warehouseProcessingService) {
        this.warehouseProcessingService = warehouseProcessingService;
    }

    @JmsListener(destination = "book.order.queue")
    public void receive(BookOrder bookOrder) {
        LOGGER.info("received a message");
        LOGGER.info("Message is: " + bookOrder);
        warehouseProcessingService.processOrder(bookOrder);
    }
}
