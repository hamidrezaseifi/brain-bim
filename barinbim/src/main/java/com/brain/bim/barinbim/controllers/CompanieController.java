package com.brain.bim.barinbim.controllers;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brain.bim.barinbim.bl.CompaniesHandler;
import com.brain.bim.barinbim.controllers.base.UiControllerBase;
import com.brain.bim.barinbim.exceptions.UiRestResponse;
import com.brain.bim.barinbim.helper.MessagesHelper;
import com.brain.bim.barinbim.helper.TableColumnSettingsCreator;
import com.brain.bim.barinbim.helper.UiToolbarManager;
import com.brain.bim.barinbim.model.CompanyModel;
import com.brain.bim.barinbim.model.gui.UiToolbarItem;
import com.brain.bim.barinbim.model.query.CompanyQueryModel;
import com.brain.bim.barinbim.model.ui.CompanyModelUi;

@Controller
@RequestMapping(path = "/basics/companies")
public class CompanieController extends UiControllerBase{

  private final Logger logger = LoggerFactory.getLogger(CompanieController.class);
  
  @Autowired
  private CompaniesHandler companiesHandler;

  @Autowired
  private UiToolbarManager toolbarManager;
  
  @Autowired
  private MessagesHelper messagesHelper;
  
  @GetMapping("/index")
  public String showList(final Model model) {
    logger.debug("show accounts list page");

    model.addAttribute("tableColumns", TableColumnSettingsCreator.createCompanyTableColumnSettings(messagesHelper));
    
    return "companies/companies_list";
  }
  
  @GetMapping("/view/{accountid}")
  public String showView(final Model model, @PathVariable long accountid) {
    logger.debug("show accounts update page");

    model.addAttribute("id", accountid);
    model.addAttribute("company", CompanyModelUi.createFromDataModel(companiesHandler.readCompany(accountid), messagesHelper));
    
    return "companies/companies_view";
  }
  
  @GetMapping("/update/{accountid}")
  public String showUpdate(final Model model, @PathVariable long accountid) {
    logger.debug("show accounts update page");

    model.addAttribute("id", accountid);
    model.addAttribute("company", CompanyModelUi.createFromDataModel(companiesHandler.readCompany(accountid), messagesHelper));
    
    return "companies/companies_update";
  }
  
  @GetMapping("/delete/{companyid}")
  public String showDelete(final Model model, @PathVariable long companyid) {
    logger.debug("show accounts update page");

    model.addAttribute("id", companyid);
    model.addAttribute("company", companiesHandler.readCompany(companyid));
    
    return "companies/companies_delete";
  }
  
  @RequestMapping(value = "/search", method = RequestMethod.POST, 
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody UiRestResponse searchCompanys(CompanyQueryModel query) {
    logger.debug("get accounts list");

    List<CompanyModel> list = companiesHandler.listCompanies(2500);
    
    return UiRestResponse.createDataResponse(CompanyModelUi.createFromDataModelList(list, messagesHelper));
  }  

  
  protected List<UiToolbarItem> getLeftToolbar() {
    return toolbarManager.getChildToolbars("menu.basicdata");
  }
}
