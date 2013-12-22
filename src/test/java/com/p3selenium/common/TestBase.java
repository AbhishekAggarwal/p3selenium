package com.p3selenium.common;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//import library_functions.Constants;

public class TestBase {
	private Connection conn = null;
	private Statement stmt;
	private ResultSet rset = null;
	private ResultSet rset_ocs = null;
	private String Item[];
	private int flag = 0; // Indicator of pass/ fail i.e. if flag=1
	private Long splittedText;
	private String sessionID;
	private WebDriver driver = null;
	private String baseUrl;
	private static WebDriverWait wait;
	private JavascriptExecutor js;
	private String originalWindowHandle;
	private WebDriverBackedSelenium selenium;
	private Statement xlStmt = null;
	private Connection xlCon = null;
	private ResultSet xlRset = null;
	private DesiredCapabilities capability = null;

	// protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	@Parameters({ "browser" })
	@BeforeClass
	public WebDriver setup(String browser) throws MalformedURLException {

		if (browser.equalsIgnoreCase("firefox")) {

			// driver=new FirefoxDriver();
			/*
			 * System.setProperty("webdriver.firefox.driver",
			 * "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			 */
			
			FirefoxBinary binary = new FirefoxBinary(new File("path_to_bin"));
			FirefoxProfile profile = new FirefoxProfile();
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
			System.out.println("getting firefox driver");
			setDriver(new FirefoxDriver(capability));
			System.out.println("got firefox driver");
			getDriver().manage().window().maximize();
			System.out.println("window maximized");
			return driver;
			// capability.setVersion("");
		}

		if (browser.equalsIgnoreCase("iexplore")) {

			// System.out.println("iexplore");
			capability = DesiredCapabilities.internetExplorer();
			// System.setProperty("webdriver.ie.driver",
			// "C:\\IEDriverServer.exe");
			capability.setBrowserName("iexplore");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			System.out.println("getting IE driver");
			setDriver(new InternetExplorerDriver(capability));
			System.out.println("got IE driver");
			getDriver().manage().window().maximize();
			System.out.println("window maximized");
			return driver;
			// System.setProperty("webdriver.ie.driver",
			// "C:\\Program Files\\Internet Explorer\\iexplore.exe");
			// capability.setVersion("");
		}

		if (browser.equalsIgnoreCase("chrome")) {

			capability = DesiredCapabilities.chrome();
			/*
			 * System.setProperty("webdriver.chrome.driver",
			 * "C:\\chromedriver.exe");
			 */
			capability.setBrowserName("chrome");
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
			System.out.println("getting chrome driver");
			setDriver(new ChromeDriver(capability));
			System.out.println("got chrome driver");
			getDriver().manage().window().maximize();
			System.out.println("window maximized");

			return getDriver();

		}
		/*
		 * setDriver(new RemoteWebDriver( new
		 * URL("http://172.16.1.120:5559/wd/hub"), capability));
		 */
		// driver.navigate().to("http://qa.aynax.com/");

		// driver.get("http://qa.aynax.com/");

		return getDriver();
	}

	@AfterMethod
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	/*
	 * public void openURL() {
	 * getDriver().get(("https://qa.aynax.com/login.php")); }
	 */

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}