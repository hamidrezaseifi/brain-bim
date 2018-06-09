package com.brain.bim.barinbim.model.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brain.bim.barinbim.helper.MessagesHelper;
import com.brain.bim.barinbim.model.AccountModel;

@Component
public class AccountModelUi {
  private Long id;
  private String accountName;
  private String comments;
  private int version;
  private int status;
  private LocalDateTime  created;
  private LocalDateTime  updated;
    
  private MessagesHelper messagesHelper;
  
  public AccountModelUi(MessagesHelper messagesHelper) {
    this.messagesHelper = messagesHelper;
  }
  
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
    
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
  
  public static AccountModelUi createFromDataModel(AccountModel dataModel, MessagesHelper messagesHelper) {
    
    AccountModelUi model = new AccountModelUi(messagesHelper);
    model.setAccountName(dataModel.getAccountName());
    model.setComments(dataModel.getComments());
    model.setCreated(dataModel.getCreated());
    model.setId(dataModel.getId());
    model.setStatus(dataModel.getStatus());
    model.setUpdated(dataModel.getUpdated());
    model.setVersion(dataModel.getVersion());
    
    return model;
  }
  
  public static List<AccountModelUi> createFromDataModelList(List<AccountModel> dataModelList, MessagesHelper messagesHelper) {
    
    List<AccountModelUi> list = new ArrayList<>();
    
    for(AccountModel dataModel : dataModelList) {
      list.add(AccountModelUi.createFromDataModel(dataModel, messagesHelper));
    }
    
    return list;
  }
}
