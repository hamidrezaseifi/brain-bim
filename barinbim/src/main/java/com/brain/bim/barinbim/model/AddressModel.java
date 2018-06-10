package com.brain.bim.barinbim.model;

import java.time.LocalDateTime;

public class AddressModel {
  private Long ownerId;
  private String ownerType;
  private String addressName;
  private String country;
  private String city;
  private String postCode;
  private String street;
  private String street2;
  private String houseNumber;
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
  
  public String getAddressName() {
    return addressName;
  }
  
  public void setAddressName(String addressName) {
    this.addressName = addressName;
  }
  
  public String getCountry() {
    return country;
  }
  
  public void setCountry(String country) {
    this.country = country;
  }
  
  public String getCity() {
    return city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getPostCode() {
    return postCode;
  }
  
  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }
  
  public String getStreet() {
    return street;
  }
  
  public void setStreet(String street) {
    this.street = street;
  }
  
  public String getStreet2() {
    return street2;
  }
  
  public void setStreet2(String street2) {
    this.street2 = street2;
  }
  
  public String getHouseNumber() {
    return houseNumber;
  }
  
  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
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
