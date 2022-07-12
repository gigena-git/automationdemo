package com.mgigena.automationdemo.tests;

import com.mgigena.automationdemo.DriverFactory;
import java.lang.reflect.Method;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseMobileTest extends BaseTest {

  @Parameters({
    "runEnv",
    "appiumVersion",
    "platformName",
    "platformVersion",
    "deviceName",
    "browserName"
  })
  @BeforeMethod(alwaysRun = true)
  public void setUp(
      @Optional("REMOTE") String runEnv,
      @Optional("1.22.3") String appiumVersion,
      @Optional("iOS") String platformName,
      @Optional("15.4") String platformVersion,
      @Optional("iPhone 13 Simulator") String deviceName,
      @Optional("Safari") String browserName,
      Method testMethod) {
    setEnvironment(runEnv);
    setDriver(
        DriverFactory.getDevice(
            getEnvironment(),
            appiumVersion,
            platformName,
            platformVersion,
            deviceName,
            browserName,
            testMethod));
    setSessionId("");
  }
}
