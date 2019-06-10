package com.mgigena.automationdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ChallengingDOMPage extends BasicPage{

  private static final String PATH = "http://the-internet.herokuapp.com/challenging_dom";

  @FindBy(how = How.CSS, using = "#content>div>div>div>div.large-10.columns>table")
  private WebElement table;
  @FindBy(how = How.CSS, using = "#canvas")
  private WebElement randomNumberText;

  public ChallengingDOMPage (WebDriver runDriver) {
    super(runDriver);
  }

  public static void goTo(WebDriver driver) {
    driver.navigate().to(PATH);
  }

  public String textAt(int rowNum, int colNum) {
    WebElement cell = table.findElement(By.cssSelector("tbody>tr:nth-child("+rowNum+")>td:nth-child("+colNum+")"));
    return cell.getText();
  }
}
