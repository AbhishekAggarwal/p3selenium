/**
 * 
 */
package com.p3selenium.scripts.pname.testcase;

import java.sql.Driver;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.p3selenium.assests.pname.product_family_level.common_lib.data_source.LoadProperty;
import com.p3selenium.assests.pname.product_family_level.common_lib.functions.TestBase;
import com.p3selenium.assests.pname.product_family_level.common_lib.functions.WebDriverFactory;

/**
 * @author Abhishek
 *
 */

//Extend TestBase class so that you can use driver of the browser which invoked in the TestBase class
public class VerifyPageTitle extends TestBase{

	
	
	@Test
	public void runScript()
	{

		// WebDriverFactory modified selenium methods (modified to increase the readability) and some custom methods  
		WebDriverFactory wdf=new WebDriverFactory();
	
		//Fetching the url from the data sheet 
		String url = LoadProperty.getVar("url","data");
		
		//Opening the url
		getDriver().get(url);
		
		//verifying the page title to check if correct page opens or not. If this condition fails then test will terminate as we have used  assertEqual 
		Assert.assertEquals("Lufthansa ® Ukraine - Homepage: Flights worldwide. Discounts. Online Check-in.", driver.getTitle());
		
		
		
	}
	
}


