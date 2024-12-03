package com.ms.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.FanoutExchangeParser;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQProducerConfig {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.queueOne.name}")
    private String queueOne;

    @Value("${spring.rabbitmq.queueTwo.name}")
    private String queueTwo;

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(exchangeName);
    }

    @Bean
    public Queue queueOne() {return new Queue(queueOne, true);
    }

    @Bean
    public Queue queueTwo() {return new Queue(queueTwo, true);
    }

    @Bean
    public Binding bindingOne(Queue queueOne, FanoutExchange exchange) {
        return BindingBuilder.bind(queueOne).to(exchange);
    }

    @Bean
    public Binding bindingTwo(Queue queueTwo, FanoutExchange exchange) {
        return BindingBuilder.bind(queueTwo).to(exchange);
    }
}
