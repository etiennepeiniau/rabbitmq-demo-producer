package com.ekino.technoshare.rabbitmq.listener;

import com.ekino.technoshare.rabbitmq.repository.UserRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserMessageListener implements MessageListener {

    @Resource
    private UserRepository userRepository;

    @Override
    public void onMessage(Message message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
