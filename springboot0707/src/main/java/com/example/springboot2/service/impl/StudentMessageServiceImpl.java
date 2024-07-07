package com.example.springboot2.service.impl;

import com.example.springboot2.entity.Student;
import com.example.springboot2.Dao.StudentMessageMapper;
import com.example.springboot2.service.StudentMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学生用户信息表 服务实现类
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Service
public class StudentMessageServiceImpl extends ServiceImpl<StudentMessageMapper, Student> implements StudentMessageService {

    @Override
    public Student studentCheck(Student student) {

        return null;
    }
}
