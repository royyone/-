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
 * 教师用户
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("teacher_message")

public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;

    private String teacherUsername;

    private String teacherPassword;

    private String teacherIsadmin;

    private String teacherCollege;

    private String teacherName;
}
