package com.works;

import com.works.services.TinkEncDec;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;

@Component
@RequiredArgsConstructor
public class BasketListener  {

    final TinkEncDec tinkEncDec;

    @JmsListener(destination = "basket")
    public void basketFnc(Message message) {
        try {
            if ( message instanceof TextMessage) {
                String data = ((TextMessage) message).getText();
                String plaintText = tinkEncDec.decrypt(data);
                System.out.println( plaintText );
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
