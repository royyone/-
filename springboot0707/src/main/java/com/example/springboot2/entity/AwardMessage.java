package com.example.springboot2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 获奖信息及对应比赛,奖状存放位置
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("award_message")

public class AwardMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer awardId;

    private Integer competitionId;

    private String awardCollege;

    private String awardGrade;
}
