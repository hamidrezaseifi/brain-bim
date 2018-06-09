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
public class MainController extends UiControllerBase{

  private final Logger logger = LoggerFactory.getLogger(MainController.class);

  @Autowired
  private UiToolbarManager toolbarManager;
  
  @GetMapping("/")
  public String index(final Model model) {
    //logger.debug("test");

    return "index";
  }
  
  @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody List<String> test() {
    //logger.debug("test");

    List<String> list = Arrays.asList("item 1", "item 2", "item 3");
    
    return list;
  }

  
  protected List<UiToolbarItem> getLeftToolbar() {
    return toolbarManager.getChildToolbars("menu.basicdata");
  }
  
}
