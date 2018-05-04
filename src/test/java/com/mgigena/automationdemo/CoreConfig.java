package com.mgigena.automationdemo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.saucelabs.saucerest.SauceREST;

public final class CoreConfig {

  private static final Logger LOGGER = LogManager.getLogger(CoreConfig.class);

  public static String getProperty(String property) {
    Properties props = new Properties();
    String filename = "configuration.properties";
    try {
      props.load(CoreConfig.class.getClassLoader().getResourceAsStream(filename));
    } catch (IOException exception) {
      LOGGER.error(exception.getMessage());
    }
    return props.getProperty(property);
  }

  public static String getSauceUserName() {
    return System.getenv("SAUCE_USERNAME");
  }

  public static String getSauceAccessKey() {
    return System.getenv("SAUCE_ACCESS_KEY");
  }

  public static SauceREST getSauceRest() {
    return new SauceREST(getSauceUserName(), getSauceAccessKey());
  }

  public static String getRemoteURL() {
    String url = "https://%s:%s@ondemand.saucelabs.com:443/wd/hub";
    return String.format(url, getSauceUserName(), getSauceAccessKey());
  }

  public static String getBuild() {
    return System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER");
  }

  public static String getJobName() {
    return System.getenv("JOB_NAME");
  }

  public static URL getLocalAppiumUrl() {
    URL localUrl = null;
    try {
      localUrl = new URL("http://localhost:4723/wd/hub");
    } catch (MalformedURLException e) {
      LOGGER.error("There was an error parsing the local appium instance.");
    }
    return localUrl;
  }

  public static URL getRemoteServerUrl() {
    URL remoteUrl = null;
    try {
      remoteUrl = new URL(getRemoteURL());
    } catch (MalformedURLException e) {
      LOGGER.info("There was an error parsing the local appium instance.");
    }
    return remoteUrl;
  }

  private CoreConfig() {
  }
}
