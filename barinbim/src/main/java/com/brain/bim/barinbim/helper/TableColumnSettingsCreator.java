package com.brain.bim.barinbim.helper;

import java.util.HashMap;
import java.util.Map;

import com.brain.bim.barinbim.model.gui.TableColumnSettings;

public class TableColumnSettingsCreator {
  
  public static Map<String, TableColumnSettings> createAccountTableColumnSettings(MessagesHelper messagesHelper){
    
    Map<String, TableColumnSettings> settings = new HashMap<String, TableColumnSettings>();


    settings.put("accountName", new TableColumnSettings("accountName", messagesHelper.get("accounts.accountName"), "accountName", "accountName"));
    
    Map<String, String> style = new HashMap<String, String>();
    style.put("width", "100px");
    settings.put("statusLabel", new TableColumnSettings("statusLabel", messagesHelper.get("accounts.status"), "statusLabel", "statusLabel", style));

    style.put("width", "140px");
    settings.put("created", new TableColumnSettings("created", messagesHelper.get("accounts.created"), "created", "created", style));

    return settings;
    
  }
}

