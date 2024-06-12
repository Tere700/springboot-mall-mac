package com.example.springbootmallmac.service;

import com.example.springbootmallmac.dto.UserRegisterRequest;
import com.example.springbootmallmac.model.User;

public interface UserService {

    Integer register (UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
}
