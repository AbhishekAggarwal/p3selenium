package com.p3selenium.common;
import java.util.concurrent.TimeUnit;

public class Login   {
	public void login(WebDriverFunctions wdFunc, String email ,String password) throws Exception {

		// Login
		wdFunc.click("Login", "link");
		Thread.sleep(1000);
		wdFunc.typeText(Aynax_Constants.DeltaConstants.u_emailname,
				email, "name");
		wdFunc.typeText(Aynax_Constants.DeltaConstants.u_password,
				password, "name");

		wdFunc.click("submit", "name");

	}

}

