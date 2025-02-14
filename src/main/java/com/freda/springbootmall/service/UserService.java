package com.freda.springbootmall.service;

import com.freda.springbootmall.dto.UserRegisterRequest;
import com.freda.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);
}
