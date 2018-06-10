package com.brain.bim.barinbim.helper;

import java.util.HashMap;
import java.util.Map;

import com.brain.bim.barinbim.model.gui.TableColumnSettings;

public class TableColumnSettingsCreator {
  
  public static Map<String, TableColumnSettings> createCompanyTableColumnSettings(MessagesHelper messagesHelper){
    
    Map<String, TableColumnSettings> settings = new HashMap<String, TableColumnSettings>();


    Map<String, String> style = new HashMap<String, String>();
    style.put("text-align", "left");
    settings.put("a", new TableColumnSettings("companyName", messagesHelper.get("companies.companyName"), "companyName", "companyName", style));
    
    style = new HashMap<String, String>();
    style.put("width", "280px");
    settings.put("b", new TableColumnSettings("telephoneNumbers", messagesHelper.get("companies.telephoneNumbers"), false, false, style));

    style = new HashMap<String, String>();
    style.put("width", "280px");
    settings.put("c", new TableColumnSettings("emails", messagesHelper.get("companies.emails"), false, false, style));

    style = new HashMap<String, String>();
    style.put("width", "100px");
    settings.put("d", new TableColumnSettings("statusLabel", messagesHelper.get("companies.status"), "statusLabel", "statusLabel", style));

    style = new HashMap<String, String>();
    style.put("width", "140px");
    settings.put("e", new TableColumnSettings("created", messagesHelper.get("companies.created"), "created", "created", style));

    return settings;
    
  }
}

