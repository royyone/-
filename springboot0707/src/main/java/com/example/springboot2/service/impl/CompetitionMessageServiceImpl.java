package com.example.springboot2.service.impl;

import com.example.springboot2.entity.CompetitionMessage;
import com.example.springboot2.Dao.CompetitionMessageMapper;
import com.example.springboot2.service.CompetitionMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 比赛信息表 服务实现类
 * </p>
 *
 * @author suhsbeba
 * @since 2024-07-07
 */
@Service
public class CompetitionMessageServiceImpl extends ServiceImpl<CompetitionMessageMapper, CompetitionMessage> implements CompetitionMessageService {

}
