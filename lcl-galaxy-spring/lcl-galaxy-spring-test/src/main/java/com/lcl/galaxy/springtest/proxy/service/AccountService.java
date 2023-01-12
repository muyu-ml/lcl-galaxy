package com.lcl.galaxy.springtest.proxy.service;

import com.lcl.galaxy.springtest.proxy.model.Account;

public interface AccountService {
    boolean doAccountTransaction(Account source, Account dest, int amount);
}
