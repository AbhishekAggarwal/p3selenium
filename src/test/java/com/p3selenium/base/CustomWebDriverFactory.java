package com.p3selenium.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//import com.configuration.com.TestBase;

public class CustomWebDriverFactory extends TestBase {

	private WebElement onElement;
	private int flag = 0; // Indicator of pass/ fail i.e. if flag=1
	private String s_flag = null;

	/*
	 * Function for clicking a link, button or radio button Created by: - Date:-
	 */
	public String findElementOnPage(String objectclicked, String type) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (objectclicked != null) {
				if ("css".equalsIgnoreCase(type)) {
					onElement = getDriver().findElement(
							By.cssSelector(objectclicked));
					return onElement.getAttribute("title");
				} else if ("link".equalsIgnoreCase(type)) {
					onElement = getDriver().findElement(
							By.linkText(objectclicked));
					return onElement.getAttribute("title");
				} else if ("type".equalsIgnoreCase(type)) {
					onElement = getDriver().findElement(
							By.linkText(objectclicked));
					return onElement.getAttribute("title");
				} else if ("id".equalsIgnoreCase(type)) {
					onElement = getDriver().findElement(By.id(objectclicked));
					return onElement.getAttribute("title");
				} else if ("name".equalsIgnoreCase(type)) {
					onElement = getDriver().findElement(By.name(objectclicked));
					return onElement.getAttribute("title");
				} else if ("value".equalsIgnoreCase(type)) {
					onElement = getDriver().findElement(By.name(objectclicked));
					return onElement.getAttribute("title");
				} else if ("label".equalsIgnoreCase(type)) {
					onElement = getDriver().findElement(By.name(objectclicked));
					return onElement.getAttribute("title");
				} else {
					onElement = getDriver()
							.findElement(By.xpath(objectclicked));
					return onElement.getAttribute("title");
				}
			}
			return s_flag;
		} catch (Exception ex) {
			return "false";
		}
	}

	public int isEditable(String objectclicked, String type) {
		try {
			// Thread.sleep(Constants.DeltaConstants.time);
			if (objectclicked != null) {
				if ("css".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.cssSelector(objectclicked))
							.isEnabled()) {

						flag = 1;
					} else {
						flag = 0;
					}

				} else if ("link".equalsIgnoreCase(type)) {
					System.out.println("driver\n" + getDriver());
					if (getDriver().findElement(By.linkText(objectclicked))
							.isEnabled()) {

						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("type".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.tagName("type"))
							.isDisplayed()) {

						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("id".equalsIgnoreCase(type)) {
					if (getDriver().findElement(By.id(objectclicked))
							.isEnabled()) {

						flag = 1;
					} else {
						flag = 0;
					}
				} else if ("name".equalsIgnoreCase(type)) {

					if (getDriver().findElement(By.name(objectclicked))
							.isEnabled()) {

						flag = 1;
					} else {
						flag = 0;

					}
				}

				else if ("value".equalsIgnoreCase(type)) {

					if (getDriver().findElement(By.name(objectclicked))
							.isEnabled()) {

						flag = 1;
					} else {
						flag = 0;

					}
				}

				else if ("label".equalsIgnoreCase(type)) {

					if (getDriver().findElement(By.name(objectclicked))
							.isEnabled()) {

						flag = 1;
					} else {
						flag = 0;

					}
				} else {
					// System.out.println("dsssssssss:  :"+driver.findElement(By.name(objectclicked)));
					if (getDriver().findElement(By.xpath(objectclicked))
							.isEnabled()) {

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
	 * Function to check whether object is editable or not Created by: - Date:-
	 */
	/*
	 * public int isEditable(String fieldname) { try {
	 * //////Thread.sleep(Constants.DeltaConstants.mintime); if (fieldname !=
	 * null) { if (getDriver().findElement(By.id(fieldname)).isEnabled()) { flag
	 * = 1; } else { flag = 0; } return flag; } else flag = 0; return flag; }
	 * catch (Exception ex) { return 0; }
	 * 
	 * 
	 * }
	 */

	public String getLocation(String fieldName) {
		Object url = getDriver().findElement(By.name(fieldName)).getLocation();
		return (String) url;

	}

	/*
	 * Function for setting text in text boxes Created by: - Date:-
	 */
}
