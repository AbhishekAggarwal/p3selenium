package com.p3selenium.base;

/**
 * @author ABHISHEK
 *
 */
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBase extends Bean {
	private WebDriver driver = null;

	private DesiredCapabilities capability = null;

	// private String project_root = System.getProperty("user.dir");
	// private LoadProperty property = null;

	@Parameters({ "browser" })
	@BeforeClass
	public WebDriver setup(String browser) throws MalformedURLException {

		if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.firefox.driver",
					"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
			setDriver(new FirefoxDriver(capability));
			getDriver().manage().window().maximize();
			System.out.println("firefox driver");
			System.out.println(getDriver());
			return getDriver();
		}

		if (browser.equalsIgnoreCase("iexplore")) {
			System.out.println("iexplore");
			capability = DesiredCapabilities.internetExplorer();
			System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
			capability.setBrowserName("iexplore");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			setDriver(new InternetExplorerDriver(capability));
			getDriver().manage().window().maximize();
			System.out.println("IE driver");
			return getDriver();

		}

		if (browser.equalsIgnoreCase("chrome")) {

			capability = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			// prompt_for_download
			capability.setBrowserName("chrome");
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
			setDriver(new ChromeDriver(capability));
			getDriver().manage().window().maximize();
			System.out.println("chrome driver");
			System.out.println(getDriver());
			return getDriver();

		}

		return getDriver();
	}

	@AfterMethod
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}