package com.mdrsolutions.jmsdemo.controller;

import com.mdrsolutions.jmsdemo.pojos.Book;
import com.mdrsolutions.jmsdemo.pojos.Customer;
import com.mdrsolutions.jmsdemo.service.jms.BookStoreOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AppController {

    private final BookStoreOrderService bookStoreOrderService;

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

        model.addAttribute("customers", customers);
        model.addAttribute("books", books);
        return "index";
    }

}
