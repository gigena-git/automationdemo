package com.mgigena.automationdemo.tests.desktop;

import org.testng.annotations.Factory;

import com.mgigena.automationdemo.data.ChallengingDOMData;

public class DesktopFactoryTest {

  @Factory
  public Object[] addRemoveTests () {
    return new Object[] {new AddRemoveElementTest()};
  }

  @Factory
  public Object[] basicAuthTests () {
    return new Object[] {new BasicAuthTest()};
  }

  @Factory
  public Object[] brokenImagesTests () {
    return new Object[] {new BrokenImagesTest()};
  }

  @Factory(dataProvider = "domElements", dataProviderClass = ChallengingDOMData.class)
  public Object[] chalengingDOMTest (int rowNum, int colNum, String tableText) {
    return new Object[] {new ChallengingDOMTest(rowNum, colNum, tableText)};
  }

  @Factory
  public Object[] randomNumberTests () {
    return new Object[] {new RandomNumberTest()};
  }
}
