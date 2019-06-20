package com.mgigena.automationdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ABPage extends BasicPage {

  private static final String PATH = "https://the-internet.herokuapp.com/abtest";

  @FindBy(how = How.CSS, using = "#content>div>h3")
  private WebElement header;
  @FindBy(how = How.CSS, using = "#content>div>p")
  private WebElement paragraph;

  public ABPage(WebDriver runDriver) {
    super(runDriver);
  }

  public static void goTo(WebDriver driver) {
    driver.navigate().to(PATH);
  }

  public String getHeaderText() {
    return header.getText();
  }

  public String getParagraphText() {
    return paragraph.getText();
  }
}
