package com.example.springboot2.mapper;

import com.example.springboot2.pojo.Certificate;

import java.util.List;

public interface awardMapper {
    Integer insertAward(Integer game_id,
                        String college_name,
                        String stu_name,
                        String award_name,
                        String award,
                        String file_path,
                        String adviser,
                        String work_name
    );
    // todo 删除获奖信息
    Integer deleteAward(Integer award_id);
    List<Certificate> selectAward(Integer game_id);
    Integer updateAward(Integer award_id, Integer status, String file_path);
    List<Certificate> stuSelect(String stu_name);
}
