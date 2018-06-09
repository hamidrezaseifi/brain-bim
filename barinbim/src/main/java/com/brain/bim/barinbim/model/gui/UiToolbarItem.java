package com.brain.bim.barinbim.model.gui;

import java.util.ArrayList;
import java.util.List;

import com.brain.bim.barinbim.helper.MessagesHelper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * a model class for represent menu item and its sub items in gui
 *
 * @author rezasei
 *
 */
@JsonIgnoreProperties({ "parent" })
@Component
public class UiToolbarItem {

  private String           id;
  private String           label;
  private String           url;
  private String           image;
  private int              status;
  private int              pos;
  private String           type;
  private boolean          active;

  private List<UiToolbarItem> children;

  private UiToolbarItem parent;

  public UiToolbarItem() {
    children = new ArrayList<>();
    label = "";
    url = "#";
    image = "";
    id = "";
    parent = null;
    type = "";
    active = false;
    pos = 0;
    status = 1;
    
  }


  public String getLabel() {
    return label;
  }

  public void setLabel(final String label) {
    this.label = label;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public String getClass(String className) {
    return image + " " + className;
  }

  public boolean hasImage() {
    return image != null && image.length() > 3;
  }

  public String getCssClass(String extraCLass) {
    return image + " " + extraCLass;
  }

  public String getImage() {
    return image;
  }

  public void setImage(final String image) {
    this.image = image;
  }

  public List<UiToolbarItem> getChildren() {
    return children;
  }

  public void setChildren(final List<UiToolbarItem> children) {
    this.children = children == null ? new ArrayList<>() : children;
  }

  public void addchildren(final List<UiToolbarItem> children) {
    this.children.clear();
    
    if(children != null) {
      for (final UiToolbarItem item : children) {
        item.setParent(this);
        this.children.add(item);
      }
    }
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
    this.label = id;
  }

  public boolean isEnabled() {
    return status == 1;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(final int status) {
    this.status = status;
  }

  public int getPos() {
    return pos;
  }

  public void setPos(final int pos) {
    this.pos = pos;
  }

  public UiToolbarItem getParent() {
    return parent;
  }

  public void setParent(final UiToolbarItem parent) {
    this.parent = parent;
  }

  
  public boolean isActive() {
    return active;
  }


  
  public void setActive(boolean active) {
    this.active = active;
  }


  public UiToolbarItem getMenuItemByUrl(final String url) {
    if (this.url.equals(url)) {
      return this;
    }

    for (final UiToolbarItem item : children) {
      final UiToolbarItem f = item.getMenuItemByUrl(url);
      if (f != null) {
        return f;
      }
    }
    return null;
  }

  public void setLabelMessage(MessagesHelper messagesHelper) {
    this.label = messagesHelper != null ? messagesHelper.get(this.label) : label; 
    for(int i=0; i<children.size(); i++){
      children.get(i).setLabelMessage(messagesHelper);
    }
  }
  
  public UiToolbarItem clone(boolean childs) {
    
    UiToolbarItem menu = new UiToolbarItem();
    
    menu.setId(id);
    menu.setImage(image);
    menu.setLabel(label);
    menu.setStatus(status);
    menu.setType(type);
    menu.setUrl(url);
    menu.setPos(pos);
    
    if(childs) {
      menu.addchildren(children);
    }
    
    return menu;
  }

}
