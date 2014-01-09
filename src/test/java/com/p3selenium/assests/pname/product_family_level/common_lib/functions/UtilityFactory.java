package com.p3selenium.assests.pname.product_family_level.common_lib.functions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.thoughtworks.selenium.SeleniumException;
import com.thoughtworks.selenium.Wait;

public class UtilityFactory extends TestBase {

	private WebElement element;
	private int flag = 0; // Indicator of pass/ fail i.e. if flag=1
	private String s_flag = null;


	private Long splittedText;
	private String originalWindowHandle;
	private WebDriverBackedSelenium selenium;
	/*
	 * This method is used for fetching fields in the table. And returns the
	 * result in the form of List
	 * 
	 * 
	 * @return cells_text
	 */
	public List<String> getTableRow(String table_id, int row_number,
			int column_start, int column_end) {
		// System.out.println(table_id);
		List<String> cells_text = new ArrayList<String>();
		WebElement baseTable = getDriver().findElement(By.id(table_id));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		// System.out.println("size " + tableRows.size());
		WebElement row = tableRows.get(row_number);
		for (int column_value = column_start; column_value <= column_end; column_value++) {
			cells_text.add(row
					.findElement(By.xpath("td[" + column_value + "]"))
					.getText());
		}
		System.out.println(cells_text);
		return cells_text;
	}

