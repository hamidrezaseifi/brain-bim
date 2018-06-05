package com.brain.bim.barinbim.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.brain.bim.barinbim.model.ui.UiMenuItem;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * a class to manage and load menu items and sub items
 *
 * @author rezasei
 *
 */
@Component
public class UiMenuManager {

  private List<UiMenuItem> menus;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ResourceLoader resourceLoader;

  
  @Autowired
  MessagesHelper messagesHelper;
  
  public UiMenuManager() {
    menus = new ArrayList<>();
  }

  @PostConstruct
  public void reload() throws JsonParseException, JsonMappingException, IOException {
    final List<UiMenuItem> menulist;

    final Resource resource = resourceLoader.getResource("classpath:config/menulist.json");
    try (final InputStream is = resource.getInputStream()) {
      menulist = objectMapper.readValue(is, new TypeReference<List<UiMenuItem>>() {
      });
      menus.clear();
      
      for(int i=0; i<menulist.size(); i++){
        menulist.get(i).setLabelMessage(messagesHelper);
      }
      
      menus.addAll(menulist);
    }

  }

  public List<UiMenuItem> getMenus() {
    return menus;
  }

  public void setMenus(final List<UiMenuItem> menus) {
    this.menus = menus;
  }

  public UiMenuItem getMenuItemByUrl(final String url) {
    for (final UiMenuItem item : menus) {
      final UiMenuItem f = item.getMenuItemByUrl(url);
      if (f != null) {
        return f;
      }
    }

    return null;
  }
}
