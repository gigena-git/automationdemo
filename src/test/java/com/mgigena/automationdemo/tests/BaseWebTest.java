package com.mgigena.automationdemo.tests;

import com.mgigena.automationdemo.DriverFactory;
import java.lang.reflect.Method;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseWebTest extends BaseTest {

  @Parameters({"runEnv", "runDriver", "runVersion", "runOs"})
  @BeforeMethod(alwaysRun = true)
  public void setUp(
      @Optional("LOCAL") String runEnv,
      @Optional("firefox") String runDriver,
      @Optional("74.0") String runVersion,
      @Optional("macOS 10.14") String runOs,
      Method testMethod) {
    setEnvironment(runEnv);
    setDriver(DriverFactory.getDriver(getEnvironment(), runDriver, runVersion, runOs, testMethod));
    setSessionId("");
  }
}