	/* This method is used to read PDF file */
	public String readPDFData(String pdf_path, int page_num) throws IOException {
		String pdfData = null;
		try {
			PdfReader reader = new PdfReader(pdf_path);
			int n = reader.getNumberOfPages();
			System.out.println("No of pages in PDF" + n);
			pdfData = PdfTextExtractor.getTextFromPage(reader, page_num);
			// Extracting the content from a particular page.
			System.out.println(pdfData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdfData;

	}

	
	
	
	

	/*
	 * Function for waiting on a page Created by: - Date :-
	 */
	public int waitForPageToLoad(String msec) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (msec != null) {
				selenium.waitForPageToLoad(msec);
				flag = 1;
				return flag;
			} else
				flag = 0;
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * Function for waiting for a pop up window
	 */
	public void waitForPopUp(final String windowID, String timeout) {
		final long millis = Long.parseLong(timeout);
		final String current = getDriver().getWindowHandle();
		new Wait() {
			@Override
			public boolean until() {
				try {
					if ("_blank".equals(windowID)) {
						selectBlankWindow();
					} else {
						getDriver().switchTo().window(windowID);
					}
					return !"about:blank".equals(getDriver().getCurrentUrl());
				} catch (SeleniumException e) {
					// Swallow
				}
				return false;
			}
		}.wait(String.format("Timed out waiting for %s. Waited %s", windowID,
				timeout), millis);
		getDriver().switchTo().window(current);
	}

	private void selectBlankWindow() {
		String current = getDriver().getWindowHandle();
		// Find the first window without a "name" attribute
		List<String> handles = new ArrayList<String>(getDriver()
				.getWindowHandles());
		for (String handle : handles) {
			getDriver().switchTo().window(handle);
			String value = (String) ((JavascriptExecutor) getDriver())
					.executeScript("return window.name;");
			if (value == null) {
				// We found it!
				return;
			}
		}
		// We couldn't find it
		getDriver().switchTo().window(current);
		throw new SeleniumException("Unable to select window _blank");
	}

	/*
	 * Function for selecting a window Created by: - Date :- 17th Oct 2011
	 */

	public void selectWindow(String windowID) {
		if ("null".equals(windowID)) {
			getDriver().switchTo().window(originalWindowHandle);
		} else if ("_blank".equals(windowID)) {
			selectBlankWindow();
		} else {
			if (windowID.startsWith("title=")) {
				selectWindowWithTitle(windowID.substring("title=".length()));
				return;
			}

			if (windowID.startsWith("name=")) {
				windowID = windowID.substring("name=".length());
			}

			try {
				getDriver().switchTo().window(windowID);
			} catch (NoSuchWindowException e) {
				selectWindowWithTitle(windowID);
			}
		}
	}

	private void selectWindowWithTitle(String title) {
		String current = getDriver().getWindowHandle();
		for (String handle : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(handle);
			if (title.equals(getDriver().getTitle())) {
				return;
			}
		}

		getDriver().switchTo().window(current);
		throw new SeleniumException("Unable to select window with title: "
				+ title);
	}

	/*
	 * Function for closing a window Created by: -
	 */
	public int close() {
		try {
			getDriver().close();
			flag = 1;
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * Function for selection value from drop down list or combo box Created
	 * by:- Date :-
	 */

	// String
	// s=driver.findElement(By.name(dropDownName)).getAttribute("value").substring(driver.findElement(By.name(dropDownName)).getAttribute("value").indexOf("=")+1);

	// if (dropDownValue.indexOf(s)!= -1) {

	public int selectValue(String dropDownName, String dropDownValue) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (dropDownName != null) {

				new Select(getDriver().findElement(By.name(dropDownName)))
						.selectByValue(dropDownValue);

				// String
				// s=driver.findElement(By.name(dropDownName)).getAttribute("value");
				// if
				// (s.indexOf(driver.findElement(By.name(dropDownName)).getAttribute("value"))
				// != -1) {

				if (dropDownValue.indexOf(selenium
						.getSelectedLabel(dropDownName)) != -1) {
					flag = 1;
				} else {
					flag = 0;
				}
				return flag;
			} else
				flag = 0;
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	// Created By

	public int selectValueByID(String dropDownID, String dropDownValue) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (dropDownID != null) {

				new Select(getDriver().findElement(By.id(dropDownID)))
						.selectByValue(dropDownValue);
				// String
				// s=driver.findElement(By.name(dropDownName)).getAttribute("value");
				// if
				// (s.indexOf(driver.findElement(By.name(dropDownName)).getAttribute("value"))
				// != -1) {

				if (dropDownValue
						.indexOf(selenium.getSelectedLabel(dropDownID)) != -1) {
					flag = 1;
				} else {
					flag = 0;
				}
				return flag;
			} else
				flag = 0;
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * Function for verification of value from drop down list or combo box
	 * Created by: -
	 */
	public int verifyValuebyid(String dropDownName, String dropDownValue) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (dropDownName != null && dropDownValue != null) {

				if (dropDownValue.indexOf(getDriver().findElement(
						By.name(dropDownName)).getAttribute("value")) != -1) {
					flag = 1;
				} else {
					flag = 0;

				}

			} else
				flag = 0;
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * Function for selection multiple values from multi-select element Created
	 * by: -
	 */
	public int addSelection(String dropDownName, String dropDownValue) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (dropDownName != null || dropDownValue != null) {
				selenium.addSelection(dropDownName, dropDownValue);
				flag = 1;
				return flag;
			} else
				flag = 0;
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	// Added by
	/*
	 * public int compareText(String ActualText, String textverify) { try { //
	 * Thread.sleep(Constants.DeltaConstants.time); if (ActualText != null) { if
	 * (ActualText.equals(textverify)) { flag = 1; } else { flag = 0; } return
	 * flag; } else flag = 0; return flag; } catch (Exception ex) { return 0; }
	 * }
	 */

	public Long findText(String objectclicked, int location) throws Exception {

		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			String mMiles = getDriver().findElement(By.name(objectclicked))
					.getText();
			String[] arr = mMiles.split(" ");
			String[] arr1 = arr[location].split(",");
			mMiles = "";
			for (int i = 0; i < arr1.length; i++) {
				mMiles = mMiles + arr1[i];
			}
			splittedText = Long.parseLong(mMiles);
		} catch (Exception err) {
			System.out.println("in error" + err.getMessage());
		}
		return splittedText;
	}

	public Long findxpathText(String objectclicked, int location)
			throws Exception {

		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			String mMiles = getDriver().findElement(By.xpath(objectclicked))
					.getText();
			String[] arr = mMiles.split(" ");
			// String[] arr1 = arr[location].split(",");
			String[] arr2 = arr[location].split(":");

			mMiles = "";
			for (int i = 0; i < arr2.length; i++) {
				mMiles = mMiles + arr2[i];
			}
			String nMiles = mMiles.replace(",", "");
			splittedText = Long.parseLong(nMiles);
		} catch (Exception err) {
			System.out.println("in error" + err.getMessage());
		}
		return splittedText;
	}

	public String getElementAttribute(String objectclicked, String type,
			String value) throws Exception {
		String ss = null;
		if (objectclicked != null) {
			if ("css".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.cssSelector(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.cssSelector(objectclicked))
							.getAttribute(value);
				}
			} else if ("link".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.linkText(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.linkText(objectclicked))
							.getAttribute(value);
				}
			} else if ("name".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.name(objectclicked)).isEnabled()) {
					ss = getDriver().findElement(By.name(objectclicked))
							.getAttribute(value);
				}
			} else if ("xpath".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.xpath(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.xpath(objectclicked))
							.getAttribute(value);
				}
			} else if ("id".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.id(objectclicked)).isEnabled()) {
					ss = getDriver().findElement(By.id(objectclicked))
							.getAttribute(value);
				}
			}
		}
		return ss;
	}

	/*
	 * Function for checking the existence of an object used as css element by
	 * calling methods isElementPresent() and isVisible() Created by: - Date:-
	 */
	public int isCSSElementPresent(String elementverify) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (elementverify != null) {
				if (getDriver().findElement(By.cssSelector(elementverify))
						.isDisplayed()) {
					flag = 1;
				} else {
					flag = 0;
				}
				return flag;
			} else
				flag = 0;
			return flag;
		} catch (Exception ex) {
			return 0;
		}

	}

	/*
	 * Function for handling alert box Created by: -
	 */
	public int assertEquals(String message) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (message != null) {
				Alert alert = getDriver().switchTo().alert();
				if (message.equalsIgnoreCase(alert.getText())) {
					flag = 1;
				} else {
					flag = 0;
				}
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	public int assertaccept() {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			getDriver().switchTo().alert().accept();
			return flag = 1;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * Function for performing DB tasks Created by: - Date:-19th September 2011
	 * 
	 * public ResultSet dbTask(String propertyFile, String query) throws
	 * SQLException { String serverName = ""; String portNumber = ""; String sid
	 * = ""; String username = ""; String password = "";
	 * 
	 * if(rset != null){ rset.close(); }
	 * 
	 * if(stmt != null){ stmt.close(); }
	 * 
	 * if(conn != null){ conn.close(); }
	 * 
	 * if (propertyFile != null && query != null) { try { ResultSet rlset =
	 * readDBdetails(propertyFile); while (rlset.next()) { serverName =
	 * rlset.getString("serverName"); portNumber =
	 * rlset.getString("portNumber"); sid = rlset.getString("sid"); username =
	 * rlset.getString("username"); password = rlset.getString("password");
	 * System.out.println(portNumber); } String url = "jdbc:oracle:thin:@" +
	 * serverName + ":" + portNumber + ":" + sid; DriverManager
	 * .registerDriver(new oracle.jdbc.driver.OracleDriver()); conn =
	 * DriverManager.getConnection(url, username, password);
	 * //conn.setAutoCommit(false); stmt = conn.createStatement(); rset =
	 * stmt.executeQuery(query); System.out.println("rset :"+rset); return rset;
	 * } catch (Exception e) { e.printStackTrace(); } } else rset = null; return
	 * rset; }
	 */
	// Function for reading Test data from excel sheet testcases_driver

	public String getToolTipText(String elementXPath, WebDriver wDriver) {
		Actions action = new Actions(wDriver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		action.moveToElement(wDriver.findElement(By.xpath(elementXPath)))
				.build().perform();

		return wDriver.findElement(By.xpath(elementXPath)).getText();

	}

	public void clearField(String objectclicked, String type) {

		if (objectclicked != null) {
			if ("id".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.id(objectclicked)).isEnabled()) {
					getDriver().findElement(By.id(objectclicked)).click();
					WebElement toClear = getDriver().findElement(
							By.id(objectclicked));
					toClear.sendKeys(Keys.CONTROL + "a");
					toClear.sendKeys(Keys.DELETE);
					flag = 1;
				} else {
					flag = 0;
				}

			}
		}

	}


	public void openURL() {
		getDriver().get(("https://qa.aynax.com/login.php"));
	}
	
	
	
}
