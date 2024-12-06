package com.example.springboot2.mapper;

public interface userMapper {
    Integer selectUser(String user_name);
    Integer insertUser(String user_name,String user_pwd,Integer isadmin,String truth_name,String user_college);
}
