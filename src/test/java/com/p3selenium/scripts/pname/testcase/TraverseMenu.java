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
public class TraverseMenu extends TestBase{

	
	
	@Test
	public void runScript()
	{

		// WebDriverFactory modified selenium methods (modified to increase the readability) and some custom methods  
		WebDriverFactory wdf=new WebDriverFactory();
		
		String url = LoadProperty.getVar("url","data");
		System.out.println(url);
		getDriver().get(url);
		Assert.assertEquals("Lufthansa ® Ukraine - Homepage: Flights worldwide. Discounts. Online Check-in.", driver.getTitle());
	}
}
