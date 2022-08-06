package com.mdrsolutions.jmsdemo.service.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Sender {

    private final JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Transactional
    public void sendMessage(String destination, String message) {
        this.jmsTemplate.convertAndSend(destination, message);
    }
}
