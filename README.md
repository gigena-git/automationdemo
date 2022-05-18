# Automation Demo
This is a small but efficient test framework designed to showcase my skills with the following technologies:

* [Java 11](https://www.oracle.com/java/technologies/downloads/) I have not tested Java 17 yet.
* [Maven](https://maven.apache.org/)
* [Selenium](https://www.seleniumhq.org/)
* [Appium](https://appium.io/)
* [SauceLabs](https://saucelabs.com/)

Each test targets a subpage of [Elemental Selenium's](http://the-internet.herokuapp.com/) webpage.

### Prerequisites

* Install JDK11 and set $JAVA_HOME variable.
* Install Maven and set it in the $PATH variable (this might be done for you depending on the OS).
* Install any Java IDE (such as IntelliJ IDEA).
* Install Appium Desktop.

### Installing

1. Clone this repo. 
1. If you are going to use IntelliJ IDEA, use the [checkstyle guide](https://checkstyle.org/idea.html) to set up auto-indent config on IntelliJ.
1. Plug your Android device to your desktop.
1. Do `adb devices` to get the device name assigned to your phone. Also, note down your Android version number.
1. Start Appium Desktop.
1. Create a `configuration.properties` file inside `src/test/resources`. It's contents should be:
    ```
    device=your_device_name_here
    platformversion=android_version_here
    ```
1. Inside your IDE, go to `com.mgigena.automationdemo.tests.mobile` and pick any class file.
1. Run any test and check it ends successfully.
1. Repeat the last two steps, but with `com.mgigena.automationdemo.tests.desktop` to run desktop tests.

## Running the tests
To run the tests, you must have a [SauceLabs](https://saucelabs.com/) account. Setup the environment variables: `SAUCE_USERNAME` and `SAUCE_ACCESS_KEY`.

After that, from the project root, run:
```
mvn test -Dlog4j.configurationFile=src/test/resources/errorloglevel.xml
```
For a more verbose output, run:
```
mvn test -Dlog4j.configurationFile=src/test/resources/infologlevel.xml
```
