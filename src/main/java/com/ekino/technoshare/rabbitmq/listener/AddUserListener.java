package com.ekino.technoshare.rabbitmq.listener;

import com.ekino.technoshare.rabbitmq.model.User;
import com.ekino.technoshare.rabbitmq.repository.UserRepository;
import com.google.common.base.Charsets;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AddUserListener implements MessageListener {

    @Resource
    private Gson gson;

    @Resource
    private UserRepository userRepository;

    @Override
    public void onMessage(Message message) {
        User user = gson.fromJson(new String(message.getBody(), Charsets.UTF_8), User.class);
        userRepository.putIfAbsent(user);
    }

}
