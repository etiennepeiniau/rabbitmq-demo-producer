package com.ekino.technoshare.rabbitmq.repository;

import com.ekino.technoshare.rabbitmq.model.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Repository
public class UserRepository {

    private static ConcurrentMap<String, User> repository = Maps.newConcurrentMap();

    public void putIfAbsent(User user){
        repository.putIfAbsent(user.getName(), user);
    }

    public void remove(User user){
        repository.remove(user.getName());
    }

    public List<User> getAll() {
        return Lists.newArrayList(repository.values());
    }

}

