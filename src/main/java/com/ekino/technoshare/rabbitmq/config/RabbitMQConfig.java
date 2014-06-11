package com.ekino.technoshare.rabbitmq.config;

import com.ekino.technoshare.rabbitmq.listener.UserMessageListener;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
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
    private UserMessageListener userMessageListener;

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
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JsonMessageConverter();
    }

    @Bean
    public Queue serverUsersQueue() {
        return new Queue("server.users.queue", false, true, false);
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueues(serverUsersQueue());
        container.setMessageListener(userMessageListener);
        return container;
    }

}
