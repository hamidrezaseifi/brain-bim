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

import com.brain.bim.barinbim.dao.AddressesDao;
import com.brain.bim.barinbim.model.AddressModel;
import com.brain.bim.barinbim.model.CompanyModel;
import com.brain.bim.barinbim.model.enums.ContactOwnerType;


@Repository
public class AddressesDaoImpl implements AddressesDao {
    
  private final Logger logger = LoggerFactory.getLogger(AddressesDaoImpl.class);

  private final JdbcTemplate               jdbcTemplate;
  private final PlatformTransactionManager platformTransactionManager;

  
  @Autowired
  public AddressesDaoImpl(final JdbcTemplate jdbcTemplate, final PlatformTransactionManager platformTransactionManager) {
    this.jdbcTemplate = jdbcTemplate;
    this.platformTransactionManager = platformTransactionManager;
  }

  
  @Override
  public Long createAddress(AddressModel address) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void deleteAddresses(Long ownerId, String ownerType) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void deleteAddress(AddressModel address) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public List<AddressModel> listAddresses(Long ownerId, ContactOwnerType ownerType) {
    // TODO Auto-generated method stub
    return queryForAddresses(ownerId, ownerType);
  }
  

  private List<AddressModel> queryForAddresses(Long ownerId, ContactOwnerType ownerType) {
    
    final String sql = " select ownerid, owner_type, address_name, country, city, postcode, street, "
        + "street2, house_number, comments, version, created, updated from tbladdresses where ownerid = ? and owner_type = ? ";
    
    List<AddressModel> list = jdbcTemplate.query(sql, new Object[]{ownerId, ownerType.toString()}, new RowMapper<AddressModel>(){

      @Override
      public AddressModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createAddressFromResultSet(rs);
      }
      
    });
    
    return list;
    
  }  

  private AddressModel createAddressFromResultSet(ResultSet rs) throws SQLException {
      
    AddressModel amodel = new AddressModel();
  
    amodel.setOwnerId(rs.getLong("ownerid"));
    amodel.setOwnerType(rs.getString("owner_type"));
    amodel.setAddressName(rs.getString("address_name"));
    amodel.setCity(rs.getString("city"));
    amodel.setComments(rs.getString("comments"));
    amodel.setCountry(rs.getString("country"));
    amodel.setHouseNumber(rs.getString("house_number"));
    amodel.setPostCode(rs.getString("postcode"));
    amodel.setStreet(rs.getString("street"));
    amodel.setStreet2(rs.getString("street2"));
    amodel.setVersion(rs.getInt("version"));
      
    amodel.setCreated(rs.getTimestamp("created").toLocalDateTime());
    amodel.setUpdated(rs.getTimestamp("updated").toLocalDateTime());
  
    
    return amodel;
      
  }
}
