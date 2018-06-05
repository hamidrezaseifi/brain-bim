package com.brain.bim.barinbim.model.ui;

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
public class UiMenuItem {

  private String           label;
  private String           labelid;
  private String           url;
  private String           image;
  private List<UiMenuItem> children;
  private long             id;
  private int              status;

  private UiMenuItem parent;

  public UiMenuItem() {
    children = new ArrayList<>();
    label = "";
    url = "#";
    image = "";
    id = 0;
    parent = null;
  }

  public UiMenuItem(final String label, final String url) {
    children = new ArrayList<>();
    this.label = label;
    this.url = url;
    image = "";
    id = 0;
    parent = null;
  }

  public UiMenuItem(final String label, final String url, final String image) {
    children = new ArrayList<>();
    this.label = label;
    this.url = url;
    this.image = image;
    id = 0;
    parent = null;
  }

  public UiMenuItem(final long id, final String label, final String url, final String image, final int status) {
    children = new ArrayList<>();
    this.label = label;
    this.url = url;
    this.image = image;
    this.id = id;
    this.status = status;
    parent = null;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(final String label) {
    this.label = label;
  }

  public String getLabelid() {
    return labelid;
  }

  public void setLabelid(final String labelid) {
    this.labelid = labelid;
    this.label = labelid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public String getImage() {
    return image;
  }

  public void setImage(final String image) {
    this.image = image;
  }

  public List<UiMenuItem> getChildren() {
    return children;
  }

  public void setChildren(final List<UiMenuItem> children) {
    this.children = children == null ? new ArrayList<>() : children;
  }

  public void addchildren(final List<UiMenuItem> children) {
    this.children.clear();
    for (final UiMenuItem item : children) {
      item.setParent(this);
      this.children.add(item);
    }
  }

  public long getId() {
    return id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(final int status) {
    this.status = status;
  }

  public UiMenuItem getParent() {
    return parent;
  }

  public void setParent(final UiMenuItem parent) {
    this.parent = parent;
  }

  public UiMenuItem getMenuItemByUrl(final String url) {
    if (this.url.equals(url)) {
      return this;
    }

    for (final UiMenuItem item : children) {
      final UiMenuItem f = item.getMenuItemByUrl(url);
      if (f != null) {
        return f;
      }
    }
    return null;
  }


  public void setLabelMessage(MessagesHelper messagesHelper) {
    this.label = messagesHelper != null ? messagesHelper.get(labelid) : labelid; //messagesHelper.get("menu.about");
    for(int i=0; i<children.size(); i++){
      children.get(i).setLabelMessage(messagesHelper);
    }
  }

}
