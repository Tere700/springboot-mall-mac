package com.example.springbootmallmac.service.impl;

import com.example.springbootmallmac.dao.UserDao;
import com.example.springbootmallmac.dto.UserLoginRequest;
import com.example.springbootmallmac.dto.UserRegisterRequest;
import com.example.springbootmallmac.model.User;
import com.example.springbootmallmac.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //取名為register是因為不只創建帳號 還有創建帳號的邏輯
        //檢查註冊的Email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if (user != null){
            log.warn("該email {} 已註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        if (user == null){
            log.warn("該email {} 尚未註冊", userLoginRequest.getEmail() );
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(user.getPassword().equals(userLoginRequest.getPassword())){
            return user;
        }else {

            log.warn("email{} 的密碼不正確", userLoginRequest.getEmail() );
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
