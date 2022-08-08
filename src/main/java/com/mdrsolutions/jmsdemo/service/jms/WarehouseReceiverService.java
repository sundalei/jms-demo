package com.mdrsolutions.jmsdemo.service.jms;

import com.mdrsolutions.jmsdemo.pojos.BookOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class WarehouseReceiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseReceiverService.class);

    private final WarehouseProcessingService warehouseProcessingService;

    public WarehouseReceiverService(WarehouseProcessingService warehouseProcessingService) {
        this.warehouseProcessingService = warehouseProcessingService;
    }

    @JmsListener(destination = "book.order.queue", containerFactory = "defaultJmsListenerContainerFactory")
    public void receive(@Payload BookOrder bookOrder,
                        @Header("orderState") String orderState,
                        @Header("bookOrderId") String bookOrderId,
                        @Header("storeId") String storeId,
                        MessageHeaders messageHeaders) {
        LOGGER.info("received a message");
        LOGGER.info("Message is: " + bookOrder);
        LOGGER.info("Message property orderState = {}, bookOrderId = {}, storeId = {}", orderState, bookOrderId, storeId);
        LOGGER.info("messageHeaders = {}", messageHeaders);

        if (bookOrder.getBook().getTitle().startsWith("L")) {
            throw new RuntimeException("OrderId=" + bookOrder.getBookOrderId() + " begins with L and these books are not allowed.");
        }
        warehouseProcessingService.processOrder(bookOrder, orderState, storeId);
    }
}
