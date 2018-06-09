package com.brain.bim.barinbim.model.query;

import java.time.LocalDateTime;

public class AccountQueryModel {

  private String accountName;
  private int status;
  private LocalDateTime  created;
  
  public String getAccountName() {
    return accountName;
  }

  
  public void setAccountName(String name) {
    this.accountName = name;
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

}
