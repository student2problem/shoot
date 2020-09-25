package com.zzxx.service.impl;

import com.zzxx.dao.AccountDao;
import com.zzxx.dao.impl.AccountDaoImpl;
import com.zzxx.service.AccountService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component("accountService")
@Service
public class AccountServiceImpl implements AccountService {
    private AccountDao ac;

    public AccountDao getAc() {
        return ac;
    }

    public void setAc(AccountDao ac) {
        this.ac = ac;
    }

    public AccountServiceImpl() {
    }

    public AccountServiceImpl(String name, AccountDao dao){
        System.out.println("name1,dao2");
    }

    @Override
    public void update() {
        ac.updateAccount();
        System.out.println("service update");
    }



}
