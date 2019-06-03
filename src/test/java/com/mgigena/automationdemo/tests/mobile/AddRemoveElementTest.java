package com.mgigena.automationdemo.tests.mobile;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mgigena.automationdemo.pages.AddRemovePage;
import com.mgigena.automationdemo.tests.BaseMobileTest;

public class AddRemoveElementTest extends BaseMobileTest {

  @Test
  public void addFourElementsAndRemoveThirdElement() {
    annotate("Going to Add and Remove web page.");
    AddRemovePage.goTo(getDriver());
    AddRemovePage addRemovePage = PageFactory.initElements(getDriver(), AddRemovePage.class);

    annotate("Adding four elements");
    addRemovePage.addElement(4);
    annotate("Removing third element");
    addRemovePage.removeElementNo(3);

    annotate("Asserting there are 3 elements left");
    Assert.assertEquals(addRemovePage.countElements(), 3, 3 + " elements were expected, but " + addRemovePage.countElements() + " were found!");
  }
}
