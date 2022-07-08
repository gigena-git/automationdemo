package com.mgigena.automationdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DigestAuthPage extends BasicPage {

  public static final String PATH = "the-internet.herokuapp.com/digest_auth";

  @FindBy(how = How.CSS, using = "#content>div>p")
  private WebElement paragraph;

  public DigestAuthPage(WebDriver runDriver) {
    super(runDriver);
  }

  public static void goTo(WebDriver driver) {
    driver.get("https://admin:admin@" + PATH);
  }

  public String getParagraphText() {
    return paragraph.getText();
  }
}
