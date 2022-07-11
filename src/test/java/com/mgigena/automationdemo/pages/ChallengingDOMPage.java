package com.mgigena.automationdemo.pages;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ChallengingDOMPage extends BasicPage {

  private static final String PATH = "https://the-internet.herokuapp.com/challenging_dom";

  @FindBy(how = How.CSS, using = "#content>div>div>div>div.large-10.columns>table")
  private WebElement table;

  @FindBy(how = How.CSS, using = "#canvas")
  private WebElement randomNumberText;

  public ChallengingDOMPage(WebDriver runDriver) {
    super(runDriver);
  }

  public static void goTo(WebDriver driver) {
    driver.navigate().to(PATH);
  }

  public String textAt(int rowNum, int colNum) {
    WebElement cell =
        table.findElement(
            By.cssSelector("tbody>tr:nth-child(" + rowNum + ")>td:nth-child(" + colNum + ")"));
    return cell.getText();
  }

  public boolean isRandomNumberGenerated() throws IOException, TesseractException {
    getRandomNumberCanvas();
    String value = retrieveRandomNumber();
    return value.matches("Answer: \\d{5}\\n");
  }

  private void getRandomNumberCanvas() throws IOException {
    String canvasBase64 =
        (String)
            ((JavascriptExecutor) getDriver())
                .executeScript(
                    "return arguments[0].toDataURL('image/png').substring(21);", randomNumberText);
    ByteArrayInputStream bis =
        new ByteArrayInputStream(Base64.getMimeDecoder().decode(canvasBase64.getBytes("UTF-8")));
    BufferedImage canvasImage = ImageIO.read(bis);
    bis.close();
    ImageIO.write(canvasImage, "png", new File("target/canvas.png"));
  }

  private String retrieveRandomNumber() throws TesseractException {
    Tesseract tesseract = new Tesseract();
    if (SystemUtils.IS_OS_WINDOWS) {
      tesseract.setDatapath("C:/Tesseract/tessdata");
    } else {
      tesseract.setDatapath("/usr/share/tessdata");
    }
    return tesseract.doOCR(new File("target/canvas.png"));
  }
}
