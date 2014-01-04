package com.p3selenium.assests.pname.product_family_level.common_lib.scripts;

/**
 * @author ABHISHEK
 *
 */
import com.p3selenium.assests.pname.product_family_level.common_lib.data_source.Aynax_Constants;
import com.p3selenium.assests.pname.product_family_level.common_lib.functions.WebDriverFactory;

public class Login {
	public void login(WebDriverFactory wdFunc, String email, String password)
			throws Exception {

		Thread.sleep(1000);
		wdFunc.typeText(Aynax_Constants.DeltaConstants.u_emailname, email,
				"name");
		wdFunc.typeText(Aynax_Constants.DeltaConstants.u_password, password,
				"name");

		wdFunc.click("submit", "name");

	}

}
