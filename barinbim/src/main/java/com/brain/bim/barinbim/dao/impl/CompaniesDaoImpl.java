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
import com.brain.bim.barinbim.dao.CompaniesDao;
import com.brain.bim.barinbim.dao.EmailsDao;
import com.brain.bim.barinbim.dao.TelephoneNumbersDao;
import com.brain.bim.barinbim.model.CompanyModel;
import com.brain.bim.barinbim.model.UserModel;
import com.brain.bim.barinbim.model.enums.ContactOwnerType;
import com.brain.bim.barinbim.model.CompanyModel;


@Repository
public class CompaniesDaoImpl implements CompaniesDao {
  
  private final Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);

  private final JdbcTemplate               jdbcTemplate;
  private final PlatformTransactionManager platformTransactionManager;

  private final AddressesDao addressesDao;
  private final TelephoneNumbersDao telephoneNumbersDao;
  private final EmailsDao emailsDao;
  
  @Autowired
  public CompaniesDaoImpl(final JdbcTemplate jdbcTemplate, final PlatformTransactionManager platformTransactionManager, 
      AddressesDao addressesDao, TelephoneNumbersDao telephoneNumbersDao, EmailsDao emailsDao) {
    this.jdbcTemplate = jdbcTemplate;
    this.platformTransactionManager = platformTransactionManager;
    this.addressesDao = addressesDao;
    this.telephoneNumbersDao = telephoneNumbersDao;
    this.emailsDao = emailsDao;
  }

  
  
  @Override
  public Long createCompany(CompanyModel company) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public CompanyModel createAndReadCompany(CompanyModel company) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public CompanyModel readCompany(final Long id) {
    // TODO Auto-generated method stub
    return queryForCompanyRead(id);
  }
  
  @Override
  public CompanyModel updateCompany(CompanyModel company) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void deleteCompany(CompanyModel company) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public List<CompanyModel> listCompanies(final int maxCount) {
    // TODO Auto-generated method stub
    return queryForCompanies(maxCount);
  }
  
  private CompanyModel queryForCompanyRead(final Long id) {
    
    final String sql = " select id, company_name, comments, status, version, created, updated from tblcompanies where id = ? ";
    
    List<CompanyModel> list = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<CompanyModel>(){

      @Override
      public CompanyModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createCompanyFromResultSet(rs);
      }
      
    });
    
    return list != null && list.size() > 0 ? list.get(0) : null;
    
  }  

  private List<CompanyModel> queryForCompanies(final int maxCount) {
    
    final String sql = " select id, company_name, comments, status, version, created, updated from tblcompanies limit ? ";
    
    List<CompanyModel> list = jdbcTemplate.query(sql, new Object[]{maxCount}, new RowMapper<CompanyModel>(){

      @Override
      public CompanyModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createCompanyFromResultSet(rs);
      }
      
    });
    
    return list;
    
  }  

  private CompanyModel createCompanyFromResultSet(ResultSet rs) throws SQLException {
      
    CompanyModel umodel = new CompanyModel();
  
    umodel.setId(rs.getLong("id"));
    umodel.setCompanyName(rs.getString("company_name"));
    umodel.setComments(rs.getString("comments"));
    umodel.setVersion(rs.getInt("version"));
    umodel.setStatus(rs.getInt("status"));
  
    umodel.setCreated(rs.getTimestamp("created").toLocalDateTime());
    umodel.setUpdated(rs.getTimestamp("updated").toLocalDateTime());
  
    umodel.addAddresses(addressesDao.listAddresses(umodel.getId(), ContactOwnerType.Company));
    umodel.addTelephoneNumbers(telephoneNumbersDao.listTelephoneNumber(umodel.getId(), ContactOwnerType.Company));
    umodel.addEmails(emailsDao.listEmails(umodel.getId(), ContactOwnerType.Company));
    
    return umodel;
      
  }
}
