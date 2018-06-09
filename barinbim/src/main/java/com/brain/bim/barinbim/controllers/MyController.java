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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brain.bim.barinbim.controllers.base.UiControllerBase;
import com.brain.bim.barinbim.helper.UiToolbarManager;
import com.brain.bim.barinbim.model.gui.UiToolbarItem;

@Controller
@RequestMapping(path = "/my")
public class MyController extends UiControllerBase{

  private final Logger logger = LoggerFactory.getLogger(MyController.class);

  @Autowired
  private UiToolbarManager toolbarManager;
  
  @GetMapping("/dashboard")
  public String index(final Model model) {
    //logger.debug("test");

    return "my/dashboard";
  }
  

  protected List<UiToolbarItem> getLeftToolbar() {
    return toolbarManager.getChildToolbars("menu.mydashboard");
  }
  
}
