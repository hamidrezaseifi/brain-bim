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

import com.brain.bim.barinbim.bl.AccountsHandler;
import com.brain.bim.barinbim.controllers.base.UiControllerBase;
import com.brain.bim.barinbim.exceptions.UiRestResponse;
import com.brain.bim.barinbim.helper.MessagesHelper;
import com.brain.bim.barinbim.helper.TableColumnSettingsCreator;
import com.brain.bim.barinbim.helper.UiToolbarManager;
import com.brain.bim.barinbim.model.AccountModel;
import com.brain.bim.barinbim.model.gui.UiToolbarItem;
import com.brain.bim.barinbim.model.query.AccountQueryModel;
import com.brain.bim.barinbim.model.ui.AccountModelUi;

@Controller
@RequestMapping(path = "/basics/accounts")
public class AccountsController extends UiControllerBase{

  private final Logger logger = LoggerFactory.getLogger(AccountsController.class);
  
  @Autowired
  private AccountsHandler accountsHandler;

  @Autowired
  private UiToolbarManager toolbarManager;
  
  @Autowired
  private MessagesHelper messagesHelper;
  
  @GetMapping("/index")
  public String showList(final Model model) {
    logger.debug("show accounts list page");

    model.addAttribute("tableColumns", TableColumnSettingsCreator.createAccountTableColumnSettings(messagesHelper));
    
    return "accounts/accounts_list";
  }
  
  @GetMapping("/view/{accountid}")
  public String showView(final Model model, @PathVariable long accountid) {
    logger.debug("show accounts update page");

    model.addAttribute("id", accountid);
    model.addAttribute("acc", accountsHandler.readAccount(accountid));
    
    return "accounts/accounts_view";
  }
  
  @GetMapping("/update/{accountid}")
  public String showUpdate(final Model model, @PathVariable long accountid) {
    logger.debug("show accounts update page");

    model.addAttribute("id", accountid);
    model.addAttribute("acc", accountsHandler.readAccount(accountid));
    
    return "accounts/accounts_update";
  }
  
  @RequestMapping(value = "/search", method = RequestMethod.POST, 
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody UiRestResponse searchAccounts(AccountQueryModel query) {
    logger.debug("get accounts list");

    List<AccountModel> list = accountsHandler.listAccount(2500);
    
    return UiRestResponse.createDataResponse(AccountModelUi.createFromDataModelList(list, messagesHelper));
  }  

  
  protected List<UiToolbarItem> getLeftToolbar() {
    return toolbarManager.getChildToolbars("menu.basicdata");
  }
}
