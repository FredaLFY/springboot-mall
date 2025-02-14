package com.freda.springbootmall.dao;

import com.freda.springbootmall.dto.UserRegisterRequest;
import com.freda.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
