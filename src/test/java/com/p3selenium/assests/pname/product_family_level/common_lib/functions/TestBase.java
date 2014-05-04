package com.p3selenium.assests.pname.product_family_level.common_lib.functions;

/**
 * @author ABHISHEK
 *
 */

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.p3selenium.assests.pname.product_family_level.common_lib.data_source.LoadProperty;

public class TestBase extends Bean {
	protected WebDriver driver = null;

	protected DesiredCapabilities capability = null;
	String test_name = null;
	String username = System.getProperty("user.name");
	String project_path = System.getProperty("user.dir");

	WritableSheet sheet;
	WritableWorkbook workbook;
	String generate_excel_report;
	
	int sheet_pointer=1;

	public WritableSheet getSheet() {
		return sheet;
	}

	public void setSheet(WritableSheet sheet) {
		this.sheet = sheet;
	}

	@Parameters({ "browser", "excel_name" })
	@BeforeClass
	public WebDriver init(String browser, String excel_name) {
		try {
			String url = LoadProperty.getVar("url", "data");
			System.out.println("URL: '" + url + "'");
			test_name = this.getClass().getSimpleName();
			System.out.println("Starting Test: '" + test_name + "'");

			generate_excel_report = LoadProperty.getVar(
					"generate_excel_report", "config");
			System.out.println(generate_excel_report);
			if (generate_excel_report.equals("true")) {
				String data_path = project_path
						+ "\\src\\test\\resources\\TestData";
				workbook = Workbook.createWorkbook(new File(data_path + "\\"
						+ excel_name + ".xls"));
				sheet = workbook.createSheet(excel_name, 0);

			} else {
				System.out.println("else");
			}

			System.out.println("browser name: " + browser);
			if (browser.equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.firefox.driver",
						"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
				capability = DesiredCapabilities.firefox();
				capability.setBrowserName(browser);
				capability.setPlatform(org.openqa.selenium.Platform.ANY);
				System.out.println("firefox");
				setDriver(new FirefoxDriver(capability));
				System.out.println("firefox1");
			}

			else if (browser.equalsIgnoreCase("iexplore")) {
				System.out.println("iexplore");
				capability = DesiredCapabilities.internetExplorer();
				System.setProperty("webdriver.ie.driver", project_path
						+ "\\src\\test\\resources\\driver\\IEDriverServer.exe");
				capability.setBrowserName(browser);
				capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				setDriver(new InternetExplorerDriver(capability));
			}

			else if (browser.equalsIgnoreCase("chrome")) {
				capability = DesiredCapabilities.chrome();
				/*
				 * System.setProperty("webdriver.chrome.driver",
				 * "C:\\chromedriver2.8.exe");
				 */
				System.setProperty(
						"webdriver.chrome.driver",
						project_path
								+ "\\src\\test\\resources\\driver\\chromedriver_v32.exe");

				capability.setBrowserName(browser);
				capability.setPlatform(org.openqa.selenium.Platform.ANY);
				setDriver(new ChromeDriver(capability));

			}

			getDriver().manage().window().maximize();
			return getDriver();
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@AfterMethod
	@AfterClass
	public void tearDown() throws IOException, WriteException {
		System.out.println("Ending Test Name: '" + test_name + "'");
		System.out.println("Shuting down driver of Test Name: '" + test_name
				+ "'");

		driver.quit();
		if (generate_excel_report.equals("true")) {
		workbook.write();
		workbook.close();
		}
		else
		{
			
		}
		System.out.println("Driver quit for Test: '" + test_name + "'");
		System.out.println("\n");
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@AfterSuite(alwaysRun = true)
	public void tearDownAfterSuit() {
		if (driver != null) {
			System.out
					.println("Driver of Test name: "
							+ test_name
							+ " is closing at the end of suit, means this script did not worked properly");
			driver.quit();
		}
	}

}