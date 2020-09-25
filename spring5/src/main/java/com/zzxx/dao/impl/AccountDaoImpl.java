package com.zzxx.dao.impl;

import com.zzxx.dao.AccountDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component("accountDao")
@Repository
public class AccountDaoImpl implements AccountDao {

    @Override
    public void updateAccount() {
        System.out.println("update");
    }
}
