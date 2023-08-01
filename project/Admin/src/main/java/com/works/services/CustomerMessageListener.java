package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.CustomerMessage;
import com.works.repositories.CustomerMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerMessageListener {

    final ObjectMapper objectMapper;
    final CustomerMessageRepository customerMessageRepository;

    @JmsListener(destination = "customerMessage")
    public void fncCustomerListener(
            TextMessage textMessage,
            @Headers MessageHeaders messageHeaders
            ) throws Exception {
        Object sessionIDObj = messageHeaders.get("sessionID");
        if (sessionIDObj != null) {
            String sessionID = (String) sessionIDObj;
            if ( !sessionID.equals("") ) {
                CustomerMessage customerMessage = objectMapper.readValue(textMessage.getText(), CustomerMessage.class );
                customerMessageRepository.save(customerMessage);
                System.out.println(customerMessage);
            }
        }

    }

    public Page<CustomerMessage> allMessage() {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(0, 20, sort );
        return customerMessageRepository.findAll(pageable);
    }

    public void delete(Long id) {
        customerMessageRepository.deleteById(id);
    }

}
