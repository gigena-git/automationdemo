package com.mgigena.automationdemo.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.mgigena.automationdemo.CoreConfig;
import com.saucelabs.saucerest.SauceREST;

public class BaseTest {

  private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
  private String environment;
  private WebDriver driver;
  private String sessionId;
  private final SauceREST sauceREST = CoreConfig.getSauceRest();

  public String getEnvironment() {
    return environment;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
    LOGGER.info(String.format("Running at %s", environment));
  }

  public WebDriver getDriver() {
    return driver;
  }

  public void setDriver(WebDriver driver) {
    this.driver = driver;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    if ("REMOTE".equals(environment)) {
      this.sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
      LOGGER.info(String.format("Session Id: %s", this.sessionId));
    } else {
      this.sessionId = sessionId;
    }
  }

  public void annotate(String message) {
    if (!sessionId.isEmpty()) {
      ((JavascriptExecutor) driver).executeScript("sauce:context=" + message);
    }
    Logger logger = LogManager.getLogger(getClass());
    logger.info(message);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult result) {
    if(driver == null){
      LOGGER.warn("No driver! skipping tear down!");
    } else if(!sessionId.isEmpty()) {
      if (result.isSuccess()) {
        sauceREST.jobPassed(sessionId);
      } else {
        sauceREST.jobFailed(sessionId);
      }
      driver.quit();
      printSessionId();
    } else {
      driver.manage().deleteAllCookies();
      driver.close();
      if (driver instanceof ChromeDriver) {
        driver.quit();
      }
      driver = null;
    }
  }

  private void printSessionId() {
    String message = String
        .format("SauceOnDemandSessionID=%1$s job-name=%2$s", sessionId, CoreConfig.getJobName());
    System.out.println(message);
  }
}
