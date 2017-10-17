package com.matio.mapping;

import com.matio.pojo.Vcode;

public interface VcodeMapper {
    int deleteByPrimaryKey(String phoneNum);

    int insert(Vcode record);

    int insertSelective(Vcode record);

    Vcode selectByPrimaryKey(String phoneNum);

    int updateByPrimaryKeySelective(Vcode record);

    int updateByPrimaryKey(Vcode record);
}