package com.brain.bim.barinbim.model.query;


public class UserQueryModel {
  private String userName;

  private int maxRecords = 200;
  
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getMaxRecords() {
    return maxRecords;
  }

  public void setMaxRecords(int maxRecords) {
    this.maxRecords = maxRecords;
  }

}
