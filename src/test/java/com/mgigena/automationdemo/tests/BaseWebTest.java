package com.mgigena.automationdemo.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.mgigena.automationdemo.DriverFactory;

public class BaseWebTest extends BaseTest {

  @Parameters({"runEnv", "runDriver", "runVersion", "runOs"})
  @BeforeMethod(alwaysRun = true)
  public void setUp(@Optional("LOCAL") String runEnv, @Optional("chrome") String runDriver,
      @Optional("54.0") String runVersion, @Optional("OS X 10.10") String runOs,
      Method testMethod) {
    setEnvironment(runEnv);
    setDriver(DriverFactory.getDriver(getEnvironment(), runDriver, runVersion, runOs, testMethod));
    setSessionId("");
  }
}
