package com.example.springbootmallmac.service;

import com.example.springbootmallmac.dto.UserRequest;
import com.example.springbootmallmac.model.User;

public interface UserService {

    Integer register (UserRequest userRequest);
    User getUserById(Integer userId);
}
