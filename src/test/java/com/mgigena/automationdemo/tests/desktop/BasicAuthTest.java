package com.mgigena.automationdemo.tests.desktop;

import com.mgigena.automationdemo.pages.BasicAuthPage;
import com.mgigena.automationdemo.tests.BaseWebTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthTest extends BaseWebTest {

  @Test
  public void basicAuthHappyPath() {
    annotate("Going to basic auth page.");
    BasicAuthPage.goTo(getDriver());
    BasicAuthPage authPage = PageFactory.initElements(getDriver(), BasicAuthPage.class);

    annotate("Asserting happy message is displayed.");
    Assert.assertTrue(authPage.isHappyMessageDesplayed(), "Element not present!");
  }
}
