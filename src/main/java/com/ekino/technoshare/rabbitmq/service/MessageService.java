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
    private Gson gson;

    @Resource
    private DirectExchange serverDirect;

    @Resource
    private FanoutExchange serverFanout;

    @Resource
    private TopicExchange serverTopic;

    public void sendToAll(Message message) {

    }

    public void sendToOne(Message message, User user) {

    }

    public void sendToSome(Message message, String routingKey) {

    }

}
