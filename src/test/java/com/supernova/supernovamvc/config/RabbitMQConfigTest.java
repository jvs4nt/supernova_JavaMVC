package com.supernova.supernovamvc.config;

import com.supernova.supernovamvc.configs.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class RabbitMQConfigTest {

    @Test
    void testRabbitMQBeansAreLoaded() {
        var context = new AnnotationConfigApplicationContext(RabbitMQConfig.class);

        Queue queue = context.getBean("documentoAprovadoQueue", Queue.class);
        assertNotNull(queue);
        assertEquals(RabbitMQConfig.QUEUE, queue.getName());
        assertTrue(queue.isDurable());

        Queue dlq = context.getBean("deadLetterQueue", Queue.class);
        assertNotNull(dlq);
        assertEquals(RabbitMQConfig.DLQ, dlq.getName());

        TopicExchange exchange = context.getBean(TopicExchange.class);
        assertNotNull(exchange);
        assertEquals(RabbitMQConfig.EXCHANGE, exchange.getName());

        Binding binding = context.getBean("binding", Binding.class);
        assertNotNull(binding);
        assertEquals(RabbitMQConfig.ROUTING_KEY, binding.getRoutingKey());

        Binding dlqBinding = context.getBean("dlqBinding", Binding.class);
        assertNotNull(dlqBinding);
        assertEquals(RabbitMQConfig.DLQ_ROUTING_KEY, dlqBinding.getRoutingKey());

        context.close();
    }
}
