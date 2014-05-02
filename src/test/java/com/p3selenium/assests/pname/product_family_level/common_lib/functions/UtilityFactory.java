package com.p3selenium.assests.pname.product_family_level.common_lib.functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.format.Colour;
import jxl.read.biff.BiffException;

import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
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

	private String user;
	private List<String> al1 = null;

	
	
	
	/*
	 * This method is use to log the data in excel file
	 */
	public void logData(WritableSheet sheet, String testcaseName,
			String testCaseID, String expectedResult, String actualResult,
			String Status, String Comment)  {

		try{
		System.out.println("write excel");
		// Workbook readbook = Workbook.getWorkbook(new
		// File(path+"\\Report.xls"));

		// Sheet addUser = readbook.getSheet("AddUser");

		WritableFont wfobj = new WritableFont(WritableFont.ARIAL, 12,
				WritableFont.BOLD);
		WritableCellFormat cfobj = new WritableCellFormat(wfobj);

		if (Status == "success")
			cfobj.setBackground(Colour.GREEN);
		else
			cfobj.setBackground(Colour.RED);

		cfobj.setWrap(true);
		
		Label lblDate = new Label(0, sheet_pointer, testcaseName, cfobj);
		sheet.addCell(lblDate);

		lblDate = new Label(1, sheet_pointer, testCaseID, cfobj);
		sheet.addCell(lblDate);

		lblDate = new Label(2, sheet_pointer, expectedResult, cfobj);
		sheet.addCell(lblDate);

		lblDate = new Label(3, sheet_pointer, testCaseID, cfobj);
		sheet.addCell(lblDate);

		lblDate = new Label(4, sheet_pointer, expectedResult, cfobj);
		sheet.addCell(lblDate);

		lblDate = new Label(5, sheet_pointer, actualResult, cfobj);
		sheet.addCell(lblDate);

		lblDate = new Label(6, sheet_pointer, Status, cfobj);
		sheet.addCell(lblDate);

		lblDate = new Label(7, sheet_pointer, Comment, cfobj);
		sheet.addCell(lblDate);

		sheet_pointer++;
		
		WritableCellFormat cf1 = new WritableCellFormat(DateFormats.FORMAT9);
		DateTime dt = new DateTime(3, 1, new Date(), cf1);
		sheet.addCell(dt);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * This method is used for fetching fields in the table. And returns the
	 * result in the form of List
	 * 
	 * @param table_id, row_number, column_start, column_end
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

	/*
	 * This method is used to read PDF file
	 * 
	 * @param pdf_path, page_num
	 * 
	 * @return pdfDdata
	 */
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
	 * Read data from CSV file
	 * 
	 * @param
	 * 
	 * @return String
	 */
	public String fetchDataFromCSV() {
		try {
			String path = System.getProperty("user.dir");
			path = path + "\\src\\test\\java\\csv\\users.csv";
			System.out.println(path);
			al1 = new ArrayList<String>();

			BufferedReader br = new BufferedReader(new FileReader(path));
			String userData = br.readLine();
			while (userData != null) {
				String userArray[] = userData.split(",");
				for (String item1 : userArray) {
					al1.add(item1);
				}
				userData = br.readLine();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = al1.get(0);
		return user;
	}

	/*
	 * Function for waiting for a pop up window
	 * 
	 * @param windowID, timeout
	 * 
	 * @return "current window"
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

	/*
	 * Function for waiting for a pop up window
	 * 
	 * @param
	 * 
	 * @return "current window"
	 */
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
				return;
			}
		}
		getDriver().switchTo().window(current);
		throw new SeleniumException("Unable to select window _blank");
	}

	/*
	 * Function for selecting a window
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

	/*
	 * Function for checking the existence of an object used as css element by
	 * calling methods isElementPresent() and isVisible()
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
	 * Function for handling alert box
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

	public boolean isAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public boolean isThisDateValid(String dateToValidate, String dateFromat) {

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			// if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	/*
	 * **********************************************************
	 * scrap *********************************************************
	 */
	public void openURL() {
		getDriver().get(("https://qa.aynax.com/login.php"));
	}

	/*
	 * Function for waiting on a page
	 * 
	 * @param msec
	 * 
	 * @return int
	 */
	public int waitForPageToLoad(String msec) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (msec != null) {
				selenium.waitForPageToLoad(msec);
				flag = 1;
			} else
				flag = 0;
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

}
