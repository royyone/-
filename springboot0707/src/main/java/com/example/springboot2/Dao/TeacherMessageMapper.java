package com.example.springboot2.Dao;

import com.example.springboot2.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 教师用户 Mapper 接口
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Mapper
public interface TeacherMessageMapper extends BaseMapper<Teacher> {

}
