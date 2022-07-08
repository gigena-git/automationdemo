package com.mgigena.automationdemo.tests.desktop;

import com.mgigena.automationdemo.pages.CheckboxesPage;
import com.mgigena.automationdemo.tests.BaseWebTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxesTest extends BaseWebTest {

  @Test
  public void switchCheckboxes() {
    annotate("Going to Checkboxes page");
    CheckboxesPage.goTo(getDriver());
    CheckboxesPage page = PageFactory.initElements(getDriver(), CheckboxesPage.class);

    annotate("Clicking checkboxes");
    page.checkFirstCheckbox();
    page.checkSecondCheckbox();

    annotate("Determining the checkboxes are clicked.");
    Assert.assertTrue(page.isCheckboxOneClicked(), "Checkbox One is not clicked!");
    Assert.assertFalse(page.isCheckboxTwoClicked(), "Checkbox Two is clicked!");
  }
}
