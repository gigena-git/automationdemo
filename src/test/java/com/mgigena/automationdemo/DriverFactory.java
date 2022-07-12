package com.mgigena.automationdemo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class DriverFactory {

  private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);

  private DriverFactory() {}

  public static WebDriver getDriver(
      String environment, String runDriver, String runVersion, String runOs, Method testMethod) {
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
      AbstractDriverOptions browserOptions = null;
      switch (runDriver) {
        case "chrome":
          browserOptions = new ChromeOptions();
          break;
        default:
          browserOptions = new FirefoxOptions();
      }
      browserOptions.setPlatformName(runOs);
      browserOptions.setBrowserVersion(runVersion);
      Map<String, Object> sauceOptions = new HashMap<>();
      sauceOptions.put("name", testMethod.getName());
      browserOptions.setCapability("sauce:options", sauceOptions);
      driver = new RemoteWebDriver(url, browserOptions);
    }
    return driver;
  }

  public static AppiumDriver getDevice(
      String environment,
      String appiumVersion,
      String platformName,
      String platformVersion,
      String deviceName,
      String browserName,
      Method testMethod) {
    URL url;
    AppiumDriver driver = null;
    MutableCapabilities capabilities = new MutableCapabilities();
    if ("LOCAL".equals(environment)) {
      url = CoreConfig.getLocalAppiumUrl();
      if ("Android".equals(platformName)) {
        String localVersion = CoreConfig.getProperty("platformversion");
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, localVersion);
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

        driver = new AndroidDriver(url, capabilities);
      }
    } else if ("REMOTE".equals(environment)) {
      url = CoreConfig.getRemoteServerUrl();
      capabilities.setCapability("platformName", platformName);
      capabilities.setCapability("browserName", browserName);
      capabilities.setCapability("appium:deviceName", deviceName);
      capabilities.setCapability("appium:platformVersion", platformVersion);
      MutableCapabilities sauceOptions = new MutableCapabilities();
      sauceOptions.setCapability("appiumVersion", appiumVersion);
      sauceOptions.setCapability("name", testMethod.getName());
      capabilities.setCapability("sauce:options", sauceOptions);
      if ("Android".equals(platformName)) {
        driver = new AndroidDriver(url, capabilities);
      } else if ("iOS".equals(platformName)) {
        driver = new IOSDriver(url, capabilities);
      }
    }
    return driver;
  }
}
