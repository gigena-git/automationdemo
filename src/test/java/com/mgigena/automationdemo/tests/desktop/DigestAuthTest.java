package com.mgigena.automationdemo.tests.desktop;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mgigena.automationdemo.pages.DigestAuthPage;
import com.mgigena.automationdemo.tests.BaseWebTest;

public class DigestAuthTest extends BaseWebTest {

  @Test
  public void goWithCredentials() {
    DigestAuthPage.goTo(getDriver());
    DigestAuthPage page = PageFactory.initElements(getDriver(), DigestAuthPage.class);

    Assert.assertEquals(page.getParagraphText(), "Congratulations! You must have the proper credentials.", "Paragraph is not right!");
  }
}
