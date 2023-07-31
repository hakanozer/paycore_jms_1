package com.works.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.models.DataMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

import javax.jms.*;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataMessageListener {

    final ObjectMapper objectMapper;
    final JmsTemplate jmsTemplate;

    @JmsListener(destination = "messageID", id = "2", subscription = "q2")
    public void pullMessage(
            TextMessage textMessage,
            @Headers MessageHeaders messageHeaders
            ) throws Exception {
        // headers
        Set<String> keys = messageHeaders.keySet();
        for( String key : keys) {
            System.out.println( key + " - " + messageHeaders.get(key) );
        }
        Destination destination = textMessage.getJMSReplyTo();
        System.out.println( "destination :" + destination );
        String username = textMessage.getStringProperty("username");
        String password = textMessage.getStringProperty("password");
        if ( username != null && password != null ) {
            if (username.equals("ali@mail.com") && password.equals("12345")) {
                String stData = textMessage.getText();
                DataMessage dataMessage = objectMapper.readValue(stData, DataMessage.class);
                System.out.println( dataMessage );
            }else {
                System.out.println("Username or Password Fail");
                jmsTemplate.send("messagID1", new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage textMessage1 = session.createTextMessage("Hello!");
                        return textMessage1;
                    }
                });
            }
        }else {
            System.out.println("Username or Password Null");
        }
    }

}
