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
@RequestMapping(path = "/basics")
public class BasicsController extends UiControllerBase{

  private final Logger logger = LoggerFactory.getLogger(BasicsController.class);
  
  @Autowired
  private CompaniesHandler accountsHandler;

  @Autowired
  private UiToolbarManager toolbarManager;
  
  @Autowired
  private MessagesHelper messagesHelper;

  @GetMapping("/")
  public String index(final Model model) {
    //logger.debug("test");

    
    return "basics/basics_index";
  }  

  
  protected List<UiToolbarItem> getLeftToolbar() {
    return toolbarManager.getChildToolbars("menu.basicdata");
  }
}
