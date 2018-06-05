package com.brain.bim.barinbim.model.ui;

/**
 * a class to hold BreadCrumb data to show in each page
 *
 * @author rezasei
 *
 */
public class BreadCrumb {

  private String  label;
  private String  url;
  private boolean isCurrent;

  public String getLabel() {
    return label;
  }

  public void setLabel(final String label) {
    this.label = label;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public boolean isCurrent() {
    return isCurrent;
  }

  public void setCurrent(final boolean isCurrent) {
    this.isCurrent = isCurrent;
  }

  public boolean isLink() {
    return !isCurrent && !url.equals("") && !url.equals("#");
  }

  public BreadCrumb(final String label, final String url) {
    this.label = label;
    this.url = url;
    isCurrent = false;
  }

  public BreadCrumb(final String label, final String url, final boolean isCurrent) {
    this.label = label;
    this.url = url;
    this.isCurrent = isCurrent;
  }

}
