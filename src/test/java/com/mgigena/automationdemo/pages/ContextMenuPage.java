package com.mgigena.automationdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ContextMenuPage extends BasicPage {

  public static final String PATH = "https://the-internet.herokuapp.com/context_menu";

  private String message = "";

  @FindBy(how = How.ID, using = "hot-spot")
  private WebElement hotSpot;

  public ContextMenuPage(WebDriver runDriver) {
    super(runDriver);
  }

  public String getMessage() {
    return message;
  }

  public static void goTo(WebDriver driver){
    driver.navigate().to(PATH);
  }

  public void rightClickHotSpot() {
    Actions rightClick = new Actions(getDriver());
    rightClick.contextClick(hotSpot).perform();
    message = getDriver().switchTo().alert().getText();
    getDriver().switchTo().alert().accept();
  }
}
