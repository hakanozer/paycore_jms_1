package com.works.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Configuration
@RequiredArgsConstructor
public class BasketMessageConsumer implements MessageListener {

    final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message) {
        if ( message instanceof TextMessage ) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String messageID = textMessage.getJMSMessageID();
                long time = textMessage.getJMSTimestamp();
                String stData = textMessage.getText();
                Product product = objectMapper.readValue(stData, Product.class);
                System.out.println(messageID + " - " + time + " - " +product.getPid() + " - " + product.getTitle());
            }catch (Exception ex) {
                System.err.println("onMessage error : " + ex);
            }

        }
    }

}
