package com.mgigena.automationdemo.tests.desktop;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mgigena.automationdemo.pages.ChallengingDOMPage;
import com.mgigena.automationdemo.tests.BaseWebTest;
import net.sourceforge.tess4j.TesseractException;

public class ChallengingDOMNumberTest extends BaseWebTest {

  @Test
  public void detectRandomNumber() throws IOException, TesseractException {
    annotate("Going to challenging DOM page");
    ChallengingDOMPage.goTo(getDriver());
    ChallengingDOMPage domPage = PageFactory.initElements(getDriver(), ChallengingDOMPage.class);

    annotate("Determining the canvas contains a number.");
    Assert.assertTrue(domPage.isRandomNumberGenerated(), "The text inside the canvas is not a number!!");
  }
}
