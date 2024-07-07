package com.example.springboot2.Dao;

import com.example.springboot2.entity.CompetitionMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 比赛信息表 Mapper 接口
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Mapper
public interface CompetitionMessageMapper extends BaseMapper<CompetitionMessage> {

}
