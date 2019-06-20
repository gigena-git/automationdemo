package com.mgigena.automationdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasicPage {

  private static final String PATH = "https://the-internet.herokuapp.com";

  @FindBy(how = How.CSS, using = "#content>ul>li:nth-child(1)>a")
  private WebElement abPageLink;

  public MainPage(WebDriver runDriver) {
    super(runDriver);
  }

  public static void goTo(WebDriver driver) {
    driver.navigate().to(PATH);
  }

  public ABPage goToABPage() {
    abPageLink.click();
    waitForPageToLoad(30);
    return PageFactory.initElements(getDriver(), ABPage.class);
  }
}
