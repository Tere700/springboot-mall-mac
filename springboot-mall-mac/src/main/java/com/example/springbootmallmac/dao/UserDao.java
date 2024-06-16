package com.example.springbootmallmac.dao;

import com.example.springbootmallmac.dto.UserLoginRequest;
import com.example.springbootmallmac.dto.UserRegisterRequest;
import com.example.springbootmallmac.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserByEmail(String email);
    User getUserById(Integer userId);
    }
