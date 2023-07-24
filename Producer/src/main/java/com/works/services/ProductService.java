package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    final JmsTemplate jmsTemplate_basket;
    final ObjectMapper objectMapper;

    public ResponseEntity sendBasket(Product product) {
        String sendData = "";
        try {
            product.setTime( System.currentTimeMillis() );
            sendData = objectMapper.writeValueAsString(product);
            jmsTemplate_basket.convertAndSend(sendData);
            System.out.println(sendData);
        }catch (Exception ex) {}
        return new ResponseEntity(product, HttpStatus.OK);
    }

}
