package com.brain.bim.barinbim.model.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brain.bim.barinbim.helper.MessagesHelper;
import com.brain.bim.barinbim.model.AddressModel;
import com.brain.bim.barinbim.model.CompanyModel;
import com.brain.bim.barinbim.model.EmailModel;
import com.brain.bim.barinbim.model.TelephoneNumberModel;

@Component
public class CompanyModelUi {
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

  private MessagesHelper messagesHelper;
  
  public CompanyModelUi(MessagesHelper messagesHelper) {
    this.messagesHelper = messagesHelper;
  }
  
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
  
  public String getStatusLabel() {
    String statusLabel =  "common.status_unknown";
    switch(status) {
      case 0 : statusLabel = messagesHelper.get("common.idle"); break;
      case 1 : statusLabel =  messagesHelper.get("common.active"); break;
      case 2 : statusLabel =  messagesHelper.get("common.deactive"); break;
      case 3 : statusLabel =  messagesHelper.get("common.deleted"); break;
    }
    return statusLabel;
  }
  
  public int getStatus() {
    return status;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }

  public String getCreatedString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    return created.format(formatter);
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


  
  public void setAddresses(List<AddressModel> addresses) {
    this.addresses = addresses;
  }


  
  public List<TelephoneNumberModel> getTelephoneNumbers() {
    return telephoneNumbers;
  }


  
  public void setTelephoneNumbers(List<TelephoneNumberModel> telephoneNumbers) {
    this.telephoneNumbers = telephoneNumbers;
  }


  
  public List<EmailModel> getEmails() {
    return emails;
  }


  
  public void setEmails(List<EmailModel> emails) {
    this.emails = emails;
  } 

  public static CompanyModelUi createFromDataModel(CompanyModel dataModel, MessagesHelper messagesHelper) {
    
    CompanyModelUi model = new CompanyModelUi(messagesHelper);
    model.setCompanyName(dataModel.getCompanyName());
    model.setComments(dataModel.getComments());
    model.setCreated(dataModel.getCreated());
    model.setId(dataModel.getId());
    model.setStatus(dataModel.getStatus());
    model.setUpdated(dataModel.getUpdated());
    model.setVersion(dataModel.getVersion());
    model.setAddresses(dataModel.getAddresses());
    model.setTelephoneNumbers(dataModel.getTelephoneNumbers());
    model.setEmails(dataModel.getEmails());
    
    return model;
  }
  
  public static List<CompanyModelUi> createFromDataModelList(List<CompanyModel> dataModelList, MessagesHelper messagesHelper) {
    
    List<CompanyModelUi> list = new ArrayList<>();
    
    for(CompanyModel dataModel : dataModelList) {
      list.add(CompanyModelUi.createFromDataModel(dataModel, messagesHelper));
    }
    
    return list;
  }
}
