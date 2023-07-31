package com.works.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.models.DataMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Configuration
@RequiredArgsConstructor
public class DataMessageListener {

    final ObjectMapper objectMapper;

    @JmsListener(destination = "messageID")
    public void pullMessage(TextMessage textMessage) throws Exception {
        String username = textMessage.getStringProperty("username");
        String password = textMessage.getStringProperty("password");
        if ( username != null && password != null ) {
            if (username.equals("ali@mail.com") && password.equals("12345")) {
                String stData = textMessage.getText();
                DataMessage dataMessage = objectMapper.readValue(stData, DataMessage.class);
                System.out.println( dataMessage );
            }else {
                System.out.println("Username or Password Fail");
            }
        }else {
            System.out.println("Username or Password Null");
        }
    }

}
