package com.p3selenium.common;

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

public class TestBase {
	/*
	 * Connection conn = null; Statement stmt; ResultSet rset = null; ResultSet
	 * rset_ocs = null; String Item[]; int flag = 0; // Indicator of pass/ fail
	 * i.e. if flag=1 Long splittedText; public String sessionID;
	 */
	private WebDriver driver = null;
	private DesiredCapabilities capability = null;
	String project_root = System.getProperty("user.dir");

	/*
	 * private String baseUrl; static WebDriverWait wait; JavascriptExecutor js;
	 * String originalWindowHandle; WebDriverBackedSelenium selenium; Statement
	 * xlStmt = null; Connection xlCon = null; ResultSet xlRset = null;
	 */

	// protected ThreadLocal<RemoteWebDriver> threadDriver = null;

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
			// capability.setVersion("");
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
			// System.setProperty("webdriver.ie.driver",
			// "C:\\Program Files\\Internet Explorer\\iexplore.exe");
			// capability.setVersion("");
		}

		if (browser.equalsIgnoreCase("chrome")) {

			capability = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver",
					"C:\\chromedriver.exe");
			/*
			 * System.setProperty("webdriver.ie.driver", project_root +
			 * "\\src\\test\\resources\\driver\\chromedriver.exe");
			 */
			capability.setBrowserName("chrome");
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
			setDriver(new ChromeDriver(capability));
			getDriver().manage().window().maximize();
			System.out.println("chrome driver");
			System.out.println(getDriver());
			return getDriver();

		}
		// setDriver(new RemoteWebDriver(new
		// URL("http://172.16.1.120:5559/wd/hub"), capability));
		// driver.navigate().to("http://qa.aynax.com/");

		// driver.get("http://qa.aynax.com/");

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