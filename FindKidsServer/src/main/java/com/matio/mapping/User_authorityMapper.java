package com.matio.mapping;

import com.matio.pojo.User_authority;

public interface User_authorityMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User_authority record);

    int insertSelective(User_authority record);

    User_authority selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User_authority record);

    int updateByPrimaryKey(User_authority record);
}