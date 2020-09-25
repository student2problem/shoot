package com.zzxx;

import com.zzxx.dao.AccountDao;
import com.zzxx.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//将当前测试类与spring容器绑定
@RunWith(SpringJUnit4ClassRunner.class)
//指定容器的配置文件
@ContextConfiguration(locations = "classpath:beans.xml")
public class AccountDaoTest {
    @Autowired
    AccountDao dao;
    @Autowired
    AccountService service;

    @Test
    public void updateTest(){
        dao.updateAccount();
    }

    @Test
    public void insertTest(){
        dao.updateAccount();
    }

    @Test
    public void deleteTest(){
        dao.updateAccount();
    }
}
