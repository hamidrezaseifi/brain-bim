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
@RequestMapping(path = "/my")
public class MyController extends UiControllerBase{

  //private final Logger logger = LoggerFactory.getLogger(MainController.class);
  
  @GetMapping("/dashboard")
  public String index(final Model model) {
    //logger.debug("test");

    return "my/dashboard";
  }
  
  
}
