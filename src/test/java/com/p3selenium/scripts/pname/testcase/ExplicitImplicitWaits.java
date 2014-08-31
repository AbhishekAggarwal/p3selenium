package com.p3selenium.scripts.pname.testcase;

import org.testng.annotations.Test;

import com.p3selenium.assests.pname.product_family_level.common_lib.data_source.LoadProperty;
import com.p3selenium.assests.pname.product_family_level.common_lib.functions.TestBase;
import com.p3selenium.assests.pname.product_family_level.common_lib.functions.WebDriverFactory;

public class ExplicitImplicitWaits extends TestBase {
	@Test
	public void testScript() throws Exception {
		String url = LoadProperty.getVar("url","data");
		System.out.println("Started script 'TestScript'");
		WebDriverFactory wdf = new WebDriverFactory();
		driver.get(url);
		//http://docs.seleniumhq.org/docs/04_webdriver_advanced.jsp
	}
}
