package com.freda.springbootmall.service.impl;

import com.freda.springbootmall.dao.UserDao;
import com.freda.springbootmall.dto.UserRegisterRequest;
import com.freda.springbootmall.model.User;
import com.freda.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
