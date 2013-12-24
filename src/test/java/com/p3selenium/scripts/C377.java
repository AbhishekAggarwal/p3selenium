// created by Shivani Singh
package com.p3selenium.scripts;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


import com.p3selenium.common.Aynax_Constants;
import com.p3selenium.common.Login;
import com.p3selenium.common.TestBase;
import com.p3selenium.common.WebDriverFunctions;
import com.p3selenium.data.LoadProperty;
import com.thoughtworks.selenium.SeleneseTestBase;

public class C377 extends TestBase {
	// @Test
	@Test
	public void testLink() throws Exception {// Login into the application
		WebDriverFunctions wdFunc = new WebDriverFunctions();
		wdFunc.setDriver(this.getDriver());
		getDriver().get(Aynax_Constants.DeltaConstants.url);
		Login login = new Login();
		System.out.println("1");
		LoadProperty property=new LoadProperty();
		
		property.getVar("data.email");
		System.out.println("email");

		login.login(wdFunc, "testingmember30@gmail.com", "123456");

		AssertJUnit.assertEquals("List of Invoices:: Aynax.com", getDriver()
				.getTitle());

		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		/*// moving to new invoice

		getDriver().findElement(By.id("headlineButton")).click();

		// verifying the correct page is opened

		AssertJUnit.assertEquals("New Invoice :: Aynax.com", getDriver()
				.getTitle());
		Reporter.log("User is at new invoice page");

		// verifying the elements in the page

		// form name
		Assert.assertTrue(getDriver().findElement(By.id("from_name"))
				.isDisplayed());

		Reporter.log("From name field is displayed");

		// form address
		Assert.assertTrue(getDriver().findElement(By.id("from_address"))
				.isDisplayed());
		Reporter.log("From Address field is displayed");

		// to name
		Assert.assertTrue(getDriver().findElement(By.id("to_name"))
				.isDisplayed());
		Reporter.log("To name field is displayed");

		// customer name
		Assert.assertTrue(getDriver().findElement(By.id("to_new_customer"))
				.isDisplayed());
		Reporter.log("Customer name field is displayed");

		// to address
		Assert.assertTrue(getDriver().findElement(By.id("to_address"))
				.isDisplayed());
		Reporter.log("To address field is displayed");

		// invoice number
		Assert.assertTrue(getDriver().findElement(By.id("invoice_number"))
				.isDisplayed());

		Reporter.log("Invoice number field is displayed");

		// invoice date
		Assert.assertTrue(getDriver().findElement(By.id("invoice_date"))
				.isDisplayed());
		Reporter.log("Invoice date field is displayed");

		// paymen due date
		Assert.assertTrue(getDriver().findElement(By.id("payment_due_date"))
				.isDisplayed());
		Reporter.log("payment_due_date  field is displayed");

		// invoice notes
		Assert.assertTrue(getDriver().findElement(By.id("invoice_notes"))
				.isDisplayed());

		Reporter.log("Invoice_notes field is displayed");

		// showCustomization

		Assert.assertTrue(getDriver().findElement(By.id("showCustomization"))
				.isDisplayed());

		Reporter.log("show Customization field is displayed");

		Thread.sleep(2000);
		try {
			String var2 = getDriver().findElement(
					By.xpath("//tr[@id='topRow']/th[2]")).getAttribute("style");
			Reporter.log(var2);
			AssertJUnit
					.assertEquals(
							"height: 30px; color: rgb(255, 255, 253); font-family: arial; font-size: 14px; font-weight: 400; padding-left: 8px; text-align: left;",
							var2);
			Reporter.log("Color of the column header matched");
		} catch (Throwable e) {
			Reporter.log("Color of the column header not matched");
		}

		Assert.assertTrue(getDriver()
				.findElement(
						By.xpath("html/body/table[3]/tbody/tr/td/table[6]/tbody/tr/td/table/tbody/tr/td[7]/form/input"))
				.isEnabled());

		Reporter.log("Save button is found");*/

	}
}
