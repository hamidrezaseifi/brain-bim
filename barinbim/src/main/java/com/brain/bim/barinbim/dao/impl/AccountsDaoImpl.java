package com.brain.bim.barinbim.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import com.brain.bim.barinbim.dao.AccountsDao;
import com.brain.bim.barinbim.model.AccountModel;
import com.brain.bim.barinbim.model.UserModel;
import com.brain.bim.barinbim.model.AccountModel;


@Repository
public class AccountsDaoImpl implements AccountsDao {
  
  private final Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);

  private final JdbcTemplate               jdbcTemplate;
  private final PlatformTransactionManager platformTransactionManager;

  
  @Autowired
  public AccountsDaoImpl(final JdbcTemplate jdbcTemplate, final PlatformTransactionManager platformTransactionManager) {
    this.jdbcTemplate = jdbcTemplate;
    this.platformTransactionManager = platformTransactionManager;
  }

  
  
  @Override
  public Long createAccount(AccountModel account) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public AccountModel createAndReadAccount(AccountModel account) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public AccountModel readAccount(final Long id) {
    // TODO Auto-generated method stub
    return queryForAccountRead(id);
  }
  
  @Override
  public AccountModel updateAccount(AccountModel account) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void deleteAccount(AccountModel account) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public List<AccountModel> listAccounts(final int maxCount) {
    // TODO Auto-generated method stub
    return queryForAccounts(maxCount);
  }
  
  private AccountModel queryForAccountRead(final Long id) {
    
    final String sql = " select id, account_name, comments, status, version, created, updated from tblaccounts where id = ? ";
    
    List<AccountModel> list = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<AccountModel>(){

      @Override
      public AccountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createAccountFromResultSet(rs);
      }
      
    });
    
    return list != null && list.size() > 0 ? list.get(0) : null;
    
  }  

  private List<AccountModel> queryForAccounts(final int maxCount) {
    
    final String sql = " select id, account_name, comments, status, version, created, updated from tblaccounts limit ? ";
    
    List<AccountModel> list = jdbcTemplate.query(sql, new Object[]{maxCount}, new RowMapper<AccountModel>(){

      @Override
      public AccountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createAccountFromResultSet(rs);
      }
      
    });
    
    return list;
    
  }  

  private AccountModel createAccountFromResultSet(ResultSet rs) throws SQLException {
      
    AccountModel umodel = new AccountModel();
  
    umodel.setId(rs.getLong("id"));
    umodel.setAccountName(rs.getString("account_name"));
    umodel.setComments(rs.getString("comments"));
    umodel.setVersion(rs.getInt("version"));
    umodel.setStatus(rs.getInt("status"));
  
    umodel.setCreated(rs.getTimestamp("created").toLocalDateTime());
    umodel.setUpdated(rs.getTimestamp("updated").toLocalDateTime());
  
    return umodel;
      
  }
}
