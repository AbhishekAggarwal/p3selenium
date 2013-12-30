package com.p3selenium.base;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.p3selenium.data.Aynax_Constants;
import com.thoughtworks.selenium.SeleniumException;
import com.thoughtworks.selenium.Wait;

//import com.configuration.com.TestBase;

public class WebDriverFactory extends TestBase {
	/*
	 * private Connection conn = null; private Statement stmt; private ResultSet
	 * rset = null; private ResultSet rset_ocs = null; private String Item[];
	 * private Statement xlStmt = null; private Connection xlCon = null; private
	 * ResultSet xlRset = null; private String sessionID; private String
	 * baseUrl; private static WebDriverWait wait; private JavascriptExecutor
	 * js;
	 */
	private int flag = 0; // Indicator of pass/ fail i.e. if flag=1

	private Long splittedText;
	private String originalWindowHandle;
	private WebDriverBackedSelenium selenium;

	/*private String element_name;
	private String element_type;*/
	WebElement element;

	// WebDriver driver=null;
	// TestBase testbase=new TestBase();
	// WebDriver driver=new WebDriver();

	/*
	 * // Set execution speed (i.e., set the millisecond length of a delay which
	 * will follow each selenium operation). public void setSpeed(String msec) {
	 * if (!(msec.equals(null))) { selenium.setSpeed(msec); } }
	 */

	/*
	 * WebElement onElement=
	 * getDriver().findElement(By.xpath(Aynax_Constants.DeltaConstants.icon5));
	 * System.out.println("Tooltip : " + onElement.getAttribute("title"));
	 */

	public WebElement findElement(String element_name, String element_type) {
		try{
		if (element_type.equalsIgnoreCase("name")) {
			element = getDriver().findElement(By.name(element_name));
			flag = 1;
		} else if (element_type.equalsIgnoreCase("css")) {
			element = getDriver().findElement(By.cssSelector(element_name));
			flag = 1;
		} else if (element_type.equalsIgnoreCase("link")) {
			System.out.println("link");
			System.out.println("Element Name: \t"+element_name);
			element = getDriver().findElement(By.linkText(element_name));
			flag = 1;
		} else if (element_type.equalsIgnoreCase("class")) {
			element = getDriver().findElement(By.className(element_name));
			flag = 1;
		} else if (element_type.equalsIgnoreCase("id")) {
			element = getDriver().findElement(By.id(element_name));
			flag = 1;
		} else if (element_type.equalsIgnoreCase("name")
				|| element_type.equalsIgnoreCase("value")
				|| element_type.equalsIgnoreCase("label")) {
			element = getDriver().findElement(By.name(element_name));
			flag = 1;
		} else if (element_type.equalsIgnoreCase("xpath")) {
			element = getDriver().findElement(By.xpath(element_name));
			flag = 1;
		}
		else if (element_type.equalsIgnoreCase("tag")) {
			element = getDriver().findElement(By.tagName(element_name));
			flag = 1;
		}
		else if (element_type.equalsIgnoreCase("partialLinkText")) {
			element = getDriver().findElement(By.partialLinkText(element_name));
			flag = 1;
		}

		return element;
		}
		catch(Exception e)
		{
			System.out.println("------------------------------------------------------------------");
			System.out.println("Error in WebDriverFactory Class while finding the element on page");
			e.printStackTrace();
			System.out.println("------------------------------------------------------------------");
			return null;
		}
	}

