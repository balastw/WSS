package com.cognizant.framework.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Settings;

public class SauceLabsDriverFactory {

	private static Properties mobileProperties;

	private SauceLabsDriverFactory() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the Saucelabs DesktopCloud {@link RemoteWebDriver}
	 * object based on the parameters passed
	 * 
	 * @param platformName
	 *            The platform to be used for the test execution (Windows, Mac,
	 *            etc.)
	 * @param version
	 *            The browser version to be used for the test execution
	 * @param browserName
	 *            The {@link Browser} to be used for the test execution
	 * @param sauceUrl
	 *            The Saucelabs URL to be used for the test execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getSauceRemoteWebDriver(String sauceURL,
			Browser browser, String browserVersion, Platform platformName,
			SeleniumTestParameters testParameters) {
		WebDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platform", platformName);
		desiredCapabilities.setCapability("version", browserVersion);
		desiredCapabilities.setCapability("browserName", browser);
		// desiredCapabilities.setCapability("screen-resolution","800x600");
		desiredCapabilities.setCapability("name",
				testParameters.getCurrentTestcase());
		try {
			driver = new RemoteWebDriver(new URL(sauceURL), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new FrameworkException(
					"The RemoteWebDriver driver invokation has problem, please re-check the capabilities and check the SauceLabs details URL, Username and accessKey ");
		}
		return driver;
	}

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getSauceAppiumDriver(
			MobileExecutionPlatform executionPlatform, String deviceName,
			String sauceURL, SeleniumTestParameters testParameters) {

		AppiumDriver driver = null;

		mobileProperties = Settings.getMobilePropertiesInstance();

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (executionPlatform) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("app",
						mobileProperties.getProperty("SauceAndroidIdentifier"));
				desiredCapabilities.setCapability("name",
						testParameters.getCurrentTestcase());
				/*
				 * desiredCapabilities.setCapability("appPackage",
				 * mobileProperties.getProperty("Application_Package_Name"));
				 * desiredCapabilities .setCapability("appActivity",
				 * mobileProperties
				 * .getProperty("Application_MainActivity_Name"));
				 */
				// desiredCapabilities.setCapability("app",
				// "PUBLIC:appium/apiDemos.apk");
				try {
					driver = new AndroidDriver(new URL(sauceURL),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					throw new FrameworkException(
							"The android driver invokation has problem, please re-check the capabilities and check the SauceLabs details URL, Username and accessKey ");
				}

				break;

			case IOS:

				desiredCapabilities.setCapability("appiumVersion", "1.4.16");
				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("browserName", "");
				desiredCapabilities.setCapability("name",
						testParameters.getCurrentTestcase());
				desiredCapabilities.setCapability("platformVersion", "9.0");
				desiredCapabilities.setCapability("app",
						mobileProperties.getProperty("SauceIosBundleID"));

				try {
					driver = new IOSDriver(new URL(sauceURL),
							desiredCapabilities);

				} catch (MalformedURLException e) {
					throw new FrameworkException(
							"The IOS driver invokation has problem, please re-check the capabilities and check the SauceLabs details URL, Username and accessKey ");
				}
				break;

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("browserName", "Browser");
				desiredCapabilities.setCapability("name",
						testParameters.getCurrentTestcase());

				try {
					driver = new AndroidDriver(new URL(sauceURL),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					throw new FrameworkException(
							"The android driver/browser invokation has problem, please re-check the capabilities and check the SauceLabs details URL, Username and accessKey ");
				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("name",
						testParameters.getCurrentTestcase());
				desiredCapabilities.setCapability("browserName", "Safari");
				desiredCapabilities.setCapability("platformVersion", "9.0");

				try {
					driver = new IOSDriver(new URL(sauceURL),
							desiredCapabilities);

				} catch (MalformedURLException e) {
					throw new FrameworkException(
							"The IOS driver invokation/browser has problem, please re-check the capabilities and check the SauceLabs details URL, Username and accessKey ");
				}
				break;

			default:
				throw new FrameworkException("Unhandled ExecutionMode!");

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FrameworkException(
					"The Sauce appium driver invocation created a problem , please check the capabilities");
		}
		return driver;

	}

}
