package com.matio.mapping;

import com.matio.pojo.User_level;

public interface User_levelMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User_level record);

    int insertSelective(User_level record);

    User_level selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User_level record);

    int updateByPrimaryKey(User_level record);
}