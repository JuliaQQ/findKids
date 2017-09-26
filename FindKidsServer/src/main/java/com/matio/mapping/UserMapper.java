package com.matio.mapping;

import com.matio.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByUserName(String user_name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}