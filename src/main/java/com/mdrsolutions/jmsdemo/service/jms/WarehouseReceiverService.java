package com.mdrsolutions.jmsdemo.service.jms;

import com.mdrsolutions.jmsdemo.pojos.BookOrder;
import com.mdrsolutions.jmsdemo.pojos.ProcessedBookOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
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
    public JmsResponse<Message<ProcessedBookOrder>> receive(@Payload BookOrder bookOrder,
                        @Header("orderState") String orderState,
                        @Header("bookOrderId") String bookOrderId,
                        @Header("storeId") String storeId) {
        LOGGER.info("received a message");
        LOGGER.info("Message is: " + bookOrder);
        LOGGER.info("Message property orderState = {}, bookOrderId = {}, storeId = {}", orderState, bookOrderId, storeId);

        if (bookOrder.getBook().getTitle().startsWith("L")) {
            throw new IllegalArgumentException("OrderId=" + bookOrder.getBookOrderId() + " begins with L and these books are not allowed.");
        }
        return warehouseProcessingService.processOrder(bookOrder, orderState, storeId);
    }
}
