package com.tt.oa.service.impl;

import com.tt.oa.dao.TestExceptionDao;
import com.tt.oa.exception.MyException;
import com.tt.oa.service.TestExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service()
public class TestExceptionServiceImpl implements TestExceptionService {
    @Autowired
    private TestExceptionDao testExceptionDao;

    @Override
    public void servicemy() throws Exception {
        throw new MyException("Service中自定义异常");
    }

    @Override
    public void servicedb() throws Exception {
        throw new SQLException("Service中数据库异常");
    }

    @Override
    public void serviceno() throws Exception {
        throw new RuntimeException("Service中未知异常");

    }

    @Override
    public void daomy() throws Exception {
        testExceptionDao.daomy();
    }

    @Override
    public void daodb() throws Exception {
        testExceptionDao.daodb();
    }

    @Override
    public void daono() throws Exception {
        testExceptionDao.daono();
    }
}
