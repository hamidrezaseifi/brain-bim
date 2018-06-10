package com.brain.bim.barinbim.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.brain.bim.barinbim.dao.EmailsDao;
import com.brain.bim.barinbim.model.EmailModel;
import com.brain.bim.barinbim.model.enums.ContactOwnerType;
import com.brain.bim.barinbim.model.EmailModel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

@Repository
public class EmailsDaoImpl implements EmailsDao {
    
  private final Logger logger = LoggerFactory.getLogger(AddressesDaoImpl.class);
  
  private final JdbcTemplate               jdbcTemplate;
  private final PlatformTransactionManager platformTransactionManager;
  
  
  @Autowired
  public EmailsDaoImpl(final JdbcTemplate jdbcTemplate, final PlatformTransactionManager platformTransactionManager) {
   this.jdbcTemplate = jdbcTemplate;
   this.platformTransactionManager = platformTransactionManager;
  }

  
  @Override
  public Long createEmail(EmailModel email) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void deleteEmail(EmailModel email) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void deleteEmails(Long ownerId, String ownerType) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public List<EmailModel> listEmails(Long ownerId, ContactOwnerType ownerType) {
    // TODO Auto-generated method stub
    return queryForEmails(ownerId, ownerType);
  }

  private List<EmailModel> queryForEmails(Long ownerId, ContactOwnerType ownerType) {
    
    final String sql = " select ownerid, owner_type, email_name, emailaddress,"
        + " comments, version, created, updated from tblemails where ownerid = ? and owner_type = ? ";
    
    List<EmailModel> list = jdbcTemplate.query(sql, new Object[]{ownerId, ownerType.toString()}, new RowMapper<EmailModel>(){

      @Override
      public EmailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createEmailFromResultSet(rs);
      }
      
    });
    
    return list;
    
  }  

  private EmailModel createEmailFromResultSet(ResultSet rs) throws SQLException {
      
    EmailModel amodel = new EmailModel();
  
    amodel.setOwnerId(rs.getLong("ownerid"));
    amodel.setOwnerType(rs.getString("owner_type"));
    amodel.setEmailName(rs.getString("email_name"));
    amodel.setEmailaddress(rs.getString("emailaddress"));
    amodel.setComments(rs.getString("comments"));
    amodel.setVersion(rs.getInt("version"));
      
    amodel.setCreated(rs.getTimestamp("created").toLocalDateTime());
    amodel.setUpdated(rs.getTimestamp("updated").toLocalDateTime());
  
    
    return amodel;
      
  }    
}
