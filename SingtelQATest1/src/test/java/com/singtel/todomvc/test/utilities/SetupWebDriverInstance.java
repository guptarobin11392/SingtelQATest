package com.singtel.todomvc.test.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class serves a utility for WebDriver initialization
 * @category Utility Class
 * @author ROBIN GUPTA
 *
 */
public class SetupWebDriverInstance {

	private WebDriver driver;
	private WebDriverWait wait;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public SetupWebDriverInstance() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
	}

	public void launchBrowser(String browserType) {

		try {

			switch (browserType.toUpperCase()) {
			case "CHROME":
				setDriver(new ChromeDriver());
				break;
			case "FIREFOX":
				setDriver(new FirefoxDriver());
				break;
			case "IE":
				setDriver(new InternetExplorerDriver());
				break;
			}

			setWait(new WebDriverWait(driver, 10));
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();

		} catch (Exception e) {
			driver = null;
			setWait(null);
			throw new RuntimeException("Could not instantiate Browser", e);
		}
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}

}
