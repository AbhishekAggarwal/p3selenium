package com.p3selenium.assests.pname.product_level.pname.common_lib.functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.p3selenium.assests.pname.product_family_level.common_lib.functions.WebDriverFactory;
import com.p3selenium.assests.pname.product_level.pname.common_lib.data_source.Aynax_Constants;

public class Library {

	private String user;
	private List<String> al1=null;
	public String fetchDataFromCSV() {
		try
		{
		String path = System.getProperty("user.dir");
		path = path + "\\src\\test\\java\\csv\\users.csv";
		System.out.println(path);
		al1 = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(path));
		String userData = br.readLine();
		while (userData != null) {
			String userArray[] = userData.split(",");
			for (String item1 : userArray) {
				al1.add(item1);
			}
			userData = br.readLine();

		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		user=al1.get(0);
		return user;
	}
	
	
	

/*	public void openURL(String url) throws Exception {
		// driver= testbase.setup(url2);
		// System.out.println("SDjgf"+driver);
		getDriver().get(url);

	}*/

	public void login(WebDriverFactory wdFunc, String email, String password)
			throws Exception {

		// Login
		wdFunc.click("Login", "link");
		Thread.sleep(1000);
		wdFunc.typeText(Aynax_Constants.DeltaConstants.u_emailname, email,
				"name");
		wdFunc.typeText(Aynax_Constants.DeltaConstants.u_password, password,
				"name");

		wdFunc.click("submit", "name");

	}
}
