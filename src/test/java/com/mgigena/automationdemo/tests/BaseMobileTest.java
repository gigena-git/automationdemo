package com.mgigena.automationdemo.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.mgigena.automationdemo.DriverFactory;

public class BaseMobileTest extends BaseTest {

  @Parameters({"runEnv", "appiumVersion", "platformName", "platformVersion", "deviceName",
      "browserName"})
  @BeforeMethod(alwaysRun = true)
  public void setUp(@Optional("LOCAL") String runEnv, @Optional String appiumVersion,
      @Optional("Android") String platformName, @Optional String platformVersion,
      @Optional String deviceName, @Optional String browserName, Method testMethod) {
    setEnvironment(runEnv);
    setDriver(DriverFactory
        .getDevice(getEnvironment(), appiumVersion, platformName, platformVersion, deviceName,
            browserName, testMethod));
    setSessionId("");
  }
}
