package com.mgigena.automationdemo.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BrokenImagesPage extends BasicPage {

  private static final String PATH = "http://the-internet.herokuapp.com/broken_images";

  @FindBy(how = How.TAG_NAME, using = "img")
  private List<WebElement> images;
  @FindBy(css = "#content > div > img:nth-child(4)")
  private WebElement saneImage;

  private static final String SCRIPT = "return arguments[0].complete && "
      + "typeof arguments[0].naturalWidth != \"undefined\" && "
      + "arguments[0].naturalWidth > 0";

  public BrokenImagesPage(WebDriver runDriver) {
    super(runDriver);
  }

  public static void goTo(WebDriver driver) {
    driver.navigate().to(PATH);
  }

  public boolean areImagesDisplayed() {
    boolean displayed = true;
    if (images.isEmpty()) {
      displayed = false;
    } else {
      JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
      for (WebElement image : images) {
        displayed &= (boolean) jsExecutor.executeScript(SCRIPT, image);
      }
    }
    return displayed;
  }

  public boolean isSaneImageDisplayed() {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
    return (boolean) jsExecutor.executeScript(SCRIPT, saneImage);
  }
}
