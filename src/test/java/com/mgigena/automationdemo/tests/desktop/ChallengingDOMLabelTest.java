package com.mgigena.automationdemo.tests.desktop;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mgigena.automationdemo.pages.ChallengingDOMPage;
import com.mgigena.automationdemo.tests.BaseWebTest;

public class ChallengingDOMLabelTest extends BaseWebTest {

  private final int rowNum;
  private final int colNum;
  private final String tableText;

  public ChallengingDOMLabelTest(int rowNum, int colNum, String tableText) {
    this.rowNum = rowNum;
    this.colNum = colNum;
    this.tableText = tableText;
  }

  @Test
  public void determineTextOfLabels() {
    annotate("Going to challenging DOM page");
    ChallengingDOMPage.goTo(getDriver());
    ChallengingDOMPage domPage = PageFactory.initElements(getDriver(), ChallengingDOMPage.class);

    annotate("Asserting all table values are correct");
    Assert.assertEquals(domPage.textAt(rowNum,colNum),tableText + (rowNum - 1),"The contents of the cell are not the expected ones!");
  }
}
