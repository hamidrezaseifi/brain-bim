package com.brain.bim.barinbim.model.gui;

import java.util.HashMap;
import java.util.Map;

//id:"accountName", title: 'Name', style: { },  sortable: 'accountName', filter: {'accountName': 'text'}, show: true,

public class TableColumnSettings {
  private String id;
  private String title;
  private Object sortable;
  private Map<String, String> style;
  private Object filter;
  private boolean show;
  private String writeFunction;
  
  public TableColumnSettings() {
    
    this.style = new HashMap<String, String>();
    this.filter = false;
    this.sortable = false;
    this.style = new HashMap<String, String>();
    this.filter = false;
    this.show = true;
    this.writeFunction = "";
  }

  public TableColumnSettings(String id, String title, Object sortable) {
    this.id = id;
    this.title = title;
    this.sortable = sortable;
    this.style = new HashMap<String, String>();
    this.filter = false;
    this.sortable = false;
    this.style = new HashMap<String, String>();
    this.filter = false;
    this.show = true;
    this.writeFunction = "";
   
  }

  public TableColumnSettings(String id, String title, Object sortable, String filter) {
    this.id = id;
    this.title = title;
    this.sortable = sortable;
    this.style = new HashMap<String, String>();
    Map<String, String> f = new HashMap<String, String>();
    f.put(filter, "text");
    this.filter = f;
    this.show = true;
    this.writeFunction = "";
  
  }

  public TableColumnSettings(String id, String title, Object sortable, Object filter, Map<String, String> style) {
    this.id = id;
    this.title = title;
    this.sortable = sortable;
    this.style = style;
    this.filter = filter;
    this.show = true;
    this.writeFunction = "";
   
  }

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public Object getSortable() {
    return sortable;
  }
  
  public void setSortable(Object sortable) {
    this.sortable = sortable;
  }
  
  public Map<String, String> getStyle() {
    return style;
  }
  
  public void setStyle(Map<String, String> style) {
    this.style = style;
  }
  
  public Object getFilter() {
    return filter;
  }
  
  public void setFilter(Object filter) {
    this.filter = filter;
  }
  
  public boolean isShow() {
    return show;
  }
  
  public void setShow(boolean show) {
    this.show = show;
  }

  public String getWriteFunction() {
    return writeFunction;
  }
  
  public void setWriteFunction(String writeFunction) {
    this.writeFunction = writeFunction;
  }
  
}
