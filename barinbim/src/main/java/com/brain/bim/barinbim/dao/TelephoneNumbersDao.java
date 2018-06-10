package com.brain.bim.barinbim.dao;

import java.util.List;

import com.brain.bim.barinbim.model.TelephoneNumberModel;
import com.brain.bim.barinbim.model.enums.ContactOwnerType;


public interface TelephoneNumbersDao {
  
  Long createTelephoneNumber(TelephoneNumberModel address);

  void deleteTelephoneNumber(TelephoneNumberModel address);

  void deleteTelephoneNumbers(Long ownerId, String ownerType);

  List<TelephoneNumberModel> listTelephoneNumber(Long ownerId, ContactOwnerType ownerType);

}
