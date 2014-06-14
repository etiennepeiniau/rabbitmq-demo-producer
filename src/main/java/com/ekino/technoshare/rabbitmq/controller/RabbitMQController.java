package com.ekino.technoshare.rabbitmq.controller;

import com.ekino.technoshare.rabbitmq.model.Message;
import com.ekino.technoshare.rabbitmq.model.MessageType;
import com.ekino.technoshare.rabbitmq.model.User;
import com.ekino.technoshare.rabbitmq.repository.UserRepository;
import com.ekino.technoshare.rabbitmq.service.MessageService;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.InvalidParameterException;
import java.util.List;

@RestController
@RequestMapping(value = "/rabbitmq")
public class RabbitMQController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> list() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users/clear/all", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void clearAll() {
        messageService.sendToAll(new Message.Builder()
                .setType(MessageType.CLEAR)
                .build());
    }


    @RequestMapping(value = "/users/color/all", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void colorAll(@RequestParam String color) {
        messageService.sendToAll(new Message.Builder()
                .setType(MessageType.COLOR)
                .setValue(color)
                .build());
    }

    @RequestMapping(value = "/users/clear/one", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void clearOne(@RequestParam String userName) {
        messageService.sendToOne(new Message.Builder()
                .setType(MessageType.CLEAR)
                .build(), userName);
    }


    @RequestMapping(value = "/users/color/one", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void colorOne(@RequestParam String color, @RequestParam String userName) {
        messageService.sendToOne(new Message.Builder()
                .setType(MessageType.COLOR)
                .setValue(color)
                .build(), userName);
    }

    @RequestMapping(value = "/users/clear/some", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void clearSome(@RequestParam String routingKey) {
        messageService.sendToSome(new Message.Builder()
                .setType(MessageType.CLEAR)
                .build(), routingKey);
    }


    @RequestMapping(value = "/users/color/some", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void colorSome(@RequestParam String color, @RequestParam String routingKey) {
        messageService.sendToSome(new Message.Builder()
                .setType(MessageType.COLOR)
                .setValue(color)
                .build(), routingKey);
    }

}