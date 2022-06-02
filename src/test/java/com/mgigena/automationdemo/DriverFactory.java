package com.mgigena.automationdemo;

import java.lang.reflect.Method;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {

  private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);

  private DriverFactory() {
  }

  public static WebDriver getDriver(String environment, String runDriver, String runVersion,
      String runOs, Method testMethod) {
    WebDriver driver = null;
    if ("LOCAL".equals(environment)) {
      LOGGER.info("Creating local driver");
      switch (runDriver) {
        case "MicrosoftEdge":
          LOGGER.info("Creating Edge Driver");
          WebDriverManager.edgedriver().setup();
          driver = new EdgeDriver();
          break;
        case "chrome":
          LOGGER.info("Creating Chrome Driver");
          WebDriverManager.chromedriver().setup();
          driver = new ChromeDriver();
          break;
        default:
          LOGGER.info("Creating Firefox Driver");
          WebDriverManager.firefoxdriver().setup();
          driver = new FirefoxDriver();
      }
    } else if ("REMOTE".equals(environment)) {
      LOGGER.info("Creating remote driver");
      URL url = CoreConfig.getRemoteServerUrl();
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability(CapabilityType.BROWSER_NAME, runDriver);
      capabilities.setCapability(CapabilityType.VERSION, runVersion);
      capabilities.setCapability("platform", runOs);
      capabilities.setCapability("build", CoreConfig.getBuild());
      capabilities.setCapability("name", testMethod.getName());

      driver = new RemoteWebDriver(url, capabilities);
    }
    return driver;
  }

  public static AppiumDriver getDevice(String environment, String appiumVersion,
      String platformName, String platformVersion, String deviceName, String browserName,
      Method testMethod) {
    URL url;
    AppiumDriver driver = null;
    DesiredCapabilities capabilities;
    if ("LOCAL".equals(environment)) {
      url = CoreConfig.getLocalAppiumUrl();
      if ("Android".equals(platformName)) {
        String localDevice = CoreConfig.getProperty("device");
        String localVersion = CoreConfig.getProperty("platformversion");
        capabilities = DesiredCapabilities.android();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("platformVersion", localVersion);
        capabilities.setCapability("deviceName", localDevice);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");

        driver = new AndroidDriver(url, capabilities);
      }
    } else if ("REMOTE".equals(environment)) {
      url = CoreConfig.getRemoteServerUrl();
      if ("Android".equals(platformName)) {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("appiumVersion", appiumVersion);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability("build", CoreConfig.getBuild());
        capabilities.setCapability("name", testMethod.getName());

        driver = new AndroidDriver(url, capabilities);
      }
    }
    return driver;
  }
}
