package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.models.CustomerMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HomeService {

    final ObjectMapper objectMapper;
    final JmsTemplate jmsTemplate;
    final HttpServletRequest req;


    String stData = "";
    public boolean send(CustomerMessage customerMessage) {
        String uuid = UUID.randomUUID().toString();
        customerMessage.setCid(uuid);
        String sessionID = req.getSession().getId();
        try {
            stData = objectMapper.writeValueAsString(customerMessage);
        }catch (Exception ex) {}

        try {
            jmsTemplate.send("customerMessage", new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage(stData);
                    System.out.println( sessionID );
                    textMessage.setStringProperty("sessionID", sessionID);
                    return textMessage;
                }
            });
            return true;
        }catch (Exception ex) {}
        return false;
    }


}
