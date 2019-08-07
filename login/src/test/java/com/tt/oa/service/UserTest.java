package com.tt.oa.service;

import com.tt.BaseTest;
import com.tt.oa.entity.User;
import com.tt.oa.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;
public class UserTest extends BaseTest {
    @Autowired
    private UserServiceImpl userService;
    @Test
    public void test1(){
        User user= userService.getUser("tt");
        assertEquals("tt", user.getUsername());
    }
}
