package com.p3selenium.scripts.pname.testcase;

import org.testng.annotations.Test;

import com.p3selenium.assests.pname.product_family_level.common_lib.data_source.LoadProperty;
import com.p3selenium.assests.pname.product_family_level.common_lib.functions.Library;
import com.p3selenium.assests.pname.product_family_level.common_lib.functions.TestBase;
import com.p3selenium.assests.pname.product_family_level.common_lib.functions.WebDriverFactory;

public class TestScript extends TestBase {
	@Test
	public void testLink() throws Exception {
		String url = LoadProperty.getVar("data.url");
		System.out.println("Starting Script C377");
		WebDriverFactory wdf = new WebDriverFactory();
		wdf.setDriver(this.getDriver());
		getDriver().get(url);
		Library library= new Library();
		library.login(wdf, "testingmember30@gmail.com", "123456");
	}
}
