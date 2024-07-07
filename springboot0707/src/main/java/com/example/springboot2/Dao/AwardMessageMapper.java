package com.example.springboot2.Dao;

import com.example.springboot2.entity.AwardMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 获奖信息及对应比赛,奖状存放位置 Mapper 接口
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Mapper
public interface AwardMessageMapper extends BaseMapper<AwardMessage> {

}