	public int typeText(String fieldname, String fieldvalue, String type) {
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (fieldname != null && fieldvalue != null) {
			element = findElement(fieldname, type);
			element.clear();
			element.sendKeys(fieldvalue);
			}
			else
			{
				flag = 0;
			}
			/*if (fieldname != null && fieldvalue != null) {
				
				if (type.equalsIgnoreCase("name")) {

					getDriver().findElement(By.name(fieldname)).clear();
					getDriver().findElement(By.name(fieldname)).sendKeys(
							fieldvalue);
					flag = 1;
				} else if (type.equalsIgnoreCase("xpath")) {

					getDriver().findElement(By.xpath(fieldname)).clear();
					getDriver().findElement(By.xpath(fieldname)).sendKeys(
							fieldvalue);
					flag = 1;

				} else {

					getDriver().findElement(By.id(fieldname)).clear();
					getDriver().findElement(By.id(fieldname)).sendKeys(
							fieldvalue);
					flag = 1;

				}

			} else {
				flag = 0;
			}*/

			return flag;
		} catch (Exception ex) {
			return 0;
		}

	}

	/*
	 * Function for pressing enter in text boxes Created by: -
	 */
	public int pressEnter(String fieldname, String type) {
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (fieldname != null && type != null) {
			element = findElement(fieldname, type);
			element.clear();
			element.sendKeys(fieldname);
			}
			else {
				flag=0;
			}
			
			
			if (fieldname != null && type != null) {
				if (type.equalsIgnoreCase("name")) {
					getDriver().findElement(By.name(fieldname)).sendKeys("\n");
					flag = 1;
				} else if (type.equalsIgnoreCase("xpath")) {
					getDriver().findElement(By.xpath(fieldname)).sendKeys("\n");
					flag = 1;

				}

			} else {
				flag = 0;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return flag;

	}

	// Created By

	public int clearText(String fieldname, String fieldvalue, String type) {
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (fieldname != null && fieldvalue != null) {
				if (type.equalsIgnoreCase("name")) {

					getDriver().findElement(By.name(fieldname)).clear();
					getDriver().findElement(By.name(fieldname)).sendKeys("");
					flag = 1;
				} else if (type.equalsIgnoreCase("xpath")) {

					getDriver().findElement(By.xpath(fieldname)).clear();
					getDriver().findElement(By.xpath(fieldname)).sendKeys("");
					flag = 1;

				}

				else if (type.equalsIgnoreCase("id")) {

					getDriver().findElement(By.id(fieldname)).clear();
					getDriver().findElement(By.id(fieldname)).sendKeys("");
					flag = 1;

				}

			} else {
				flag = 0;
			}

			return flag;
		} catch (Exception ex) {
			return 0;
		}

	}

	/*
	 * Function for verifying text field value Created by: - Date:-
	 */
	public int verifyText(String fieldname, String type, String fieldvalue) {
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (fieldname != null && fieldvalue != null) {
				if (type.equalsIgnoreCase("name")) {
					if (getDriver().findElement(By.name(fieldname))
							.getAttribute("value").equalsIgnoreCase(fieldvalue)) {
						flag = 1;
					} else {
						flag = 0;
					}

				} else {
					if (getDriver().findElement(By.id(fieldname))
							.getAttribute("value").equalsIgnoreCase(fieldvalue)) {
						flag = 1;
					} else {
						flag = 0;
					}
				}

				return flag;
			} else
				flag = 0;
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	// created By

	public int verify(String fieldname, String type) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (fieldname != null && type != null) {
				if (type.equalsIgnoreCase("xpath")) {
					if (getDriver().findElement(By.xpath(fieldname))
							.isDisplayed()) {
						flag = 1;
					}

				} else if (type.equalsIgnoreCase("name")) {
					if (getDriver().findElement(By.name(fieldname))
							.isDisplayed()) {
						flag = 1;
					}

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

	public int verifyLinkText(String LinkText) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (LinkText != null) {

				if (getDriver().findElement(By.linkText(LinkText))
						.isDisplayed()) {
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

	/*
	 * Function for clicking a link, button or radio button Created by: - Date:-
	 */
	public int click(String objectclicked, String type) {
		try {
			if (objectclicked != null) {
			// Thread.sleep(Constants.DeltaConstants.time);
			System.out.println(element);
			element = findElement(objectclicked, type);
			element.click();
			}
			else
			{
				flag = 0;
			}
			/*if (objectclicked != null) {
				if ("css".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.cssSelector(objectclicked))
							.isEnabled()) {
						getDriver().findElement(By.cssSelector(objectclicked))
								.click();
						flag = 1;
					} else {
						flag = 0;
					}

				} else if ("link".equalsIgnoreCase(type)) {
					System.out.println("driver\n" + getDriver());
					if (getDriver().findElement(By.linkText(objectclicked))
							.isEnabled()) {
						getDriver().findElement(By.linkText(objectclicked))
								.click();
						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("type".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.tagName("type"))
							.isDisplayed()) {
						getDriver().findElement(By.linkText(objectclicked))
								.click();
						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("id".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.id(objectclicked))
							.isEnabled()) {
						getDriver().findElement(By.id(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("name".equalsIgnoreCase(type)) {

					if (getDriver().findElement(By.name(objectclicked))
							.isEnabled()) {
						getDriver().findElement(By.name(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;

					}
				}

				else if ("value".equalsIgnoreCase(type)) {

					if (getDriver().findElement(By.name(objectclicked))
							.isEnabled()) {
						getDriver().findElement(By.name(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;

					}
				}

				else if ("label".equalsIgnoreCase(type)) {

					if (getDriver().findElement(By.name(objectclicked))
							.isEnabled()) {
						getDriver().findElement(By.name(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;

					}
				} else {
					// System.out.println("dsssssssss:  :"+driver.findElement(By.name(objectclicked)));
					if (getDriver().findElement(By.xpath(objectclicked))
							.isEnabled()) {
						getDriver().findElement(By.xpath(objectclicked))
								.click();
						flag = 1;
					} else {
						flag = 0;
					}
				}
				return flag;
			} else
				flag = 0;*/
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	// to check that enable functionality---shivi

	public int enable(String objectclicked, String type) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (objectclicked != null) {
				if ("css".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.cssSelector(objectclicked))
							.isEnabled()) {
						// driver.findElement(By.cssSelector(objectclicked)).click();

						System.out.println(" It is enable");
						flag = 1;
					}

					else {
						flag = 0;
					}
				} else if ("link".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.linkText(objectclicked))
							.isEnabled()) {
						// driver.findElement(By.linkText(objectclicked)).click();
						System.out.println(" It is enable");
						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("id".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.id(objectclicked))
							.isEnabled()) {
						// driver.findElement(By.id(objectclicked)).click();
						System.out.println(" It is enable");
						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("name".equalsIgnoreCase(type)) {

					if (getDriver().findElement(By.name(objectclicked))
							.isEnabled()) {
						// driver.findElement(By.name(objectclicked)).click();
						System.out.println(" It is enable");
						flag = 1;
					} else {
						flag = 0;
					}
				} else {
					// System.out.println("dsssssssss:  :"+driver.findElement(By.name(objectclicked)));
					if (getDriver().findElement(By.xpath(objectclicked))
							.isEnabled()) {
						// driver.findElement(By.xpath(objectclicked)).click();
						System.out.println(" It is enable");
						flag = 1;
					} else {
						flag = 0;
					}
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
	 * Function for double clicking a link, button or radio button Created by: -
	 * Date:-
	 */
	public int doubleClick(String objectclicked) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (objectclicked != null) {
				if (getDriver().findElement(By.cssSelector(objectclicked))
						.isEnabled()) {
					Actions action = new Actions(getDriver());
					action.doubleClick(getDriver().findElement(
							By.cssSelector(objectclicked)));
					action.perform();
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
	 * Function for setting state of checkbox Created by: - Date:-
	 */
	public int check(String objectchecked, String type) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (objectchecked != null) {
				if (type.equalsIgnoreCase("id")) {
					if (getDriver().findElement(By.id(objectchecked))
							.isEnabled()) {
						getDriver().findElement(By.id(objectchecked)).click();
						boolean checked = getDriver().findElement(
								By.id(objectchecked)).isSelected();
						if (checked) {
							flag = 1;
						} else {
							flag = 0;
						}
					} else {
						flag = 0;
					}
				} else {
					if (getDriver().findElement(By.name(objectchecked))
							.isEnabled()) {
						getDriver().findElement(By.name(objectchecked)).click();
						boolean checked = getDriver().findElement(
								By.name(objectchecked)).isSelected();
						if (checked) {
							flag = 1;
						} else {
							flag = 0;
						}
					} else {
						flag = 0;
					}

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
	 * Function to verify whether check box/ Radio button is ON
	 */
	public int isChecked(String objectchecked, String type) {
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (objectchecked != null) {

				if (type.equalsIgnoreCase("id")) {
					if (getDriver().findElement(By.id(objectchecked))
							.isEnabled()) {
						boolean checked = getDriver().findElement(
								By.id(objectchecked)).isSelected();
						if (checked) {
							flag = 1;
						} else {
							flag = 0;
						}

					} else
						flag = 0;
				}

				else if (type.equalsIgnoreCase("xpath")) {
					if (getDriver().findElement(By.xpath(objectchecked))
							.isEnabled()) {
						boolean checked = getDriver().findElement(
								By.xpath(objectchecked)).isSelected();
						if (checked) {
							flag = 1;
						} else {
							flag = 0;
						}

					} else
						flag = 0;
				} else {
					if (getDriver().findElement(By.name(objectchecked))
							.isEnabled()) {
						boolean checked = getDriver().findElement(
								By.name(objectchecked)).isSelected();
						if (checked) {
							flag = 1;
						} else {
							flag = 0;
						}

					} else
						flag = 0;
				}

			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * Function to verify whether check box/ Radio button is ON by name
	 */
	public int isCheckedbyname(String objectchecked) {
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (objectchecked != null) {
				if (getDriver().findElement(By.name(objectchecked)).isEnabled()) {
					getDriver().findElement(By.name(objectchecked)).click();
					boolean checked = getDriver().findElement(
							By.name(objectchecked)).isSelected();
					if (checked) {
						flag = 1;
					} else {
						flag = 0;
					}

				} else
					flag = 0;

			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * Function to perform Mouse Over
	 */
	public int mouseOver(String object, String type) {
		try {
			// hread.sleep(Constants.DeltaConstants.maxtime);
			if (object != null) {
				if ("name".equalsIgnoreCase(type)) {
					Actions builder = new Actions(getDriver());
					builder.moveToElement(
							getDriver().findElement(By.name(object))).perform();
					flag = 1;
					return flag;
				} else if ("id".equalsIgnoreCase(type)) {
					Actions builder = new Actions(getDriver());
					builder.moveToElement(
							getDriver().findElement(By.id(object))).perform();
					flag = 1;
					return flag;
				}

				else if ("link".equalsIgnoreCase(type)) {
					Actions builder = new Actions(getDriver());
					builder.moveToElement(
							getDriver().findElement(By.linkText(object)))
							.perform();
					flag = 1;
					return flag;
				}

				else if ("css".equalsIgnoreCase(type)) {
					Actions builder = new Actions(getDriver());
					builder.moveToElement(
							getDriver().findElement(By.cssSelector(object)))
							.perform();
					flag = 1;
					return flag;
				} else {
					Actions builder = new Actions(getDriver());
					builder.moveToElement(
							getDriver().findElement(By.xpath(object)))
							.perform();

					builder.clickAndHold(
							getDriver().findElement(By.xpath(object)))
							.perform();
					builder.moveByOffset(5, 5);
					flag = 1;
					return flag;
				}

			} else
				flag = 0;
			return flag;
		} catch (Exception ex) {
			return 0;
		}
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
	 * Function for waiting for a pop up window Created by: - Date:-
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
	 * Created by: - Date :- 05th September 2011
	 */
	public int verifyValue(String dropDownName, String dropDownValue,
			String type) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (dropDownName != null && dropDownValue != null) {

				if (type.equalsIgnoreCase("name")) {

					if (dropDownValue.indexOf(getDriver().findElement(
							By.name(dropDownName)).getAttribute("value")) != -1) {
						flag = 1;
					} else {
						flag = 0;
					}
				} else if (type.equalsIgnoreCase("id")) {
					if (dropDownValue.indexOf(getDriver().findElement(
							By.id(dropDownName)).getAttribute("value")) != -1) {
						flag = 1;
					} else {
						flag = 0;
					}
				} else {
					if (dropDownValue.indexOf(getDriver().findElement(
							By.xpath(dropDownName)).getAttribute("value")) != -1) {
						flag = 1;
					} else {
						flag = 0;
					}
				}

			} else {
				flag = 0;
			}
			return flag;
		}

		catch (Exception ex) {
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

	/*
	 * Function for verification of text Created by: -
	 */
	public int isTextPresent(String textverify) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (textverify != null) {
				if ((getDriver().getPageSource().indexOf(textverify)) > -1) {
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

	// Added by
	public int compareText(String ActualText, String textverify) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (ActualText != null) {
				if (ActualText.equals(textverify)) {
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

	// Function to get the value of any Web Element.
	public String getText(String objectclicked, String type) throws Exception {
		String ss = null;
		if (objectclicked != null) {
			if ("css".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.cssSelector(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.cssSelector(objectclicked))
							.getText();
				}
			} else if ("link".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.linkText(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.linkText(objectclicked))
							.getText();
				}
			} else if ("name".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.name(objectclicked)).isEnabled()) {
					ss = getDriver().findElement(By.name(objectclicked))
							.getText();
				}
			} else if ("xpath".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.xpath(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.xpath(objectclicked))
							.getText();
				}
			} else if ("id".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.id(objectclicked)).isEnabled()) {
					ss = getDriver().findElement(By.id(objectclicked))
							.getText();
				}
			}
		}
		return ss;
	}

	// Function to get the value of any hidden variable on HTML.
	public String getAttribute(String objectclicked, String type)
			throws Exception {
		String ss = null;
		if (objectclicked != null) {
			if ("css".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.cssSelector(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.cssSelector(objectclicked))
							.getAttribute("value");
				}
			} else if ("link".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.linkText(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.linkText(objectclicked))
							.getAttribute("value");
				}
			} else if ("name".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.name(objectclicked)).isEnabled()) {
					ss = getDriver().findElement(By.name(objectclicked))
							.getAttribute("value");
				}
			} else if ("xpath".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.xpath(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.xpath(objectclicked))
							.getAttribute("value");
				}
			} else if ("id".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.id(objectclicked)).isEnabled()) {
					ss = getDriver().findElement(By.id(objectclicked))
							.getAttribute("value");
				}
			}
		}
		return ss;
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
	 * Function for checking the existence of an object by calling methods
	 * isElementPresent() and isVisible() Created by: -
	 */
	public int s(String elementverify) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (elementverify != null) {
				if (getDriver().findElement(By.name(elementverify)).isEnabled()) {
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
	 * Function for checking the existence of an object refered to as Id by
	 * calling methods isElementPresent() and isVisible()
	 */
	public int isIdElementPresent(String elementverify) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (elementverify != null) {
				if (getDriver().findElement(By.id(elementverify)).isEnabled()) {
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

	public int isnameElementPresent(String elementverify) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (elementverify != null) {
				if (getDriver().findElement(By.name(elementverify)).isEnabled()) {
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

	// :::::::::::::::::::
	public int isxpathElementPresent(String elementverify) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (elementverify != null) {
				if (getDriver().findElement(By.xpath(elementverify))
						.isEnabled()) {
					flag = 1;
				} else {
					flag = 0;
				}
				return flag;
			} else
				flag = 0;

			System.out.println(flag);
			return flag;
		} catch (Exception ex) {
			return 0;
		}

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
	 * Function to retrieve value from table
	 */

	public String getTable(String locator) {

		if (locator != null) {
			return selenium.getTable(locator);
		}

		System.out.println(locator + "not found");
		return null;
	}

	// / Function for getting row count of a html table

	public int tableRows(String table) {

		int rowcount = getDriver().findElements(By.xpath(table)).size();

		System.out.print("Row Count is:" + rowcount);
		return rowcount;

	}

	// Select Rows
	public int selectRows(String select) {

		int rowcount = getDriver().findElements(By.xpath(select)).size();

		System.out.print("Row Count is:" + rowcount);
		return rowcount;

	}

	/**
	 * 
	 * @param tablename
	 * @param row
	 * @param column
	 * @param value
	 * @return
	 */
	private boolean matchValue(String tablename, int row, int column,
			String value) {
		boolean fValueFound = false;
		int rownum = 0, i = 0, j = row;
		String data;

		rownum = tableRows("//tr");
		row = j;
		for (i = 1; i <= rownum - 2; i++) {
			data = getTable(tablename + "." + row + "." + column);
			row = row + 1;
			System.out.println(data);
			if (data.trim().equalsIgnoreCase(value.trim())) {
				return fValueFound = true;
			} else {
				flag = 0;
			}
		}

		return fValueFound = false;
	}

	// Function for getting cell value from table

	public String getValue(String tableid, int row, int column) {
		String data = null;
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			int rownum = 0, i = 0;
			rownum = tableRows("//tr");
			for (i = 1; i <= rownum - 1; i++) {
				data = getTable(tableid + "." + row + "." + column);
			}
		} catch (Exception ex) {

		}

		return data;
	}

	// Function for getting cell value from table by table id

	public String getValuetable(String tableid, int row, int column) {
		String data = null;
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			data = getTable(tableid + "." + row + "." + column);

		} catch (Exception ex) {

		}
		return data;

	}

	// function for getting rownum for a value present in the table

	public int getrownum(String tablename, int column, String value) {
		int rownum = 0, i = 0, flag = 0;
		String data;

		rownum = tableRows("//tr");
		// int row = 1;
		for (i = 1; i <= rownum - 1; i++) {
			data = getTable(tablename + "." + i + "." + column);

			// System.out.println(data);
			if (data.trim().equalsIgnoreCase(value.trim())) {
				return i;
			} else {
				// row = row + 1;
				flag = 0;
			}
		}
		return flag;
	}

	public int getrownumbyid(String tableid, int column, String value) {
		int rownum = 0, i = 0, flag = 0;
		String data;

		rownum = tableRows("//tr");
		// int row = 1;
		for (i = 1; i <= rownum - 1; i++) {
			data = getTable(tableid + "." + i + "." + column);

			// System.out.println(data);
			if (data.trim().equalsIgnoreCase(value.trim())) {
				return i;
			} else {
				// row = row + 1;
				flag = 0;
			}
		}
		return flag;
	}

	@AfterMethod
	public void tearDown() {

		getDriver().quit();
	}

	/*
	 * Function to check whether object is enable or not Created by: - Date:-
	 */
	public int isEnable(String objectclicked, String type) {
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (objectclicked != null) {
				if ("css".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.cssSelector(objectclicked))
							.isEnabled()) {
						// getDriver().findElement(By.cssSelector(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;
					}

				} else if ("link".equalsIgnoreCase(type)) {
					System.out.println("driver\n" + getDriver());
					if (getDriver().findElement(By.linkText(objectclicked))
							.isEnabled()) {
						// getDriver().findElement(By.linkText(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("type".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.tagName("type"))
							.isDisplayed()) {
						// getDriver().findElement(By.linkText(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("id".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.id(objectclicked))
							.isEnabled()) {
						// getDriver().findElement(By.id(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("name".equalsIgnoreCase(type)) {

					if (getDriver().findElement(By.name(objectclicked))
							.isEnabled()) {
						// getDriver().findElement(By.name(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;

					}
				}

				else if ("value".equalsIgnoreCase(type)) {

					if (getDriver().findElement(By.name(objectclicked))
							.isEnabled()) {
						// getDriver().findElement(By.name(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;

					}
				}

				else if ("label".equalsIgnoreCase(type)) {

					if (getDriver().findElement(By.name(objectclicked))
							.isEnabled()) {
						// getDriver().findElement(By.name(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;

					}
				} else {
					// System.out.println("dsssssssss:  :"+driver.findElement(By.name(objectclicked)));
					if (getDriver().findElement(By.xpath(objectclicked))
							.isEnabled()) {
						// getDriver().findElement(By.xpath(objectclicked)).click();
						flag = 1;
					} else {
						flag = 0;
					}
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

	public String getTitle() {
		String title = getDriver().getTitle();
		return title;
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

	// Newly added to validate tax: created by - praachi

	public boolean validateTax(double totalamount, double tax1, double tax2,
			double a, double b) {

		double tax1_calc = (totalamount * tax1) / 100;
		double tax2_calc = (totalamount * tax2) / 100;

		if (!(tax1_calc == a) || !(tax2_calc == b)) {
			return true;
		}
		return false;
	}

	public String readPDFData(String pdfPath) throws IOException {
		String pdfData = null;
		try {
			PdfReader reader = new PdfReader(pdfPath);
			int n = reader.getNumberOfPages();
			System.out.println("No of pages in PDF" + n);
			pdfData = PdfTextExtractor.getTextFromPage(reader, 1); // Extracting
																	// the
																	// content
																	// from a
																	// particular
																	// page.
			System.out.println(pdfData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdfData;

	}

	public String getAttribute_adv(String objectclicked, String type,
			String attrName) throws Exception {
		String ss = null;
		if (objectclicked != null) {
			if ("css".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.cssSelector(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.cssSelector(objectclicked))
							.getAttribute(attrName);
				}
			} else if ("link".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.linkText(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.linkText(objectclicked))
							.getAttribute(attrName);
				}
			} else if ("name".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.name(objectclicked)).isEnabled()) {
					ss = getDriver().findElement(By.name(objectclicked))
							.getAttribute(attrName);
				}
			} else if ("xpath".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.xpath(objectclicked))
						.isEnabled()) {
					ss = getDriver().findElement(By.xpath(objectclicked))
							.getAttribute(attrName);
				}
			} else if ("id".equalsIgnoreCase(type)) {
				if (getDriver().findElement(By.id(objectclicked)).isEnabled()) {
					ss = getDriver().findElement(By.id(objectclicked))
							.getAttribute(attrName);
				}
			}
		}
		return ss;
	}

	/*
	 * Function for getting cell value from table
	 */
	public List getTableValue(String tableid, int column) {
		List cells_text = new ArrayList();
		WebElement baseTable = getDriver().findElement(By.id("dataTable"));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		for (WebElement row : tableRows) {
			// List<WebElement> cells = row.findElements(By.xpath("./*"));
			System.out.println(column);
			// List<WebElement> cells =
			// row.findElements(By.tagName("td["+column+"]"));

			// for (WebElement cell : cells) {
			// And so on
			// System.out.print(cell.getText() +" || ");
			cells_text.add(row.findElement(By.xpath("td[" + column + "]"))
					.getText());
			System.out.println("Cell: " + cells_text);
			// }
			// System.out.println("");
			// System.out.println(tableRows);
			// System.out.println(tableRows.get(index).getText());
		}
		return cells_text;
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

	public String getRequiredCellValueByColumnValue(String tableId,
			String columnValue, int columnIndex, int requiredIndex) {
		String requiredValue = null;
		WebElement baseTable = getDriver().findElement(By.id(tableId));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		System.out.println("size " + tableRows.size());

		for (int i = 1; i < tableRows.size() - 1; i++) {
			WebElement row = tableRows.get(i);
			String colValue = row.findElement(
					By.xpath("td[" + columnIndex + "]")).getText();
			System.out.println(colValue);
			if (colValue.equalsIgnoreCase(columnValue)) {
				requiredValue = row.findElement(
						By.xpath("td[" + requiredIndex + "]")).getText();
				break;

			}
		}

		return requiredValue;
	}

	// getting complete row

	public ArrayList getTableRowValues(String tableid, int row_number) {
		System.out.println(tableid);
		ArrayList cells_text = new ArrayList();
		WebElement baseTable = getDriver().findElement(By.id(tableid));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		System.out.println("size " + tableRows.size());

		WebElement row = tableRows.get(row_number);
		for (int column_value = 1; column_value <= 8; column_value++) {
			cells_text.add(row
					.findElement(By.xpath("td[" + column_value + "]"))
					.getText());
		}
		System.out.println(cells_text);
		return cells_text;
	}

	public ArrayList getTableRowValues2(String tableid, int row_number) {
		System.out.println(tableid);
		ArrayList cells_text = new ArrayList();
		WebElement baseTable = getDriver().findElement(By.id(tableid));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		System.out.println("size " + tableRows.size());

		WebElement row = tableRows.get(row_number);
		for (int column_value = 1; column_value <= 6; column_value++) {
			cells_text.add(row
					.findElement(By.xpath("td[" + column_value + "]"))
					.getText());
		}
		System.out.println(cells_text);
		return cells_text;
	}

	public void openURL() {
		getDriver().get(("https://qa.aynax.com/login.php"));
	}

}
