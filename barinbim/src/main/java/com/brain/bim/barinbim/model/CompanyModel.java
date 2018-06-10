package com.brain.bim.barinbim.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompanyModel {
  private Long id;
  private String companyName;
  private String comments;
  private int version;
  private int status;
  private LocalDateTime  created;
  private LocalDateTime  updated;
  
  private List<AddressModel> addresses = new ArrayList<>();
  private List<TelephoneNumberModel> telephoneNumbers = new ArrayList<>();
  private List<EmailModel> emails = new ArrayList<>();
  
  public Long getId() {
    return id;
  }

  
  public void setId(Long id) {
    this.id = id;
  }

  
  public String getCompanyName() {
    return companyName;
  }

  
  public void setCompanyName(String name) {
    this.companyName = name;
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


  
  public List<AddressModel> getAddresses() {
    return addresses;
  }


  
  public void addAddresses(List<AddressModel> addresses) {
    this.addresses.addAll(addresses);
  }

  public void setAddresses(List<AddressModel> addresses) {
    this.addresses = addresses;
  }


  
  public List<TelephoneNumberModel> getTelephoneNumbers() {
    return telephoneNumbers;
  }


  
  public void addTelephoneNumbers(List<TelephoneNumberModel> telephoneNumbers) {
    this.telephoneNumbers.addAll(telephoneNumbers);
  }

  public void setTelephoneNumbers(List<TelephoneNumberModel> telephoneNumbers) {
    this.telephoneNumbers = telephoneNumbers;
  }


  
  public List<EmailModel> getEmails() {
    return emails;
  }


  
  public void addEmails(List<EmailModel> emails) {
    this.emails.addAll(emails);
  } 
  
  public void setEmails(List<EmailModel> emails) {
    this.emails = emails;
  } 
}
