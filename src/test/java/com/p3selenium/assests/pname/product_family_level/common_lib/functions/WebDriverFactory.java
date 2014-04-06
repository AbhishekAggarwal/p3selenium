package com.p3selenium.assests.pname.product_family_level.common_lib.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebDriverFactory extends TestBase {
	private int flag = 0;
	private WebElement element;
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
		} catch (Exception e) {
			System.out
					.println("------------------------------------------------------------------");
			System.out
					.println("Error in WebDriverFactory Class while finding the element on page");
			System.out.println("Element name was '" + element_name
					+ "' which was not found and type was '" + element_type
					+ "'");
			System.out.println("Below is the Stack Trace");
			e.printStackTrace();
			System.out
					.println("------------------------------------------------------------------");
			return null;
			/*
			 * @Dev: Abhishek
			 * 
			 * @Status: see and update the exception handling
			 * mechanism. use centralized exception handling ref:
			 * http://stackoverflow
			 * .com/questions/6909920/common-centralized-method
			 * -to-handle-multiple-exceptions Also find and implement a graceful
			 * shutting down method of script when ever this error occur
			 */
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
	 * @Type custom
	 * 
	 * Use to pressing enter in text boxes
	 */
	public int pressEnter(String element_name, String element_type) {
		try {
			if (element_name != null) {
				// Thread.sleep(Constants.DeltaConstants.time);
				// System.out.println(element);
				element = findElement(element_name, element_type);
				element.sendKeys("\n");
				// getDriver().findElement(By.name(fieldname)).sendKeys("\n");
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

	/*-------------------------------------Custom Methods Ends---------------------------------------*/
}
