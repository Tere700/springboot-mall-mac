package com.example.springbootmallmac.dao;

import com.example.springbootmallmac.dto.UserRequest;
import com.example.springbootmallmac.model.User;

public interface UserDao {
    Integer createUser(UserRequest userRequest);
    User getUserById(Integer userId);
}
