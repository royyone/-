package com.example.springboot2.service.impl;

import com.example.springboot2.entity.AwardMessage;
import com.example.springboot2.Dao.AwardMessageMapper;
import com.example.springboot2.service.AwardMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 获奖信息及对应比赛,奖状存放位置 服务实现类
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Service
public class AwardMessageServiceImpl extends ServiceImpl<AwardMessageMapper, AwardMessage> implements AwardMessageService {

}
