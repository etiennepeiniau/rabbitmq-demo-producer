package com.ekino.technoshare.rabbitmq.service;

import com.ekino.technoshare.rabbitmq.model.Message;
import com.ekino.technoshare.rabbitmq.model.User;
import com.google.gson.Gson;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MessageConverterService converter;

    @Resource
    private DirectExchange serverDirect;

    @Resource
    private FanoutExchange serverFanout;

    @Resource
    private TopicExchange serverTopic;

    public void sendToAll(Message message) {
        rabbitTemplate.send(serverFanout.getName(), "", converter.toAmqpMessage(message));
    }

    public void sendToOne(Message message, User user) {
        rabbitTemplate.send(serverDirect.getName(), user.getName(), converter.toAmqpMessage(message));
    }

    public void sendToSome(Message message, String routingKey) {
        rabbitTemplate.send(serverTopic.getName(), routingKey, converter.toAmqpMessage(message));
    }

}
