package com.p3selenium.assests.pname.product_family_level.common_lib.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

	private int flag = 0;

	private Long splittedText;
	private String originalWindowHandle;
	private WebDriverBackedSelenium selenium;

	/*
	 * private String element_name; private String element_type;
	 */
	private WebElement element;

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

	/*
	 * Details of methods will go here Methods that we have in WebDriverFatory
	 * are as follows 1. findElement(String element_name, String
	 * element_type):Webelement
	 */

	/*
	 * find the element on page
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
		} catch (Exception e) {
			System.out
					.println("------------------------------------------------------------------");
			System.out
					.println("Error in WebDriverFactory Class while finding the element on page");
			System.out.println("Element name was " + element_name
					+ "which was not found" + "and type was" + element_type);
			System.out.println("Below is the Stack Trace");
			e.printStackTrace();
			System.out
					.println("------------------------------------------------------------------");
			return null;
			/*
			 * @Dev: Abhishek
			 * 
			 * @Status: Development see and update the exception handling
			 * mechanism. use centralized exception handling ref:
			 * http://stackoverflow
			 * .com/questions/6909920/common-centralized-method
			 * -to-handle-multiple-exceptions Also find and implement a graceful
			 * shutting down method of script when ever this error occur
			 */
		}
	}

	/*
	 * Use to clear text of the fields
	 */
	public int clear(String element_name, String fieldvalue, String element_type) {

		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (element_name != null && fieldvalue != null) {
				element = findElement(element_name, element_type);
				element.clear();
				element.sendKeys("");

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
				// Thread.sleep(Constants.DeltaConstants.time);
				System.out.println(element);
				element = findElement(element_name, element_type);
				element.click();
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	public String getAttribute(String element_name, String element_type,
			String attrName) throws Exception {
		String ss = null;
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (element_name != null && attrName != null) {
				element = findElement(element_name, element_type);
				element.getAttribute(attrName);
			} else {
			}
			return ss;
		} catch (Exception ex) {
			return ss;
		}
	}

	public String getCssValue(String element_name, String element_type,
			String attrName) throws Exception {
		String ss = null;
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (element_name != null && attrName != null) {
				element = findElement(element_name, element_type);
				ss = element.getCssValue(attrName);
			} else {
			}
			return ss;
		} catch (Exception ex) {
			return ss;
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

	public Dimension getSize(String element_name, String element_type)
			throws Exception {
		Dimension dimension = null;
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
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

	public String getTagName(String element_name, String element_type)
			throws Exception {
		String tag = null;
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
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
	 * 
	 * Function to check element is displayed or not
	 */
	public int isDisplayed(String element_name, String element_type) {
		try {
			if (element_name != null) {
				// Thread.sleep(Constants.DeltaConstants.time);
				System.out.println(element);
				element = findElement(element_name, element_type);
				element.isDisplayed();
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
	 */

	public int isEnabled(String element_name, String element_type) {
		try {
			if (element_name != null) {
				// Thread.sleep(Constants.DeltaConstants.time);
				System.out.println(element);
				element = findElement(element_name, element_type);
				element.isEnabled();
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	public int isSelected(String element_name, String element_type) {
		try {
			if (element_name != null) {
				// Thread.sleep(Constants.DeltaConstants.time);
				System.out.println(element);
				element = findElement(element_name, element_type);
				element.isSelected();
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*----------------------------------------------------------------------------*/

	/*
	 * public int verifyLinkText(String LinkText) { try { //
	 * Thread.sleep(Constants.DeltaConstants.time); if (LinkText != null) {
	 * 
	 * if (getDriver().findElement(By.linkText(LinkText)) .isDisplayed()) { flag
	 * = 1; } else { flag = 0; }
	 * 
	 * } else { flag = 0; } return flag; } catch (Exception ex) { return 0; } }
	 */

	/*
	 * 
	 * public int doubleClick(String objectclicked) { try { //
	 * Thread.sleep(Constants.DeltaConstants.time); if (objectclicked != null) {
	 * if (getDriver().findElement(By.cssSelector(objectclicked)) .isEnabled())
	 * { Actions action = new Actions(getDriver());
	 * action.doubleClick(getDriver().findElement(
	 * By.cssSelector(objectclicked))); action.perform(); flag = 1; } else {
	 * flag = 0; } return flag; } else flag = 0; return flag; } catch (Exception
	 * ex) { return 0; } }
	 * 
	 * 
	 * 
	 * Function for setting state of checkbox Created by: - Date:-
	 * 
	 * public int check(String objectchecked, String type) { try { //
	 * Thread.sleep(Constants.DeltaConstants.time); if (objectchecked != null) {
	 * if (type.equalsIgnoreCase("id")) { if
	 * (getDriver().findElement(By.id(objectchecked)) .isEnabled()) {
	 * getDriver().findElement(By.id(objectchecked)).click(); boolean checked =
	 * getDriver().findElement( By.id(objectchecked)).isSelected(); if (checked)
	 * { flag = 1; } else { flag = 0; } } else { flag = 0; } } else { if
	 * (getDriver().findElement(By.name(objectchecked)) .isEnabled()) {
	 * getDriver().findElement(By.name(objectchecked)).click(); boolean checked
	 * = getDriver().findElement( By.name(objectchecked)).isSelected(); if
	 * (checked) { flag = 1; } else { flag = 0; } } else { flag = 0; }
	 * 
	 * } return flag; } else flag = 0; return flag; } catch (Exception ex) {
	 * return 0; } }
	 * 
	 * 
	 * Function to verify whether check box/ Radio button is ON
	 * 
	 * public int isChecked(String objectchecked, String type) { try { //
	 * ////Thread.sleep(Constants.DeltaConstants.mintime); if (objectchecked !=
	 * null) {
	 * 
	 * if (type.equalsIgnoreCase("id")) { if
	 * (getDriver().findElement(By.id(objectchecked)) .isEnabled()) { boolean
	 * checked = getDriver().findElement( By.id(objectchecked)).isSelected(); if
	 * (checked) { flag = 1; } else { flag = 0; }
	 * 
	 * } else flag = 0; }
	 * 
	 * else if (type.equalsIgnoreCase("xpath")) { if
	 * (getDriver().findElement(By.xpath(objectchecked)) .isEnabled()) { boolean
	 * checked = getDriver().findElement( By.xpath(objectchecked)).isSelected();
	 * if (checked) { flag = 1; } else { flag = 0; }
	 * 
	 * } else flag = 0; } else { if
	 * (getDriver().findElement(By.name(objectchecked)) .isEnabled()) { boolean
	 * checked = getDriver().findElement( By.name(objectchecked)).isSelected();
	 * if (checked) { flag = 1; } else { flag = 0; }
	 * 
	 * } else flag = 0; }
	 * 
	 * } return flag; } catch (Exception ex) { return 0; } }
	 * 
	 * 
	 * Function to verify whether check box/ Radio button is ON by name
	 * 
	 * public int isCheckedbyname(String objectchecked) { try { //
	 * ////Thread.sleep(Constants.DeltaConstants.mintime); if (objectchecked !=
	 * null) { if (getDriver().findElement(By.name(objectchecked)).isEnabled())
	 * { getDriver().findElement(By.name(objectchecked)).click(); boolean
	 * checked = getDriver().findElement( By.name(objectchecked)).isSelected();
	 * if (checked) { flag = 1; } else { flag = 0; }
	 * 
	 * } else flag = 0;
	 * 
	 * } return flag; } catch (Exception ex) { return 0; } }
	 */
	/*
	 * -----------------------------------Custom
	 * Methods-------------------------
	 */

	/*
	 * Function to perform Mouse Over
	 */
	public int mouseOver(String element_name, String element_type) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (element_name != null) {
				element = findElement(element_name, element_type);
				Actions builder = new Actions(getDriver());
				builder.moveToElement(element).perform();

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
	 * @Type custom
	 * 
	 * Use to type text in the field
	 */
	public int typeText(String fieldname, String fieldvalue, String type) {
		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (fieldname != null && fieldvalue != null) {
				element = findElement(fieldname, type);
				element.clear();
				element.sendKeys(fieldvalue);
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}

	}

	/*
	 * @Type custom
	 * 
	 * Use to pressing enter in text boxes
	 */
	public int pressEnter(String element_name, String element_type) {
		try {
			if (element_name != null) {
				// Thread.sleep(Constants.DeltaConstants.time);
				System.out.println(element);
				element = findElement(element_name, element_type);
				element.sendKeys("\n");
				// getDriver().findElement(By.name(fieldname)).sendKeys("\n");
			} else {
				flag = 0;
			}
			return flag;
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * @Type custom
	 * 
	 * Function for verifying text field value
	 */
	public int verifyValue(String element_name, String element_type,
			String fieldvalue) {

		try {
			// ////Thread.sleep(Constants.DeltaConstants.mintime);
			if (element_name != null && fieldvalue != null) {
				element = findElement(element_name, element_type);
				if (element.getAttribute("value").equalsIgnoreCase(fieldvalue)) {
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

	/* @dev */

	/*
	 * @Type custom
	 * 
	 * Function to verify text is present
	 * 
	 * @edit Need to check if it will fine or not
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

	public boolean isAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	/* Extra */
	/*
	 * public String getRequiredCellValueByColumnValue(String tableId, String
	 * columnValue, int columnIndex, int requiredIndex) { String requiredValue =
	 * null; WebElement baseTable = getDriver().findElement(By.id(tableId));
	 * List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
	 * System.out.println("size " + tableRows.size());
	 * 
	 * for (int i = 1; i < tableRows.size() - 1; i++) { WebElement row =
	 * tableRows.get(i); String colValue = row.findElement( By.xpath("td[" +
	 * columnIndex + "]")).getText(); System.out.println(colValue); if
	 * (colValue.equalsIgnoreCase(columnValue)) { requiredValue =
	 * row.findElement( By.xpath("td[" + requiredIndex + "]")).getText(); break;
	 * 
	 * } }
	 * 
	 * return requiredValue; }
	 * 
	 * 
	 * 
	 * public List getTableValue(String tableid, int column) { List cells_text =
	 * new ArrayList(); WebElement baseTable =
	 * getDriver().findElement(By.id("dataTable")); List<WebElement> tableRows =
	 * baseTable.findElements(By.tagName("tr")); for (WebElement row :
	 * tableRows) { // List<WebElement> cells =
	 * row.findElements(By.xpath("./*")); System.out.println(column); //
	 * List<WebElement> cells = //
	 * row.findElements(By.tagName("td["+column+"]"));
	 * 
	 * // for (WebElement cell : cells) { // And so on //
	 * System.out.print(cell.getText() +" || ");
	 * cells_text.add(row.findElement(By.xpath("td[" + column + "]"))
	 * .getText()); System.out.println("Cell: " + cells_text); // } //
	 * System.out.println(""); // System.out.println(tableRows); //
	 * System.out.println(tableRows.get(index).getText()); } return cells_text;
	 * }
	 * 
	 * 
	 * public boolean validateTax(double totalamount, double tax1, double tax2,
	 * double a, double b) {
	 * 
	 * double tax1_calc = (totalamount * tax1) / 100; double tax2_calc =
	 * (totalamount * tax2) / 100;
	 * 
	 * if (!(tax1_calc == a) || !(tax2_calc == b)) { return true; } return
	 * false; }
	 * 
	 * public String getTitle() { String title = getDriver().getTitle(); return
	 * title; }
	 */

}
