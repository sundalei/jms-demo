package com.mdrsolutions.jmsdemo.config;

import com.mdrsolutions.jmsdemo.pojos.Book;
import com.mdrsolutions.jmsdemo.pojos.BookOrder;
import com.mdrsolutions.jmsdemo.pojos.Customer;
import com.thoughtworks.xstream.security.ExplicitTypePermission;
import com.thoughtworks.xstream.security.TypePermission;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.oxm.xstream.XStreamMarshaller;

@EnableJms
@Configuration
public class JmsConfig {

    @Bean
    public MessageConverter xmlMarshallingMessageConverter() {
        MarshallingMessageConverter converter = new MarshallingMessageConverter(xmlMarshaller());
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

    @Bean
    public XStreamMarshaller xmlMarshaller() {
        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setTypePermissions(new ExplicitTypePermission(
                new Class[] {Book.class, Customer.class, BookOrder.class}));
        marshaller.setSupportedClasses(Book.class, Customer.class, BookOrder.class);
        return marshaller;
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
    }

    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        // factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setMessageConverter(xmlMarshallingMessageConverter());
        return factory;
    }
}
