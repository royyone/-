package com.example.springboot2.service.impl;

import com.example.springboot2.entity.Teacher;
import com.example.springboot2.Dao.TeacherMessageMapper;
import com.example.springboot2.service.TeacherMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教师用户 服务实现类
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Service
public class TeacherMessageServiceImpl extends ServiceImpl<TeacherMessageMapper, Teacher> implements TeacherMessageService {

}
