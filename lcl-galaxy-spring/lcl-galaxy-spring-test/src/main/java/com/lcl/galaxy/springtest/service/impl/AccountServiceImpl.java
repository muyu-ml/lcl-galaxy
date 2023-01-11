package com.lcl.galaxy.springtest.service.impl;

import com.lcl.galaxy.springtest.model.Account;
import com.lcl.galaxy.springtest.service.AccountService;

public class AccountServiceImpl implements AccountService {
    @Override
    public boolean doAccountTransaction(Account source, Account dest, int amount) {
        //System.out.println(source.toString() + "向" + dest.toString() + "转账：" + amount);
        return false;
    }
}
