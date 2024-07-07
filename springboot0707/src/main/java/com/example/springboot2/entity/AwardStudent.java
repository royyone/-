package com.example.springboot2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 获奖信息学生部分
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("award_student")
public class AwardStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer awardId;

    private Integer studentId;
}
