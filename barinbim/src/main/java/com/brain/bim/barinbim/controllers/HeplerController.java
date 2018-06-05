package com.brain.bim.barinbim.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;

import com.brain.bim.barinbim.exceptions.UiRestResponse;
import com.brain.bim.barinbim.helper.UiMenuManager;

/**
 * controller class to manage rest api for provide value list and menu list for gui...
 *
 *
 * @author rezasei
 *
 */
@Controller
@RequestMapping(path = "/helper")
public class HeplerController {

  private final Logger logger = LoggerFactory.getLogger(HeplerController.class);

  @Autowired
  private UiMenuManager menuManager;

  @ResponseBody
  @RequestMapping(value = "/layout/menulist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public UiRestResponse getMenulist() throws IOException {
    logger.debug("load menu list");

    return UiRestResponse.createDataResponse(menuManager.getMenus());
  }


  @GetMapping("/dialog/{filename}")
  @ExceptionHandler({ TemplateInputException.class, NoHandlerFoundException.class })
  public String

         dialogTemplate(@PathVariable final String filename) throws ServletException {

    return "dialogs/" + filename + ".html";
  }
}
