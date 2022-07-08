package com.mgigena.automationdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasicAuthPage extends BasicPage {

  private static final String PATH = "the-internet.herokuapp.com/basic_auth";

  @FindBy(how = How.CSS, using = "#content > div > p")
  private WebElement happyMessage;

  public BasicAuthPage(WebDriver runDriver) {
    super(runDriver);
  }

  public static void goTo(WebDriver driver) {
    driver.navigate().to("https://admin:admin@" + PATH);
  }

  public boolean isHappyMessageDesplayed() {
    return happyMessage.isDisplayed();
  }
}
