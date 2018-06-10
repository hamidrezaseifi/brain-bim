package com.brain.bim.barinbim.dao;

import java.util.List;

import com.brain.bim.barinbim.model.CompanyModel;


public interface CompaniesDao {
  
  Long createCompany(CompanyModel company);

  CompanyModel createAndReadCompany(CompanyModel company);

  CompanyModel readCompany(final Long id);

  CompanyModel updateCompany(CompanyModel company);

  void deleteCompany(CompanyModel company);

  List<CompanyModel> listCompanies(final int maxCount);

}
