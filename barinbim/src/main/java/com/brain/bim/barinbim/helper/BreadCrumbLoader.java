package com.brain.bim.barinbim.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brain.bim.barinbim.model.ui.BreadCrumb;
import com.brain.bim.barinbim.model.ui.UiMenuItem;

/**
 * a class to manage BreadCrumb items for each page
 *
 * @author rezasei
 *
 */
@Component
public class BreadCrumbLoader {

  @Autowired
  private UiMenuManager menuManager;

  /**
   * this code create BreadCrumb list from the url string. the urls are in menu items and each url must have a identical menu item. if there
   * is a menu item it will be iterate until first parent. that code make sure that first element in list must be the home link. current
   * menu item and without link menu items are not allowed to have link but just label
   *
   * @param url
   * @return
   */
  public List<BreadCrumb> getBreadCrumbList(final String url) {

    final ArrayList<BreadCrumb> list = new ArrayList<>();

    UiMenuItem home = menuManager.getMenuItemByUrl("/");
    final UiMenuItem item = menuManager.getMenuItemByUrl(url);
    UiMenuItem temp = item;

    while (temp != null) {
      list.add(0, new BreadCrumb(temp.getLabel(), temp.getUrl(), temp.getId() == item.getId()));

      if (temp.getId() == home.getId()) {
        home = null;
      }
      temp = temp.getParent();
    }

    if (home != null) {
      if (temp == null || home.getId() != item.getId()) {

        list.add(0, new BreadCrumb(home.getLabel(), home.getUrl()));
      }
    }

    return list;
  }
}
