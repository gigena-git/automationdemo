package com.mgigena.automationdemo.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasicPage {

  private final WebDriver driver;

  public BasicPage(WebDriver runDriver){
    driver = runDriver;
  }

  public WebDriver getDriver() {
    return driver;
  }

  protected void waitForPageToLoad(int seconds) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    ExpectedCondition<Boolean> expectation = inputDriver -> "complete".equals(
        ((JavascriptExecutor) inputDriver).executeScript("return document.readyState").toString());
    wait.until(expectation);
  }
}
