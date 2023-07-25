package com.works;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class BasketListener  {

    @JmsListener(destination = "basket")
    public void basketFnc(Message message) {
        try {
            if ( message instanceof TextMessage) {
                String data = ((TextMessage) message).getText();
                System.out.println( data );
            }
        }catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }

    }

    @JmsListener(destination = "user")
    public void userFnc(Message message) {
        try {
            if ( message instanceof TextMessage) {
                String data = ((TextMessage) message).getText();
                System.out.println( data );
            }
        }catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

}
