package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.models.DataMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DataMessageService {

    final JmsTemplate jmsTemplate;
    final ObjectMapper objectMapper;

    String stData = "";
    public ResponseEntity send(DataMessage dataMessage) {
        String uuid = UUID.randomUUID().toString();
        dataMessage.setId(uuid);
        try {
            stData = objectMapper.writeValueAsString(dataMessage);
        }catch (Exception ex) {}
        jmsTemplate.send("messageID", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(stData);
                textMessage.setStringProperty("username", "ali@mail.com");
                textMessage.setStringProperty("password", "12345");
                return textMessage;
            }
        });
        return new ResponseEntity("Message Send..", HttpStatus.OK);
    }

}
