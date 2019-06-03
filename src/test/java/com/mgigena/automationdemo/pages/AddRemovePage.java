package com.mgigena.automationdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddRemovePage extends BasicPage {

  private static final String PATH = "https://the-internet.herokuapp.com/add_remove_elements/";

  @FindBy(how = How.CSS, using = "#content > div > button")
  private WebElement addButton;

  public AddRemovePage(WebDriver runDriver) {
    super(runDriver);
  }

  public static void goTo(WebDriver driver) {
    driver.navigate().to(PATH);
  }

  public void addElement(int numberOfElements) {
    for(int i = 0; i <numberOfElements; i++ ){
      addButton.click();
    }
  }

  public void removeElementNo(int elementNumber) {
    getDriver().findElement(By.cssSelector("#elements > button:nth-child(" + elementNumber + ")")).click();
  }

  public int countElements() {
    return getDriver().findElements(By.cssSelector("#elements > button")).size();
  }
}
