package com.matio.mapping;

import com.matio.pojo.Session;

public interface SessionMapper {
    int insert(Session record);

    int insertSelective(Session record);

    Session selectBySession(String session_id);

    Session selectByUserId(Integer user_id);

    void updateSession(Session update);

    void deleteBySession(String session);
}