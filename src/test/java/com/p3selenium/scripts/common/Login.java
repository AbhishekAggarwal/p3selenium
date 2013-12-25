package com.p3selenium.scripts.common;

/**
 * @author ABHISHEK
 *
 */
import com.p3selenium.base.WebDriverFactory;
import com.p3selenium.data.Aynax_Constants;

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
