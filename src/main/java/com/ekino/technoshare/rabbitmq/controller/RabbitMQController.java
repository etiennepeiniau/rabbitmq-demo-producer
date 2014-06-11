package com.ekino.technoshare.rabbitmq.controller;

import com.ekino.technoshare.rabbitmq.model.User;
import com.ekino.technoshare.rabbitmq.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value="/rabbitmq")
public class RabbitMQController {

    @Resource
    private UserRepository userRepository;

    @RequestMapping(value="/users", method= RequestMethod.GET)
    public List<User> get() {
        return userRepository.getAll();
    }

}