package com.brain.bim.barinbim.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.brain.bim.barinbim.dao.ToolbarDao;
import com.brain.bim.barinbim.model.gui.UiToolbarItem;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * a class to manage and load menu items and sub items
 *
 * @author rezasei
 *
 */
@Component
public class UiToolbarManager {

  private List<UiToolbarItem> toolbas;

  @Autowired
  private ToolbarDao toolbarDao;

  
  @Autowired
  MessagesHelper messagesHelper;
  
  public UiToolbarManager() {
    toolbas = new ArrayList<>();
  }

  @PostConstruct
  public void reload() throws JsonParseException, JsonMappingException, IOException {
    
    final List<UiToolbarItem> menulist = toolbarDao.listToolbarItems();
    for(int i=0; i<menulist.size(); i++){
      menulist.get(i).setLabelMessage(messagesHelper);
    }
    toolbas.addAll(menulist);

  }

  public List<UiToolbarItem> getToolbars() {
    return toolbas;
  }

  public UiToolbarItem getToolbarItemByUrl(final String url) {
    for (final UiToolbarItem item : toolbas) {
      final UiToolbarItem f = item.getMenuItemByUrl(url);
      if (f != null) {
        return f;
      }
    }

    return null;
  }
  
  public List<UiToolbarItem> getTopToolbars() {
    List<UiToolbarItem> topmenus = new ArrayList<UiToolbarItem>();
    
    for (final UiToolbarItem item : toolbas) {
      if(item.getType().equals("top")) {
        topmenus.add(item.clone(false));  
      }
    }
    
    return topmenus;
  }
  
  public List<UiToolbarItem> getChildToolbars(String id) {
    List<UiToolbarItem> topmenus = new ArrayList<UiToolbarItem>();
    
    for (final UiToolbarItem item : toolbas) {
      if(item.getId().equals(id)) {
        topmenus.addAll(item.getChildren());
      }
    }
    
    return topmenus;
  }

}
