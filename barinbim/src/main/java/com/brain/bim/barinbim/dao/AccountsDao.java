package com.brain.bim.barinbim.dao;

import java.util.List;

import com.brain.bim.barinbim.model.AccountModel;


public interface AccountsDao {
  
  Long createAccount(AccountModel account);

  AccountModel createAndReadAccount(AccountModel account);

  AccountModel readAccount(final Long id);

  AccountModel updateAccount(AccountModel account);

  void deleteAccount(AccountModel account);

  List<AccountModel> listAccounts(final int maxCount);

}
