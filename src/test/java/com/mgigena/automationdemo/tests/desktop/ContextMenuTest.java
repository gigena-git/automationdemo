package com.mgigena.automationdemo.tests.desktop;

import com.mgigena.automationdemo.pages.ContextMenuPage;
import com.mgigena.automationdemo.tests.BaseWebTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContextMenuTest extends BaseWebTest {

  @Test
  public void loadContextMenu() {
    annotate("Going to context menu page");
    ContextMenuPage.goTo(getDriver());
    ContextMenuPage page = PageFactory.initElements(getDriver(), ContextMenuPage.class);

    annotate("Right-clicking hot-spot, getting the alert message and closing alert");
    page.rightClickHotSpot();

    annotate("Determining alert has the expected message");
    Assert.assertEquals(
        page.getMessage(),
        "You selected a context menu",
        "The expected value and actual messages differ!");
  }
}
