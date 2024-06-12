package com.example.springbootmallmac.service.impl;

import com.example.springbootmallmac.dao.UserDao;
import com.example.springbootmallmac.dto.UserRequest;
import com.example.springbootmallmac.model.User;
import com.example.springbootmallmac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRequest userRequest) {
        return userDao.createUser(userRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
