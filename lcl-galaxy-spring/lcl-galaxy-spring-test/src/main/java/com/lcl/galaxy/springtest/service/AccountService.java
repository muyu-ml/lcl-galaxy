package com.lcl.galaxy.springtest.service;

import com.lcl.galaxy.springtest.model.Account;

public interface AccountService {
    boolean doAccountTransaction(Account source, Account dest, int amount);
}
