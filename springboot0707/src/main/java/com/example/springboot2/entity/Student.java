package com.example.springboot2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生用户信息表
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("student_message")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;


    private String studentUsername;

    private String studentPassword;

    private String truthName;

    private String studentCollege;
}
