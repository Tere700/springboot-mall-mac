package com.example.springbootmallmac.controller;

import com.example.springbootmallmac.dao.UserDao;
import com.example.springbootmallmac.dto.UserLoginRequest;
import com.example.springbootmallmac.dto.UserRegisterRequest;
import com.example.springbootmallmac.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userDao;

    private ObjectMapper objectMapper = new ObjectMapper();

    // 註冊新帳號
    @Transactional //還原資料庫
    @Test
    public void register_success() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test@gmail.com");
        userRegisterRequest.setPassword("123456");

        String json = objectMapper.writeValueAsString(userRegisterRequest);
        RequestBuilder RB = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(RB)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo(userRegisterRequest.getEmail())))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));

        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        assertNotEquals(userRegisterRequest.getPassword(), user.getPassword());
    }

    @Transactional
    @Test
    public void register_invalidEmailFormat() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("testjfpj");
        userRegisterRequest.setPassword("123456");

        String json = objectMapper.writeValueAsString(userRegisterRequest);
        RequestBuilder RB = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(RB)
                .andExpect(status().is(400));
    }

    @Transactional
    @Test
    public void register_emailAlreadyExist() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test@gmail.com");
        userRegisterRequest.setPassword("123456");

        String json = objectMapper.writeValueAsString(userRegisterRequest);
        RequestBuilder RB = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(RB)
                .andExpect(status().is(201));

        mockMvc.perform(RB)
                .andExpect(status().is(400));
    }

    // 登入
    @Transactional
    @Test
    public void login_success() throws Exception {
    UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
    userRegisterRequest.setEmail("test@gmail.com");
    userRegisterRequest.setPassword("123456");
    register(userRegisterRequest);

    UserLoginRequest userLoginRequest = new UserLoginRequest();
    userLoginRequest.setEmail("test@gmail.com");
    userLoginRequest.setPassword("123456");

    String json = objectMapper.writeValueAsString(userLoginRequest);
    RequestBuilder RB = MockMvcRequestBuilders
            .post("/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json);
    mockMvc.perform(RB)
            .andExpect(status().is(200))
            .andExpect(jsonPath("$.userId", notNullValue()))
            .andExpect(jsonPath("$.email", equalTo(userLoginRequest.getEmail())))
            .andExpect(jsonPath("$.createdDate", notNullValue()))
            .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }

    @Transactional
    @Test
    public void login_wrongPassword() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test@gmail.com");
        userRegisterRequest.setPassword("123456");
        register(userRegisterRequest);

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("test@gmail.com");
        userLoginRequest.setPassword("165432");
        String json = objectMapper.writeValueAsString(userLoginRequest);

        RequestBuilder RB = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(RB)
                .andExpect(status().is(400));

    }

    @Test
    public void login_invalidEmailFormat() throws Exception {
        UserLoginRequest us = new UserLoginRequest();
        us.setEmail("testejifjom");
        us.setPassword("123456");
        String json = objectMapper.writeValueAsString(us);
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(rb)
                .andExpect(status().is(400));
    }

    @Transactional
    @Test
    public void login_emailNotExist() throws Exception {
        //因為驗證email不存在 所以沒有用UserRegisterRequest創建email也沒關係
        UserLoginRequest us = new UserLoginRequest();
        us.setEmail("test@gmail.com");
        us.setPassword("123456");
        String json = objectMapper.writeValueAsString(us);
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(rb)
                .andExpect(status().is(400));

    }

    private void register(UserRegisterRequest userRegisterRequest) throws Exception {
        String json = objectMapper.writeValueAsString(userRegisterRequest);
        RequestBuilder RB = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(RB)
                .andExpect(status().isCreated());

    }
}