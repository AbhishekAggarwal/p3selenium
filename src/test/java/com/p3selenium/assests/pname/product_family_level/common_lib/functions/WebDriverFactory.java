package com.p3selenium.assests.pname.product_family_level.common_lib.functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.format.Colour;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.thoughtworks.selenium.SeleniumException;
import com.thoughtworks.selenium.Wait;

public class WebDriverFactory extends TestBase {
	private WebElement element;
	private int flag = 0; // Indicator of pass/ fail i.e. if flag=1
	private String s_flag = null;

	private Long splittedText;
	private String originalWindowHandle;
	private WebDriverBackedSelenium selenium;

	private String user;
	private List<String> al1 = null;
	private boolean b = false;

	/*
	 * This method is used to find the element on the page
	 * 
	 * @param element_name , element_type
	 * 
	 * @return WebElement
	 */
	public WebElement findElement(String element_name, String element_type) {
		try {
			if (element_type.equalsIgnoreCase("name")) {
				element = getDriver().findElement(By.name(element_name));
				// flag = 1;
			} else if (element_type.equalsIgnoreCase("css")) {
				element = getDriver().findElement(By.cssSelector(element_name));
				// flag = 1;
			} else if (element_type.equalsIgnoreCase("link")) {
				// System.out.println("link");
				// System.out.println("Element Name: \t" + element_name);
				element = getDriver().findElement(By.linkText(element_name));
				// flag = 1;
			} else if (element_type.equalsIgnoreCase("class")) {
				element = getDriver().findElement(By.className(element_name));
				// flag = 1;
			} else if (element_type.equalsIgnoreCase("id")) {
				element = getDriver().findElement(By.id(element_name));
				// flag = 1;
			} else if (element_type.equalsIgnoreCase("name")
					|| element_type.equalsIgnoreCase("value")
					|| element_type.equalsIgnoreCase("label")) {
				element = getDriver().findElement(By.name(element_name));
				// flag = 1;
			} else if (element_type.equalsIgnoreCase("xpath")) {
				element = getDriver().findElement(By.xpath(element_name));
				// flag = 1;
			} else if (element_type.equalsIgnoreCase("tag")) {
				element = getDriver().findElement(By.tagName(element_name));
				// flag = 1;
			} else if (element_type.equalsIgnoreCase("partialLinkText")) {
				element = getDriver().findElement(
						By.partialLinkText(element_name));
				// flag = 1;
			}

			return element;
		} catch (ElementNotFoundException | ElementNotVisibleException e) {

			System.out
					.println("------------------------------------------------------------------");
			System.out
					.println("Error in WebDriverFactory Class while finding the element on page");
			System.out.println("Element name was '" + element_name
					+ "and type was '" + element_type
					+ "' which was not found.");
			System.out.println("Below is the Stack Trace:");
			e.printStackTrace();
			throw e;
			/*
			 * @Dev: Abhishek
			 * 
			 * @Status: see and update the exception handling mechanism. use
			 * centralized exception handling ref: http://stackoverflow
			 * .com/questions/6909920/common-centralized-method
			 * -to-handle-multiple-exceptions Also find and implement a graceful
			 * shutting down method of script when ever this error occur
			 */
		} catch (Exception e) {

			e.printStackTrace();
			System.out
					.println("------------------------------------------------------------------");
			throw e;
		}
	}

