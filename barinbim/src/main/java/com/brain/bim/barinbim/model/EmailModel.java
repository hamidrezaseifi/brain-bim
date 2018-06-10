package com.brain.bim.barinbim.model;

import java.time.LocalDateTime;

public class EmailModel {
  private Long ownerId;
  private String ownerType;
  private String emailName;
  private String emailaddress;
  private String comments;
  private int version;
  private LocalDateTime  created;
  private LocalDateTime  updated;
  
  public Long getOwnerId() {
    return ownerId;
  }
  
  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }
  
  public String getOwnerType() {
    return ownerType;
  }
  
  public void setOwnerType(String ownerType) {
    this.ownerType = ownerType;
  }

  
  public String getEmailName() {
    return emailName;
  }

  
  public void setEmailName(String emailName) {
    this.emailName = emailName;
  }

  
  public String getEmailaddress() {
    return emailaddress;
  }

  
  public void setEmailaddress(String emailaddress) {
    this.emailaddress = emailaddress;
  }

  
  public String getComments() {
    return comments;
  }

  
  public void setComments(String comments) {
    this.comments = comments;
  }

  
  public int getVersion() {
    return version;
  }

  
  public void setVersion(int version) {
    this.version = version;
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
