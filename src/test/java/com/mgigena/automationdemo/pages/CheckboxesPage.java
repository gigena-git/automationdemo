package com.mgigena.automationdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckboxesPage extends BasicPage {

  public static final String PATH = "https://the-internet.herokuapp.com/checkboxes";

  @FindBy(how = How.CSS, using = "#checkboxes > input[type=checkbox]:nth-child(1)")
  private WebElement checkBoxOne;

  @FindBy(how = How.CSS, using = "#checkboxes > input[type=checkbox]:nth-child(3)")
  private WebElement checkBoxTwo;

  public CheckboxesPage(WebDriver runDriver) {
    super(runDriver);
  }

  public static void goTo(WebDriver driver) {
    driver.navigate().to(PATH);
  }

  public void checkFirstCheckbox() {
    checkBoxOne.click();
  }

  public void checkSecondCheckbox() {
    checkBoxTwo.click();
  }

  public boolean isCheckboxOneClicked() {
    return checkBoxOne.isSelected();
  }

  public boolean isCheckboxTwoClicked() {
    return checkBoxTwo.isSelected();
  }
}
