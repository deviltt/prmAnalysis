package com.tt.oa.service.impl;

import com.tt.oa.dao.UserDao;
import com.tt.oa.entity.User;
import com.tt.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }
}
