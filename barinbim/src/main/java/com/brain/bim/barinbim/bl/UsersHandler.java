package com.brain.bim.barinbim.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brain.bim.barinbim.dao.UsersDao;
import com.brain.bim.barinbim.model.UserModel;
import com.brain.bim.barinbim.model.UserQueryModel;


@Service
public class UsersHandler {
  

  private final UsersDao usersDao;

  @Autowired
  public UsersHandler(final UsersDao usersDao) {
    this.usersDao = usersDao;
  }
  
  public List<UserModel> searchUsers(UserQueryModel queryModel){
    
    return usersDao.searchUsers(queryModel);
  }
}
