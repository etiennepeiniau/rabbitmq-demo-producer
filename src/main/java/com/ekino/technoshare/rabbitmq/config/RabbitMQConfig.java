package com.ekino.technoshare.rabbitmq.config;

import com.ekino.technoshare.rabbitmq.listener.AddUserListener;
import com.ekino.technoshare.rabbitmq.listener.RemoveUserListener;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ComponentScan(basePackages = {
        "com.ekino.technoshare.rabbitmq.listener"
})
public class RabbitMQConfig {

    @Resource
    private AddUserListener addUserListener;

    @Resource
    private RemoveUserListener removeUserListener;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    // User management

    @Bean
    public Queue addUserQueue() {
        return new Queue("server.add.user.queue", false, true, true);
    }

    @Bean
    public SimpleMessageListenerContainer addUserListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setConcurrentConsumers(1);
        container.setExclusive(true);
        container.setQueues(addUserQueue());
        container.setMessageListener(addUserListener);
        return container;
    }

    @Bean
    public Queue removeUserQueue() {
        return new Queue("server.remove.user.queue", false, true, true);
    }

    @Bean
    public SimpleMessageListenerContainer removeUserListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setConcurrentConsumers(1);
        container.setExclusive(true);
        container.setQueues(removeUserQueue());
        container.setMessageListener(removeUserListener);
        return container;
    }

}
