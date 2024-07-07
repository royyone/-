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
 * 比赛信息表
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("competition_message")
public class CompetitionMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "competition_id", type = IdType.AUTO)
    private Integer competitionId;

    private String competitionType;

    private String competitionLevel;

    private String competitionName;

    private String competitionRegister;

    private String competitionYear;

    private String competitionTerm;
}
