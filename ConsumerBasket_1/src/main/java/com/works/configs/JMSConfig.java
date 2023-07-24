package com.works.configs;

import lombok.RequiredArgsConstructor;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

@Configuration
public class JMSConfig {

    final BasketMessageConsumer basketMessageConsumer;
    public JMSConfig(BasketMessageConsumer basketMessageConsumer) {
        this.basketMessageConsumer = basketMessageConsumer;
    }

    @Value("${jms.broker.url}")
    private String brokerUrl;

    @Value("${jms.queue.basket}")
    private String basket;

    @Value("${jms.queue.user}")
    private String user;



    /*
    @Bean
    public BrokerService broker() throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.addConnector(brokerUrl);
        brokerService.setPersistent(false);
        System.out.println("Broker Success");
        return brokerService;
    }
     */

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(new ActiveMQConnectionFactory(brokerUrl));
    }

    @Bean
    public Destination destination_basket() {
        return new ActiveMQQueue(basket);
    }

    @Bean
    public MessageListenerContainer basketMessageListenerContainer(
            ConnectionFactory connectionFactory, Destination destination_basket
    ) {
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
        defaultMessageListenerContainer.setDestination(destination_basket);
        defaultMessageListenerContainer.setMessageListener( basketMessageConsumer );
        return defaultMessageListenerContainer;
    }

}
