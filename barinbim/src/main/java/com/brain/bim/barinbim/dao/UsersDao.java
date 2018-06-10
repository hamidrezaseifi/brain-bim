package com.brain.bim.barinbim.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.brain.bim.barinbim.model.UserModel;
import com.brain.bim.barinbim.model.query.UserQueryModel;


public interface UsersDao {
  
  Long createUser(UserModel user);

  UserModel createAndReadUser(UserModel user);

  UserModel readUser(Long id);

  UserModel updateUser(UserModel user);

  void deleteUser(Long id, LocalDateTime updated);

  List<UserModel> listUsers(int maxCount);

}
