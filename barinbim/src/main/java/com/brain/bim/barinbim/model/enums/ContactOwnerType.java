package com.brain.bim.barinbim.model.enums;


public enum ContactOwnerType {
  Company,
  User;
  
  @Override
  public String toString() {
    switch(this) {
      case Company: return "company";
      case User: return "user";
      default: throw new IllegalArgumentException();
    }
  }
}
