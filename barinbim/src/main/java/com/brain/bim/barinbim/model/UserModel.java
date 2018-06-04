package com.brain.bim.barinbim.model;

import java.time.LocalDateTime;

public class UserModel {
  
  private Long id;
  private String userName;
  private String hashPassword;
  private int version;
  private int status;
  private LocalDateTime  created;
  private LocalDateTime  updated;

  
  public UserModel(){
    
  }
  
  public UserModel(Long id, String uname, String pass, int version){
    this.id = id;
    this.userName = uname;
    this.hashPassword = pass;
    this.version = version;
    
  }

  
  public Long getId() {
    return id;
  }

  
  public void setId(Long id) {
    this.id = id;
  }

  
  public String getUserName() {
    return userName;
  }

  
  public void setUserName(String userName) {
    this.userName = userName;
  }

  
  public String getHashPassword() {
    return hashPassword;
  }

  
  public void setHashPassword(String hashPassword) {
    this.hashPassword = hashPassword;
  }

  
  public int getVersion() {
    return version;
  }

  
  public void setVersion(int version) {
    this.version = version;
  }
  
  public void addVersion() {
    this.version ++;
  }
  
  public int getStatus() {
    return status;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  
  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  
  public LocalDateTime getUpdated() {
    return updated;
  }

  
  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }
  
}
