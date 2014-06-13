package com.ekino.technoshare.rabbitmq.service;

import com.ekino.technoshare.rabbitmq.model.Message;
import com.google.gson.Gson;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageConverterService {

    @Resource
    private Gson gson;

    public org.springframework.amqp.core.Message toAmqpMessage(Message message) {
        return MessageBuilder
                .withBody(gson.toJson(message).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
    }

}
