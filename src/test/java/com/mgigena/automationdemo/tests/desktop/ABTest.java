package com.mgigena.automationdemo.tests.desktop;

import com.mgigena.automationdemo.pages.ABPage;
import com.mgigena.automationdemo.pages.MainPage;
import com.mgigena.automationdemo.tests.BaseWebTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ABTest extends BaseWebTest {

  @Test
  public void controlAccess() {
    annotate("Going to AB testing page.");
    ABPage.goTo(getDriver());
    ABPage page = PageFactory.initElements(getDriver(), ABPage.class);

    annotate("Verifying header and paragraphs.");
    Assert.assertEquals(page.getHeaderText(), "A/B Test Control", "The header is not right!");
    Assert.assertEquals(
        page.getParagraphText(),
        "Also known as split testing. This is a way in which businesses are able to simultaneously"
            + " test and learn different versions of a page to see which text and/or functionality"
            + " works best towards a desired outcome (e.g. a user action such as a click-through).",
        "The paragraph is not right");
  }

  @Test
  public void clickThroughAccess() {
    annotate("Going to main page.");
    MainPage.goTo(getDriver());
    MainPage mainPage = PageFactory.initElements(getDriver(), MainPage.class);

    annotate("Going from main page to AB testing page.");
    ABPage abPage = mainPage.goToABPage();

    annotate("Verifying header and paragraphs.");
    Assert.assertEquals(abPage.getHeaderText(), "A/B Test Variation 1", "The header is not right!");
    Assert.assertEquals(
        abPage.getParagraphText(),
        "Also known as split testing. This is a way in which businesses are able to simultaneously"
            + " test and learn different versions of a page to see which text and/or functionality"
            + " works best towards a desired outcome (e.g. a user action such as a click-through).",
        "The paragraph is not right");
  }
}
