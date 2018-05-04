package com.mgigena.automationdemo.pages;

import org.openqa.selenium.WebDriver;

public class BasicPage {

  private final WebDriver driver;

  public BasicPage(WebDriver runDriver){
    driver = runDriver;
  }

  public WebDriver getDriver() {
    return driver;
  }
}
