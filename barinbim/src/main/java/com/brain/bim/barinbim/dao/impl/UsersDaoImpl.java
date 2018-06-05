package com.brain.bim.barinbim.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import com.brain.bim.barinbim.dao.AccountsDao;
import com.brain.bim.barinbim.dao.UsersDao;
import com.brain.bim.barinbim.model.UserModel;



@Repository
public class UsersDaoImpl implements UsersDao {
  
  private final Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);

  private final AccountsDao                 accountsDao;
  private final JdbcTemplate                jdbcTemplate;
  private final PlatformTransactionManager  platformTransactionManager;


  @Autowired
  public UsersDaoImpl(final JdbcTemplate jdbcTemplate, final PlatformTransactionManager platformTransactionManager, AccountsDao accountsDao) {
    this.jdbcTemplate = jdbcTemplate;
    this.platformTransactionManager = platformTransactionManager;
    this.accountsDao = accountsDao;
  }

  @Override
  public Long createUser(UserModel user) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public UserModel createAndReadUser(UserModel user) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public UserModel readUser(Long id) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public UserModel updateUser(UserModel user) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void deleteUser(Long id, LocalDateTime updated) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public List<UserModel> listUsers(int maxCount) {
    // TODO Auto-generated method stub
    return queryForUsers(maxCount);
  }
  

  private List<UserModel> queryForUsers(final int maxCount) {
    
    final String sql = " select id, account, username, hash_password, version, status, created, updated from tblusers limit ? ";
    
    List<UserModel> list = jdbcTemplate.query(sql, new Object[]{maxCount}, new RowMapper<UserModel>(){

      @Override
      public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        return createUserFromResultSet(rs);
      }
      
    });
    
    return list;
    
  }

  private UserModel createUserFromResultSet(ResultSet rs) throws SQLException {
    
    UserModel umodel = new UserModel();

    umodel.setId(rs.getLong("id"));
    umodel.setAccount(accountsDao.readAccount(rs.getLong("account")));
    umodel.setUserName(rs.getString("username"));
    umodel.setHashPassword(rs.getString("hash_password"));
    umodel.setVersion(rs.getInt("version"));
    umodel.setStatus(rs.getInt("status"));

    umodel.setCreated(rs.getTimestamp("created").toLocalDateTime());
    umodel.setUpdated(rs.getTimestamp("updated").toLocalDateTime());

    
    return umodel;
    
  }

}
