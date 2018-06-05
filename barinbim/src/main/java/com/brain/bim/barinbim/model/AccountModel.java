package com.brain.bim.barinbim.model;

import java.time.LocalDateTime;

public class AccountModel {
  private Long id;
  private String accountName;
  private String comments;
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

  
  public String getAccountName() {
    return accountName;
  }

  
  public void setAccountName(String name) {
    this.accountName = name;
  }

  
  public String getComments() {
    return comments;
  }

  
  public void setComments(String comment) {
    this.comments = comment;
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
