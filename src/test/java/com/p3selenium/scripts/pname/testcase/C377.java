package com.p3selenium.scripts.pname.testcase;

import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.p3selenium.base.TestBase;
import com.p3selenium.base.WebDriverFactory;
import com.p3selenium.data.Aynax_Constants;
import com.p3selenium.scripts.common.Login;
import com.thoughtworks.selenium.SeleneseTestBase;

public class C377 extends TestBase {
	@Test
	public void testLink() throws Exception {
		System.out.println("Starting Script C377");
		WebDriverFactory wdFunc = new WebDriverFactory();
		SeleneseTestBase nb = new SeleneseTestBase();
		wdFunc.setDriver(this.getDriver());
		getDriver().get(Aynax_Constants.DeltaConstants.url);
		Login login = new Login();
		// login.login(wdFunc, Aynax_Constants.DeltaConstants.ab_uname,
		// Aynax_Constants.DeltaConstants.ab_upassword);
		login.login(wdFunc, "testingmember30@gmail.com", "123456");
/*
		getDriver().findElement(By.id("headlineButton")).click();
		// /Thread.sleep(3000);
		if (getDriver().findElement(By.id("from_name")).isDisplayed()) {
			System.out.println("From name field is displayed");
		} else {
			System.out.println("From name field is not displayed");
		}

		if (getDriver().findElement(By.id("from_address")).isDisplayed()) {
			System.out.println("From Address field is displayed");
		} else {
			System.out.println("From Address field is not displayed");
		}
		if (getDriver().findElement(By.id("to_name")).isDisplayed()) {
			System.out.println("To name field is displayed");
		} else {
			System.out.println("To name field is not displayed");
		}

		if (getDriver().findElement(By.id("to_new_customer")).isDisplayed()) {
			System.out.println("Customer name field is displayed");
		} else {
			System.out.println("Customer name field is not displayed");
		}

		if (getDriver().findElement(By.id("to_address")).isDisplayed()) {
			System.out.println("To address field is displayed");
		} else {
			System.out.println("To address field is not displayed");
		}
		if (getDriver().findElement(By.id("invoice_number")).isDisplayed()) {
			System.out.println("Invoice number field is displayed");
		} else {
			System.out.println("Invoice number field is not displayed");
		}

		if (getDriver().findElement(By.id("invoice_date")).isDisplayed()) {
			System.out.println("Invoice Date field is displayed");
		} else {
			System.out.println("Invoice Date field is not displayed");
		}
		if (getDriver().findElement(By.id("payment_due_date")).isDisplayed()) {
			System.out.println("Payment Due Date field is displayed");
		} else {
			System.out.println("Payment Due Date field is not displayed");
		}

		if (getDriver().findElement(By.id("invoice_notes")).isDisplayed()) {
			System.out.println("Invoice_notes field is displayed");
		} else {
			System.out.println("Invoice_notes field is not displayed");
		}
		Thread.sleep(2000);
		try {
			String var2 = getDriver().findElement(
					By.xpath("//tr[@id='topRow']/th[2]")).getAttribute("style");
			System.out.println(var2);
			AssertJUnit
					.assertEquals(
							"height: 30px; color: rgb(255, 255, 253); font-family: arial; font-size: 14px; font-weight: 400; padding-left: 8px; text-align: left;",
							var2);
			System.out.println("Color of the column header matched");
		} catch (Throwable e) {
			System.out.println("Color of the column header not matched");
		}

		if (getDriver().findElement(By.id("invoiceHeadlineNoLogo"))
				.isDisplayed()) {
			System.out.println("Headling logo is displayed");
		} else {
			System.out.println("Headling logo is not displayed");
		}
		try {

			String var4 = getDriver()
					.findElement(
							By.xpath("//*[@id='main_table_wrapper']/tbody/tr/td/table[5]/tbody/tr/td/table/tbody/tr/td[7]/form/input"))
					.getAttribute("value");
			System.out.println(var4);
			AssertJUnit.assertEquals("Save", var4);

			System.out.println("Save button is found");
		} catch (Throwable e) {
			System.out.println("Save button is not found");
		}
*/
	}
}
