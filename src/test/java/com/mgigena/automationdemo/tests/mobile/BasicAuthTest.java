package com.mgigena.automationdemo.tests.mobile;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mgigena.automationdemo.pages.BasicAuthPage;
import com.mgigena.automationdemo.tests.BaseMobileTest;

public class BasicAuthTest extends BaseMobileTest {

  @Test
  public void simpleTest() {
    annotate("Going to basic auth page.");
    BasicAuthPage.goTo(getDriver());
    BasicAuthPage authPage = PageFactory.initElements(getDriver(), BasicAuthPage.class);

    annotate("Asserting happy message is displayed.");
    Assert.assertTrue(authPage.isHappyMessageDesplayed(), "Element not present!");
  }
}
