package com.brain.bim.barinbim.controllers;

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

import com.brain.bim.barinbim.bl.AccountsHandler;
import com.brain.bim.barinbim.exceptions.UiRestResponse;
import com.brain.bim.barinbim.model.AccountModel;

@Controller
@RequestMapping(path = "/accounts")
public class AccountsController{

  private final Logger logger = LoggerFactory.getLogger(AccountsController.class);
  
  @Autowired
  private AccountsHandler accountsHandler;
  
  @GetMapping("/index")
  public String showList(final Model model) {
    logger.debug("show accounts list page");

    return "accounts/accounts_list";
  }
  
  @GetMapping("/update/{accountid}")
  public String showUpdate(final Model model, @PathVariable long accountid) {
    logger.debug("show accounts update page");

    model.addAttribute("id", accountid);
    model.addAttribute("acc", accountsHandler.readAccount(accountid));
    
    return "accounts/accounts_update";
  }
  
  @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody UiRestResponse getList() {
    logger.debug("get accounts list");

    List<AccountModel> list = accountsHandler.listAccount(2500);
    
    return UiRestResponse.createDataResponse(list);
  }

  
}
