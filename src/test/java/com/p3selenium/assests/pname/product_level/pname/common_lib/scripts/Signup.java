//Created by Anurag 18-10-13.......//

package com.p3selenium.assests.pname.product_level.pname.common_lib.scripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Signup {

	String firstUser;

	public String signup() throws Exception {
		try {
			String path = System.getProperty("user.dir");

			path = path + "\\src\\test\\java\\csv\\users.csv";
			System.out.println(path);
			// C:\Selenium Grid\Aynax_Reports\src\test\java\csv

			List<String> al1 = new ArrayList<String>();

			BufferedReader br = new BufferedReader(new FileReader(path));
			String userData = br.readLine();
			while (userData != null) {
				String userArray[] = userData.split(",");
				for (String item1 : userArray) {
					al1.add(item1);
				}
				userData = br.readLine();

			}
			// System.out.println(al1);
			int oldLength = al1.size();
			// System.out.println("new length"+oldLength);

			firstUser = al1.get(0);
			// System.out.println(firstUser);

			/*
			 * Lib lib =new Lib(); firstUser=lib.fetchDataFromCSV();
			 */

			al1.remove(0);

			// System.out.println(al1);
			int newLength = al1.size();
			// System.out.println("new length"+newLength);
			br.close();

			FileWriter writer = new FileWriter(path);

			for (int i = 0; i < newLength; i++) {
				System.out.println(i + "\t" + al1.get(i));
				writer.append("" + al1.get(i));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("Error occured");
			e.printStackTrace();
			e.printStackTrace();
		}
		return firstUser;
	}

}
