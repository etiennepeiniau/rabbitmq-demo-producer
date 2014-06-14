package com.ekino.technoshare.rabbitmq.repository;

import com.ekino.technoshare.rabbitmq.model.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Repository
public class UserRepository {

    private static ConcurrentSkipListSet<User> repository = new ConcurrentSkipListSet<User>();

    public void putIfAbsent(User user){
        repository.add(user);
    }

    public void remove(User user){
        repository.remove(user);
    }

    public List<User> findAll() {
        return ImmutableList.copyOf(repository);
    }

}

