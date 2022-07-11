package com.mgigena.automationdemo;

import com.saucelabs.common.Utils;
import com.saucelabs.saucerest.SauceREST;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    return Utils.readPropertyOrEnv("SAUCE_USERNAME", "");
  }

  public static String getSauceAccessKey() {
    return Utils.readPropertyOrEnv("SAUCE_ACCESS_KEY", "");
  }

  public static SauceREST getSauceRest() {
    return new SauceREST(getSauceUserName(), getSauceAccessKey());
  }

  public static String getJobName() {
    return Utils.readPropertyOrEnv("JOB_NAME", "");
  }

  public static String getBuildNumber() {
    return Utils.readPropertyOrEnv("BUILD_NUMBER", "");
  }

  public static String getRemoteURL() {
    String url = "https://%s:%s@ondemand.us-west-1.saucelabs.com:443/wd/hub";
    return String.format(url, getSauceUserName(), getSauceAccessKey());
  }

  public static String getBuild() {
    return getJobName() + "__" + getBuildNumber();
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

  private CoreConfig() {}
}
