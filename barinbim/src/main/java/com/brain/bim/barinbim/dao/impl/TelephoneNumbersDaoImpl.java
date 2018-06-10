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

import com.brain.bim.barinbim.dao.TelephoneNumbersDao;
import com.brain.bim.barinbim.model.TelephoneNumberModel;
import com.brain.bim.barinbim.model.enums.ContactOwnerType;


@Repository
public class TelephoneNumbersDaoImpl implements TelephoneNumbersDao {
     
  private final Logger logger = LoggerFactory.getLogger(AddressesDaoImpl.class);
  
  private final JdbcTemplate               jdbcTemplate;
  private final PlatformTransactionManager platformTransactionManager;
  
  
  @Autowired
  public TelephoneNumbersDaoImpl(final JdbcTemplate jdbcTemplate, final PlatformTransactionManager platformTransactionManager) {
    this.jdbcTemplate = jdbcTemplate;
    this.platformTransactionManager = platformTransactionManager;
  }



  @Override
  public Long createTelephoneNumber(TelephoneNumberModel address) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void deleteTelephoneNumber(TelephoneNumberModel address) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void deleteTelephoneNumbers(Long ownerId, String ownerType) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public List<TelephoneNumberModel> listTelephoneNumber(Long ownerId, ContactOwnerType ownerType) {
    // TODO Auto-generated method stub
    return queryForTelephoneNumbers(ownerId, ownerType);
  }

  private List<TelephoneNumberModel> queryForTelephoneNumbers(Long ownerId, ContactOwnerType ownerType) {
    
    final String sql = " select ownerid, owner_type, telephone_name, country_code, city_code, subscriber,"
        + " comments, version, created, updated from tbltelephones where ownerid = ? and owner_type = ? ";
    
    List<TelephoneNumberModel> list = jdbcTemplate.query(sql, new Object[]{ownerId, ownerType.toString()}, new RowMapper<TelephoneNumberModel>(){

      @Override
      public TelephoneNumberModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createTelephoneNumberFromResultSet(rs);
      }
      
    });
    
    return list;
    
  }  

  private TelephoneNumberModel createTelephoneNumberFromResultSet(ResultSet rs) throws SQLException {
      
    TelephoneNumberModel amodel = new TelephoneNumberModel();
  
    amodel.setOwnerId(rs.getLong("ownerid"));
    amodel.setOwnerType(rs.getString("owner_type"));
    amodel.setTelephoneName(rs.getString("telephone_name"));
    amodel.setCountryCode(rs.getString("country_code"));
    amodel.setCityCode(rs.getString("city_code"));
    amodel.setSubscriber(rs.getString("subscriber"));
    amodel.setComments(rs.getString("comments"));
    amodel.setVersion(rs.getInt("version"));
      
    amodel.setCreated(rs.getTimestamp("created").toLocalDateTime());
    amodel.setUpdated(rs.getTimestamp("updated").toLocalDateTime());
  
    
    return amodel;
      
  }  
}
