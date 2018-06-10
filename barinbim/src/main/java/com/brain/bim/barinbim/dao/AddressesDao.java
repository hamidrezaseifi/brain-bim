package com.brain.bim.barinbim.dao;

import java.util.List;

import com.brain.bim.barinbim.model.AddressModel;
import com.brain.bim.barinbim.model.enums.ContactOwnerType;


public interface AddressesDao {
  
  Long createAddress(AddressModel address);

  void deleteAddresses(Long ownerId, String ownerType);

  void deleteAddress(AddressModel address);

  List<AddressModel> listAddresses(Long ownerId, ContactOwnerType ownerType);

}
