package com.brain.bim.barinbim.dao;

import java.util.List;

import com.brain.bim.barinbim.model.EmailModel;
import com.brain.bim.barinbim.model.enums.ContactOwnerType;


public interface EmailsDao {
  
  Long createEmail(EmailModel email);

  void deleteEmail(EmailModel email);

  void deleteEmails(Long ownerId, String ownerType);

  List<EmailModel> listEmails(Long ownerId, ContactOwnerType ownerType);

}
