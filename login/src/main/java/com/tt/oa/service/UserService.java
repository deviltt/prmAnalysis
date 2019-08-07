package com.tt.oa.service;

import com.tt.oa.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    User getUser(@Param("username") String username);
}
