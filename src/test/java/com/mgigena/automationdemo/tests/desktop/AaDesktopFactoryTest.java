package com.mgigena.automationdemo.tests.desktop;

import org.testng.annotations.Factory;

import com.mgigena.automationdemo.data.ChallengingDOMData;

public class AaDesktopFactoryTest {

  @Factory
  public Object[] abTests () {
    return new Object[] {new ABTest()};
  }

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
  public Object[] chalengingDOMLabelTests (int rowNum, int colNum, String tableText) {
    return new Object[] {new ChallengingDOMLabelTest(rowNum, colNum, tableText)};
  }

  @Factory
  public  Object[] challengingDOMNumberTests () {
    return new Object[] {new ChallengingDOMNumberTest()};
  }

  @Factory
  public Object[] checkboxesTests () {
    return new Object[] {new CheckboxesTest()};
  }

  @Factory
  public Object[] contextMenuTests () {
    return new Object[] {new ContextMenuTest()};
  }

  @Factory
  public Object[] digestAuthTests () {
    return new Object[] {new DigestAuthTest()};
  }
}
