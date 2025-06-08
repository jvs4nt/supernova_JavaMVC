package com.supernova.supernovamvc.configs;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "autenticacao_aprovada_queue";
    public static final String DLQ = "autenticacao_aprovada_queue.dlq";
    public static final String EXCHANGE = "supernova-exchange";
    public static final String ROUTING_KEY = "supernova.documento.aprovado";
    public static final String DLQ_ROUTING_KEY = "supernova.documento.aprovado.dlq";

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DLQ);
    }

    @Bean
    public Queue documentoAprovadoQueue() {
        return QueueBuilder.durable(QUEUE)
                .withArgument("x-dead-letter-exchange", EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DLQ_ROUTING_KEY)
                .build();
    }



    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(documentoAprovadoQueue()).to(exchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(exchange()).with(DLQ_ROUTING_KEY);
    }
}
