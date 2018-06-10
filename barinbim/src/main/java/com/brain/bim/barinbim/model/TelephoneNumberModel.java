package com.brain.bim.barinbim.model;

import java.time.LocalDateTime;

public class TelephoneNumberModel {
  private Long ownerId;
  private String ownerType;
  private String telephoneName;
  private String countryCode;
  private String cityCode;
  private String subscriber;
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
  
  public String getTelephoneName() {
    return telephoneName;
  }
  
  public void setTelephoneName(String telephoneName) {
    this.telephoneName = telephoneName;
  }
  
  public String getCountryCode() {
    return countryCode;
  }
  
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
  
  public String getCityCode() {
    return cityCode;
  }
  
  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }
  
  public String getSubscriber() {
    return subscriber;
  }
  
  public void setSubscriber(String subscriber) {
    this.subscriber = subscriber;
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
