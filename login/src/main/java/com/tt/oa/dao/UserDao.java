package com.tt.oa.dao;

import com.tt.oa.entity.User;

public interface UserDao {
    User getUser(String username);
}
