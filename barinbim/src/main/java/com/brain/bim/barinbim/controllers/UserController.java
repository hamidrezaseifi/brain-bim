package com.brain.bim.barinbim.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brain.bim.barinbim.bl.UsersHandler;
import com.brain.bim.barinbim.model.UserModel;
import com.brain.bim.barinbim.model.UserQueryModel;


@RestController
@RequestMapping(path = "/users")
public class UserController {
  
  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UsersHandler usersHandler;

  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody List<UserModel> test() {
    logger.debug("load all users");
    
    return usersHandler.listUsers(50);
  }
}