	/*
	 * Use to clear text in a field
	 * 
	 * @param element_name, element_type , value
	 * 
	 * @return int
	 */
	public int clear(String element_name, String value, String element_type) {

		try {
			if (element_name != null && value != null) {
				element = findElement(element_name, element_type);
				element.clear();
				// element.sendKeys("");
				flag = 1;
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * Function for clicking a link, button or radio button
	 * 
	 * @param element_name, element_type
	 * 
	 * @return int
	 */
	public int click(String element_name, String element_type) {
		try {
			if (element_name != null) {
				// System.out.println(element);
				element = findElement(element_name, element_type);
				element.click();
				flag = 1;
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * Method to get attribute of an element
	 * 
	 * @param element_name, element_type, attribute
	 * 
	 * @return String
	 */
	public String getAttribute(String element_name, String element_type,
			String attribute) throws Exception {
		String value = null;
		try {
			if (element_name != null && attribute != null) {
				element = findElement(element_name, element_type);
				value = element.getAttribute(attribute);
			} else {
			}
			return value;
		} catch (Exception ex) {
			return value;
		}
	}

	/*
	 * Method to get attribute of an element
	 * 
	 * @param element_name, element_type, attribute
	 * 
	 * @return String
	 */
	public String getCssValue(String element_name, String element_type,
			String attribute) throws Exception {
		String str = null;
		try {
			if (element_name != null && attribute != null) {
				element = findElement(element_name, element_type);
				str = element.getCssValue(attribute);
			} else {
			}
			return str;
		} catch (Exception ex) {
			return str;
		}

	}

	public Point getLocation(String element_name, String element_type)
			throws Exception {
		Point point = null;
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (element_name != null) {
				element = findElement(element_name, element_type);
				point = element.getLocation();
			} else {
			}
			return point;
		} catch (Exception ex) {
			return point;
		}

	}

	/*
	 * Function to get dimension of an element
	 * 
	 * @param element_name, element_type, attribute
	 * 
	 * @return dimension
	 */
	public Dimension getSize(String element_name, String element_type)
			throws Exception {
		Dimension dimension = null;
		try {
			if (element_name != null) {
				element = findElement(element_name, element_type);
				dimension = element.getSize();
			} else {
			}
			return dimension;
		} catch (Exception ex) {
			return dimension;
		}
	}

	/*
	 * Function to tag name of an element
	 * 
	 * @param element_name, element_type, attribute
	 * 
	 * @return tag
	 */
	public String getTagName(String element_name, String element_type)
			throws Exception {
		String tag = null;
		try {
			if (element_name != null) {
				element = findElement(element_name, element_type);
				tag = element.getTagName();
			} else {
			}
			return tag;
		} catch (Exception ex) {
			return tag;
		}
	}

	/*
	 * Function to get text of an element
	 * 
	 * @param element_name, element_type, attribute
	 * 
	 * @return text
	 */
	public String getText(String element_name, String element_type)
			throws Exception {
		String text = null;
		try {
			if (element_name != null) {
				element = findElement(element_name, element_type);
				text = element.getText();
			} else {
			}
			return text;
		} catch (Exception ex) {
			return text;
		}
	}

	/*
	 * Function to check element is displayed or not
	 * 
	 * @param element_name, element_type, attribute
	 * 
	 * @return int
	 */
	public int isDisplayed(String element_name, String element_type) {
		try {
			if (element_name != null) {
				// Thread.sleep(Constants.DeltaConstants.time);
				// System.out.println(element);
				element = findElement(element_name, element_type);
				b = element.isDisplayed();
				if (b == true)
					flag = 1;
				else
					flag = 0;
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}

	}

	/*
	 * To check that field is enabled or not
	 * 
	 * @param element_name, element_type, attribute
	 * 
	 * @return int
	 */

	public int isEnabled(String element_name, String element_type) {
		try {
			if (element_name != null) {
				// Thread.sleep(Constants.DeltaConstants.time);
				// System.out.println(element);
				element = findElement(element_name, element_type);
				b = element.isEnabled();
				if (b == true)
					flag = 1;
				else
					flag = 0;
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * To check that field is selected or not
	 * 
	 * @param element_name, element_type, attribute
	 * 
	 * @return int
	 */
	public int isSelected(String element_name, String element_type) {
		try {
			if (element_name != null) {
				// Thread.sleep(Constants.DeltaConstants.time);
				// System.out.println(element);
				element = findElement(element_name, element_type);
				b = element.isSelected();
				if (b == true)
					flag = 1;
				else
					flag = 0;
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*----------------------------------------------------------------------------*/

	/*-------------------------------------Custom Methods Starts---------------------------------------*/

	/*
	 * Method to perform Mouse Over an element
	 * 
	 * @return int
	 * 
	 * @param element_name, element_type
	 */
	public int mouseOver(String element_name, String element_type) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (element_name != null) {
				element = findElement(element_name, element_type);
				Actions builder = new Actions(getDriver());
				builder.moveToElement(element).perform();
				flag = 1;
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * Use to type text in the field
	 * 
	 * @return int
	 * 
	 * @param element_name, element_type
	 */
	public int typeText(String fieldname, String fieldvalue, String type) {
		try {
			if (fieldname != null && fieldvalue != null) {
				element = findElement(fieldname, type);
				element.clear();
				element.sendKeys(fieldvalue);
				flag = 1;
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}

	}

	/*
	 * Use to pressing enter in text boxes
	 */
	public int pressEnter(String element_name, String element_type) {
		try {
			if (element_name != null) {
				element = findElement(element_name, element_type);
				element.sendKeys(Keys.ENTER);
				flag = 1;
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/* @dev */

	/*
	 * Function to verify text is present
	 */
	public int isTextPresent(String text) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (text != null) {
				if (getDriver().getPageSource().contains(text)) {
					flag = 1;
				} else {
					flag = 0;
				}
				return flag;
			} else {
				flag = 0;

				return flag;
			}
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * This method is use to log the data in excel file
	 */
	public void logData(WritableSheet sheet, String testcaseName,
			String testCaseID, String expectedResult, String actualResult,
			String Status, String Comment) {

		try {
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
		} catch (Exception e) {
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
	public List<String> getTableRow(String element_name, String element_type,
			int row_number, int column_start, int column_end) {
		// System.out.println(table_id);
		List<String> cells_text = new ArrayList<String>();
		element = findElement(element_name, element_type);
		// WebElement baseTable = getDriver().findElement(By.id(element_name));
		List<WebElement> tableRows = element.findElements(By.tagName("tr"));
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
	 * 
	 * This method points to the project's root folder give file variable the
	 * file name like file.csv if your file is in root folder
	 */
	public String fetchDataFromCSV(String file) {
		try {
			String path = System.getProperty("user.dir");
			// path = path + "\\src\\test\\java\\csv\\users.csv";
			path = path + file;
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
	


	public String getToolTipText(String element_name, String element_type, WebDriver wDriver) {
		Actions action = new Actions(wDriver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		action.moveToElement(findElement(element_name,element_type)).build().perform();
		return findElement(element_name, element_type).getText();

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

	//Doubt
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



	// ////////////////////////////////////DD////////////////////////////////////
	/*
	 * Function for selection value from drop down list or combo box Created
	 */

	public int selectDropDownValue(String drop_down_name, String dropDownValue,
			String element_type) {
		try {
			if (drop_down_name != null) {
				new Select(findElement(drop_down_name, element_type))
						.selectByValue(dropDownValue);
				if (dropDownValue.indexOf(selenium
						.getSelectedLabel(drop_down_name)) != -1) {
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

	// //////////////////////////////////DD////////////////////////////////////

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
	public void selectBlankWindow() {
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

	/*-------------------------------------Custom Methods Ends---------------------------------------*/
}
