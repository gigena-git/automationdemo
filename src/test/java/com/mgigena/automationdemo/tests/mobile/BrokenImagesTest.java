package com.mgigena.automationdemo.tests.mobile;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mgigena.automationdemo.pages.BrokenImagesPage;
import com.mgigena.automationdemo.tests.BaseMobileTest;

public class BrokenImagesTest extends BaseMobileTest {

  @Test(groups = "broken")
  public void brokenImagesHappyPath() {
    annotate("Going to broken images page.");
    BrokenImagesPage.goTo(getDriver());
    BrokenImagesPage brokenPage = PageFactory.initElements(getDriver(), BrokenImagesPage.class);

    annotate("Asserting all images are displayed correctly.");
    Assert.assertTrue(brokenPage.areImagesDisplayed(), "Some images are broken!");
  }

  @Test
  public void saneImage() {
    annotate("Going to broken images page.");
    BrokenImagesPage.goTo(getDriver());
    BrokenImagesPage brokenPage = PageFactory.initElements(getDriver(), BrokenImagesPage.class);

    annotate("Asserting sane image is displayed correctly.");
    Assert.assertTrue(brokenPage.isSaneImageDisplayed(), "Some images are broken!");
  }
}
