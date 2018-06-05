package com.brain.bim.barinbim.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brain.bim.barinbim.dao.AccountsDao;
import com.brain.bim.barinbim.model.AccountModel;


@Service
public class AccountsHandler {
  

  private final AccountsDao accountsDao;

  @Autowired
  public AccountsHandler(final AccountsDao accountsDao) {
    this.accountsDao = accountsDao;
  }
  
  public List<AccountModel> listAccount(int maxCount){
    
    return accountsDao.listAccounts(maxCount);
  }
  
  public AccountModel readAccount(Long id){
    
    return accountsDao.readAccount(id);
  }
  
  public AccountModel saveAccount(AccountModel account){
    
    return accountsDao.updateAccount(account);
  }
  
  
}
