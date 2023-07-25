package com.works;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@RestController
@RequiredArgsConstructor
public class BasketRestController {

    final JmsTemplate jmsTemplate;
    final ObjectMapper objectMapper;

    String stData = "";
    @PostMapping("/sendBasket")
    public String sendBasket(@RequestBody Product product) {
        product.setTime( System.currentTimeMillis() );
        try {
            stData = objectMapper.writeValueAsString(product);
        }catch (Exception ex) {}
        jmsTemplate.send("basket", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(stData);
                return textMessage;
            }
        });
        return "Sending...";
    }

}
