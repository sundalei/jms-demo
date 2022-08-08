package com.mdrsolutions.jmsdemo.controller;

import com.mdrsolutions.jmsdemo.pojos.Book;
import com.mdrsolutions.jmsdemo.pojos.BookOrder;
import com.mdrsolutions.jmsdemo.pojos.Customer;
import com.mdrsolutions.jmsdemo.service.jms.BookStoreOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class AppController {

    private final BookStoreOrderService bookStoreOrderService;
    private final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    List<Book> books = Arrays.asList(
            new Book("jpw-1234", "Lord of the Flies"),
            new Book("uyh-2345", "Being and Nothingness"),
            new Book("iuhj-87654","At Sea and Lost"));

    List<Customer> customers = Arrays.asList(
            new Customer("mr-1234", "Michael Rodgers"),
            new Customer("jp-2345", "Jeff Peek"),
            new Customer("sm-8765", "Steve McClarney"));

    public AppController(BookStoreOrderService bookStoreOrderService) {
        this.bookStoreOrderService = bookStoreOrderService;
    }

    @RequestMapping("/")
    public String appHome(Model model) {

        LOGGER.info("AppController home");

        model.addAttribute("customers", customers);
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(path = "/process/store/{storeId}/order/{orderId}/{customerId}/{bookId}/{orderState}/", method = RequestMethod.GET)
    @ResponseBody
    public String processOrder(@PathVariable String storeId,
                               @PathVariable String orderId,
                               @PathVariable String customerId,
                               @PathVariable String bookId,
                               @PathVariable String orderState) {
        LOGGER.info("storeId: " + storeId);
        LOGGER.info("orderId: " + orderId);
        LOGGER.info("customerId: " + customerId);
        LOGGER.info("bookId: " + bookId);
        LOGGER.info("orderState: " + orderState);

        try {
            bookStoreOrderService.send(build(customerId, bookId, orderId), storeId, orderState);
        } catch (Exception exception) {
            return "Error occurred!" + exception.getLocalizedMessage();
        }

        return "Order sent to warehouse for bookId = "
                + bookId + " from customerId = " + customerId + " successfully processed!";
    }

    private BookOrder build(String customerId, String bookId, String orderId) {
        Book myBook = null;
        Customer myCustomer = null;

        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                myBook = book;
                break;
            }
        }
        if (myBook == null) {
            throw new IllegalArgumentException("Book selected does not exist in inventory!");
        }

        for (Customer customer : customers) {
            if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                myCustomer = customer;
                break;
            }
        }
        if (myCustomer == null) {
            throw new IllegalArgumentException("Customer selected does not appear to be valid!");
        }

        return new BookOrder(orderId, myBook, myCustomer);
    }

}
