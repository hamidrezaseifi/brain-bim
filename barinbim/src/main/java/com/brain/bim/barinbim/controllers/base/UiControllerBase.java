package com.brain.bim.barinbim.controllers.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brain.bim.barinbim.helper.BreadCrumbLoader;
import com.brain.bim.barinbim.helper.UiToolbarManager;
import com.brain.bim.barinbim.model.ui.UiToolbarItem;

@Controller
public abstract class UiControllerBase {

  @Autowired
  private BreadCrumbLoader breadCrumbLoader;

  @Autowired
  private UiToolbarManager toolbarManager;

  protected List<UiToolbarItem> getTopToolbar() {
    return toolbarManager.getToolbars();
  }

  protected abstract List<UiToolbarItem> getLeftToolbar();

  protected String getCurrentRelatedUrl() {
    ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
    final String root = builder.build().toUriString();
    builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
    String path = builder.build().toUriString();
    path = path.replace(root, "");

    return path;
  }

  @ModelAttribute
  public void addAttributes(final Model model) {
    model.addAttribute("pageTopToolList", getTopToolbar());
    model.addAttribute("pageLeftToolList", getLeftToolbar());
    //model.addAttribute("breadCrumb", breadCrumbLoader.getBreadCrumbList(getCurrentRelatedUrl()));

    // builder.scheme("http");

    model.addAttribute("url", getCurrentRelatedUrl());

  }
}
