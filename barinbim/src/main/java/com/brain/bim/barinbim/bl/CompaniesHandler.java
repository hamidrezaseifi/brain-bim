package com.brain.bim.barinbim.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brain.bim.barinbim.dao.CompaniesDao;
import com.brain.bim.barinbim.model.CompanyModel;


@Service
public class CompaniesHandler {
  

  private final CompaniesDao companiesDao;

  @Autowired
  public CompaniesHandler(final CompaniesDao accountsDao) {
    this.companiesDao = accountsDao;
  }
  
  public List<CompanyModel> listCompanies(int maxCount){
    
    return companiesDao.listCompanies(maxCount);
  }
  
  public CompanyModel readCompany(Long id){
    
    return companiesDao.readCompany(id);
  }
  
  public CompanyModel saveCompany(CompanyModel account){
    
    return companiesDao.updateCompany(account);
  }
  
  
}
