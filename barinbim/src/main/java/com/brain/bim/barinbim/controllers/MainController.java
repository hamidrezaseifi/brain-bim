package com.brain.bim.barinbim.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController extends UiControllerBase{

  //private final Logger logger = LoggerFactory.getLogger(MainController.class);
  
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

  
}
