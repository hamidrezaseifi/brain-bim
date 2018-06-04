package com.brain.bim.barinbim.model;

import java.time.LocalDateTime;

public class ProjectModel {
  private Long id;
  private AccountModel account;
  private String projectName;
  private int version;
  private int status;
  private LocalDateTime  created;
  private LocalDateTime  updated;
  
  public Long getId() {
    return id;
  }

  
  public void setId(Long id) {
    this.id = id;
  }
  
  
  public AccountModel getAccount() {
    return account;
  }

  
  public void setAccount(AccountModel account) {
    this.account = account;
  }

  
  public String getProjectName() {
    return projectName;
  }

  
  public void setProjectName(String name) {
    this.projectName = name;
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
