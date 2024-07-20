package com.example.springboot2.service;

import com.example.springboot2.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学生用户信息表 服务类
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
public interface StudentMessageService extends IService<Student> {
    Student studentCheck(Student student);
}
