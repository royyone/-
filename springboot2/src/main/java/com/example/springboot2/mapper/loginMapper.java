package com.example.springboot2.mapper;

import com.example.springboot2.pojo.Login;

public interface loginMapper {
    Login loginCheck(String username, String password);

    Login getById(Integer id);

    Login getUsername(String username);
}
